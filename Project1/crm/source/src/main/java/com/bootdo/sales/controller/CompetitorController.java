package com.bootdo.sales.controller;

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

import com.bootdo.sales.domain.CompetitorDO;
import com.bootdo.sales.service.CompetitorService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 竞争对手信息表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-12-11 14:31:23
 */
 
@Controller
@RequestMapping("/sales/competitor")
public class CompetitorController {
	@Autowired
	private CompetitorService competitorService;
	
	@GetMapping()
	@RequiresPermissions("sales:competitor:competitor")
	String Competitor(){
	    return "sales/companyCustomer/competitor";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("sales:competitor:competitor")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CompetitorDO> competitorList = competitorService.list(query);
		int total = competitorService.count(query);
		PageUtils pageUtils = new PageUtils(competitorList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("sales:competitor:add")
	String add(){
	    return "sales/companyCustomer/addCompe";
	}

	@GetMapping("/edit/{complaintId}")
	@RequiresPermissions("sales:competitor:edit")
	String edit(@PathVariable("complaintId") String complaintId,Model model){
		CompetitorDO competitor = competitorService.get(complaintId);
		model.addAttribute("competitor", competitor);
	    return "sales/companyCustomer/editCompe";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("sales:competitor:add")
	public R save( CompetitorDO competitor){
		if(competitorService.save(competitor)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sales:competitor:edit")
	public R update( CompetitorDO competitor){
		competitorService.update(competitor);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("sales:competitor:batchRemove")
	public R remove( String complaintId){
		if(competitorService.remove(complaintId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("sales:competitor:batchRemove")
	public R remove(@RequestParam("ids[]") String[] complaintIds){
		competitorService.batchRemove(complaintIds);
		return R.ok();
	}
	
}
