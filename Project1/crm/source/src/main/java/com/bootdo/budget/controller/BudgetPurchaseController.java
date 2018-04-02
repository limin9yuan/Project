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

import com.bootdo.budget.domain.BudgetPurchaseDO;
import com.bootdo.budget.service.BudgetPurchaseService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 项目采购预算表
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-12-11 14:43:33
 */
 
@Controller
@RequestMapping("/budget/budgetPurchase")
public class BudgetPurchaseController {
	@Autowired
	private BudgetPurchaseService budgetPurchaseService;
	
	@GetMapping()
	@RequiresPermissions("budget:budget:budget")
	String Purchase(){
	    //return "budget/budgetPurchase/budgetPurchase";
		return "budget/budget/budgetPurchase";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("budget:budget:budget")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<BudgetPurchaseDO> purchaseList = budgetPurchaseService.list(query);
		int total = budgetPurchaseService.count(query);
		PageUtils pageUtils = new PageUtils(purchaseList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("budget:budget:add")
	String add(){
	    //return "budget/purchase/add";
		return "budget/budget/purchaseAdd";
	}

	@GetMapping("/edit/{purchaseId}")
	@RequiresPermissions("budget:budget:edit")
	String edit(@PathVariable("purchaseId") String purchaseId,Model model){
		BudgetPurchaseDO purchase = budgetPurchaseService.get(purchaseId);
		model.addAttribute("purchase", purchase);
	    //return "budget/purchase/edit";
		return "budget/budget/purchaseEdit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("budget:budget:add")
	public R save( BudgetPurchaseDO purchase){
		if(budgetPurchaseService.save(purchase)>0){
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
	public R update( BudgetPurchaseDO purchase){
		budgetPurchaseService.update(purchase);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("budget:budget:remove")
	public R remove( String purchaseId){
		if(budgetPurchaseService.remove(purchaseId)>0){
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
	public R remove(@RequestParam("ids[]") String[] purchaseIds){
		budgetPurchaseService.batchRemove(purchaseIds);
		return R.ok();
	}
	
}
