package com.bootdo.contract.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.contract.domain.PayableDO;
import com.bootdo.contract.service.PayableService;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 付款计划表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-12-15 16:02:12
 */
 
@Controller
@RequestMapping("/additionalRecords/payable")
public class RecordsPayableController  extends BaseController{
	@Autowired
	private PayableService payableService;
	
	@GetMapping()
	@RequiresPermissions("contract:additionalRecords:additionalRecords")
	String Payable(){
	    return "contract/additionalRecords/payable";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("contract:additionalRecords:additionalRecords")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<PayableDO> payableList = payableService.list(query);
		int total = payableService.count(query);
		PageUtils pageUtils = new PageUtils(payableList, total);
		return pageUtils;
	}
	
	@GetMapping("/add{contractId}")
	@RequiresPermissions("contract:additionalRecords:add")
	String add(@PathVariable("contractId") String contractId,Model model) {
		model.addAttribute("contractId", contractId);
	    return "contract/additionalRecords/addPayable";
	}
	//————修改
	@RequestMapping("/edit_ajax/{payableId}")
	@ResponseBody
	Map<String, Object> edit_ajax(@PathVariable("payableId") String payableId) {
		PayableDO payable = payableService.get(payableId);
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("payable", payable);
		return returnData;
	}
	
	@GetMapping("/edit/{payableId}")
	@RequiresPermissions("contract:additionalRecords:edit")
	String edit(@PathVariable("payableId") String payableId,Model model){
		model.addAttribute("payableId", payableId);
	    return "contract/additionalRecords/editPayable";
	}
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("contract:additionalRecords:add")
	public R save( PayableDO payable){
		payable.setPayableOperator(getUserId());
		if(payableService.save(payable)>0){
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("contract:additionalRecords:edit")
	public R update(PayableDO payable){
		payable.setPayableOperator(getUserId());
		payableService.update(payable);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("contract:additionalRecords:remove")
	public R remove(String payableId){
		if(payableService.remove(payableId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("contract:additionalRecords:batchRemove")
	public R remove(@RequestParam("ids[]") String[] payableIds){
		payableService.batchRemove(payableIds);
		return R.ok();
	}
	
}
