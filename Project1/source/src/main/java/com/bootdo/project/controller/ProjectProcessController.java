package com.bootdo.project.controller;

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

import com.bootdo.project.domain.ProjectProcessDO;
import com.bootdo.project.service.ProjectProcessService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-06-07 14:30:09
 */
 
@Controller
@RequestMapping("/project/process")
public class ProjectProcessController {
	@Autowired
	private ProjectProcessService projectProcessService;
	
	@GetMapping()
	@RequiresPermissions("project:process")
	String Process(){
	    return "/project/process";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:process:process")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ProjectProcessDO> processList = projectProcessService.list(query);
		int total = projectProcessService.count(query);
		PageUtils pageUtils = new PageUtils(processList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:process:add")
	String add(){
	    return "system/process/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:process:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		ProjectProcessDO process = projectProcessService.get(id);
		model.addAttribute("process", process);
	    return "system/process/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:process:add")
	public R save( ProjectProcessDO process){
		if(projectProcessService.save(process)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:process:edit")
	public R update( ProjectProcessDO process){
		projectProcessService.update(process);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:process:remove")
	public R remove( Integer id){
		if(projectProcessService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:process:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		projectProcessService.batchRemove(ids);
		return R.ok();
	}
	
}
