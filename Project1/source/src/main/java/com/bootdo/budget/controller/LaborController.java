package com.bootdo.budget.controller;

import java.math.BigDecimal;
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

import com.bootdo.budget.domain.BudgetDO;
import com.bootdo.budget.domain.LaborDO;
import com.bootdo.budget.service.BudgetService;
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
	@Autowired
	private BudgetService budgetService;
	//private BudgetDO budget;
	
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
	 * @param laborId 
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("budget:budget:add")
	public R save( LaborDO labor) {
		labor.setLaborOperator(getUserId());
		if(laborService.save(labor)>0){
			BudgetDO budget= budgetService.get(labor.getBudgetId());
			String budgetId= budget.getBudgetId();
			System.out.println(budgetId);
			budgetService.updateBudgetLaborCost(budgetId);
			if("软件项目技术开发类".equals(budget.getBudgetType().toString())){
				budgetService.updateSoftware(budgetId);	
			}
			if("老项目".equals(budget.getBudgetType().toString())){
				budgetService.updateOldProject(budgetId);
			}
			if("硬件类".equals(budget.getBudgetType().toString())){
				budgetService.updateBlender(budgetId);
			}
			if("软硬件混合项目软件为主".equals(budget.getBudgetType().toString())){
				budgetService.updateBlender(budgetId);
			}
			if("软硬件混合项目硬件为主".equals(budget.getBudgetType().toString())){
				budgetService.updateBlender(budgetId);	
			}
			BudgetDO budget2= budgetService.get(budgetId);
			BigDecimal budgetTotalCost =budget2.getBudgetTotalCost();
			BigDecimal budgetCost =budget2.getBudgetCost();
			//budgetTotalCost.compareTo(budgetCost); //大于 时，返回 1 小于 时返回 -1  等于 时，返回 0
//			System.out.println(budgetTotalCost);
//			System.out.println(budgetCost);
//			System.out.println(budgetTotalCost.compareTo(budgetCost));
			if(budgetTotalCost.compareTo(budgetCost)==1){
				budget2.setBudgetConformance("是");
			}
			if(budgetTotalCost.compareTo(budgetCost)==-1){
				budget2.setBudgetConformance("否");
			}
			budgetService.update(budget2);
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
	public R update( LaborDO labor,String laborId){
		labor.setLaborOperator(getUserId());
		//laborService.update(labor);
		LaborDO labor2= laborService.get(laborId);
		BudgetDO budget= budgetService.get(labor2.getBudgetId());
		String budgetId=budget.getBudgetId();
		System.out.println(budgetId);
		if(laborService.update(labor)>0){
			budgetService.updateBudgetLaborCost(budgetId);
			if("软件项目技术开发类".equals(budget.getBudgetType().toString())){
				budgetService.updateSoftware(budgetId);
			}
			if("老项目".equals(budget.getBudgetType().toString())){
				budgetService.updateOldProject(budgetId);	
			}
			if("硬件类".equals(budget.getBudgetType().toString())){
				budgetService.updateBlender(budgetId);	
			}
			if("软硬件混合项目软件为主".equals(budget.getBudgetType().toString())){
				budgetService.updateBlender(budgetId);	
			}
			if("软硬件混合项目硬件为主".equals(budget.getBudgetType().toString())){
				budgetService.updateBlender(budgetId);	
			}
			BudgetDO budget2= budgetService.get(budgetId);
			BigDecimal budgetTotalCost =budget2.getBudgetTotalCost();
			BigDecimal budgetCost =budget2.getBudgetCost();
			//budgetTotalCost.compareTo(budgetCost); //大于 时，返回 1 小于 时返回 -1  等于 时，返回 0
			System.out.println(budgetTotalCost);
			System.out.println(budgetCost);
			System.out.println(budgetTotalCost.compareTo(budgetCost));
			if(budgetTotalCost.compareTo(budgetCost)==1){
				budget2.setBudgetConformance("是");
			}
			if(budgetTotalCost.compareTo(budgetCost)==-1){
				budget2.setBudgetConformance("否");
			}
			budgetService.update(budget2);
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("budget:budget:remove")
	public R remove(String laborId){
		LaborDO labor2= laborService.get(laborId);
		BudgetDO budget= budgetService.get(labor2.getBudgetId());
		String budgetId=budget.getBudgetId();
		System.out.println(budgetId);
		if(laborService.remove(laborId)>0){
			System.out.println(budgetId);
			budgetService.updateBudgetLaborCost(budgetId);
			if("软件项目技术开发类".equals(budget.getBudgetType().toString())){
				budgetService.updateSoftware(budgetId);	
			}
			if("老项目".equals(budget.getBudgetType().toString())){
				budgetService.updateOldProject(budgetId);	
			}
			if("硬件类".equals(budget.getBudgetType().toString())){
				budgetService.updateBlender(budgetId);	
			}
			if("软硬件混合项目软件为主".equals(budget.getBudgetType().toString())){
				budgetService.updateBlender(budgetId);	
			}
			if("软硬件混合项目硬件为主".equals(budget.getBudgetType().toString())){
				budgetService.updateBlender(budgetId);	
			}
			BudgetDO budget2= budgetService.get(budgetId);
			BigDecimal budgetTotalCost =budget2.getBudgetTotalCost();
			BigDecimal budgetCost =budget2.getBudgetCost();
			//budgetTotalCost.compareTo(budgetCost); //大于 时，返回 1 小于 时返回 -1  等于 时，返回 0
			System.out.println(budgetTotalCost);
			System.out.println(budgetCost);
			System.out.println(budgetTotalCost.compareTo(budgetCost));
			if(budgetTotalCost.compareTo(budgetCost)==1){
				budget2.setBudgetConformance("是");
			}
			if(budgetTotalCost.compareTo(budgetCost)==-1){
				budget2.setBudgetConformance("否");
			}
			budgetService.update(budget2);
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
		LaborDO labor2=laborService.get(laborIds[0]);
		BudgetDO budget= budgetService.get(labor2.getBudgetId()); 
		String budgetId=budget.getBudgetId();
		System.out.println(budgetId);
		if(laborService.batchRemove(laborIds)>0){
			budgetService.updateBudgetLaborCost(budgetId);
			if("软件项目技术开发类".equals(budget.getBudgetType().toString())){
				budgetService.updateSoftware(budgetId);	
			}
			if("老项目".equals(budget.getBudgetType().toString())){
				budgetService.updateOldProject(budgetId);	
			}
			if("硬件类".equals(budget.getBudgetType().toString())){
				budgetService.updateBlender(budgetId);	
			}
			if("软硬件混合项目软件为主".equals(budget.getBudgetType().toString())){
				budgetService.updateBlender(budgetId);	
			}
			if("软硬件混合项目硬件为主".equals(budget.getBudgetType().toString())){
				budgetService.updateBlender(budgetId);	
			}
			BudgetDO budget2= budgetService.get(budgetId);
			BigDecimal budgetTotalCost =budget2.getBudgetTotalCost();
			BigDecimal budgetCost =budget2.getBudgetCost();
			//budgetTotalCost.compareTo(budgetCost); //大于 时，返回 1 小于 时返回 -1  等于 时，返回 0
			System.out.println(budgetTotalCost);
			System.out.println(budgetCost);
			System.out.println(budgetTotalCost.compareTo(budgetCost));
			if(budgetTotalCost.compareTo(budgetCost)==1){
				budget2.setBudgetConformance("是");
			}
			if(budgetTotalCost.compareTo(budgetCost)==-1){
				budget2.setBudgetConformance("否");
			}
			budgetService.update(budget2);
		return R.ok();
		}
		return R.error();
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
