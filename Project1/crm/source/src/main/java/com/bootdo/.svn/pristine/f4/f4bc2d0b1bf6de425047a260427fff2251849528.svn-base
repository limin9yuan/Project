package com.bootdo.activiti.controller;

import com.bootdo.activiti.service.ActTaskService;
import com.bootdo.activiti.vo.ProcessVO;
import com.bootdo.activiti.vo.TaskVO;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.ShiroUtils;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
    	
    	return new ModelAndView("act/task/formComm");
    }

    @GetMapping("/todo")
    ModelAndView todo(){
        return new ModelAndView("act/task/todoTask");
    }

    @GetMapping("/todoList")
    List<TaskVO> todoList(){
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(ShiroUtils.getUser().getUsername()).list();//admin
        List<TaskVO> taskVOS =  new ArrayList<>();
        for(Task task : tasks){
            TaskVO taskVO = new TaskVO(task);
            taskVOS.add(taskVO);
        }
        return taskVOS;
    }
    /*
    @GetMapping("/finishedList")
    List<TaskVO> finishedList(){
    	HistoricProcessInstanceQuery finishedQuery = historyService.createHistoricProcessInstanceQuery()
                .finished().orderByProcessInstanceEndTime().desc();
    	
        List<TaskVO> taskVOS =  new ArrayList<>();
        for(Task task : finishedQuery){
            TaskVO taskVO = new TaskVO(task);
            taskVOS.add(taskVO);
        }
        return taskVOS;
    }
	*/

    /**
     * 读取带跟踪的图片
     */
    @RequestMapping(value = "/trace/photo/{procDefId}/{execId}")
    public void tracePhoto(@PathVariable("procDefId") String procDefId, @PathVariable("execId") String execId, HttpServletResponse response) throws Exception {
        InputStream imageStream = actTaskService.tracePhoto(procDefId, execId);

        // 输出资源内容到相应对象
        byte[] b = new byte[1024];
        int len;
        while ((len = imageStream.read(b, 0, 1024)) != -1) {
            response.getOutputStream().write(b, 0, len);
        }
    }


}
