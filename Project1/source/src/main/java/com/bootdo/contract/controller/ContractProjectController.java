package com.bootdo.contract.controller;

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

import com.bootdo.contract.domain.ContractProjectDO;
import com.bootdo.contract.service.ContractProjectService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 合同项目表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-07-25 13:58:59
 */
 
@Controller
@RequestMapping("/contract/project")
public class ContractProjectController {
	@Autowired
	private ContractProjectService contractProjectService;
	
	@GetMapping()
	@RequiresPermissions("system:project:project")
	String Project(){
	    return "system/project/project";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:project:project")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ContractProjectDO> projectList = contractProjectService.list(query);
		int total = contractProjectService.count(query);
		PageUtils pageUtils = new PageUtils(projectList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:project:add")
	String add(){
	    return "system/project/add";
	}

	@GetMapping("/edit/{contractProjectId}")
	@RequiresPermissions("system:project:edit")
	String edit(@PathVariable("contractProjectId") Integer contractProjectId,Model model){
		ContractProjectDO project = contractProjectService.get(contractProjectId);
		model.addAttribute("project", project);
	    return "system/project/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:project:add")
	public R save( ContractProjectDO project){
		if(contractProjectService.save(project)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:project:edit")
	public R update( ContractProjectDO project){
		contractProjectService.update(project);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:project:remove")
	public R remove( Integer contractProjectId){
		if(contractProjectService.remove(contractProjectId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:project:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] contractProjectIds){
		contractProjectService.batchRemove(contractProjectIds);
		return R.ok();
	}
	
}
