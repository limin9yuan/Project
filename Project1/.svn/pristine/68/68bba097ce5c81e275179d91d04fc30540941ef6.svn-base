package com.bootdo.sales.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.system.domain.DeptDO;
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

import com.bootdo.sales.domain.CustomerDeptDO;
import com.bootdo.sales.domain.CustomerJobDO;
import com.bootdo.sales.service.CustomerDeptService;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 客户组织机构_部门
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-12-20 10:21:11
 */
 
@Controller
@RequestMapping("/sales/customerDept")
public class CustomerDeptController extends BaseController{
	@Autowired
	private CustomerDeptService customerDeptService;
	
	@GetMapping()
	@RequiresPermissions("sales:customerDept:customerDept")
	String CustomerDept(){
	    return "sales/companyCustomer/customerDept";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("sales:customerDept:customerDept")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CustomerDeptDO> customerDeptList = customerDeptService.list(query);
		int total = customerDeptService.count(query);
		PageUtils pageUtils = new PageUtils(customerDeptList, total);
		return pageUtils;
	}
	
	@GetMapping("/add/{customerId}")
	@RequiresPermissions("sales:customerDept:add")
	String add(@PathVariable("customerId") String customerId,Model model){
		model.addAttribute("customerId", customerId);
	    return "sales/companyCustomer/addDept";
	}

	// ajax修改绑定数据
		@RequestMapping("/edit_ajax/{customerDeptId}")
		@ResponseBody
		Map<String, Object> edit_ajax(@PathVariable("customerDeptId") String customerDeptId) {
			CustomerDeptDO customerDept = customerDeptService.get(customerDeptId);
			Map<String, Object> returnData = new HashMap<String, Object>();
			returnData.put("customerDept", customerDept);
			return returnData;
		}
	//结构详情
	@GetMapping("/detailedInformation")
	@RequiresPermissions("sales:customerDept:detailed_information")
	String detailedInformation(){
		return "sales/companyCustomer/detailedInformation";
	}

	@GetMapping("/edit/{customerDeptId}")
	@RequiresPermissions("sales:customerDept:edit")
	String edit(@PathVariable("customerDeptId") String customerDeptId,Model model){
		CustomerDeptDO customerDept = customerDeptService.get(customerDeptId);
		model.addAttribute("customerDept", customerDept);
	    return "sales/companyCustomer/editDept";
	}

	@GetMapping("/treeList")
	@ResponseBody
	public List<CustomerDeptDO> treeList(@RequestParam Map<String, Object> params) {
		List<CustomerDeptDO> list = new ArrayList<CustomerDeptDO>();
		list = customerDeptService.getTreeList(params);
		return list;
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("sales:customerDept:add")
	public R save( CustomerDeptDO customerDept){
		customerDept.setCustomerDeptOperator(Long.toString(getUserId()));
		if(customerDeptService.save(customerDept)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sales:customerDept:edit")
	public R update( CustomerDeptDO customerDept){
		customerDeptService.update(customerDept);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("sales:customerDept:batchRemove")
	public R remove( String customerDeptId){
		if(customerDeptService.remove(customerDeptId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("sales:customerDept:batchRemove")
	public R remove(@RequestParam("ids[]") String[] customerDeptIds){
		customerDeptService.batchRemove(customerDeptIds);
		return R.ok();
	}
	
}
