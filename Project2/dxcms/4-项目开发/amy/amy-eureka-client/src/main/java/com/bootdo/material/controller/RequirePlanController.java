package com.bootdo.material.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.utils.FileUtil;
import com.dx.client.model.purchase.RequireApplyItemBean;
import com.dx.client.model.purchase.RequirePlanBean;
import com.dx.client.model.purchase.RequirePlanItemBean;
import com.dx.service.purchase.service.api.IRequirePlanService;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.common.utils.R;
import org.springframework.web.multipart.MultipartFile;
import org.wxcl.amy.utils.common.ResultMsg;

import javax.servlet.http.HttpServletRequest;

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
	@Autowired
	private BootdoConfig bootdoConfig;
	
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
		ResultMsg rsm = requirePlanService.detail(planNo);
		List<RequirePlanItemBean> checkList = new ArrayList<>();//调用接口
		String stringDate = "2018-08-27";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = dateFormat.parse(stringDate);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		//做测试数据 调用接口前使用 begin
		for (int i = 1; i < 6; i++) {
			RequirePlanItemBean requirePlanItemBean = new RequirePlanItemBean();
			requirePlanItemBean.setRequirePlanid("物资编码" + i);
			requirePlanItemBean.setMaterialName("物资A" + i);
			requirePlanItemBean.setMaterilaCode("物资编码" + i);
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
			checkList.add(requirePlanItemBean);
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
		requirePlanModel.setCode(planNo);
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
		ResultMsg rsm = requirePlanService.detail(planNo);
		List<RequirePlanItemBean> editList = new ArrayList<>();//调用接口
		String stringDate = "2018-08-27";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = dateFormat.parse(stringDate);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		//做测试数据 调用接口前使用 begin
		for (int i = 1; i < 6; i++) {
			RequirePlanItemBean requirePlanItemBean = new RequirePlanItemBean();
			requirePlanItemBean.setRequirePlanid("物资编码" + i);
			requirePlanItemBean.setMaterialName("物资A" + i);
			requirePlanItemBean.setMaterilaCode("物资编码" + i);
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
			editList.add(requirePlanItemBean);
		}
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("editList", editList);
		return returnData;
	}

	@ResponseBody
	@GetMapping("/getMaterialDetailByCode/{code}")
	@RequiresPermissions("requirementPlan:add")
	Map<String, Object> getMaterialDetailByCode(@PathVariable("code") String code){
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
			requireMap.put("requireDate","2018-8-27");
			requireMap.put("createDate","2018-8-20");
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
			requirePlanMap.put("createDate","2018-8-27");
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
		RequirePlanBean requirePlanBean = new RequirePlanBean();
		requirePlanBean.setName((String) params.get("title"));
		requirePlanBean.setCode((String)params.get("planNo"));
		requirePlanBean.setPurchaseDeptName((String)params.get("purchaseDept"));
		requirePlanBean.setRequireTypeName((String)params.get("type"));
		requirePlanBean.setAuthorUserName((String)params.get("authorUser"));
		requirePlanBean.setRemark((String)params.get("remark"));
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date invoiceDate = dateFormat.parse((String)params.get("invoiceDate"));
			requirePlanBean.setBusinessDate(invoiceDate);
			Date createDate = dateFormat.parse((String)params.get("createDate"));
			requirePlanBean.setCreateDate(createDate);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		ArrayList<RequirePlanItemBean> itemList = new ArrayList<>();
		JSONArray jsonArray = JSONArray.fromObject(params.get("applyEntryJson"));
		for (int i = 0; i < jsonArray.size(); i++){
			RequirePlanItemBean requirePlanItemBean = (RequirePlanItemBean) JSONObject.toBean((JSONObject)jsonArray.get(i), RequirePlanItemBean.class);
			itemList.add(requirePlanItemBean);
		}
		requirePlanBean.setRequirePlanItemBeans(itemList);
		ResultMsg rms =requirePlanService.save(requirePlanBean);
		if ("1".equals(rms.getCode())) {
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("requirementPlan:edit")
	public R update(@RequestParam Map<String, Object> params){
		RequirePlanBean requirePlanBean = new RequirePlanBean();
		requirePlanBean.setName((String) params.get("title"));
		requirePlanBean.setCode((String)params.get("planNo"));
		requirePlanBean.setPurchaseDeptName((String)params.get("purchaseDept"));
		requirePlanBean.setRequireTypeName((String)params.get("type"));
		requirePlanBean.setAuthorUserName((String)params.get("authorUser"));
		requirePlanBean.setRemark((String)params.get("remark"));
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date invoiceDate = dateFormat.parse((String)params.get("invoiceDate"));
			requirePlanBean.setBusinessDate(invoiceDate);
			Date createDate = dateFormat.parse((String)params.get("createDate"));
			requirePlanBean.setCreateDate(createDate);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		ArrayList<RequirePlanItemBean> itemList = new ArrayList<>();
		JSONArray jsonArray = JSONArray.fromObject(params.get("applyEntryJson"));
		for (int i = 0; i < jsonArray.size(); i++){
			RequirePlanItemBean requirePlanItemBean = (RequirePlanItemBean) JSONObject.toBean((JSONObject)jsonArray.get(i), RequirePlanItemBean.class);
			itemList.add(requirePlanItemBean);
		}
		requirePlanBean.setRequirePlanItemBeans(itemList);
		ResultMsg rms =requirePlanService.save(requirePlanBean);
		if ("1".equals(rms.getCode())) {
			return R.ok();
		}
		return R.error();
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

	@GetMapping("/import")
	@RequiresPermissions("requirementPlan:import")
	String importM() {
		return "material/requirementPlan/import";
	}

	/**
	 * exls表格导入
	 */
	@ResponseBody
	@PostMapping("/uploadExcel")
	@RequiresPermissions("requirementPlan:import")
	R uploadExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		String fileName = file.getOriginalFilename();
		fileName = FileUtil.renameToUUID(fileName);
		File datafile = null;
		try {
			FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath(), fileName);
			datafile = new File(bootdoConfig.getUploadPath() + fileName);

			Map<String, Object> errorMsgs = ImportMaterial(datafile);
			if ("success".equals(errorMsgs.get("result"))) {
				R r = R.ok();
				r.put("list", errorMsgs.get("list"));
				return r;
			} else {
				return R.error();
			}
		} catch (Exception e) {
			return R.error();
		}

	}

	public Map<String, Object> ImportMaterial(File file) {
		Workbook wookbook = null;
		List<String> errorMsgs = null;
		Map<String, Object> result = null;
		List<RequirePlanItemBean> list = new ArrayList<RequirePlanItemBean>();

		int rtn = 0;
		try {
			result = new HashMap<String, Object>();
			errorMsgs = new ArrayList<String>();

			FileInputStream is = new FileInputStream(file); // 文件流
			wookbook = WorkbookFactory.create(is); // 这种方式 Excel 2003/2007/2010 都是可以处理的

			// 在Excel文档中，第一张工作表的缺省索引是0
			// 其语句为：HSSFSheet sheet = wookbook.getSheetAt(0);
			Sheet sheet = wookbook.getSheetAt(0);// wookbook.getSheet("Sheet1");
			// 获取到Excel文件中的所有行数
			int rows = sheet.getPhysicalNumberOfRows();
			// Excel文件中的第一行（标题行）
			int cellCount = 0;

			// 遍历行
			my: for (int i = 0; i < rows; i++) {
				// 读取左上端单元格(跳过第一行标题行)
				Row row = sheet.getRow(i);

				//获取标题行
				Row rowTitleName = sheet.getRow(0);
				RequirePlanItemBean requirePlanItemBean = new RequirePlanItemBean(); //

				// 行不为空
				if (row != null) {
					if (i == 0) {
						// 获取到Excel文件中的第一行（标题行）
						Row rowCount = sheet.getRow(i);

						// 获取到Excel文件中的所有的列
						cellCount = rowCount.getPhysicalNumberOfCells();
						continue;
					}
					// 获取到Excel文件中的所有的列
					// int cells = row.getPhysicalNumberOfCells();
					String cellvalue = "";
					String contact = "";
					String titleName = "";
					// String agentCode = null;
					String companyName = null;
					// 遍历列
					for (int j = 0; j < cellCount; j++) {
						cellvalue = ""; // 清空之前之前取到的列的值
						titleName = "";
						// 获取到列的值
						Cell cell = row.getCell(j);
						//获取标题到列的值
						Cell cellTitleName = rowTitleName.getCell(j);
						// String value = "";
						if (cell != null) {
							switch (cell.getCellType()& cellTitleName.getCellType()) {
								case XSSFCell.CELL_TYPE_FORMULA:
									break;
								case XSSFCell.CELL_TYPE_NUMERIC: {
									short format = cell.getCellStyle().getDataFormat();
//
									if (format == 14 || format == 31 || format == 57 || format == 58) { // excel中的时间格式
										SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
										double value = cell.getNumericCellValue();
										Date date = DateUtil.getJavaDate(value);
										cellvalue = sdf.format(date);
									}
									short formatTitleName = cellTitleName.getCellStyle().getDataFormat();
									if (formatTitleName == 14 || formatTitleName == 31 || formatTitleName == 57 || formatTitleName == 58) {
										SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
										double values = cellTitleName.getNumericCellValue();
										Date dateTitleName = DateUtil.getJavaDate(values);
										titleName = sdf.format(dateTitleName);
									}
									// 判断当前的cell是否为Date
									else if (HSSFDateUtil.isCellDateFormatted(cell)) { // 先注释日期类型的转换，在实际测试中发现HSSFDateUtil.isCellDateFormatted(cell)只识别2014/02/02这种格式。
										// 如果是Date类型则，取得该Cell的Date值 // 对2014-02-02格式识别不出是日期格式
										Date date = cell.getDateCellValue();
										DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
										cellvalue = formater.format(date);
										titleName =cellTitleName.getStringCellValue();
									} else { // 如果是纯数字
										// 取得当前Cell的数值
										titleName = cellTitleName.getStringCellValue();
										cellvalue = NumberToTextConverter.toText(cell.getNumericCellValue());
									}
									break;
								}
								case XSSFCell.CELL_TYPE_STRING:
									titleName = cellTitleName.getStringCellValue();
									cellvalue = cell.getStringCellValue();
									break;
								default:
									break;
							}
						}
//                        if (i == 0) {
//                            titleName=cellvalue==null?"":cellvalue;
//                        }else{
						if ("物资编码".equals(titleName.trim())) {
							requirePlanItemBean.setMaterilaCode(cellvalue);
						} else if ("物资名称".equals(titleName.trim())) {
							requirePlanItemBean.setMaterialName(cellvalue);
						} else if ("需求数量".equals(titleName.trim())) {
							requirePlanItemBean.setRequireQty(Double.parseDouble(cellvalue));
						} else if ("参考单价".equals(titleName.trim())) {
							requirePlanItemBean.setReferencePrice(new BigDecimal(cellvalue));
						} else if ("要求到货日期".equals(titleName.trim())) {
							if (cellvalue == null || cellvalue == "") {
								continue;
							} else {
								SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
								requirePlanItemBean.setRequireDate(formatter.parse(cellvalue));
							}
						} else if ("说明信息".equals(titleName.trim())) {
							requirePlanItemBean.setRemark(cellvalue);
						}
//                        }


					} // --->遍历列

				}
				list.add(requirePlanItemBean);
				rtn = list.size();
			}
		} catch (Exception e) {
			errorMsgs.add(e.getMessage());
			e.printStackTrace();
		} finally {
			wookbook.cloneSheet(0); // 关闭sheet页
			try {
				wookbook.close(); // 关闭Excel文件
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (CollectionUtils.isEmpty(errorMsgs)) { // errorMsgs.size() == 0

			if (rtn > 0) {
				result.put("result", "success");
				result.put("list", list);
			} else {
				result.put("result", "false");
			}
		} else {
			result.put("result", "error");
			result.put("msg", errorMsgs);
		}

		return result;
	}
}
