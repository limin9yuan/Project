package com.bootdo.timesheet.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import com.bootdo.timesheet.domain.TimesheetDO;
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

import com.bootdo.timesheet.service.TimesheetService;
import com.bootdo.common.controller.BaseController;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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
public class TimesheetController extends BaseController {
	@Autowired
	private TimesheetService timesheetService;

	
	@GetMapping()
	@RequiresPermissions("timesheet:timesheet:timesheet")
	String Timesheet(Model model){
		SimpleDateFormat simdf = new SimpleDateFormat("yyyy-MM-dd");//日期方法

		Calendar cal = Calendar.getInstance();
		String date8=simdf.format(cal.getTime());


		cal.set(cal.DAY_OF_WEEK, cal.MONDAY);
		String date11 = simdf.format(cal.getTime());
		java.sql.Date date1=java.sql.Date.valueOf(date11);


		Date date=new Date();
		cal.add(cal.DATE,+1);
		date=cal.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String date2 = formatter.format(date);


		cal.add(cal.DATE,+1);
		date=cal.getTime();
		String date3 = formatter.format(date);


		cal.add(cal.DATE,+1);
		date=cal.getTime();
		String date4 = formatter.format(date);


		cal.add(cal.DATE,+1);
		date=cal.getTime();
		String date5 = formatter.format(date);


		cal.add(cal.DATE,+1);
		date=cal.getTime();
		String date6 = formatter.format(date);

		cal.add(cal.DATE,+1);
		date=cal.getTime();
		String date7 = formatter.format(date);



		model.addAttribute("Date1", date1);
		model.addAttribute("Date2", date2);
		model.addAttribute("Date3", date3);
		model.addAttribute("Date4", date4);
		model.addAttribute("Date5", date5);
		model.addAttribute("Date6", date6);
		model.addAttribute("Date7", date7);
		model.addAttribute("Date8", date8);






		return "timesheet/timesheet/timesheet";
	}


	@ResponseBody
	@GetMapping("/list1")
	@RequiresPermissions("timesheet:timesheet:timesheet")
	public PageUtils list1(@RequestParam Map<String, Object> params){

		if (params.get("timeMin") != null && params.get("timeMin") != "") {
			params.put("timeMin", params.get("timeMin") + " 00:00:00");
		}
		if (params.get("timeMax") != null && params.get("timeMax") != "") {
			params.put("timeMax", params.get("timeMax") + " 23:59:59");
		}


		SimpleDateFormat simdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar cal = Calendar.getInstance();

		String date8=simdf.format(cal.getTime());
		java.sql.Date dat8=java.sql.Date.valueOf(date8);
		cal.set(cal.DAY_OF_WEEK, cal.MONDAY);
		String date11 = simdf.format(cal.getTime());
		java.sql.Date date1=java.sql.Date.valueOf(date11);


		Date date=new Date();
		cal.add(cal.DATE,+1);
		date=cal.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String date2 = formatter.format(date);
		java.sql.Date dat2=java.sql.Date.valueOf(date2);

		cal.add(cal.DATE,+1);
		date=cal.getTime();
		String date3 = formatter.format(date);
		java.sql.Date dat3=java.sql.Date.valueOf(date3);

		cal.add(cal.DATE,+1);
		date=cal.getTime();
		String date4 = formatter.format(date);
		java.sql.Date dat4=java.sql.Date.valueOf(date4);

		cal.add(cal.DATE,+1);
		date=cal.getTime();
		String date5 = formatter.format(date);
		java.sql.Date dat5=java.sql.Date.valueOf(date5);

		cal.add(cal.DATE,+1);
		date=cal.getTime();
		String date6 = formatter.format(date);
		java.sql.Date dat6=java.sql.Date.valueOf(date6);

		Date date13=new Date();
		cal.add(cal.DATE,+1);
		date13=cal.getTime();






		params.put("date1",date1);
		params.put("date2",dat2);
		params.put("date3",dat3);
		params.put("date4",dat4);
		params.put("date5",dat5);
		params.put("date6",dat6);

		params.put("date7",date13);
		params.put("date8",dat8);

		//查询列表数据
		params.put("uerId",getUserId());
		Query query = new Query(params);
		List<TimesheetDO> timesheetList = timesheetService.list(query);

		int total = timesheetService.count(query);
		PageUtils pageUtils = new PageUtils(timesheetList, total);
		return pageUtils;
	}


	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("timesheet:timesheet:timesheet")
	public PageUtils list(@RequestParam Map<String, Object> params){

		if (params.get("timeMin") != null && !"".equals(params.get("timeMin"))) {
           Date da1=new Date();

			String d1=(String)(params.get("timeMin"));

			da1 = java.sql.Date.valueOf(d1);

			SimpleDateFormat simdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(da1);




			cal.add(cal.DATE, +1);
			Date date = new Date();
			date = cal.getTime();
			String date2 = simdf.format(date);
			java.sql.Date dat2 = java.sql.Date.valueOf(date2);



			cal.add(cal.DATE, +1);
			date = cal.getTime();
			String date3 = simdf.format(date);
			java.sql.Date dat3 = java.sql.Date.valueOf(date3);

			cal.add(cal.DATE, +1);
			date = cal.getTime();
			String date4 = simdf.format(date);
			java.sql.Date dat4 = java.sql.Date.valueOf(date4);

			cal.add(cal.DATE, +1);
			date = cal.getTime();
			String date5 = simdf.format(date);
			java.sql.Date dat5 = java.sql.Date.valueOf(date5);

			cal.add(cal.DATE, +1);
			date = cal.getTime();
			String date6 = simdf.format(date);
			java.sql.Date dat6 = java.sql.Date.valueOf(date6);

			cal.add(cal.DATE, +1);
			date = cal.getTime();
			String date7 = simdf.format(date);
			java.sql.Date dat7 = java.sql.Date.valueOf(date7);



			params.put("date1", da1);
			params.put("date2", dat2);
			params.put("date3", dat3);
			params.put("date4", dat4);
			params.put("date5", dat5);
			params.put("date6", dat6);
			params.put("date7", dat7);




		}
        else {

			SimpleDateFormat simdf = new SimpleDateFormat("yyyy-MM-dd");

			Calendar cal = Calendar.getInstance();

			String date8 = simdf.format(cal.getTime());
			java.sql.Date dat8 = java.sql.Date.valueOf(date8);
			cal.set(cal.DAY_OF_WEEK, cal.MONDAY);
			String date11 = simdf.format(cal.getTime());
			java.sql.Date date1 = java.sql.Date.valueOf(date11);


			Date date = new Date();
			cal.add(cal.DATE, +1);
			date = cal.getTime();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String date2 = formatter.format(date);
			java.sql.Date dat2 = java.sql.Date.valueOf(date2);

			cal.add(cal.DATE, +1);
			date = cal.getTime();
			String date3 = formatter.format(date);
			java.sql.Date dat3 = java.sql.Date.valueOf(date3);

			cal.add(cal.DATE, +1);
			date = cal.getTime();
			String date4 = formatter.format(date);
			java.sql.Date dat4 = java.sql.Date.valueOf(date4);

			cal.add(cal.DATE, +1);
			date = cal.getTime();
			String date5 = formatter.format(date);
			java.sql.Date dat5 = java.sql.Date.valueOf(date5);

			cal.add(cal.DATE, +1);
			date = cal.getTime();
			String date6 = formatter.format(date);
			java.sql.Date dat6 = java.sql.Date.valueOf(date6);

			Date date13 = new Date();
			cal.add(cal.DATE, +1);
			date13 = cal.getTime();


			params.put("date1", date1);
			params.put("date2", dat2);
			params.put("date3", dat3);
			params.put("date4", dat4);
			params.put("date5", dat5);
			params.put("date6", dat6);

			params.put("date7", date13);
			params.put("date8", dat8);
		}





//
//		查询列表数据
		params.put("uerId",getUserId());
        Query query = new Query(params);
		List<TimesheetDO> timesheetList = timesheetService.list(query);

		int total = timesheetService.count(query);
		PageUtils pageUtils = new PageUtils(timesheetList, total);
		return pageUtils;
	}


	@GetMapping("/getlist/{timeMin}")
	@ResponseBody
	Map<String, Object> getlist(@PathVariable("timeMin") String timeMin) {






			Date da1 = new Date();
			da1 = java.sql.Date.valueOf(timeMin);
		SimpleDateFormat simdf = new SimpleDateFormat("yyyy-MM-dd");
		String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
		Calendar cal = Calendar.getInstance();
		cal.setTime(da1);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		String week1=weekDays[w];








			cal.add(cal.DATE, +1);
			Date date = new Date();
			date = cal.getTime();
			String date2 = simdf.format(date);
			java.sql.Date dat2 = java.sql.Date.valueOf(date2);


		cal.setTime(dat2);
		 w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		String week2=weekDays[w];


			cal.add(cal.DATE, +1);
			date = cal.getTime();
			String date3 = simdf.format(date);
			java.sql.Date dat3 = java.sql.Date.valueOf(date3);

		cal.setTime(dat3);
		w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		String week3=weekDays[w];

			cal.add(cal.DATE, +1);
			date = cal.getTime();
			String date4 = simdf.format(date);
			java.sql.Date dat4 = java.sql.Date.valueOf(date4);

		cal.setTime(dat4);
		w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		String week4=weekDays[w];

			cal.add(cal.DATE, +1);
			date = cal.getTime();
			String date5 = simdf.format(date);
			java.sql.Date dat5 = java.sql.Date.valueOf(date5);

		cal.setTime(dat5);
		w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		String week5=weekDays[w];

			cal.add(cal.DATE, +1);
			date = cal.getTime();
			String date6 = simdf.format(date);
			java.sql.Date dat6 = java.sql.Date.valueOf(date6);

		cal.setTime(dat6);
		w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		String week6=weekDays[w];

			cal.add(cal.DATE, +1);
			date = cal.getTime();
			String date7 = simdf.format(date);
			java.sql.Date dat7 = java.sql.Date.valueOf(date7);

		cal.setTime(dat7);
		w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		String week7=weekDays[w];


			Map<String, Object> returnData = new HashMap<String, Object>();

			returnData.put("date11", da1+week1);
			returnData.put("dat2", dat2+week2);
			returnData.put("dat3", dat3+week3);
			returnData.put("dat4", dat4+week4);
			returnData.put("dat5", dat5+week5);
			returnData.put("dat6", dat6+week6);
			returnData.put("dat7", dat7+week7);

			returnData.put("pd1",da1);
		 	returnData.put("pd2",dat2);
			returnData.put("pd3",dat3);
			returnData.put("pd4",dat4);
			returnData.put("pd5",dat5);
			returnData.put("pd6",dat6);
			returnData.put("pd7",dat7);


			return returnData;
		}












//	//ajax修改绑定数据
//	@RequestMapping("/edit_ajax/{timesheetId}")
//	@ResponseBody
//	Map<String, Object> edit_ajax(@PathVariable("timesheetId") String timesheetId) {
//		TimesheetDO timeSheet = timesheetService.get(timesheetId);
//		Map<String, Object> returnData = new HashMap<String, Object>();
//		returnData.put("timeSheet", timeSheet);
//		return returnData;
//	}
	@GetMapping("/add")
	@RequiresPermissions("timesheet:timesheet:add")
	String add(){



	    return "timesheet/timesheet/add";
	}
	@GetMapping("/add1")
	@RequiresPermissions("timesheet:timesheet:add1")
	String add1(){



		return "timesheet/timesheet/add1";
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
