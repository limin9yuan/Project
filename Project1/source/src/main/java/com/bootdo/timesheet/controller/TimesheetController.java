package com.bootdo.timesheet.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.bootdo.activiti.service.ActTaskService;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.activiti.utils.ActivitiUtils;
import com.bootdo.approval.domain.AssignmentDO;
import com.bootdo.common.domain.DictDO;
import com.bootdo.project.domain.ProjectDO;
import com.bootdo.timesheet.domain.TimesheetDO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bootdo.project.service.ProjectService;
import com.bootdo.timesheet.service.TimesheetService;
import com.bootdo.approval.service.AssignmentService;
import com.bootdo.common.controller.BaseController;
import com.bootdo.activiti.service.impl.ActTaskServiceImpl;

import java.util.Calendar;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import org.activiti.engine.task.Task;
import org.activiti.engine.TaskService;
import org.springframework.web.servlet.ModelAndView;

/**
 * 工时信息表
 * 
 * @author chgleepublic class TimesheetController {

 * @email 1992lcg@163.com
 * @date 2018-03-14 17:52:48
 */
 
@Controller
@RequestMapping("/timesheet/timesheet")
public class TimesheetController extends BaseController {
	@Autowired
	private TimesheetService timesheetService;
	@Autowired
	private AssignmentService assignmentService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	TaskService taskService;

	@Autowired
	ActTaskService actTaskService;
	@Autowired
	ActivitiUtils activitiUtils;


	/**
	 * ********************** 审批流程相关  *********************************
	 */
	//申请页面
	@GetMapping("/form")
	@RequiresPermissions("timesheet:timesheet:add")
	String form(){
		return "timesheet/timesheet/add";
	}
	//审批处理页面
	@GetMapping("/form/{taskId}")
	@RequiresPermissions("timesheet:timesheet:add")
	String formTask(@PathVariable("taskId") String taskId,Model model){
		//取得流程表单数据
		TimesheetDO timesheet = timesheetService.view(activitiUtils.getBusinessKeyByTaskId(taskId));
		if(timesheet!=null){
			model.addAttribute("timesheet", timesheet);

		}
		return "timesheet/timesheet/viewTimeSheet";
	}


	//审批处理保存
	@ResponseBody
	@RequestMapping("/form/update")
	@RequiresPermissions("timesheet:timesheet:edit")
	public R formUpdate( TimesheetDO timesheet){

		timesheetService.formUpdate(timesheet);//审批流程保存
		return R.ok();
	}
	//审批处理保存
	@ResponseBody
	@RequestMapping("/form/updateAll")
	@RequiresPermissions("timesheet:timesheet:edit")
	public R formUpdateall( TimesheetDO timesheet){

		timesheetService.formUpdate(timesheet);
		return R.ok();
	}






	@GetMapping()
	@RequiresPermissions("timesheet:timesheet:timesheet")
	String Timesheet(Model model){
		SimpleDateFormat simdf = new SimpleDateFormat("yyyy-MM-dd");//日期方法

		Calendar cal = Calendar.getInstance();
		String date8=simdf.format(cal.getTime());


		cal.set(cal.DAY_OF_WEEK, cal.MONDAY);
		String date11 = simdf.format(cal.getTime());
		java.sql.Date date1=java.sql.Date.valueOf(date11);


		Date date=new Date();
		cal.add(cal.DATE,+1);
		date=cal.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String date2 = formatter.format(date);


		cal.add(cal.DATE,+1);
		date=cal.getTime();
		String date3 = formatter.format(date);


		cal.add(cal.DATE,+1);
		date=cal.getTime();
		String date4 = formatter.format(date);


		cal.add(cal.DATE,+1);
		date=cal.getTime();
		String date5 = formatter.format(date);


		cal.add(cal.DATE,+1);
		date=cal.getTime();
		String date6 = formatter.format(date);

		cal.add(cal.DATE,+1);
		date=cal.getTime();
		String date7 = formatter.format(date);



		model.addAttribute("Date1", date1);
		model.addAttribute("Date2", date2);
		model.addAttribute("Date3", date3);
		model.addAttribute("Date4", date4);
		model.addAttribute("Date5", date5);
		model.addAttribute("Date6", date6);
		model.addAttribute("Date7", date7);
		model.addAttribute("Date8", date8);






		return "timesheet/timesheet/timesheet";
	}


	@GetMapping("/approvetimesheet")
	@RequiresPermissions("timesheet:timesheet:approvetimesheet")
	String approvetimesheet(Model model){
		SimpleDateFormat simdf = new SimpleDateFormat("yyyy-MM-dd");//日期方法

		Calendar cal = Calendar.getInstance();
		String date8=simdf.format(cal.getTime());


		cal.set(cal.DAY_OF_WEEK, cal.MONDAY);
		String date11 = simdf.format(cal.getTime());
		java.sql.Date date1=java.sql.Date.valueOf(date11);


		Date date=new Date();
		cal.add(cal.DATE,+1);
		date=cal.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String date2 = formatter.format(date);


		cal.add(cal.DATE,+1);
		date=cal.getTime();
		String date3 = formatter.format(date);


		cal.add(cal.DATE,+1);
		date=cal.getTime();
		String date4 = formatter.format(date);


		cal.add(cal.DATE,+1);
		date=cal.getTime();
		String date5 = formatter.format(date);


		cal.add(cal.DATE,+1);
		date=cal.getTime();
		String date6 = formatter.format(date);

		cal.add(cal.DATE,+1);
		date=cal.getTime();
		String date7 = formatter.format(date);



		model.addAttribute("Date1", date1);
		model.addAttribute("Date2", date2);
		model.addAttribute("Date3", date3);
		model.addAttribute("Date4", date4);
		model.addAttribute("Date5", date5);
		model.addAttribute("Date6", date6);
		model.addAttribute("Date7", date7);
		model.addAttribute("Date8", date8);






		return "timesheet/timesheet/approvetimesheet";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("timesheet:timesheet:timesheet")
	public PageUtils list(@RequestParam Map<String, Object> params){

		if (params.get("timeMin") != null && !"".equals(params.get("timeMin"))) {
           Date da1=new Date();

			String d1=(String)(params.get("timeMin"));

			da1 = java.sql.Date.valueOf(d1);

			SimpleDateFormat simdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(da1);




			cal.add(cal.DATE, +1);
			Date date = new Date();
			date = cal.getTime();
			String date2 = simdf.format(date);
			java.sql.Date dat2 = java.sql.Date.valueOf(date2);



			cal.add(cal.DATE, +1);
			date = cal.getTime();
			String date3 = simdf.format(date);
			java.sql.Date dat3 = java.sql.Date.valueOf(date3);

			cal.add(cal.DATE, +1);
			date = cal.getTime();
			String date4 = simdf.format(date);
			java.sql.Date dat4 = java.sql.Date.valueOf(date4);

			cal.add(cal.DATE, +1);
			date = cal.getTime();
			String date5 = simdf.format(date);
			java.sql.Date dat5 = java.sql.Date.valueOf(date5);

			cal.add(cal.DATE, +1);
			date = cal.getTime();
			String date6 = simdf.format(date);
			java.sql.Date dat6 = java.sql.Date.valueOf(date6);

			cal.add(cal.DATE, +1);
			date = cal.getTime();
			String date7 = simdf.format(date);
			java.sql.Date dat7 = java.sql.Date.valueOf(date7);


            params.put("istask",params.get("istask"));
			params.put("date1", da1);
			params.put("date2", dat2);
			params.put("date3", dat3);
			params.put("date4", dat4);
			params.put("date5", dat5);
			params.put("date6", dat6);
			params.put("date7", dat7);




		}
        else {

			SimpleDateFormat simdf = new SimpleDateFormat("yyyy-MM-dd");

			Calendar cal = Calendar.getInstance();

			String date8 = simdf.format(cal.getTime());
			java.sql.Date dat8 = java.sql.Date.valueOf(date8);
			cal.set(cal.DAY_OF_WEEK, cal.MONDAY);
			String date11 = simdf.format(cal.getTime());
			java.sql.Date date1 = java.sql.Date.valueOf(date11);


			Date date = new Date();
			cal.add(cal.DATE, +1);
			date = cal.getTime();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String date2 = formatter.format(date);
			java.sql.Date dat2 = java.sql.Date.valueOf(date2);

			cal.add(cal.DATE, +1);
			date = cal.getTime();
			String date3 = formatter.format(date);
			java.sql.Date dat3 = java.sql.Date.valueOf(date3);

			cal.add(cal.DATE, +1);
			date = cal.getTime();
			String date4 = formatter.format(date);
			java.sql.Date dat4 = java.sql.Date.valueOf(date4);

			cal.add(cal.DATE, +1);
			date = cal.getTime();
			String date5 = formatter.format(date);
			java.sql.Date dat5 = java.sql.Date.valueOf(date5);

			cal.add(cal.DATE, +1);
			date = cal.getTime();
			String date6 = formatter.format(date);
			java.sql.Date dat6 = java.sql.Date.valueOf(date6);

			Date date13 = new Date();
			cal.add(cal.DATE, +1);
			date13 = cal.getTime();


			params.put("date1", date1);
			params.put("date2", dat2);
			params.put("date3", dat3);
			params.put("date4", dat4);
			params.put("date5", dat5);
			params.put("date6", dat6);

			params.put("date7", date13);
			params.put("date8", dat8);
		}





//
//		查询列表数据
		params.put("uerId",getUserId());
        Query query = new Query(params);
		List<TimesheetDO> timesheetList = timesheetService.list(query);
		int total = timesheetService.count(query);
		PageUtils pageUtils = new PageUtils(timesheetList, total);
		return pageUtils;
	}

	@ResponseBody
	@GetMapping("/approvelist")
	@RequiresPermissions("timesheet:timesheet:approvetimesheet")
	public PageUtils approvelist(@RequestParam Map<String, Object> params){
  //时间查询
		if (params.get("timeMin") != null && !"".equals(params.get("timeMin"))) {
			Date da1=new Date();

			String d1=(String)(params.get("timeMin"));

			da1 = java.sql.Date.valueOf(d1);

			SimpleDateFormat simdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(da1);




			cal.add(cal.DATE, +1);
			Date date = new Date();
			date = cal.getTime();
			String date2 = simdf.format(date);
			java.sql.Date dat2 = java.sql.Date.valueOf(date2);



			cal.add(cal.DATE, +1);
			date = cal.getTime();
			String date3 = simdf.format(date);
			java.sql.Date dat3 = java.sql.Date.valueOf(date3);

			cal.add(cal.DATE, +1);
			date = cal.getTime();
			String date4 = simdf.format(date);
			java.sql.Date dat4 = java.sql.Date.valueOf(date4);

			cal.add(cal.DATE, +1);
			date = cal.getTime();
			String date5 = simdf.format(date);
			java.sql.Date dat5 = java.sql.Date.valueOf(date5);

			cal.add(cal.DATE, +1);
			date = cal.getTime();
			String date6 = simdf.format(date);
			java.sql.Date dat6 = java.sql.Date.valueOf(date6);

			cal.add(cal.DATE, +1);
			date = cal.getTime();
			String date7 = simdf.format(date);
			java.sql.Date dat7 = java.sql.Date.valueOf(date7);


			params.put("istask",params.get("istask"));
			params.put("date1", da1);
			params.put("date2", dat2);
			params.put("date3", dat3);
			params.put("date4", dat4);
			params.put("date5", dat5);
			params.put("date6", dat6);
			params.put("date7", dat7);




		}
		else {

			SimpleDateFormat simdf = new SimpleDateFormat("yyyy-MM-dd");

			Calendar cal = Calendar.getInstance();

			String date8 = simdf.format(cal.getTime());
			java.sql.Date dat8 = java.sql.Date.valueOf(date8);
			cal.set(cal.DAY_OF_WEEK, cal.MONDAY);
			String date11 = simdf.format(cal.getTime());
			java.sql.Date date1 = java.sql.Date.valueOf(date11);


			Date date = new Date();
			cal.add(cal.DATE, +1);
			date = cal.getTime();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String date2 = formatter.format(date);
			java.sql.Date dat2 = java.sql.Date.valueOf(date2);

			cal.add(cal.DATE, +1);
			date = cal.getTime();
			String date3 = formatter.format(date);
			java.sql.Date dat3 = java.sql.Date.valueOf(date3);

			cal.add(cal.DATE, +1);
			date = cal.getTime();
			String date4 = formatter.format(date);
			java.sql.Date dat4 = java.sql.Date.valueOf(date4);

			cal.add(cal.DATE, +1);
			date = cal.getTime();
			String date5 = formatter.format(date);
			java.sql.Date dat5 = java.sql.Date.valueOf(date5);

			cal.add(cal.DATE, +1);
			date = cal.getTime();
			String date6 = formatter.format(date);
			java.sql.Date dat6 = java.sql.Date.valueOf(date6);

			Date date13 = new Date();
			cal.add(cal.DATE, +1);
			date13 = cal.getTime();


			params.put("date1", date1);
			params.put("date2", dat2);
			params.put("date3", dat3);
			params.put("date4", dat4);
			params.put("date5", dat5);
			params.put("date6", dat6);

			params.put("date7", date13);
			params.put("date8", dat8);
		}





//
//		查询列表数据
		params.put("uerId",getUserId());
		Query query = new Query(params);
		List<TimesheetDO> timesheetList = timesheetService.approvelist(query);

		Task task =null;
		for(TimesheetDO timesheet : timesheetList){

			if(timesheet.getTimeSheetApprovalStatusDate1()==0){
				task = taskService.createTaskQuery().processDefinitionKey("timeSheet").taskAssignee(ShiroUtils.getUser().getUsername()).processInstanceBusinessKey(timesheet.getIdDate1()).singleResult();//admin
				if(task!=null){
					timesheet.setTaskIdDate1(task.getId());
					timesheet.setPdIdDate1(task.getProcessDefinitionId());
				}
			}
			if(timesheet.getTimeSheetApprovalStatusDate2()==0){
				task = taskService.createTaskQuery().processDefinitionKey("timeSheet").taskAssignee(ShiroUtils.getUser().getUsername()).processInstanceBusinessKey(timesheet.getIdDate2()).singleResult();//admin
				if(task!=null){
					timesheet.setTaskIdDate2(task.getId());
					timesheet.setPdIdDate2(task.getProcessDefinitionId());
				}
			}

			if(timesheet.getTimeSheetApprovalStatusDate3()==0){
				task = taskService.createTaskQuery().processDefinitionKey("timeSheet").taskAssignee(ShiroUtils.getUser().getUsername()).processInstanceBusinessKey(timesheet.getIdDate3()).singleResult();//admin
				if(task!=null){
					timesheet.setTaskIdDate3(task.getId());
					timesheet.setPdIdDate3(task.getProcessDefinitionId());
				}
			}

			if(timesheet.getTimeSheetApprovalStatusDate4()==0){
				task = taskService.createTaskQuery().processDefinitionKey("timeSheet").taskAssignee(ShiroUtils.getUser().getUsername()).processInstanceBusinessKey(timesheet.getIdDate4()).singleResult();//admin
				if(task!=null){
					timesheet.setTaskIdDate4(task.getId());
					timesheet.setPdIdDate4(task.getProcessDefinitionId());
				}
			}

			if(timesheet.getTimeSheetApprovalStatusDate5()==0){
				task = taskService.createTaskQuery().processDefinitionKey("timeSheet").taskAssignee(ShiroUtils.getUser().getUsername()).processInstanceBusinessKey(timesheet.getIdDate5()).singleResult();//admin
				if(task!=null){
					timesheet.setTaskIdDate5(task.getId());
					timesheet.setPdIdDate5(task.getProcessDefinitionId());
				}
			}

			if(timesheet.getTimeSheetApprovalStatusDate6()==0){
				task = taskService.createTaskQuery().processDefinitionKey("timeSheet").taskAssignee(ShiroUtils.getUser().getUsername()).processInstanceBusinessKey(timesheet.getIdDate6()).singleResult();//admin
				if(task!=null){
					timesheet.setTaskIdDate6(task.getId());
					timesheet.setPdIdDate6(task.getProcessDefinitionId());
				}
			}

			if(timesheet.getTimeSheetApprovalStatusDate7()==0){
				task = taskService.createTaskQuery().processDefinitionKey("timeSheet").taskAssignee(ShiroUtils.getUser().getUsername()).processInstanceBusinessKey(timesheet.getIdDate7()).singleResult();//admin
				if(task!=null){
					timesheet.setTaskIdDate7(task.getId());
					timesheet.setPdIdDate7(task.getProcessDefinitionId());
				}
			}





		}


		int total = timesheetService.count(query);
		PageUtils pageUtils = new PageUtils(timesheetList, total);
		return pageUtils;
	}


	@GetMapping("/getlist/{timeMin}")
	@ResponseBody
	Map<String, Object> getlist(@PathVariable("timeMin") String timeMin) {






			Date da1 = new Date();
			da1 = java.sql.Date.valueOf(timeMin);
		SimpleDateFormat simdf = new SimpleDateFormat("yyyy-MM-dd");
		String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
		Calendar cal = Calendar.getInstance();
		cal.setTime(da1);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		String week1=weekDays[w];








			cal.add(cal.DATE, +1);
			Date date = new Date();
			date = cal.getTime();
			String date2 = simdf.format(date);
			java.sql.Date dat2 = java.sql.Date.valueOf(date2);


		cal.setTime(dat2);
		 w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		String week2=weekDays[w];


			cal.add(cal.DATE, +1);
			date = cal.getTime();
			String date3 = simdf.format(date);
			java.sql.Date dat3 = java.sql.Date.valueOf(date3);

		cal.setTime(dat3);
		w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		String week3=weekDays[w];

			cal.add(cal.DATE, +1);
			date = cal.getTime();
			String date4 = simdf.format(date);
			java.sql.Date dat4 = java.sql.Date.valueOf(date4);

		cal.setTime(dat4);
		w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		String week4=weekDays[w];

			cal.add(cal.DATE, +1);
			date = cal.getTime();
			String date5 = simdf.format(date);
			java.sql.Date dat5 = java.sql.Date.valueOf(date5);

		cal.setTime(dat5);
		w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		String week5=weekDays[w];

			cal.add(cal.DATE, +1);
			date = cal.getTime();
			String date6 = simdf.format(date);
			java.sql.Date dat6 = java.sql.Date.valueOf(date6);

		cal.setTime(dat6);
		w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		String week6=weekDays[w];

			cal.add(cal.DATE, +1);
			date = cal.getTime();
			String date7 = simdf.format(date);
			java.sql.Date dat7 = java.sql.Date.valueOf(date7);

		cal.setTime(dat7);
		w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		String week7=weekDays[w];


			Map<String, Object> returnData = new HashMap<String, Object>();

			returnData.put("date11", da1+week1);
			returnData.put("dat2", dat2+week2);
			returnData.put("dat3", dat3+week3);
			returnData.put("dat4", dat4+week4);
			returnData.put("dat5", dat5+week5);
			returnData.put("dat6", dat6+week6);
			returnData.put("dat7", dat7+week7);

			returnData.put("pd1",da1);
		 	returnData.put("pd2",dat2);
			returnData.put("pd3",dat3);
			returnData.put("pd4",dat4);
			returnData.put("pd5",dat5);
			returnData.put("pd6",dat6);
			returnData.put("pd7",dat7);


			return returnData;
		}

//	//ajax修改绑定数据
//	@RequestMapping("/edit_ajax/{timesheetId}")
//	@ResponseBody
//	Map<String, Object> edit_ajax(@PathVariable("timesheetId") String timesheetId) {
//		TimesheetDO timeSheet = timesheetService.get(timesheetId);
//		Map<String, Object> returnData = new HashMap<String, Object>();
//		returnData.put("timeSheet", timeSheet);
//		return returnData;
//	}
	@GetMapping("/add")
	@RequiresPermissions("timesheet:timesheet:add")
	String add(){

	    return "timesheet/timesheet/add";
	}


	@GetMapping("/edit/{timesheetId}/{projectId}")
	@RequiresPermissions("timesheet:timesheet:edit")
	String edit(@PathVariable("timesheetId") String timesheetId,@PathVariable("projectId") String projectId,Model model){



  //如果timesheetid=-1新增id并添加

		if(timesheetId.equals("-1"))
			{
				TimesheetDO newtimesheet=new TimesheetDO();
				String newtimesheetId=timesheetService.getnewtimesheetId(newtimesheet);
				TimesheetDO timesheet = timesheetService.get(newtimesheetId);

				ProjectDO project=projectService.get(projectId);
				timesheet.setProjectId(project.getProjectId());
				timesheet.setTimesheetProjectCagegory(project.getProjectGategory());

				timesheet.setTimesheetPm(project.getProjectOwner());

                 timesheetService.update(timesheet);
				model.addAttribute("timesheet", timesheet);
				return "timesheet/timesheet/edit";
		}
			else
		{

			TimesheetDO timesheet = timesheetService.get(timesheetId);
			model.addAttribute("timesheet", timesheet);
			return "timesheet/timesheet/edit";
		}

	}


	@GetMapping("/approve/{procDefId}/{taskId}")
	public ModelAndView approve(@PathVariable("procDefId") String procDefId, @PathVariable("taskId") String taskId, Model model) throws IOException {


		String formKey = actTaskService.getFormKey(procDefId, taskId);//获取流程表单
		model.addAttribute("taskId", taskId);
		model.addAttribute("formSrc", formKey+"/"+taskId);
		model.addAttribute("formSubmit", formKey+"/update");//流程审批处理保存

		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();//根据任务id查询实例id
		model.addAttribute("taskName", task.getName());
		model.addAttribute("processDefinitionId", task.getProcessDefinitionId());
		model.addAttribute("executionId", task.getExecutionId());
		model.addAttribute("processInstanceId", task.getProcessInstanceId());

		return new ModelAndView("act/task/formComm");
	}






	@PostMapping("/approveall")
	public ModelAndView approveall(String pdIdDate1,String taskIdDate1,
								  String pdIdDate2,String taskIdDate2,
								  String pdIdDate3,String taskIdDate3,
								  String pdIdDate4,String taskIdDate4,
								  String pdIdDate5,String taskIdDate5,
								  String pdIdDate6,String taskIdDate6,
								  String pdIdDate7,String taskIdDate7,
								  Model model) throws IOException {
		//传参数判断是否为空
  if (pdIdDate1 != null && !"".equals(pdIdDate1) )
  {
  if (taskIdDate1 != null && !"".equals(taskIdDate1))
  {
			   String formKey = actTaskService.getFormKey(pdIdDate1, taskIdDate1);//获取流程表单

			   model.addAttribute("taskId", taskIdDate1);
			   model.addAttribute("formSrc", formKey + "/" + taskIdDate1);
			   model.addAttribute("formSubmit", formKey + "/update");//流程审批处理保存

			   Task task = taskService.createTaskQuery().taskId(taskIdDate1).singleResult();//根据任务id查询实例id
			   model.addAttribute("taskName", task.getName());
			   model.addAttribute("processDefinitionId", task.getProcessDefinitionId());
			   model.addAttribute("executionId", task.getExecutionId());
			   model.addAttribute("processInstanceId", task.getProcessInstanceId());
		   }
	   }
		if (pdIdDate2 != null && !"".equals(pdIdDate2) )
		{
			if (taskIdDate2 != null && !"".equals(taskIdDate2))
			{
				String formKey = actTaskService.getFormKey(pdIdDate2, taskIdDate2);//获取流程表单

				model.addAttribute("taskId", taskIdDate2);
				model.addAttribute("formSrc", formKey + "/" + taskIdDate2);
				model.addAttribute("formSubmit", formKey + "/update");//流程审批处理保存

				Task task = taskService.createTaskQuery().taskId(taskIdDate2).singleResult();//根据任务id查询实例id
				model.addAttribute("taskName", task.getName());
				model.addAttribute("processDefinitionId", task.getProcessDefinitionId());
				model.addAttribute("executionId", task.getExecutionId());
				model.addAttribute("processInstanceId", task.getProcessInstanceId());
			}
		}
		if (pdIdDate3 != null && !"".equals(pdIdDate3) )
		{
			if (taskIdDate3 != null && !"".equals(taskIdDate3))
			{
				String formKey = actTaskService.getFormKey(pdIdDate3, taskIdDate3);//获取流程表单

				model.addAttribute("taskId", taskIdDate3);
				model.addAttribute("formSrc", formKey + "/" + taskIdDate3);
				model.addAttribute("formSubmit", formKey + "/update");//流程审批处理保存

				Task task = taskService.createTaskQuery().taskId(taskIdDate3).singleResult();//根据任务id查询实例id
				model.addAttribute("taskName", task.getName());
				model.addAttribute("processDefinitionId", task.getProcessDefinitionId());
				model.addAttribute("executionId", task.getExecutionId());
				model.addAttribute("processInstanceId", task.getProcessInstanceId());
			}
		}
		if (pdIdDate4 != null && !"".equals(pdIdDate4) )
		{
			if (taskIdDate4 != null && !"".equals(taskIdDate4))
			{
				String formKey = actTaskService.getFormKey(pdIdDate4, taskIdDate4);//获取流程表单

				model.addAttribute("taskId", taskIdDate4);
				model.addAttribute("formSrc", formKey + "/" + taskIdDate4);
				model.addAttribute("formSubmit", formKey + "/update");//流程审批处理保存

				Task task = taskService.createTaskQuery().taskId(taskIdDate4).singleResult();//根据任务id查询实例id
				model.addAttribute("taskName", task.getName());
				model.addAttribute("processDefinitionId", task.getProcessDefinitionId());
				model.addAttribute("executionId", task.getExecutionId());
				model.addAttribute("processInstanceId", task.getProcessInstanceId());
			}
		}
		if (pdIdDate5 != null && !"".equals(pdIdDate5) )
		{
			if (taskIdDate5 != null && !"".equals(taskIdDate5))
			{
				String formKey = actTaskService.getFormKey(pdIdDate5, taskIdDate5);//获取流程表单

				model.addAttribute("taskId", taskIdDate5);
				model.addAttribute("formSrc", formKey + "/" + taskIdDate5);
				model.addAttribute("formSubmit", formKey + "/update");//流程审批处理保存

				Task task = taskService.createTaskQuery().taskId(taskIdDate5).singleResult();//根据任务id查询实例id
				model.addAttribute("taskName", task.getName());
				model.addAttribute("processDefinitionId", task.getProcessDefinitionId());
				model.addAttribute("executionId", task.getExecutionId());
				model.addAttribute("processInstanceId", task.getProcessInstanceId());
			}
		}
		if (pdIdDate6 != null && !"".equals(pdIdDate6) )
		{
			if (taskIdDate6 != null && !"".equals(taskIdDate6))
			{
				String formKey = actTaskService.getFormKey(pdIdDate6, taskIdDate6);//获取流程表单

				model.addAttribute("taskId", taskIdDate6);
				model.addAttribute("formSrc", formKey + "/" + taskIdDate6);
				model.addAttribute("formSubmit", formKey + "/update");//流程审批处理保存

				Task task = taskService.createTaskQuery().taskId(taskIdDate6).singleResult();//根据任务id查询实例id
				model.addAttribute("taskName", task.getName());
				model.addAttribute("processDefinitionId", task.getProcessDefinitionId());
				model.addAttribute("executionId", task.getExecutionId());
				model.addAttribute("processInstanceId", task.getProcessInstanceId());
			}
		}
		if (pdIdDate7 != null && !"".equals(pdIdDate7) )
		{
			if (taskIdDate7 != null && !"".equals(taskIdDate7))
			{
				String formKey = actTaskService.getFormKey(pdIdDate7, taskIdDate7);//获取流程表单

				model.addAttribute("taskId", taskIdDate7);
				model.addAttribute("formSrc", formKey + "/" + taskIdDate7);
				model.addAttribute("formSubmit", formKey + "/update");//流程审批处理保存

				Task task = taskService.createTaskQuery().taskId(taskIdDate7).singleResult();//根据任务id查询实例id
				model.addAttribute("taskName", task.getName());
				model.addAttribute("processDefinitionId", task.getProcessDefinitionId());
				model.addAttribute("executionId", task.getExecutionId());
				model.addAttribute("processInstanceId", task.getProcessInstanceId());
			}
		}

			return new ModelAndView("act/task/formComm");

	}



	@ResponseBody
	@GetMapping("/listDic")
	public List<DictDO> listByType() {
		// 查询列表数据
		Map<String, Object> map = new HashMap<>(16);
		map.put("type", "");
		List<DictDO> dictList = timesheetService.listDic();
		return dictList;
	}

	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("timesheet:timesheet:add")
	public R save( TimesheetDO timesheet){
//添加当前用户
		timesheet.setEmployeeId(getUserId());
//添加当前天日期
		SimpleDateFormat simdf = new SimpleDateFormat("yyyy-MM-dd");//日期方法
		Calendar cal = Calendar.getInstance();
		Date date = new Date();
		date=cal.getTime();
      timesheet.setTimesheetDate(date);

		//添加任务表


		String assignmentId = assignmentService.saveAssignmentInTimesheet(timesheet);


		//添加工时表
		timesheet.setTimesheetAssignmentId(assignmentId);
		if(timesheetService.save(timesheet)>0){
  timesheet.setTimesheetAssignmentId(Long.toString(getUserId()));
			return R.ok();
		}
		return R.error();
	}


	@ResponseBody
	@PostMapping("/save1")
	@RequiresPermissions("timesheet:timesheet:add1")
	public R save1( TimesheetDO timesheet){
//添加当前用户
		timesheet.setEmployeeId(getUserId());
//添加当前天日期
		SimpleDateFormat simdf = new SimpleDateFormat("yyyy-MM-dd");//日期方法
		Calendar cal = Calendar.getInstance();
		Date date = new Date();
		date=cal.getTime();
		timesheet.setTimesheetDate(date);




		if(timesheetService.save1(timesheet)>0){

			return R.ok();
		}
		return R.error();
	}


	@GetMapping("/add1")
	@RequiresPermissions("timesheet:timesheet:add1")
	String add1(){

		return "timesheet/timesheet/add1";
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("timesheet:timesheet:edit")
	public R update( TimesheetDO timesheet){
		//添加当前天日期
		SimpleDateFormat simdf = new SimpleDateFormat("yyyy-MM-dd");//日期方法
		Calendar cal = Calendar.getInstance();
		Date date = new Date();
		date=cal.getTime();

		timesheet.setTimesheetDate(date);
		//添加当前用户
		timesheet.setEmployeeId(getUserId());
		timesheetService.update(timesheet);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("timesheet:timesheet:remove")
	public R remove( String timesheetId){
		if(timesheetService.remove(timesheetId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("timesheet:timesheet:batchRemove")
	public R remove(@RequestParam("ids[]") String[] timesheetIds){
		timesheetService.batchRemove(timesheetIds);
		return R.ok();
	}
	@RequestMapping("/getProjectId/{projectId}")
	@ResponseBody
	Map<String, Object> getProjectId(@PathVariable("projectId") String projectId) {
		ProjectDO project = timesheetService.getProjectId(projectId);
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("project",project);


		return returnData;
	}

	
}
