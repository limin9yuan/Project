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

import com.bootdo.sales.domain.BugCategoryDO;
import com.bootdo.sales.domain.RecordDO;
import com.bootdo.sales.domain.SalesProjectDO;
import com.bootdo.sales.service.BugCategoryService;
import com.bootdo.common.config.Constant;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * BUG分类信息表
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-11-29 10:13:20
 */
 
@Controller
@RequestMapping("/sales/bugCategory")
public class BugCategoryController extends BaseController {
	@Autowired
	private BugCategoryService bugCategoryService;
	
	@GetMapping()
	@RequiresPermissions("sales:bugCategory:bugCategory")
	String BugCategory(){
	    return "sales/bugCategory/bugCategory";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("sales:bugCategory:bugCategory")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<BugCategoryDO> bugCategoryList = bugCategoryService.list(query);
		int total = bugCategoryService.count(query);
		PageUtils pageUtils = new PageUtils(bugCategoryList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("sales:bugCategory:add")
	String add(){
	    return "sales/bugCategory/add";
	}
	@RequestMapping("/edit_ajax/{bugId}")
	@ResponseBody
	Map<String, Object> edit_ajax(@PathVariable("bugId") String bugId) {
		BugCategoryDO bugCategory = bugCategoryService.get(bugId);
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("bugCategory", bugCategory);
		return returnData;
	}
	@GetMapping("/edit/{bugId}")
	@RequiresPermissions("sales:bugCategory:edit")
	String edit(@PathVariable("bugId") String bugId,Model model){
		//BugCategoryDO bugCategory = bugCategoryService.get(bugId);
		model.addAttribute("bugId", bugId);
	    return "sales/bugCategory/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("sales:bugCategory:add")
	public R save( BugCategoryDO bugCategory){
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		bugCategory.setBugCreator(getUserId());
		bugCategory.setBugRecorder(getUserId());
		if(bugCategoryService.save(bugCategory)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sales:bugCategory:edit")
	public R update( BugCategoryDO bugCategory){
		bugCategory.setBugRecorder(getUserId());
		bugCategoryService.update(bugCategory);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("sales:bugCategory:remove")
	public R remove( String bugId){
		if(bugCategoryService.remove(bugId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("sales:bugCategory:batchRemove")
	public R remove(@RequestParam("ids[]") String[] bugIds){
		bugCategoryService.batchRemove(bugIds);
		return R.ok();
	}
	
}
