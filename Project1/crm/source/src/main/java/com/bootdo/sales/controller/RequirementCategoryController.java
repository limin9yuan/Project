package com.bootdo.sales.controller;

import java.util.HashMap;
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

import com.bootdo.sales.domain.RecordDO;
import com.bootdo.sales.domain.RequirementCategoryDO;
import com.bootdo.sales.service.RequirementCategoryService;
import com.bootdo.common.config.Constant;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 需求分类信息表
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-11-29 14:20:38
 */
 
@Controller
@RequestMapping("/sales/requirementCategory")
public class RequirementCategoryController extends BaseController {
	@Autowired
	private RequirementCategoryService requirementCategoryService;
	
	@GetMapping()
	@RequiresPermissions("sales:requirementCategory:requirementCategory")
	String RequirementCategory(){
	    return "sales/requirementCategory/requirementCategory";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("sales:requirementCategory:requirementCategory")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<RequirementCategoryDO> requirementCategoryList = requirementCategoryService.list(query);
		int total = requirementCategoryService.count(query);
		PageUtils pageUtils = new PageUtils(requirementCategoryList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("sales:requirementCategory:add")
	String add(){
	    return "sales/requirementCategory/add";
	}
	@RequestMapping("/edit_ajax/{requirementId}")
	@ResponseBody
	Map<String, Object> edit_ajax(@PathVariable("requirementId") String requirementId) {
		RequirementCategoryDO requirementCategory = requirementCategoryService.get(requirementId);
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("requirementCategory", requirementCategory);
		return returnData;
	}
	@GetMapping("/edit/{requirementId}")
	@RequiresPermissions("sales:requirementCategory:edit")
	String edit(@PathVariable("requirementId") String requirementId,Model model){
		//RequirementCategoryDO requirementCategory = requirementCategoryService.get(requirementId);
		model.addAttribute("requirementId", requirementId);
	    return "sales/requirementCategory/edit";                                 
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("sales:requirementCategory:add")
	public R save( RequirementCategoryDO requirementCategory){
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		requirementCategory.setRequirementCreator(getUserId());
		requirementCategory.setRequirementRecorder(getUserId());
		if(requirementCategoryService.save(requirementCategory)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sales:requirementCategory:edit")
	public R update( RequirementCategoryDO requirementCategory){
		requirementCategory.setRequirementRecorder(getUserId());
		requirementCategoryService.update(requirementCategory);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("sales:requirementCategory:remove")
	public R remove( String requirementId){
		if(requirementCategoryService.remove(requirementId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("sales:requirementCategory:batchRemove")
	public R remove(@RequestParam("ids[]") String[] requirementIds){
		requirementCategoryService.batchRemove(requirementIds);
		return R.ok();
	}
	@ResponseBody
	@GetMapping("/listDic")
	public List<DictDO> listByType() {
		// 查询列表数据
		Map<String, Object> map = new HashMap<>(16);
		map.put("type", "");
		List<DictDO> dictList = requirementCategoryService.listDic();
		return dictList;
	}
	
}
