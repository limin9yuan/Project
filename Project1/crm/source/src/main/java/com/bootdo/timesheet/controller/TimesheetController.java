package com.bootdo.timesheet.controller;

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

import com.bootdo.timesheet.domain.TimesheetDO;
import com.bootdo.timesheet.service.TimesheetService;



import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 工时信息表
 * 
 * @author chgleepublic class TimesheetController {

 * @email 1992lcg@163.com
 * @date 2018-03-14 17:52:48
 */
 
@Controller
@RequestMapping("/timesheet/timesheet")
public class TimesheetController {
	@Autowired
	private TimesheetService timesheetService;
	
	@GetMapping()
	@RequiresPermissions("timesheet:timesheet:timesheet")
	String Timesheet(){
	    return "timesheet/timesheet/timesheet";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("timesheet:timesheet:timesheet")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TimesheetDO> timesheetList = timesheetService.list(query);
		int total = timesheetService.count(query);
		PageUtils pageUtils = new PageUtils(timesheetList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("timesheet:timesheet:add")
	String add(){
	    return "timesheet/timesheet/add";
	}

	@GetMapping("/edit/{timesheetId}")
	@RequiresPermissions("timesheet:timesheet:edit")
	String edit(@PathVariable("timesheetId") String timesheetId,Model model){
		TimesheetDO timesheet = timesheetService.get(timesheetId);
		model.addAttribute("timesheet", timesheet);
	    return "timesheet/timesheet/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("timesheet:timesheet:add")
	public R save( TimesheetDO timesheet){
		if(timesheetService.save(timesheet)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("timesheet:timesheet:edit")
	public R update( TimesheetDO timesheet){
		timesheetService.update(timesheet);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("timesheet:timesheet:remove")
	public R remove( String timesheetId){
		if(timesheetService.remove(timesheetId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("timesheet:timesheet:batchRemove")
	public R remove(@RequestParam("ids[]") String[] timesheetIds){
		timesheetService.batchRemove(timesheetIds);
		return R.ok();
	}
	
}
