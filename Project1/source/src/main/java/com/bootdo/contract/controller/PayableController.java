package com.bootdo.contract.controller;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
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
@RequestMapping("/contract/payable")
public class PayableController  extends BaseController{
	@Autowired
	private PayableService payableService;
	
	@GetMapping()
	@RequiresPermissions("contract:payable:payable")
	String Payable(){
	    return "contract/contract/payable";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("contract:payable:payable")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<PayableDO> payableList = payableService.list(query);
		int total = payableService.count(query);
		PageUtils pageUtils = new PageUtils(payableList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("contract:payable:add")
	String add(){
	    return "contract/contract/addPayable";
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
	@RequiresPermissions("contract:payable:edit")
	String edit(@PathVariable("payableId") String payableId,Model model){
		model.addAttribute("payableId", payableId);
	    return "contract/contract/editPayable";
	}
	
//	//__测试专用__
//	@InitBinder  
//    protected void initBinder(HttpServletRequest request,  
//            ServletRequestDataBinder binder) throws Exception {  
//            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
//            CustomDateEditor editor = new CustomDateEditor(df, false);  
//            binder.registerCustomEditor(Date.class, editor);  
//    } 
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("contract:payable:add")
	public R save( PayableDO payable){
		payable.setPayableOperator(getUsername());
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
	@RequiresPermissions("contract:payable:edit")
	public R update(PayableDO payable){
		payable.setPayableOperator(getUsername());
		payableService.update(payable);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("contract:payable:remove")
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
	@RequiresPermissions("contract:payable:batchRemove")
	public R remove(@RequestParam("ids[]") String[] payableIds){
		payableService.batchRemove(payableIds);
		return R.ok();
	}
	
}
