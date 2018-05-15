package com.bootdo.activiti.controller;

import com.bootdo.activiti.domain.ActivitiDO;
import com.bootdo.activiti.service.ActTaskService;
import com.bootdo.activiti.vo.ProcessVO;
import com.bootdo.activiti.vo.TaskVO;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.contract.domain.TravelDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.UserService;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**

 */
@RequestMapping("activiti/task")
@RestController
public class TaskController {
    @Autowired
    RepositoryService repositoryService;
    @Autowired
    FormService formService;
    @Autowired
    TaskService taskService;
    @Autowired
    ActTaskService actTaskService;
    @Autowired
    HistoryService historyService;
    @Autowired
    private UserService userService;
    
    @GetMapping("goto")
    public ModelAndView gotoTask(){
        return new ModelAndView("act/task/gotoTask");
    }

    @GetMapping("/gotoList")
    PageUtils list(int offset, int limit) {
        List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery()
                .listPage(offset, limit);
        int count = (int) repositoryService.createProcessDefinitionQuery().count();
        List<Object> list = new ArrayList<>();
        for(ProcessDefinition processDefinition: processDefinitions){
            list.add(new ProcessVO(processDefinition));
        }

        PageUtils pageUtils = new PageUtils(list, count);
        return pageUtils;
    }

    @GetMapping("/form/{procDefId}")
    public void startForm(@PathVariable("procDefId") String procDefId  ,HttpServletResponse response) throws IOException {
        String formKey = actTaskService.getFormKey(procDefId, null);
        response.sendRedirect(formKey);
    }

    @GetMapping("/form/{procDefId}/{taskId}")
    public void form(@PathVariable("procDefId") String procDefId,@PathVariable("taskId") String taskId ,HttpServletResponse response) throws IOException {
        // 获取流程XML上的表单KEY

        String formKey = actTaskService.getFormKey(procDefId, taskId);


        //response.sendRedirect(formKey+"/"+taskId);
        response.sendRedirect("/activiti/task/formComm/"+procDefId+"/"+taskId);
    }
    
    @GetMapping("/formComm/{procDefId}/{taskId}")
    public ModelAndView formComm(@PathVariable("procDefId") String procDefId,@PathVariable("taskId") String taskId,Model model) throws IOException {
    	
    	String formKey = actTaskService.getFormKey(procDefId, taskId);
    	model.addAttribute("taskId", taskId);
    	model.addAttribute("formSrc", formKey+"/"+taskId);
    	model.addAttribute("formSubmit", formKey+"/update");
    	
    	Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
    	model.addAttribute("taskName", task.getName());
    	model.addAttribute("processDefinitionId", task.getProcessDefinitionId());
    	model.addAttribute("executionId", task.getExecutionId());
    	model.addAttribute("processInstanceId", task.getProcessInstanceId());
    	
    	return new ModelAndView("act/task/formComm");
    }
    
    @ResponseBody
    @PostMapping("/claim/{taskId}")
    public R claim(@PathVariable("taskId") String taskId,Model model) throws IOException {
    	try{
	    	taskService.claim(taskId, ShiroUtils.getUser().getUsername());
	    	return R.ok();
    	}catch(Exception e){
    		return R.error();
    	}
    }

    @GetMapping("/todo")
    ModelAndView todo(){
        return new ModelAndView("act/task/todoTask");
    }
    //待办列表
    @GetMapping("/todoList")
    List<TaskVO> todoList(){
        //List<Task> tasks = taskService.createTaskQuery().taskAssignee(ShiroUtils.getUser().getUsername()).includeProcessVariables().list();//admin
    	List<Task> tasks = taskService.createTaskQuery().taskCandidateOrAssigned(ShiroUtils.getUser().getUsername()).includeProcessVariables().list();//admin
        List<TaskVO> taskVOS =  new ArrayList<>();
        
        for(Task task : tasks){
            TaskVO taskVO = new TaskVO(task);
         // 获取流程定义
            ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
                    .getDeployedProcessDefinition(task.getProcessDefinitionId());
            
            taskVO.setProcessName(processDefinition.getName());
            
        //  获取历史流程实例
            HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                    .processInstanceId(task.getProcessInstanceId()).singleResult();
            if(historicProcessInstance!=null){
            	UserDO user=userService.getByUsername(historicProcessInstance.getStartUserId());
            	taskVO.setStartUserName(user.getName());
            	taskVO.setStartDate(historicProcessInstance.getStartTime());
            }
            /* 
            //另一种获取方式
            String title = (String) taskService.getVariable(task.getId(), "title");  
            taskVO.setTitle(title);
            */
          //查询标题
            Map<String,Object> varIns=task.getProcessVariables();
        	if(varIns!=null&&varIns.get("title")!=null){
        		taskVO.setTitle(varIns.get("title").toString());
        	}
           
            taskVOS.add(taskVO);
        }
        return taskVOS;
    }
    //已完列表
    @GetMapping("/finishedList/{flag}")
    PageUtils finishedList(int offset, int limit,@PathVariable("flag") String flag){
    	HistoricProcessInstanceQuery finishedQuery;
    	if(flag!=null&&flag.equals("1")){ //已完流程
    		finishedQuery = historyService.createHistoricProcessInstanceQuery()
    			.finished().includeProcessVariables().involvedUser(ShiroUtils.getUser().getUsername()).orderByProcessInstanceEndTime().desc();
    	}else{//未完流程
    		finishedQuery = historyService.createHistoricProcessInstanceQuery()
        			.unfinished().includeProcessVariables().involvedUser(ShiroUtils.getUser().getUsername()).orderByProcessInstanceEndTime().desc();
    	}

        List<TaskVO> taskVOS =  new ArrayList<>();
        int count = (int) finishedQuery.count();
        List<HistoricProcessInstance> finishedInstaceList=  finishedQuery.listPage(offset, limit);
        
        for(HistoricProcessInstance instance : finishedInstaceList){
            TaskVO taskVO = new TaskVO(null);
         // 获取流程定义
            ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
                    .getDeployedProcessDefinition(instance.getProcessDefinitionId());
            taskVO.setProcessName(processDefinition.getName());
            
            UserDO user=userService.getByUsername(instance.getStartUserId());
        	taskVO.setStartUserName(user.getName());
        	
        	taskVO.setStartDate(instance.getStartTime());
        	taskVO.setEndDate(instance.getEndTime());
        	
        	taskVO.setProcessDefinitionId(instance.getProcessDefinitionId()); 
        	taskVO.setProcessId(instance.getId());
        	
        	if(instance.getEndTime()!=null&&!instance.getEndTime().equals("")){
        		taskVO.setProcessStatus("已结束");
        	}else{
        		taskVO.setProcessStatus("运行中");
        	}
        	
        	//查询标题
        	/*
        	//另一种获取方式
        	HistoricVariableInstance varIns = historyService  
                    .createHistoricVariableInstanceQuery().processInstanceId(instance.getId())  
                    .variableName("title").singleResult();  
        	
            if(varIns!=null&&varIns.getValue()!=null){
            	taskVO.setTitle(varIns.getValue().toString());
            }
            */
        	Map<String,Object> varIns=instance.getProcessVariables();
        	if(varIns!=null&&varIns.get("title")!=null){
        		taskVO.setTitle(varIns.get("title").toString());
        	}
            taskVOS.add(taskVO);
        }
        
        PageUtils pageUtils = new PageUtils(taskVOS, count);
        return pageUtils;
    }
	

    /**
     * 读取带跟踪的图片
     */
    @RequestMapping(value = "/trace/photo/{processInstanceId}")
    public void tracePhoto(@PathVariable("processInstanceId") String processInstanceId, HttpServletResponse response) throws Exception {
        InputStream imageStream = actTaskService.tracePhoto(processInstanceId);

        // 输出资源内容到相应对象
        byte[] b = new byte[1024];
        int len;
        while ((len = imageStream.read(b, 0, 1024)) != -1) {
            response.getOutputStream().write(b, 0, len);
        }
    }
    
    

    @GetMapping("/taskTraceList/{pProcessInstanceId}")
    List<ActivitiDO> taskTraceList(@PathVariable("pProcessInstanceId") String pProcessInstanceId){
    	return actTaskService.traceTaskData(pProcessInstanceId);
    }
    
    @GetMapping("/taskTrace/{pProcessInstanceId}")
    ModelAndView taskTrace(@PathVariable("pProcessInstanceId") String pProcessInstanceId,Model model){
    	model.addAttribute("processInstanceId", pProcessInstanceId);
        return new ModelAndView("act/task/taskTrace");
    }
    //变更处理人
    @GetMapping("/changeAssigned")
    ModelAndView changeAssigned(){
        return new ModelAndView("act/task/changeAssigned");
    }
    
   //变更处理人
    @GetMapping("/changeAssignedList")
    PageUtils changeAssignedList(int offset, int limit){
    	HistoricProcessInstanceQuery finishedQuery;
    	//未完流程
    	/*
    	finishedQuery = historyService.createHistoricProcessInstanceQuery()
        			.unfinished().includeProcessVariables().orderByProcessInstanceEndTime().desc();
    	*/
    	// 
    	TaskQuery taskQuery= taskService.createTaskQuery();//.includeProcessVariables().orderByTaskCreateTime().desc().listPage(offset, limit);
   
        List<TaskVO> taskVOS =  new ArrayList<>();
        int count = (int) taskQuery.count();
        List<Task> tasks=  taskQuery.includeProcessVariables().orderByTaskCreateTime().desc().listPage(offset, limit);
        
        for(Task instance : tasks){
            TaskVO taskVO = new TaskVO(instance);
         // 获取流程定义
            ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
                    .getDeployedProcessDefinition(instance.getProcessDefinitionId());
            taskVO.setProcessName(processDefinition.getName());
            
        	
        	HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                    .processInstanceId(instance.getProcessInstanceId()).singleResult();
            if(historicProcessInstance!=null){
            	UserDO user=userService.getByUsername(historicProcessInstance.getStartUserId());
            	taskVO.setStartUserName(user.getName());
            	taskVO.setStartDate(historicProcessInstance.getStartTime());
            }
            if(taskVO.getAssignee()!=null&&!taskVO.getAssignee().equals("")){
            	UserDO user=userService.getByUsername(taskVO.getAssignee());
            	taskVO.setAssigneeName(user.getName());
            }else{
            	taskVO.setAssigneeName("未签收");
            }
        	
        	Map<String,Object> varIns=instance.getProcessVariables();
        	if(varIns!=null&&varIns.get("title")!=null){
        		taskVO.setTitle(varIns.get("title").toString());
        	}
            taskVOS.add(taskVO);
        }
        
        PageUtils pageUtils = new PageUtils(taskVOS, count);
        return pageUtils;
    }
    //变更处理人目录树选择
    @GetMapping("/userTree/{taskId}")
    ModelAndView userTree(@PathVariable("taskId") String taskId,Model model){
    	model.addAttribute("taskId", taskId);
        return new ModelAndView("act/task/userTree");
    }
    
    //变更处理人提交
   	@ResponseBody
   	@RequestMapping("/changeAssignedUpdate/{taskId}/{userId}")
   	public R changeAssignedUpdate(@PathVariable("taskId") String taskId,@PathVariable("userId") String userId){
   		try{
	   		actTaskService.changeAssigned(taskId, userId);;
	   		return R.ok();
   		}catch(Exception e){
    		return R.error();
    	}
   	}

}
