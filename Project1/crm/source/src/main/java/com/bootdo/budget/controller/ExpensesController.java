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

import com.bootdo.budget.domain.ExpensesDO;
import com.bootdo.budget.domain.LaborDO;
import com.bootdo.budget.service.ExpensesService;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 项目报销预算表
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-12-11 14:42:42
 */
 
@Controller
@RequestMapping("/budget/expenses")
public class ExpensesController extends BaseController {
	@Autowired
	private ExpensesService expensesService;
	
	@GetMapping()
	@RequiresPermissions("budget:budget:budget")
	String Expenses(){
	    return "budget/budget/expenses";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("budget:budget:budget")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ExpensesDO> expensesList = expensesService.list(query);
		int total = expensesService.count(query);
		PageUtils pageUtils = new PageUtils(expensesList, total);
		return pageUtils;
	}
	
	//@GetMapping("/add")
	@GetMapping("/add/{budgetId}")
	@RequiresPermissions("budget:budget:add")
	String add(@PathVariable("budgetId") String budgetId,Model model) {
		model.addAttribute("budgetId", budgetId);
	    return "budget/budget/expensesAdd";
	}
	@RequestMapping("/edit_ajax/{expensesId}")
	@ResponseBody
	Map<String, Object> edit_ajax(@PathVariable("expensesId") String expensesId) {
		ExpensesDO expenses = expensesService.get(expensesId);
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("expenses", expenses);
		return returnData;
	}
	@GetMapping("/edit/{expensesId}")
	@RequiresPermissions("budget:budget:edit")
	String edit(@PathVariable("expensesId") String expensesId,Model model){
		//ExpensesDO expenses = expensesService.get(expensesId);
		model.addAttribute("expensesId", expensesId);
	    return "budget/budget/expensesEdit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("budget:budget:add")
	public R save( ExpensesDO expenses){
		expenses.setExpensesOperator(getUserId());
		if(expensesService.save(expenses)>0){
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
	public R update( ExpensesDO expenses){
		expensesService.update(expenses);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("budget:budget:remove")
	public R remove( String expensesId){
		if(expensesService.remove(expensesId)>0){
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
	public R remove(@RequestParam("ids[]") String[] expensesIds){
		expensesService.batchRemove(expensesIds);
		return R.ok();
	}
	
}
