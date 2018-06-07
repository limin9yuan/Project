package com.bootdo.approval.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.common.domain.MainCopyPersonDO;
import com.bootdo.common.service.MainCopyPersonService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.activiti.domain.SalaryDO;
import com.bootdo.activiti.service.impl.ActTaskServiceImpl;
import com.bootdo.activiti.utils.ActivitiUtils;
import com.bootdo.approval.domain.PurchaseDO;
import com.bootdo.approval.service.PurchaseService;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.contract.domain.TravelDO;
import com.bootdo.project.domain.ProjectDO;

/**
 * 采购申请表
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-11-30 14:40:43
 */
 
@Controller
@RequestMapping("/approval/purchase")
public class PurchaseController extends BaseController {
	@Autowired
	private PurchaseService purchaseService;
	@Autowired
	private ActivitiUtils activitiUtils;
	@Autowired
	private MainCopyPersonService mainCopyPersonService;
	
	@GetMapping()
	@RequiresPermissions("approval:purchase:purchase")
	String Purchase(){
	    return "approval/purchase/purchase";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("approval:purchase:purchase")
	public PageUtils list(@RequestParam Map<String, Object> params){
		params.put("userId", getUserId());
		params.put("userName", getUsername());
		params.put("tableName", "approval_purchase");
		params.put("projectId", params.get("projectId"));
		//查询列表数据
		if (params.get("purchaseOperator") != null && !"".equals(params.get("purchaseOperator"))) {
			params.put("purchaseOperator", "%" + (String) params.get("purchaseOperator") + "%");
		}
        Query query = new Query(params);
		List<PurchaseDO> purchaseList = purchaseService.list(query);
		int total = purchaseService.count(query);
		PageUtils pageUtils = new PageUtils(purchaseList, total);
		return pageUtils;
	}
	
	@GetMapping("/exaiminePurchase")
	@RequiresPermissions("approval:purchase:purchase")
	String exaiminePurchase(){
	    return "approval/purchase/examinePurchase";
	}
	//审批后金额列表
	@ResponseBody
	@GetMapping("/listPurchaseApprovalStatus")
	@RequiresPermissions("approval:purchase:purchase")
	public PageUtils listPurchaseApprovalStatus(@RequestParam Map<String, Object> params){
		params.put("userId", getUserId());
		params.put("userName", getUsername());
		params.put("tableName", "approval_purchase");
		//查询列表数据
		if (params.get("purchaseOperator") != null && !"".equals(params.get("purchaseOperator"))) {
			params.put("purchaseOperator", "%" + (String) params.get("purchaseOperator") + "%");
		}
        Query query = new Query(params);
		List<PurchaseDO> purchaseList = purchaseService.listPurchaseApprovalStatus(query);
		int total = purchaseService.countPurchaseApprovalStatus(query);
		PageUtils pageUtils = new PageUtils(purchaseList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("approval:purchase:add")
	String add(){
	    return "approval/purchase/add";
	}
	@RequestMapping("/edit_ajax/{purchaseId}")
	@ResponseBody
	Map<String, Object> edit_ajax(@PathVariable("purchaseId") String purchaseId) {
		PurchaseDO purchase = purchaseService.get(purchaseId);
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("purchase", purchase);
		return returnData;
	}
	@GetMapping("/edit/{purchaseId}")
	@RequiresPermissions("approval:purchase:edit")
	String edit(@PathVariable("purchaseId") String purchaseId,Model model){
		//PurchaseDO purchase = purchaseService.get(purchaseId);
		model.addAttribute("purchaseId", purchaseId);
	    return "approval/purchase/edit";
	}

	@GetMapping("/view/{purchaseId}")
	@RequiresPermissions("approval:purchase:edit")
	String view(@PathVariable("purchaseId") String purchaseId,Model model){
		//PurchaseDO purchase = purchaseService.get(purchaseId);
		model.addAttribute("purchaseId", purchaseId);
		return "approval/purchase/view";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("approval:purchase:add")
	public R save( PurchaseDO purchase){
		purchase.setPurchaseOperator(getUserId());
		int purchaseIds = purchaseService.save(purchase);
		if (purchaseIds > 0) {
			MainCopyPersonDO mcp = new MainCopyPersonDO();
			String mainPersonId = purchase.getMainPersonId();
			if (!"".equals(mainPersonId)) {
				String mainPersonIdArray[] = mainPersonId.split(",");
				for (int i = 0; i < mainPersonIdArray.length; i++) {
					mcp.setTId(purchase.getPurchaseId());
					mcp.setMainPerson(1);
					mcp.setEmployeeId(mainPersonIdArray[i]);
					mcp.setOperator(getUserId());
					mcp.setTableName("approval_purchase");
					mainCopyPersonService.save(mcp);

				}
			}

			String copyPersonId = purchase.getCopyPersonId();
			if (!"".equals(copyPersonId)) {
				String copyPersonIdArray[] = copyPersonId.split(",");
				for (int i = 0; i < copyPersonIdArray.length; i++) {
					mcp.setTId(purchase.getPurchaseId());
					mcp.setMainPerson(0);
					mcp.setEmployeeId(copyPersonIdArray[i]);
					mcp.setOperator(getUserId());
					mcp.setTableName("approval_purchase");
					mainCopyPersonService.save(mcp);
				}


			}
			R r = R.ok();
			r.put("purchaseId", purchaseIds);
			return r;
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("approval:purchase:edit")
	public R update( PurchaseDO purchase){
		purchase.setPurchaseOperator(getUserId());
		String purchaseIds = purchase.getPurchaseId();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("offset",1);
		params.put("limit",2);
		params.put("tId",purchaseIds);
		params.put("tableName","approval_purchase");
		purchaseService.update(purchase);
		mainCopyPersonService.remove(params);
		if (!purchaseIds.equals("")) {
			MainCopyPersonDO mcp = new MainCopyPersonDO();
			String mainPersonId = purchase.getMainPersonId();
			if (!"".equals(mainPersonId)) {
				String mainPersonIdArray[] = mainPersonId.split(",");

				for (int i = 0; i < mainPersonIdArray.length; i++) {
					mcp.setTId(purchaseIds);
					mcp.setMainPerson(1);
					mcp.setEmployeeId(mainPersonIdArray[i]);
					mcp.setOperator(getUserId());
					mcp.setTableName("approval_purchase");
					mainCopyPersonService.save(mcp);

				}
			}

			String copyPersonId = purchase.getCopyPersonId();
			if (!"".equals(copyPersonId)) {
				String copyPersonIdArray[] = copyPersonId.split(",");
				for (int i = 0; i < copyPersonIdArray.length; i++) {
					mcp.setTId(purchaseIds);
					mcp.setMainPerson(0);
					mcp.setEmployeeId(copyPersonIdArray[i]);
					mcp.setOperator(getUserId());
					mcp.setTableName("approval_purchase");
					mainCopyPersonService.save(mcp);

				}
			}

		}
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("approval:purchase:remove")
	public R remove( String purchaseId){
		if(purchaseService.remove(purchaseId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("approval:purchase:batchRemove")
	public R remove(@RequestParam("ids[]") String[] purchaseIds){
		purchaseService.batchRemove(purchaseIds);
		return R.ok();
	}
	/**
	 * ********************** 审批流程相关  *********************************
	 */
	//申请页面
	@GetMapping("/form")
	@RequiresPermissions("approval:purchase:add")
	String form(){
	    return "approval/purchase/add";
	}
	//审批处理页面
	@GetMapping("/form/{taskId}")
	@RequiresPermissions("approval:purchase:add")
	String formTask(@PathVariable("taskId") String taskId,Model model){
		//取得流程表单数据
		PurchaseDO purchase = purchaseService.view(activitiUtils.getBusinessKeyByTaskId(taskId));
		if(purchase!=null){
			model.addAttribute("purchase", purchase);
			//model.addAttribute("taskId",taskId);
		}
	    return "approval/purchase/viewPurchase";
	}
	
	
	 //审批处理保存
	@ResponseBody
	@RequestMapping("/form/update")
	@RequiresPermissions("approval:purchase:edit")
	public R formUpdate( PurchaseDO purchase){
		
		purchaseService.formUpdate(purchase);
		return R.ok();
	}
}
