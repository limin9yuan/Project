package com.bootdo.budget.controller;

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

import com.bootdo.budget.domain.BudgetDO;
import com.bootdo.budget.domain.LaborDO;
import com.bootdo.budget.service.LaborService;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.inner.domain.InnerOrgEmployeeDO;

/**
 * 项目人力安排表
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-12-11 14:43:07
 */
 
@Controller
@RequestMapping("/budget/labor")
public class LaborController extends BaseController {
	@Autowired
	private LaborService laborService;
	
	@GetMapping()
	@RequiresPermissions("budget:budget:budget")
	String Labor(){
	    return "budget/budget/labor";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("budget:budget:budget")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<LaborDO> laborList = laborService.list(query);
		int total = laborService.count(query);
		PageUtils pageUtils = new PageUtils(laborList, total);
		return pageUtils;
	}
	
	//@GetMapping("/add")
	//@RequiresPermissions("budget:budget:add")
	@GetMapping("/add/{budgetId}")
	@RequiresPermissions("budget:budget:add")
	String add(@PathVariable("budgetId") String budgetId,Model model) {
		model.addAttribute("budgetId", budgetId);
	    return "budget/budget/laborAdd";
	}
	@RequestMapping("/edit_ajax/{laborId}")
	@ResponseBody
	Map<String, Object> edit_ajax(@PathVariable("laborId") String laborId) {
		LaborDO labor = laborService.get(laborId);
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("labor", labor);
		return returnData;
	}
	@GetMapping("/edit/{laborId}")
	@RequiresPermissions("budget:budget:edit")
	String edit(@PathVariable("laborId") String laborId,Model model){
		//LaborDO labor = laborService.get(laborId);
		model.addAttribute("laborId", laborId);
	    return "budget/budget/laborEdit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("budget:budget:add")
	public R save( LaborDO labor){
		labor.setLaborOperator(getUserId());
		if(laborService.save(labor)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("budget:budget:edit")
	public R update( LaborDO labor){
		labor.setLaborOperator(getUserId());
		laborService.update(labor);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("budget:budget:remove")
	public R remove( String laborId){
		if(laborService.remove(laborId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("budget:budget:batchRemove")
	public R remove(@RequestParam("ids[]") String[] laborIds){
		laborService.batchRemove(laborIds);
		return R.ok();
	}

	@RequestMapping("/getEmployeeLevelSalary/{employeeId}")
	@ResponseBody
	Map<String, Object> getEmployeeLevelSalary(@PathVariable("employeeId") String employeeId) {
		InnerOrgEmployeeDO employee = laborService.getEmployeeLevelSalary(employeeId);
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("employee", employee);
		return returnData;
	}
}
