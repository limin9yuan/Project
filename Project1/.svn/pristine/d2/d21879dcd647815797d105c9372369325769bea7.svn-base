package com.bootdo.workDay.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/workDay/workDay")
public class WorkDayController {
	@Autowired
	private WorkDayService workDayService;
	
	@GetMapping()
	@RequiresPermissions("workDay:workDay:workDay")
	String Day(){
	    return "/workDay/workDay";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("workDay:workDay:workDay")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<WorkDayDO> dayList = workDayService.list(query);
		int total = workDayService.count(query);
		PageUtils pageUtils = new PageUtils(dayList, total);
		return pageUtils;
	}

	@ResponseBody
	@GetMapping("/listOfficeDay")
	@RequiresPermissions("workDay:workDay:workDay")
	public PageUtils listOfficeDay(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<WorkDayDO> dayList = workDayService.listOfficeDay(query);
		int total = workDayService.countOfficeDay(query);
		PageUtils pageUtils = new PageUtils(dayList, total);
		return pageUtils;
	}
	
	@GetMapping("/addOfficeDay")
	@RequiresPermissions("workDay:workDay:addOfficeDay")
	String addOfficeDay(){
	    return "/workDay/addOfficeDay";
	}

	@GetMapping("/add")
	@RequiresPermissions("workDay:workDay:add")
	String add(){
		return "/workDay/add";
	}

	@GetMapping("/pickYear")
	@RequiresPermissions("workDay:workDay:addWorkDay")
	String pickYear(){
		return "/workDay/pickYear";
	}

	@GetMapping("/edit/{holidayId}")
	@RequiresPermissions("workDay:workDay:edit")
	String edit(@PathVariable("holidayId") Integer id,Model model){
		WorkDayDO day = workDayService.get(id);
		model.addAttribute("holiday", day);
	    return "/workDay/edit";
	}

	@GetMapping("/editOfficeDay/{officeId}")
	@RequiresPermissions("workDay:workDay:editOfficeDay")
	String editOfficeDay(@PathVariable("officeId") Integer id,Model model){
		WorkDayDO day = workDayService.getOfficeDay(id);
		model.addAttribute("office", day);
		return "/workDay/editOfficeDay";
	}

	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("workDay:workDay:add")
	public R saveHoliday( WorkDayDO day){
		if(workDayService.saveHoliday(day)>0){
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/saveOfficeDay")
	@RequiresPermissions("workDay:workDay:addOfficeDay")
	public R saveOfficeDay( WorkDayDO day){
		if(workDayService.saveOfficeDay(day)>0){
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/saveWorkDay/{pickYear}")
	@RequiresPermissions("workDay:workDay:addWorkDay")
	public R saveWorkDay(@PathVariable("pickYear") String pickYear){
		WorkDayDO day =new WorkDayDO();
		day.setCurrentYear(pickYear);
		if(workDayService.removeWorkDay(pickYear)>0 && workDayService.saveWorkDay(day)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("workDay:workDay:edit")
	public R update( WorkDayDO day){
		workDayService.update(day);
		return R.ok();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/updateOfficeDay")
	@RequiresPermissions("workDay:workDay:editOfficeDay")
	public R updateOfficeDay( WorkDayDO day){
		workDayService.updateOfficeDay(day);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("workDay:workDay:remove")
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
	@RequiresPermissions("workDay:workDay:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		workDayService.batchRemove(ids);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/removeOfficeDay")
	@ResponseBody
	@RequiresPermissions("workDay:workDay:removeOfficeDay")
	public R removeOfficeDay( Integer id){
		if(workDayService.removeOfficeDay(id)>0){
			return R.ok();
		}
		return R.error();
	}

//	/**
//	 * 删除
//	 */
//	@PostMapping( "/removeWorkDay")
//	@ResponseBody
//	@RequiresPermissions("workDay:workDay:addWorkDay")
//	public R removeWorkDay( Integer id){
//		if(workDayService.removeWorkDay(id)>0){
//			return R.ok();
//		}
//		return R.error();
//	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemoveOfficeDay")
	@ResponseBody
	@RequiresPermissions("workDay:workDay:batchRemoveOfficeDay")
	public R batchRemoveOfficeDay(@RequestParam("ids[]") Integer[] ids){
		workDayService.batchRemoveOfficeDay(ids);
		return R.ok();
	}
	
}
