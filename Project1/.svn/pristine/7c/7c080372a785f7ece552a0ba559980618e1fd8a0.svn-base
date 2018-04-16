package com.bootdo.project.controller;


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

import com.bootdo.project.domain.ProductCategoryDO;
import com.bootdo.project.domain.ProjectDO;
import com.bootdo.project.service.ProductCategoryService;
import com.bootdo.common.config.Constant;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 产品分类信息表
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-11-29 11:15:42
 */
 
@Controller
@RequestMapping("/project/productCategory")
public class ProductCategoryController extends BaseController {
	@Autowired
	private ProductCategoryService productCategoryService;
	
	@GetMapping()
	@RequiresPermissions("project:productCategory:productCategory")
	String ProductCategory(){
	    return "project/productCategory/productCategory";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("project:productCategory:productCategory")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ProductCategoryDO> productCategoryList = productCategoryService.list(query);
		int total = productCategoryService.count(query);
		PageUtils pageUtils = new PageUtils(productCategoryList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("project:productCategory:add")
	String add(){
	    return "project/productCategory/add";
	}
	@RequestMapping("/edit_ajax/{productId}")
	@ResponseBody
	Map<String, Object> edit_ajax(@PathVariable("productId") String productId) {
		ProductCategoryDO productCategory = productCategoryService.get(productId);
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("productCategory", productCategory);
		return returnData;
	}
	@GetMapping("/edit/{productId}")
	@RequiresPermissions("project:productCategory:edit")
	String edit(@PathVariable("productId") String productId,Model model){
		//ProductCategoryDO productCategory = productCategoryService.get(productId);
		model.addAttribute("productId", productId);
	    return "project/productCategory/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("project:productCategory:add")
	public R save( ProductCategoryDO productCategory){
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		productCategory.setProductCreator(getUserId());
		productCategory.setProductRecorder(getUserId());
		if(productCategoryService.save(productCategory)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("project:productCategory:edit")
	public R update( ProductCategoryDO productCategory){
		productCategory.setProductRecorder(getUserId());
		productCategoryService.update(productCategory);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("project:productCategory:remove")
	public R remove( String productId){
		if(productCategoryService.remove(productId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("project:productCategory:batchRemove")
	public R remove(@RequestParam("ids[]") String[] productIds){
		productCategoryService.batchRemove(productIds);
		return R.ok();
	}
	
	@ResponseBody
	@GetMapping("/listDic")
	public List<DictDO> listByType() {
		// 查询列表数据
		Map<String, Object> map = new HashMap<>(16);
		map.put("type", "");
		List<DictDO> dictList = productCategoryService.listDic();
		return dictList;
	}
	
}
