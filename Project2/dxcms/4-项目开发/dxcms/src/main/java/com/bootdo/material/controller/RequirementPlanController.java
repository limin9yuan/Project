package com.bootdo.material.controller;

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

import com.bootdo.material.domain.RequirementPlanDO;
import com.bootdo.material.service.RequirementPlanService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-08-21 16:46:41
 */
 
@Controller
@RequestMapping("/requirementPlan/requirementPlan")
public class RequirementPlanController {
	@Autowired
	private RequirementPlanService requirementPlanService;
	
	@GetMapping()
	@RequiresPermissions("requirementPlan:requirementPlan")
	String RequirementPlan(){
	    return "material/requirementPlan/requirementPlan";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("requirementPlan:requirementPlan")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<RequirementPlanDO> requirementPlanList = requirementPlanService.list(query);
		int total = requirementPlanService.count(query);
		PageUtils pageUtils = new PageUtils(requirementPlanList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:requirementPlan:add")
	String add(){
	    return "system/requirementPlan/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:requirementPlan:edit")
	String edit(@PathVariable("id") Long id,Model model){
		RequirementPlanDO requirementPlan = requirementPlanService.get(id);
		model.addAttribute("requirementPlan", requirementPlan);
	    return "system/requirementPlan/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("requirementPlan:requirementPlan:add")
	public R save( RequirementPlanDO requirementPlan){
		if(requirementPlanService.save(requirementPlan)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:requirementPlan:edit")
	public R update( RequirementPlanDO requirementPlan){
		requirementPlanService.update(requirementPlan);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("requirementPlan:requirementPlan:remove")
	public R remove( Long id){
		if(requirementPlanService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("requirementPlan:requirementPlan:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		requirementPlanService.batchRemove(ids);
		return R.ok();
	}
	
}
