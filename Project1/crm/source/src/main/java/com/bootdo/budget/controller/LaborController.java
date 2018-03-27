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
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 项目人力安排表
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-12-11 14:43:07
 */
 
@Controller
@RequestMapping("/budget/labor")
public class LaborController {
	@Autowired
	private LaborService laborService;
	
	@GetMapping()
	@RequiresPermissions("budget:labor:labor")
	String Labor(){
	    return "budget/budget/labor";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("budget:labor:labor")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<LaborDO> laborList = laborService.list(query);
		int total = laborService.count(query);
		PageUtils pageUtils = new PageUtils(laborList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("budget:labor:add")
	String add(){
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
	@RequiresPermissions("budget:labor:edit")
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
	@RequiresPermissions("budget:labor:add")
	public R save( LaborDO labor){
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
	@RequiresPermissions("budget:labor:edit")
	public R update( LaborDO labor){
		laborService.update(labor);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("budget:labor:remove")
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
	@RequiresPermissions("budget:labor:batchRemove")
	public R remove(@RequestParam("ids[]") String[] laborIds){
		laborService.batchRemove(laborIds);
		return R.ok();
	}
	
}
