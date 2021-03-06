package com.bootdo.approval.controller;

import java.util.List;
import java.util.Map;

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

import com.bootdo.approval.domain.PurchaseDetailDO;
import com.bootdo.approval.service.PurchaseDetailService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 采购申请明细表

 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-06-13 17:15:42
 */
 
@Controller
@RequestMapping("/approval/purchaseDetail")
public class PurchaseDetailController {
	@Autowired
	private PurchaseDetailService purchaseDetailService;
	
	@GetMapping()
	@RequiresPermissions("approval:purchaseDetail:purchaseDetail")
	String PurchaseDetail(){
	    return "system/purchaseDetail/purchaseDetail";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("approval:purchaseDetail:purchaseDetail")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<PurchaseDetailDO> purchaseDetailList = purchaseDetailService.list(query);
		int total = purchaseDetailService.count(query);
		PageUtils pageUtils = new PageUtils(purchaseDetailList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("approval:purchaseDetail:add")
	String add(){
	    return "approval/purchaseDetail/add";
	}

	@GetMapping("/edit/{purchaseId}")
	@RequiresPermissions("approval:purchaseDetail:edit")
	String edit(@PathVariable("purchaseId") Integer purchaseId,Model model){

	    return "system/purchaseDetail/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("approval:purchaseDetail:add")
	public R save( PurchaseDetailDO purchaseDetail){
		if(purchaseDetailService.save(purchaseDetail)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("approval:purchaseDetail:edit")
	public R update( PurchaseDetailDO purchaseDetail){
		purchaseDetailService.update(purchaseDetail);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("approval:purchaseDetail:remove")
	public R remove( Integer purchaseId){
		if(purchaseDetailService.remove(purchaseId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("approval:purchaseDetail:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] purchaseIds){
		purchaseDetailService.batchRemove(purchaseIds);
		return R.ok();
	}
	
}
