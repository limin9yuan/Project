package com.bootdo.common.controller;

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

import com.bootdo.common.domain.FieldDO;
import com.bootdo.common.service.FieldService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-05-28 14:33:34
 */
 
@Controller
@RequestMapping("/system/field")
public class FieldController {
	@Autowired
	private FieldService fieldService;
	
	@GetMapping()
	@RequiresPermissions("system:field:field")
	String Field(){
	    return "system/field/field";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:field:field")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<FieldDO> fieldList = fieldService.list(query);
		int total = fieldService.count(query);
		PageUtils pageUtils = new PageUtils(fieldList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:field:add")
	String add(){
	    return "system/field/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:field:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		FieldDO field = fieldService.get(id);
		model.addAttribute("field", field);
	    return "system/field/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:field:add")
	public R save( FieldDO field){
		if(fieldService.save(field)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:field:edit")
	public R update( FieldDO field){
		fieldService.update(field);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:field:remove")
	public R remove( Integer id){
		if(fieldService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:field:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		fieldService.batchRemove(ids);
		return R.ok();
	}
	
}
