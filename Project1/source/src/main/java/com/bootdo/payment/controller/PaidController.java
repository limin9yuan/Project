package com.bootdo.payment.controller;

import java.util.HashMap;
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

import com.bootdo.payment.domain.InvoiceDO;
import com.bootdo.payment.domain.PaidDO;
import com.bootdo.payment.service.PaidService;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 付款信息表
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-12-05 10:14:55
 */
 
@Controller
@RequestMapping("/payment/paid")
public class PaidController extends BaseController {
	@Autowired
	private PaidService paidService;
	
	@GetMapping()
	@RequiresPermissions("payment:paid:paid")
	String Paid(){
	    return "payment/paid/paid";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("payment:paid:paid")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
				if (params.get("projectOwner") != null && !"".equals(params.get("projectOwner"))) {
					params.put("projectOwner", "%" + (String) params.get("projectOwner") + "%");
				}	
        Query query = new Query(params); 
		List<PaidDO> paidList = paidService.list(query);
		int total = paidService.count(query);
		PageUtils pageUtils = new PageUtils(paidList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("payment:paid:add")
	String add(){
	    return "payment/paid/add";
	}
	@RequestMapping("/edit_ajax/{paidId}")
	@ResponseBody
	Map<String, Object> edit_ajax(@PathVariable("paidId") String paidId) {
		PaidDO paid = paidService.get(paidId);
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("paid", paid);
		return returnData;
	}
	@GetMapping("/edit/{paidId}")
	@RequiresPermissions("payment:paid:edit")
	String edit(@PathVariable("paidId") String paidId,Model model){
		//PaidDO paid = paidService.get(paidId);
		model.addAttribute("paidId", paidId);
	    return "payment/paid/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("payment:paid:add")
	public R save( PaidDO paid){
		paid.setPaidOperator(getUserId());
		if(paidService.save(paid)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("payment:paid:edit")
	public R update( PaidDO paid){
		paid.setPaidOperator(getUserId());
		paidService.update(paid);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("payment:paid:remove")
	public R remove( String paidId){
		if(paidService.remove(paidId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("payment:paid:batchRemove")
	public R remove(@RequestParam("ids[]") String[] paidIds){
		paidService.batchRemove(paidIds);
		return R.ok();
	}
	
}