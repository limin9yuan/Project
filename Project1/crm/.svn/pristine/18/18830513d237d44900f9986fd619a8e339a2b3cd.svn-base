package com.bootdo.sales.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.sales.domain.CompanyCustomerDO;
import com.bootdo.sales.domain.CustomerChildCompanyDo;
import com.bootdo.sales.domain.CustomerDeptDO;
import com.bootdo.sales.service.CustomerChildCompanyService;

/**
 * //客户组织机构_子公司
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/sales/customerChildCompany")
public class CustomerChildCompanyController extends BaseController {
	@Autowired
	private CustomerChildCompanyService customerChildCompanyService;
	// @Autowired
	// private BootdoConfig bootdoConfig;

	@GetMapping()
	@RequiresPermissions("sales:customerChildCompany:customerChildCompany")
	String CustomerChildCompany() {
		return "sales/customerChildCompany/customerChildCompany";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("sales:customerChildCompany:customerChildCompany")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<CustomerChildCompanyDo> customerChildCompanyList = customerChildCompanyService.list(query);
		int total = customerChildCompanyService.count(query);
		PageUtils pageUtils = new PageUtils(customerChildCompanyList, total);
		return pageUtils;
	}

	//ajax修改绑定数据
		@RequestMapping("/edit_ajax/{childCompanyId}")
		@ResponseBody
		Map<String, Object> edit_ajax(@PathVariable("childCompanyId") String childCompanyId) {
			CustomerChildCompanyDo CustomerChildCompany = customerChildCompanyService.get(childCompanyId);
			Map<String, Object> returnData = new HashMap<String, Object>();
			returnData.put("CustomerChildCompany", CustomerChildCompany);
			return returnData;
		}
	@GetMapping("/add")
	@RequiresPermissions("sales:customerChildCompany:add")
	String add(){
	    return "sales/companyCustomer/addChild";
	}
	
	@GetMapping("/edit/{childCompanyId}")
	@RequiresPermissions("sales:customerChildCompany:edit")
	String edit(@PathVariable("childCompanyId") String childCompanyId,Model model){
		CustomerChildCompanyDo customerChildCompany = customerChildCompanyService.get(childCompanyId);
		model.addAttribute("customerChildCompany", customerChildCompany);
	    return "sales/companyCustomer/editChild";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("sales:customerChildCompany:add")
	public R save( CustomerChildCompanyDo customerChildCompany){
		customerChildCompany.setChildCompanyOperator(Long.toString(getUserId()));//操作人
		if(customerChildCompanyService.save(customerChildCompany)>0){
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sales:customerChildCompany:edit")
	public R update( CustomerChildCompanyDo customerChildCompany){
		customerChildCompanyService.update(customerChildCompany);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("sales:customerChildCompany:batchRemove")
	public R remove( String childCompanyId){
		if(customerChildCompanyService.remove(childCompanyId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("sales:customerChildCompany:batchRemove")
	public R remove(@RequestParam("ids[]") String[] childCompanyId){
		customerChildCompanyService.batchRemove(childCompanyId);
		return R.ok();
	}
}
































