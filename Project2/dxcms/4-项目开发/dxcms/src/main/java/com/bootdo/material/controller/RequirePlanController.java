package com.bootdo.material.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-08-21 16:46:41
 */
 
@Controller
@RequestMapping("/requirementPlan/requirementPlan")
public class RequirePlanController {
//	@Autowired
//	private RequirementPlanService requirementPlanService;
	
	@GetMapping()
	@RequiresPermissions("requirementPlan:requirementPlan")
	String RequirementPlan(){
	    return "material/requirementPlan/requirementPlan";
	}

	@GetMapping("/add")
	@RequiresPermissions("requirementPlan:add")
	String addRequirePlan(){
		return "material/requirementPlan/add";
	}

	@GetMapping("/addMore")
	@RequiresPermissions("requirementPlan:add")
	String addMore(){
		return "material/requirementPlan/addMore";
	}

	@GetMapping("/nextStep/{materilaCode}")
	@RequiresPermissions("requirementPlan:add")
	String nextStep(@PathVariable("materilaCode") String materilaCode, Model model){
		model.addAttribute("materilaCode",materilaCode);

		return "material/requirementPlan/nextStep";
	}

	@ResponseBody
	@GetMapping("/getMaterialDetailByCode/{code}")
	@RequiresPermissions("requirementPlan:add")
	Map<String, Object> getMaterialDetailByCode(@PathVariable("code") String code){
		List<Map<String, Object>> getRequirePlanDetailList = new ArrayList<>();//调用接口
		String codeArray[] = code.split(",");
		//做测试数据 调用接口前使用 begin
		for (int i = 0; i < codeArray.length; i++) {
			Map<String, Object> requireMap = new HashMap<>();
			requireMap.put("requirePlanid", codeArray[i]);
			requireMap.put("materialName", "物资A" + i);
			requireMap.put("materilaCode", "物资编码" + i);
			requireMap.put("specification", "规格" + i);
			requireMap.put("materialUnitName", "单位" + i);
			requireMap.put("materialSubArray", "包装物料" + i);
			requireMap.put("requireQty","25345");
			requireMap.put("purchaseQty","456");
			requireMap.put("stockQty", "47");
			requireMap.put("reserveQty", "57657");
			requireMap.put("onwayQty", "878");
			requireMap.put("budgetQty", "8768");
			requireMap.put("referencePrice", "789");
			requireMap.put("budgetPrice", "8908");
			requireMap.put("referenceAmount", "34");
			requireMap.put("requireDate","2018/8/27");
			requireMap.put("arriveDate", "2018/8/27");
			requireMap.put("purchaserName", "张三");
			requireMap.put("description", "sb");
			getRequirePlanDetailList.add(requireMap);
		}
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("getRequirePlanDetailList", getRequirePlanDetailList);

		return returnData;

	}

	@ResponseBody
	@GetMapping("/getRequirePlanDetail")
	@RequiresPermissions("requirementPlan:add")
	Map<String, Object> getRequirePlanDetail(@RequestParam("code") String code){
		List<Map<String, Object>> getRequirePlanDetailList = new ArrayList<>();//调用接口
		String codeArray[] = code.split(",");
		//做测试数据 调用接口前使用 begin
		for (int i = 0; i < codeArray.length; i++) {
			Map<String, Object> requireMap = new HashMap<>();
			requireMap.put("requirePlanid", codeArray[i]);
			requireMap.put("materialName", "物资A" + i);
			requireMap.put("materilaCode", "物资编码" + i);
			requireMap.put("specification", "规格" + i);
			requireMap.put("materialUnitName", "单位" + i);
			requireMap.put("materialSubArray", "包装物料" + i);
			requireMap.put("requireQty","25345");
			requireMap.put("purchaseQty","456");
			requireMap.put("stockQty", "47");
			requireMap.put("reserveQty", "57657");
			requireMap.put("onwayQty", "878");
			requireMap.put("budgetQty", "8768");
			requireMap.put("referencePrice", "789");
			requireMap.put("budgetPrice", "8908");
			requireMap.put("referenceAmount", "34");
			requireMap.put("requireDate","2018/8/27");
			requireMap.put("arriveDate", "2018/8/27");
			requireMap.put("purchaserName", "张三");
			requireMap.put("description", "sb");
			getRequirePlanDetailList.add(requireMap);
		}
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("getRequirePlanDetailList", getRequirePlanDetailList);

		return returnData;

	}

	@ResponseBody
	@GetMapping("/requirePlanDetailList")
	@RequiresPermissions("requirementPlan:requirementPlan")
	public PageUtils requirePlanDetailList(@RequestParam Map<String, Object> params){
		//查询列表数据
//		Query query = new Query(params);
		List<Map<String, Object>> requirementPlanDetailList = new ArrayList<>();//调用接口
		for(int i=1;i<11;i++){
			//做测试数据 调用接口前使用 begin
			Map<String, Object> requireMap = new HashMap<>();
			requireMap.put("materialName","物资A"+i);
			requireMap.put("materilaCode","物资编码"+i);
			requireMap.put("brand","型号"+i);
			requireMap.put("materialUnitName","单位"+i);
			requireMap.put("requireQty","1000");
			requireMap.put("requireDept","需求部门"+i);
			requireMap.put("requireDate","2018/8/27");
			requireMap.put("createDate","2018/8/20");
			requireMap.put("remark","sb");
			requirementPlanDetailList.add(requireMap);
		}
		int total = 20;//调用接口
		PageUtils pageUtils = new PageUtils(requirementPlanDetailList, total);
		return pageUtils;
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("requirementPlan:requirementPlan")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<Map<String, Object>> requirementPlanList = new ArrayList<>();//调用接口
		for(int i=1;i<11;i++){
			//做测试数据 调用接口前使用 begin
			Map<String, Object> requirePlanMap = new HashMap<>();
			requirePlanMap.put("status","状态"+i);
			requirePlanMap.put("planNo","编号"+i);
			requirePlanMap.put("name","名称"+i);
			requirePlanMap.put("authorDept","编制部门"+i);
			requirePlanMap.put("purchaseDept","采购部门"+i);
			requirePlanMap.put("budgetMoney","1000");
			requirePlanMap.put("totalMoney","2000");
			requirePlanMap.put("authorUser","编制人"+i);
			requirePlanMap.put("createDate","2018/8/27");
			requirementPlanList.add(requirePlanMap);
		}
		int total = 20;//调用接口
		PageUtils pageUtils = new PageUtils(requirementPlanList, total);
		return pageUtils;
	}

//	@GetMapping("/edit/{id}")
//	@RequiresPermissions("requirementPlan:edit")
//	String edit(@PathVariable("id") Long id,Model model){
//		RequirePlanModel requirementPlan = requirementPlanService.get(id);
//		model.addAttribute("requirementPlan", requirementPlan);
//	    return "material/requirementPlan/edit";
//	}
//
//	/**
//	 * 保存
//	 */
//	@ResponseBody
//	@PostMapping("/save")
//	@RequiresPermissions("requirementPlan:requirementPlan:add")
//	public R save( RequirePlanModel requirementPlan){
//		if(requirementPlanService.save(requirementPlan)>0){
//			return R.ok();
//		}
//		return R.error();
//	}
//	/**
//	 * 修改
//	 */
//	@ResponseBody
//	@RequestMapping("/update")
//	@RequiresPermissions("system:requirementPlan:edit")
//	public R update( RequirePlanModel requirementPlan){
//		requirementPlanService.update(requirementPlan);
//		return R.ok();
//	}
//
//	/**
//	 * 删除
//	 */
//	@PostMapping( "/remove")
//	@ResponseBody
//	@RequiresPermissions("requirementPlan:remove")
//	public R remove( Long id){
//		if(requirementPlanService.remove(id)>0){
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
//	@RequiresPermissions("requirementPlan:batchRemove")
//	public R remove(@RequestParam("ids[]") Long[] ids){
//		requirementPlanService.batchRemove(ids);
//		return R.ok();
//	}
	
}
