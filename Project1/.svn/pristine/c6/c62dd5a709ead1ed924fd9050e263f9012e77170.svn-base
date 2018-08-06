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

import com.bootdo.contract.domain.PlanDO;
import com.bootdo.contract.service.PlanService;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 交付计划表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-12-18 10:17:48
 */
 
@Controller
@RequestMapping("/contract/plan")
public class PlanController  extends BaseController{
	@Autowired
	private PlanService planService;
	
	@GetMapping()
	@RequiresPermissions("contract:contract:contract")
	String Plan(){
	    return "contract/contract/plan";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("contract:contract:contract")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<PlanDO> planList = planService.list(query);
		int total = planService.count(query);
		PageUtils pageUtils = new PageUtils(planList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("contract:contract:add")
	String add(){
	    return "contract/contract/addPla‪n";
	}

	@GetMapping("/edit/{planId}")
	@RequiresPermissions("contract:contract:edit")
	String edit(@PathVariable("planId") String planId,Model model){
		PlanDO plan = planService.get(planId);
		model.addAttribute("plan", plan);
	    return "contract/contract/editPla‪n";
	}
	@RequestMapping("/edit_ajax/{planId}")
	@ResponseBody
	Map<String, Object> edit_ajax(@PathVariable("planId") String planId) {
		PlanDO plan = planService.get(planId);
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("plan", plan);
		return returnData;
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("contract:contract:add")
	public R save( PlanDO plan){
		plan.setPlanOperator(getUserId());
		if(planService.save(plan)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("contract:contract:edit")
	public R update( PlanDO plan){
		plan.setPlanOperator(getUserId());
		planService.update(plan);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("contract:contract:remove")
	public R remove( String planId){
		if(planService.remove(planId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("contract:contract:batchRemove")
	public R remove(@RequestParam("ids[]") String[] planIds){
		planService.batchRemove(planIds);
		return R.ok();
	}
	
}
