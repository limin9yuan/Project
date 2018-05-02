package com.bootdo.workDay.controller;

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

import com.bootdo.workDay.domain.WorkDayDO;
import com.bootdo.workDay.service.WorkDayService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-05-02 16:57:14
 */
 
@Controller
@RequestMapping("/system/day")
public class WorkDayController {
	@Autowired
	private WorkDayService workDayService;
	
	@GetMapping()
	@RequiresPermissions("system:day:day")
	String Day(){
	    return "system/day/day";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:day:day")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<WorkDayDO> dayList = workDayService.list(query);
		int total = workDayService.count(query);
		PageUtils pageUtils = new PageUtils(dayList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:day:add")
	String add(){
	    return "system/day/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:day:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		WorkDayDO day = workDayService.get(id);
		model.addAttribute("day", day);
	    return "system/day/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:day:add")
	public R save( WorkDayDO day){
		if(workDayService.save(day)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:day:edit")
	public R update( WorkDayDO day){
		workDayService.update(day);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:day:remove")
	public R remove( Integer id){
		if(workDayService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:day:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		workDayService.batchRemove(ids);
		return R.ok();
	}
	
}
