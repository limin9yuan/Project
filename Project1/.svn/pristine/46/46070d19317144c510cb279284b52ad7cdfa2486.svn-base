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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.project.domain.ProjectDeptDO;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2018-01-24 17:08:45
 */
 
@Controller
@RequestMapping("/project/projectDept")
public class ProjectDeptController {
//	@Autowired
//	private ProjectDeptService projectDeptService;
//	
//	@GetMapping()
//	@RequiresPermissions("project:projectDept:projectDept")
//	String ProjectDept(){
//	    return "project/projectDept/projectDept";
//	}
//	
//	@ResponseBody
//	@GetMapping("/list")
//	@RequiresPermissions("project:projectDept:projectDept")
//	public PageUtils list(@RequestParam Map<String, Object> params){
//		//查询列表数据
//        Query query = new Query(params);
//		List<ProjectDeptDO> projectDeptList = projectDeptService.list(query);
//		int total = projectDeptService.count(query);
//		PageUtils pageUtils = new PageUtils(projectDeptList, total);
//		return pageUtils;
//	}
//	
//	@GetMapping("/add")
//	@RequiresPermissions("project:projectDept:add")
//	String add(){
//	    return "project/projectDept/add";
//	}
//
//	@GetMapping("/edit/{projectId}")
//	@RequiresPermissions("project:projectDept:edit")
//	String edit(@PathVariable("projectId") String projectId,Model model){
//		ProjectDeptDO projectDept = projectDeptService.get(projectId);
//		model.addAttribute("projectDept", projectDept);
//	    return "project/projectDept/edit";
//	}
//	
//	/**
//	 * 保存
//	 */
//	@ResponseBody
//	@PostMapping("/save")
//	@RequiresPermissions("project:projectDept:add")
//	public R save( ProjectDeptDO projectDept){
//		if(projectDeptService.save(projectDept)>0){
//			return R.ok();
//		}
//		return R.error();
//	}
//	/**
//	 * 修改
//	 */
//	@ResponseBody
//	@RequestMapping("/update")
//	@RequiresPermissions("project:projectDept:edit")
//	public R update( ProjectDeptDO projectDept){
//		projectDeptService.update(projectDept);
//		return R.ok();
//	}
//	
//	/**
//	 * 删除
//	 */
//	@PostMapping( "/remove")
//	@ResponseBody
//	@RequiresPermissions("project:projectDept:remove")
//	public R remove( String projectId){
//		if(projectDeptService.remove(projectId)>0){
//		return R.ok();
//		}
//		return R.error();
//	}
//	
//	/**
//	 * 删除
//	 */
//	@PostMapping( "/batchRemove")
//	@ResponseBody
//	@RequiresPermissions("project:projectDept:batchRemove")
//	public R remove(@RequestParam("ids[]") String[] projectIds){
//		projectDeptService.batchRemove(projectIds);
//		return R.ok();
//	}
//	
}
