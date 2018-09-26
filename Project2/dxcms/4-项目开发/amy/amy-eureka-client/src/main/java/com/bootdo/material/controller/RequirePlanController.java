package com.bootdo.material.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import com.dx.client.model.purchase.RequirePlanBean;
import com.dx.client.model.purchase.RequirePlanItemBean;
import com.dx.service.purchase.service.api.IRequirePlanService;
import com.github.pagehelper.PageInfo;
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
import org.wxcl.amy.utils.common.ResultMsg;

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
	@Autowired
	private IRequirePlanService requirePlanService;
	
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

	@GetMapping("/check/{planNo}")
	@RequiresPermissions("requirementPlan:check")
	String check(@PathVariable("planNo") String planNo, Model model){
		String stringDate = "2018-08-27";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = dateFormat.parse(stringDate);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		ResultMsg rm = requirePlanService.primary(planNo);
		RequirePlanBean requirePlanModel = new RequirePlanBean();
		requirePlanModel.setId(planNo);
		requirePlanModel.setName("2018年8月采购申请");
		requirePlanModel.setBusinessDate(date);
		requirePlanModel.setPurchaseDeptId("1");
		requirePlanModel.setPurchaseDeptName("采购部门1");
		requirePlanModel.setAuthorUserId("111");
		requirePlanModel.setAuthorUserName("张三");
		requirePlanModel.setCreateDate(date);
		requirePlanModel.setRemark("sb");

		rm = new ResultMsg();
		rm.setData(requirePlanModel);
		model.addAttribute("requirePlanModel",rm.getData());

		return "material/requirementPlan/check";
	}

	@RequestMapping("/check_ajax/{planNo}")
	@ResponseBody
	Map<String, Object> check_ajax(@PathVariable("planNo") String planNo) {
		List<Map<String, Object>> checkList = new ArrayList<>();//调用接口
		//做测试数据 调用接口前使用 begin
		for (int i = 1; i < 6; i++) {
			Map<String, Object> requireMap = new HashMap<>();
			requireMap.put("requirePlanid", "物资编码" + i);
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
			checkList.add(requireMap);
		}
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("checkList", checkList);
		return returnData;
	}

	@GetMapping("/edit/{planNo}")
	@RequiresPermissions("requirementPlan:edit")
	String edit(@PathVariable("planNo") String planNo, Model model){
		String stringDate = "2018-08-27";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = dateFormat.parse(stringDate);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		ResultMsg rm = requirePlanService.primary(planNo);
		RequirePlanBean requirePlanModel = new RequirePlanBean();
		requirePlanModel.setId(planNo);
		requirePlanModel.setName("2018年8月采购申请");
		requirePlanModel.setBusinessDate(date);
		requirePlanModel.setPurchaseDeptId("1");
		requirePlanModel.setPurchaseDeptName("采购部门1");
		requirePlanModel.setAuthorUserId("111");
		requirePlanModel.setAuthorUserName("张三");
		requirePlanModel.setCreateDate(date);
		requirePlanModel.setRemark("sb");

		rm = new ResultMsg();
		rm.setData(requirePlanModel);
		model.addAttribute("requirePlanModel",rm.getData());

		return "material/requirementPlan/edit";
	}

	@RequestMapping("/edit_ajax/{planNo}")
	@ResponseBody
	Map<String, Object> edit_ajax(@PathVariable("planNo") String planNo) {
		List<Map<String, Object>> editList = new ArrayList<>();//调用接口
		//做测试数据 调用接口前使用 begin
		for (int i = 1; i < 6; i++) {
			Map<String, Object> requireMap = new HashMap<>();
			requireMap.put("requirePlanid", "物资编码" + i);
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
			editList.add(requireMap);
		}
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("editList", editList);
		return returnData;
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
		List ml = new ArrayList();
		ml.add(code);
		ResultMsg rsm = requirePlanService.createItems(ml);
		List<RequirePlanItemBean> getRequirePlanDetailList = new ArrayList<>();//调用接口
		String codeArray[] = code.split(",");
		String stringDate = "2018-08-27";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = dateFormat.parse(stringDate);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		//做测试数据 调用接口前使用 begin
		for (int i = 0; i < codeArray.length; i++) {
			RequirePlanItemBean requirePlanItemBean = new RequirePlanItemBean();
			requirePlanItemBean.setRequirePlanid(codeArray[i]);
			requirePlanItemBean.setMaterialName("物资A" + i);
			requirePlanItemBean.setMaterilaCode(codeArray[i]);
			requirePlanItemBean.setSpecification("规格" + i);
			requirePlanItemBean.setMaterialUnitName("单位" + i);
			requirePlanItemBean.setMaterialSubArray("包装物料" + i);
			requirePlanItemBean.setRequireQty((double)25345);
			requirePlanItemBean.setPurchaseQty((double)456);
			requirePlanItemBean.setStockQty((double)47);
			requirePlanItemBean.setReserveQty((double)57657);
			requirePlanItemBean.setOnwayQty((double)878);
			requirePlanItemBean.setBudgetQty((double)8768);
			requirePlanItemBean.setReferencePrice(BigDecimal.valueOf(789));
			requirePlanItemBean.setBudgetPrice(BigDecimal.valueOf(8908));
			requirePlanItemBean.setRequireDate(date);
			requirePlanItemBean.setArriveDate(date);
			requirePlanItemBean.setPurchaserName("张三");
			requirePlanItemBean.setDescription("sb");
			getRequirePlanDetailList.add(requirePlanItemBean);
		}
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("getRequirePlanDetailList", getRequirePlanDetailList);

		return returnData;

	}

	@ResponseBody
	@GetMapping("/requirePlanAddList")
	@RequiresPermissions("requirementPlan:requirementPlan")
	public PageInfo requirePlanAddList(@RequestParam Map<String, Object> params){
		//查询列表数据
//		Query query = new Query(params);
		List<Map<String, Object>> requirementPlanDetailList = new ArrayList<>();//调用接口
		for(int i=1;i<21;i++){
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
		PageInfo pageInfo = new PageInfo(requirementPlanDetailList, total);
		return pageInfo;
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("requirementPlan:requirementPlan")
	public PageInfo list(@RequestParam Map<String, Object> params){
		//查询列表数据
		ResultMsg rsm = requirePlanService.search(params.get("pageNumber").toString(),
				params.get("pageSize").toString(),"",
				params);
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
		PageInfo pageInfo = new PageInfo(requirementPlanList,
				Integer.parseInt(params.get("pageNumber").toString()));
		pageInfo.setTotal(total);
		return pageInfo;
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("requirementPlan:add")
	public R save(@RequestParam Map<String, Object> params){
		System.out.println(params);
		//int contactIds = service.save(customerContact);

		return R.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("requirementPlan:add")
	public R update(@RequestParam Map<String, Object> params){
		System.out.println(params);
		//int contactIds = service.save(customerContact);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("requirementPlan:remove")
	public R remove( String id){
		//调用接口
		ResultMsg rms = requirePlanService.remove(id);
		if ("1".equals(rms.getCode())) {
			return R.ok();
		}
		return R.error(rms.getCode(), rms.getMsg());
	}

	/**
	 * 提交审批
	 */
	@PostMapping( "/submitApproval")
	@ResponseBody
	@RequiresPermissions("requirementPlan:submitApproval")
	public R submitApproval( String planNo){
		System.out.println(planNo);
		//int contactIds = service.save(customerContact);

		return R.ok();
	}
	/**
	 * 撤回审批
	 */
	@PostMapping( "/withdrawApproval")
	@ResponseBody
	@RequiresPermissions("requirementPlan:withdrawApproval")
	public R withdrawApproval( String planNo){
		System.out.println(planNo);
		//int contactIds = service.save(customerContact);

		return R.ok();
	}
}
