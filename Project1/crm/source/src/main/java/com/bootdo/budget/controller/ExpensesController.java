package com.bootdo.budget.controller;

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
import com.bootdo.budget.service.ExpensesService;
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
public class ExpensesController {
	@Autowired
	private ExpensesService expensesService;
	
	@GetMapping()
	@RequiresPermissions("budget:expenses:expenses")
	String Expenses(){
	    return "budget/budget/expenses";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("budget:expenses:expenses")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ExpensesDO> expensesList = expensesService.list(query);
		int total = expensesService.count(query);
		PageUtils pageUtils = new PageUtils(expensesList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("budget:expenses:add")
	String add(){
	    return "budget/budget/expensesAdd";
	}

	@GetMapping("/edit/{expensesId}")
	@RequiresPermissions("budget:expenses:edit")
	String edit(@PathVariable("expensesId") String expensesId,Model model){
		ExpensesDO expenses = expensesService.get(expensesId);
		model.addAttribute("expenses", expenses);
	    return "budget/budget/expensesEdit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("budget:expenses:add")
	public R save( ExpensesDO expenses){
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
	@RequiresPermissions("budget:expenses:edit")
	public R update( ExpensesDO expenses){
		expensesService.update(expenses);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("budget:expenses:remove")
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
	@RequiresPermissions("budget:expenses:batchRemove")
	public R remove(@RequestParam("ids[]") String[] expensesIds){
		expensesService.batchRemove(expensesIds);
		return R.ok();
	}
	
}
