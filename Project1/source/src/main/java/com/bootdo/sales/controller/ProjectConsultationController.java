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

import com.bootdo.sales.domain.ProjectConsultationDO;
import com.bootdo.sales.service.ProjectConsultationService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 客户项目咨询信息表
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-12-27 11:53:50
 */
 
@Controller
@RequestMapping("/sales/projectConsultation")
public class ProjectConsultationController {
	@Autowired
	private ProjectConsultationService projectConsultationService;
	
	@GetMapping()
	@RequiresPermissions("sales:projectConsultation:projectConsultation")
	String ProjectConsultation(){
	    return "sales/projectConsultation/projectConsultation";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("sales:projectConsultation:projectConsultation")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
				if (params.get("consultationId") != null
						&& !"".equals(params.get("consultationId"))) {
					params.put("consultationId", "%" + (String) params.get("consultationId")
							+ "%");
				}
        Query query = new Query(params);
		List<ProjectConsultationDO> projectConsultationList = projectConsultationService.list(query);
		int total = projectConsultationService.count(query);
		PageUtils pageUtils = new PageUtils(projectConsultationList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("sales:projectConsultation:add")
	String add(){
	    return "sales/projectConsultation/add";
	}

	@GetMapping("/edit/{consultationId}")
	@RequiresPermissions("sales:projectConsultation:edit")
	String edit(@PathVariable("consultationId") String consultationId,Model model){
		ProjectConsultationDO projectConsultation = projectConsultationService.get(consultationId);
		model.addAttribute("projectConsultation", projectConsultation);
	    return "sales/projectConsultation/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("sales:projectConsultation:add")
	public R save( ProjectConsultationDO projectConsultation){
		if(projectConsultationService.save(projectConsultation)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sales:projectConsultation:edit")
	public R update( ProjectConsultationDO projectConsultation){
		projectConsultationService.update(projectConsultation);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("sales:projectConsultation:remove")
	public R remove( String consultationId){
		if(projectConsultationService.remove(consultationId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("sales:projectConsultation:batchRemove")
	public R remove(@RequestParam("ids[]") String[] consultationIds){
		projectConsultationService.batchRemove(consultationIds);
		return R.ok();
	}
	
}
