package com.bootdo.approval.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.activiti.utils.ActivitiUtils;
import com.bootdo.contract.domain.TravelDO;

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

import com.bootdo.approval.domain.AssignmentDO;
import com.bootdo.approval.service.AssignmentService;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 任务委托表
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-12-01 13:15:13
 */
 
@Controller
@RequestMapping("/approval/assignment")
public class AssignmentController extends BaseController {
	@Autowired
	private AssignmentService assignmentService;

	@Autowired
	ActivitiUtils activitiUtils;
	
	@GetMapping()
	@RequiresPermissions("approval:assignment:assignment")
	String Assignment(){
	    return "approval/assignment/assignment";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("approval:assignment:assignment")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		if (params.get("assignmentPrincipal") != null && !"".equals(params.get("assignmentPrincipal"))) {
			params.put("assignmentPrincipal", "%" + (String) params.get("assignmentPrincipal") + "%");
		}
		if (params.get("assignmentRecipient") != null && !"".equals(params.get("assignmentRecipient"))) {
			params.put("assignmentRecipient", "%" + (String) params.get("assignmentRecipient") + "%");
		}
		if (params.get("assignmentTaskName") != null && !"".equals(params.get("assignmentTaskName"))) {
			params.put("assignmentTaskName", "%" + (String) params.get("assignmentTaskName") + "%");
		}
		if (params.get("projectId") != null && params.get("projectId") != "") {
			params.put("projectId", "%" + params.get("projectId") + "%");
		}
        Query query = new Query(params);
		List<AssignmentDO> assignmentList = assignmentService.list(query);
		int total = assignmentService.count(query);
		PageUtils pageUtils = new PageUtils(assignmentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("approval:assignment:add")
	String add(){
	    return "approval/assignment/add";
	}
	@RequestMapping("/edit_ajax/{assignmentId}")
	@ResponseBody
	Map<String, Object> edit_ajax(@PathVariable("assignmentId") String assignmentId) {
		AssignmentDO assignment = assignmentService.get(assignmentId);
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("assignment", assignment);
		return returnData;
	}
	
	@GetMapping("/edit/{assignmentId}")
	@RequiresPermissions("approval:assignment:edit")
	String edit(@PathVariable("assignmentId") String assignmentId,Model model){
		//AssignmentDO assignment = assignmentService.get(assignmentId);
		model.addAttribute("assignmentId", assignmentId);
	    return "approval/assignment/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("approval:assignment:add")
	public R save( AssignmentDO assignment){
		assignment.setAssignmentCreator(getUserId());
		assignment.setAssignmentOperator(getUserId());
		if(assignmentService.save(assignment)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("approval:assignment:edit")
	public R update( AssignmentDO assignment){
		assignment.setAssignmentOperator(getUserId());
		assignmentService.update(assignment);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("approval:assignment:remove")
	public R remove( String assignmentId){
		if(assignmentService.remove(assignmentId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("approval:assignment:batchRemove")
	public R remove(@RequestParam("ids[]") String[] assignmentIds){
		assignmentService.batchRemove(assignmentIds);
		return R.ok();
	}

	/**
	 * ********************** 审批流程相关  *********************************
	 */
	//申请页面
	@GetMapping("/form")
	@RequiresPermissions("approval:assignment:add")
	String form(){
		return "approval/assignment/add";
	}
	//审批处理页面
	@GetMapping("/form/{taskId}")
	@RequiresPermissions("approval:assignment:add")
	String formTask(@PathVariable("taskId") String taskId,Model model){
		//取得流程表单数据
		AssignmentDO assignmentDO = assignmentService.view(activitiUtils.getBusinessKeyByTaskId(taskId));
		if(assignmentDO!=null){
			model.addAttribute("approvalAssignment", assignmentDO);
			//model.addAttribute("taskId",taskId);
		}
		return "approval/assignment/viewAssignment";
	}


	//审批处理保存
	@ResponseBody
	@RequestMapping("/form/update")
	@RequiresPermissions("approval:assignment:edit")
	public R formUpdate( AssignmentDO assignmentDO){

		assignmentService.formUpdate(assignmentDO);
		return R.ok();
	}
	
}
