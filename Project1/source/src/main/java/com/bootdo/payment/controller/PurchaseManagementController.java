package com.bootdo.payment.controller;

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

import com.bootdo.payment.domain.PurchaseManagementDO;
import com.bootdo.payment.service.PurchaseManagementService;
import com.bootdo.sales.domain.SalesProjectDO;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 采购信息表
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-12-05 15:59:36
 */
 
@Controller
@RequestMapping("/payment/purchaseManagement")
public class PurchaseManagementController extends BaseController {
	@Autowired
	private PurchaseManagementService purchaseService;
	@Autowired
	private MainCopyPersonService mainCopyPersonService;
	
	@GetMapping()
	@RequiresPermissions("payment:purchaseManagement:purchaseManagement")
	String Purchase(){
	    return "payment/purchaseManagement/purchaseManagement";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("payment:purchaseManagement:purchaseManagement")
	public PageUtils list(@RequestParam Map<String, Object> params){
		params.put("userId", getUserId());
		params.put("userName", getUsername());
		params.put("tableName", "purchase");
		//查询列表数据
		params.put("purchaseOperators", (getUserId()));
		params.put("Identification", (getIdentification()));
		if (params.get("purchaseOperator") != null && !"".equals(params.get("purchaseOperator"))) {
			params.put("purchaseOperator", "%" + (String) params.get("purchaseOperator") + "%");
		}
		/*if (params.get("purchaseTime") != null && !"".equals(params.get("purchaseTime"))) {
			params.put("purchaseTime", "%" + (String) params.get("purchaseTime") + "%");
		}	*/
        Query query = new Query(params);
		List<PurchaseManagementDO> purchaseList = purchaseService.list(query);
		int total = purchaseService.count(query);
		PageUtils pageUtils = new PageUtils(purchaseList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("payment:purchaseManagement:add")
	String add(){
	    return "payment/purchaseManagement/add";
	}
	@RequestMapping("/edit_ajax/{purchaseId}")
	@ResponseBody
	Map<String, Object> edit_ajax(@PathVariable("purchaseId") String purchaseId) {
		PurchaseManagementDO purchase = purchaseService.get(purchaseId);
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("purchase", purchase);
		return returnData;
	}                
	@GetMapping("/edit/{purchaseId}")
	@RequiresPermissions("payment:purchaseManagement:edit")
	String edit(@PathVariable("purchaseId") String purchaseId,Model model){
		//PurchaseManagementDO purchase = purchaseService.get(purchaseId);
		model.addAttribute("purchaseId", purchaseId);
	    return "payment/purchaseManagement/edit";
	}
	@GetMapping("/view/{purchaseId}")
	@RequiresPermissions("payment:purchaseManagement:edit")
	String view(@PathVariable("purchaseId") String purchaseId,Model model){
		//PurchaseManagementDO purchase = purchaseService.get(purchaseId);
		model.addAttribute("purchaseId", purchaseId);
		return "payment/purchaseManagement/view";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody        
	@PostMapping("/save")
	@RequiresPermissions("payment:purchaseManagement:add")
	public R save( PurchaseManagementDO purchase){
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
					mcp.setTableName("purchase");
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
					mcp.setTableName("purchase");
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
	@RequiresPermissions("payment:purchaseManagement:edit")
	public R update( PurchaseManagementDO purchase){
		purchase.setPurchaseOperator(getUserId());
		String purchaseIds = purchase.getPurchaseId();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("offset",1);
		params.put("limit",2);
		params.put("tId",purchaseIds);
		params.put("tableName","purchase");
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
					mcp.setTableName("purchase");
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
					mcp.setTableName("purchase");
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
	@RequiresPermissions("payment:purchaseManagement:remove")
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
	@RequiresPermissions("payment:purchaseManagement:batchRemove")
	public R remove(@RequestParam("ids[]") String[] purchaseIds){
		purchaseService.batchRemove(purchaseIds);
		return R.ok();
	}
	
}
