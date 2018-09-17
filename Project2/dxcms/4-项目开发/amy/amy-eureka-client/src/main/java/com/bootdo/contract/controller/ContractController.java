package com.bootdo.contract.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.domain.Tree;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.FileType;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.service.UserService;
import com.dx.client.model.contract.ContractBean;
import com.dx.client.model.contract.ContractDeliverBean;
import com.dx.client.model.contract.ContractMaterialBean;
import com.dx.client.model.contract.ContractSuitBean;
//import com.dx.service.contract.service.api.IContractService;

/**
 * 合同起草
 * 
 * @author Administrator
 * @param <contractDelivers>
 * 
 * 
 */
@Controller
@RequestMapping("/ContractCreation/ContractCreation")
public class ContractController<contractDelivers> extends BaseController {
	@Autowired
	UserService userService;
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	private FileService sysFileService;

//	@Autowired 
//	private ContractBean contractBean;
//	@Autowired
//	private IContractService iContractService;
	/**
	 * @param model
	 * @return
	 */
	@RequiresPermissions("ContractCreation:ContractCreation")
	@GetMapping()
	String ContractCreation(Model model) {
		Long userId = getUser().getUserId();
		String userName = getUser().getUsername();
		String deptName = getUser().getDeptName();
		Long deptId = getUser().getDeptId();
		model.addAttribute("authorUserName", userName);
		model.addAttribute("authorUserId", userId);
		model.addAttribute("authorDeptName", deptName);
		model.addAttribute("authorDeptId", deptId);
		return "contract/ContractCreation/add";
	}

	@GetMapping("/list")
	@RequiresPermissions("ContractCreation:ContractCreation:list")
	String list(Model model) {
		Long userId = getUser().getUserId();
		String userName = getUser().getUsername();
		String deptName = getUser().getDeptName();
		Long deptId = getUser().getDeptId();
		model.addAttribute("authorUserName", userName);
		model.addAttribute("authorUserId", userId);
		model.addAttribute("authorDeptName", deptName);
		model.addAttribute("authorDeptId", deptId);
		return "contract/ContractCreation/contract";
	}

	// 列表页
	@RequestMapping("listData")
	@ResponseBody
	@RequiresPermissions("ContractCreation:ContractCreation:list")
	public PageUtils listData(@RequestParam Map<String, Object> params) {
		// 测试数据
		Query query = new Query(params);
		List<ContractBean> list = new ArrayList<ContractBean>();// 调用接口
		for (int i = 1; i <= 10; i++) {
			ContractBean contractBean = new ContractBean();
			Date date = new Date();
			contractBean.setStatusName("待审批");// 审批状态
			contractBean.setContractCode(Integer.toString(i));// 合同/协议编号
			contractBean.setProjectId("0" + i);// 项目编号
			contractBean.setCurrencyTypeName("美元");// 类型
			contractBean.setContractName("测试合同--" + i);// 名称
			contractBean.setCompanyNameA("甲方--" + i);// 甲方
			contractBean.setCompanyNameB("乙方--" + i);// 乙方
			contractBean.setTotalMoney(new BigDecimal("10"));// 合同总金额
			contractBean.setTaxRate(11);// 税率
			contractBean.setDateFrom(date);// 有效起始时间
			contractBean.setDateTo(date);// 有效结束时间
			contractBean.setAuthorCorpName("编制机构--" + i);// 编制机构
			contractBean.setAuthorDeptName("编制部门--" + i);// 编制部门
			list.add(contractBean);
		}
		int total = list.size();// 调用接口
		PageUtils pageUtils = new PageUtils(list, total);
		return pageUtils;
	}

//	@RequestMapping("/edit_ajax/{recordId}")
//	@ResponseBody
//	Map<String, Object> edit_ajax(@PathVariable("recordId") String recordId) {
//		RecordDO record = recordService.get(recordId);
//		Map<String, Object> returnData = new HashMap<String, Object>();
//		returnData.put("record", record);
//		return returnData;
//	}
	// 修改
	@GetMapping("/edit/{contractCode}")
	@RequiresPermissions("ContractCreation:ContractCreation:edit")
	String edit(@PathVariable("contractCode") String contractCode, Model model) {
		// RecordDO record = recordService.get(recordId);
		List<Map<String, Object>> beanlist = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<>();
		map.put("contractName", "合同名称");
		map.put("contractCode", "123");
		map.put("projectName", "项目名称");
		map.put("bidSchemeId", "1");
		map.put("currencyTypeId", "RMB");
		map.put("type", "RMB");
		map.put("taxRate", "12");
		map.put("performanceBond", "123456");
		map.put("warrantyBond", "123456");
		map.put("dateFrom", "2018-9-11");
		map.put("dateTo", "2018-9-11");
		map.put("totalMoney", "45678913");
		map.put("contractSuitsId", "6");
		map.put("contractSuits", "研发部");
		map.put("contractDelivers", "供货公司0");
		map.put("authorCorpName", "研发部");
		map.put("authorCorpId", "6");
		map.put("authorDeptName", "研发部");
		map.put("authorDeptId", "6");
		map.put("performUserName", "王健林");
		map.put("performUserId", "135");
		map.put("authorUserName", "admin");
		beanlist.add(map);

		model.addAttribute("beanlist", beanlist);
		return "/contract/ContractCreation/edit";
	}

	/**
	 * 查看
	 */
	@GetMapping("/see/{contractCode}")
	@RequiresPermissions("ContractCreation:ContractCreation:see")
	String see(@PathVariable("contractCode") String contractCode, Model model) {
		List<Map<String, Object>> beanlist = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<>();
		map.put("contractName", "合同名称");
		map.put("contractCode", "123");
		map.put("projectName", "项目名称");
		map.put("bidSchemeId", "1");
		map.put("currencyTypeId", "RMB");
		map.put("type", "RMB");
		map.put("taxRate", "12");
		map.put("performanceBond", "123456");
		map.put("warrantyBond", "123456");
		map.put("dateFrom", "2018-9-11");
		map.put("dateTo", "2018-9-11");
		map.put("totalMoney", "45678913");
		map.put("contractSuitsId", "6");
		map.put("contractSuits", "研发部");
		map.put("contractDelivers", "供货公司0");
		map.put("authorCorpName", "研发部");
		map.put("authorCorpId", "6");
		map.put("authorDeptName", "研发部");
		map.put("authorDeptId", "6");
		map.put("performUserName", "王健林");
		map.put("performUserId", "135");
		map.put("authorUserName", "admin");
		beanlist.add(map);

		model.addAttribute("beanlist", beanlist);
		model.addAttribute("contractCode", contractCode);
		return "contract/ContractCreation/see";
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("ContractCreation:ContractCreation:remove")
	public R remove(String id) {
		System.out.println(id);
		if (id != "") {
			return R.ok();
		}
//		if (recordService.remove(contractCode) > 0) {
//			return R.ok();
//		}
		return R.error();
	}

	/**
	 * 批量删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("ContractCreation:ContractCreation:batchRemove")
	public R remove(@RequestParam("ids[]") String[] contractCode) {
//		recordService.batchRemove(contractCode);
		return R.ok();
	}

	@GetMapping("/selectTree")
	@ResponseBody
	@RequiresPermissions("ContractCreation:ContractCreation")
	List<Map<String, Object>> selectTree(@RequestParam Map<String, Object> params, Model model) {

		// 适用机构 下拉列表查询数据
		List<Map<String, Object>> selectTree = new ArrayList<>();// 调用接口
		for (int i = 0; i < 4; i++) {
			ContractSuitBean contractSuitBean = new ContractSuitBean();
			Map<String, Object> contractSuits = new HashMap<>();
			// 测试数据 调用接口前使用
			contractSuits.put("id", "1" + i); // 注意*前台页面接受的必须是id、pid即name,前台引用jquery
			contractSuits.put("pId", "0"); // 树形下拉框插件MultipleTreeSelect,也必须是json数据*
			contractSuits.put("text", "火之国");
			JSONObject js = new JSONObject(contractSuits); // 将map类型转成json数据
			selectTree.add(js); // 将转换完成的json数据add到selectTree
		}
//		model.addAttribute("selectTree", selectTree); // 将数据传到前台
		return selectTree;
	}

	// 富文本编辑
	@PostMapping("/richText")
	@ResponseBody
	@RequiresPermissions("ContractCreation:ContractCreation")
	R richText(@RequestParam("ctrls[]") String[] ctrls) {
		System.out.println("************************");
		for (int i = 0; i < ctrls.length; i++) {// 遍历前台页面传回的数据
			String data = ctrls[i];
			System.out.println(data + "************************");
		}
		System.out.println("************************");
		return R.ok();
	}

	//
	@GetMapping("/tree")
	@ResponseBody
	public Tree<DeptDO> tree() {
		Tree<DeptDO> tree = new Tree<DeptDO>();
		tree = userService.getTree();
		return tree;
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("ContractCreation:ContractCreation:edit")
	public R update(@RequestParam("rowData") String rowData,@RequestParam("signupForm") String signupForm,@RequestParam("richTextKV") String richTextKV) {
		// int contactIds = service.save(customerContact);

		return R.ok();
	}

	 @ResponseBody
	    @PostMapping("/save")
	    @RequiresPermissions("ContractCreation:ContractCreation:add")
	    public R save(@RequestParam("rowData") String rowData,@RequestParam("signupForm") String signupForm,@RequestParam("richTextKV") String richTextKV) {
	        System.out.println("合同物资"+" "+rowData);
	        System.out.println("****************************************");
	        System.out.println("表单"+" "+signupForm);
	        System.out.println("****************************************");
	        System.out.println("富文本"+" "+richTextKV);
	        //int contactIds = service.save(customerContact);

	        return R.ok();
	    }
	@GetMapping("/project")
	@RequiresPermissions("ContractCreation:ContractCreation:add")
	String project() {
		return "/contract/ContractCreation/projectList";
	}

	@GetMapping("/projectList")
	@RequiresPermissions("ContractCreation:ContractCreation:add")
	@ResponseBody
	public PageUtils projectList(@RequestParam Map<String, Object> params) {
		// 查询列表数据
//			Query query = new Query(params);
		List<Map<String, Object>> projectList = new ArrayList<>();// 调用接口
		for (int i = 1; i <= 10; i++) {
			// 做测试数据 调用接口前使用 begin
			Map<String, Object> map = new HashMap<>();
			map.put("projectId", "100" + i);
			map.put("projectName", "物资编码" + i);
			projectList.add(map);
		}
		int total = 20;// 调用接口
		PageUtils pageUtils = new PageUtils(projectList, total);
		return pageUtils;
	}

	@GetMapping("/contractDelivers")
	@RequiresPermissions("ContractCreation:ContractCreation:add")
	String contractDeliversList() {
		return "/contract/ContractCreation/relevantParty";
	}

	@GetMapping("/contractDeliversList")
	@RequiresPermissions("ContractCreation:ContractCreation:add")
	@ResponseBody
	public PageUtils contractDeliversList(@RequestParam Map<String, Object> params) {
		// 查询列表数据
//			Query query = new Query(params);
		List<ContractDeliverBean> contractDelivers = new ArrayList<ContractDeliverBean>();// 调用接口

//		List<Map<String, Object>> contractDeliversList = new ArrayList<>();// 调用接口

		for (int i = 0; i <= 10; i++) {
			ContractDeliverBean aa = new ContractDeliverBean();
			Date nowDate = new Date();
			aa.setDeliverCompanyId("123" + i);
			aa.setDeliverCompanyName("供货公司" + i);
			aa.setId("1" + i);
			aa.setCreateDate(nowDate);
			contractDelivers.add(aa);
		}

		int total = 20;// 调用接口
		PageUtils pageUtils = new PageUtils(contractDelivers, total);
		return pageUtils;
	}

	// 合同物料测试数据
	@GetMapping("/test")
	@RequiresPermissions("ContractCreation:ContractCreation:add")
	@ResponseBody
	public PageUtils test(@RequestParam Map<String, Object> params) {
		List<ContractMaterialBean> list = new ArrayList<ContractMaterialBean>();// 调用接口
		// 做测试数据 调用接口前使用
		ContractMaterialBean bean = new ContractMaterialBean();
		ContractMaterialBean beans = new ContractMaterialBean();
		ContractMaterialBean beanT = new ContractMaterialBean();

		beanT.setId("34");
		beanT.setMaterilaCode("1");// 子id
		beanT.setParentId("");// 父id
		beanT.setSuitCorpId("适用机构");// 适用机构

		beanT.setMaterialName("物料名称");// 物料名称
		beanT.setMaterilaCode("301");// 物料编码

		beanT.setMaterialUnitName("单位");// 单位
		beanT.setQty(Double.valueOf("1"));// 数量
		beanT.setPrice(new BigDecimal(1));// 含税单价(元)
		Date datet = new Date();
		beanT.setDateFrom(datet);// 有效期至日期
		beanT.setDateTo(datet);// 有效截止日期
		beanT.setRemark("备注");// 备注
		list.add(beanT);

		bean.setId("2");
		bean.setMaterilaCode(Integer.toString(2));// 子id
		bean.setParentId(Integer.toString(301));// 父id
		bean.setSuitCorpId("适用机构1");// 适用机构

		bean.setMaterialName("物料名称");// 物料名称
		bean.setMaterilaCode("2002");// 物料编码
		bean.setSpecification("规格型号");// 规格型号

		bean.setMaterialUnitName("单位");// 单位
		bean.setQty(Double.valueOf("1"));// 数量
		bean.setPrice(new BigDecimal(1));// 含税单价(元)
		Date date = new Date();
		bean.setDateFrom(date);// 有效期至日期
		bean.setDateTo(date);// 有效截止日期
		bean.setRemark("备注");// 备注
		list.add(bean);

		beans.setId("3");
		beans.setMaterilaCode(Integer.toString(3));// 子id
		beans.setParentId(Integer.toString(301));// 父id
		beans.setSuitCorpId("适用机构2");// 适用机构

		beans.setMaterialName("物料名称");// 物料名称
		beans.setMaterilaCode("2001");// 物料编码
		beans.setSpecification("规格型号");// 规格型号

		beans.setMaterialUnitName("单位");// 单位
		beans.setQty(Double.valueOf("1"));// 数量
		beans.setPrice(new BigDecimal(1));// 含税单价(元)
		Date dates = new Date();
		beans.setDateFrom(dates);// 有效期至日期
		beans.setDateTo(dates);// 有效截止日期
		beans.setRemark("备注");// 备注
		list.add(beans);
//			JSONObject js = new JSONObject(map); // 将map类型转成json数据

		int total = 20;// 调用接口
		PageUtils pageUtils = new PageUtils(list, total);
		return pageUtils;
	}

	@GetMapping("/importM")
	@RequiresPermissions("ContractCreation:ContractCreation:import")
	String importM() {
		return "/contract/ContractCreation/ImportMaterial";
	}
	
	@GetMapping("/importE")
	@RequiresPermissions("ContractCreation:ContractCreation:import")
	String importE() {
		return "/contract/ContractCreation/editImportMaterial";
	}

	/**
	 * 上传文件
	 * 
	 * @param file
	 * @param request
	 * @return
	 */
	@ResponseBody
	@PostMapping("/upload")
	R upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {

//		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
//		chars.charAt((int)(Math.random() * 52))+
		String fileName = file.getOriginalFilename();
//		fileName = FileUtil.renameToUUID(fileName);
		FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/" + fileName, new Date());
		try {
			FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath(), fileName);
		} catch (Exception e) {
			return R.error();
		}
		int ids = sysFileService.save(sysFile);
		System.out.println(ids);
		if (ids > 0) {
			R r = R.ok();
			r.put("customerAttachment", ids);
			r.put("fileName", sysFile.getUrl());
			return r;
//			return R.ok().put("fileName", sysFile.getUrl());
		}
		return R.error();
	}

	// 根据文件名称下载相关代码
	@ResponseBody
	@RequestMapping("/down")
	public void download(HttpServletResponse response, @RequestParam("fileName") String fileName) {
		try {
			// path是指欲下载的文件的路径。
			String path = "C:/var/uploaded_files/" + fileName;

			File file = new File(path);
			// 取得文件名。
			String filename = file.getName();

			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			// 设置文件名
			response.addHeader("Content-Disposition",
					"attachment;filename=" + new String(filename.getBytes(), "ISO-8859-1"));
			// 设置文件打下
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	// 模板下载
	@ResponseBody
	@RequestMapping("/downloadTemplate")
	public void download(HttpServletResponse response, HttpServletRequest request) {
		try {

			// File files = new
			// File(".\\src\\main\\resources\\downloadTemplate\\企业客户导入摸板.xls");
			// System.out.println("getAbsolutePath:"+files.getAbsolutePath());
			// //getAbsolutePath()会将.认为是一个以.命名的文件
			// System.out.println("getCanonicalPath:"+files.getCanonicalPath());//getCanonicalPath()得到的是一个规范路径没有.
			//

			File file = new File("./src/main/resources/downTemplate/合同订立中物资批量导入模版.xlsx");
			// 取得文件名。
			String filename = file.getName();

			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(file.getCanonicalPath()));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			// 设置文件名
			response.addHeader("Content-Disposition",
					"attachment;filename=" + new String(filename.getBytes(), "ISO-8859-1"));
			// 设置文件打下
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	// exls表格导入
	@ResponseBody
	@PostMapping("/uploadExcel")
	R uploadExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		String fileName = file.getOriginalFilename();
		fileName = FileUtil.renameToUUID(fileName);
		File datafile = null;
		try {
			FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath(), fileName);
			datafile = new File(bootdoConfig.getUploadPath() + fileName);
			// log数据保存
			long userid = getUserId(); // 用户id
//	        String s1 = JSON.toJSONString(list);
//	        JSONArray jsonArray = JSONArray.parseArray(s1);
//	        System.out.println(jsonArray);

//			net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(list);

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
		List<ContractMaterialBean> list = new ArrayList<ContractMaterialBean>();

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
				ContractMaterialBean contractMaterialBean = new ContractMaterialBean(); // 售前项目信息表

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
					// String agentCode = null;
					String companyName = null;
					// 遍历列
					for (int j = 0; j < cellCount; j++) {
						cellvalue = ""; // 清空之前之前取到的列的值
						// 获取到列的值
						Cell cell = row.getCell(j);
						// String value = "";
						if (cell != null) {
							switch (cell.getCellType()) {
							case XSSFCell.CELL_TYPE_FORMULA:
								break;
							case XSSFCell.CELL_TYPE_NUMERIC: {
								short format = cell.getCellStyle().getDataFormat();
								if (format == 14 || format == 31 || format == 57 || format == 58) { // excel中的时间格式
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
									double value = cell.getNumericCellValue();
									Date date = DateUtil.getJavaDate(value);
									cellvalue = sdf.format(date);
								}
								// 判断当前的cell是否为Date
								else if (HSSFDateUtil.isCellDateFormatted(cell)) { // 先注释日期类型的转换，在实际测试中发现HSSFDateUtil.isCellDateFormatted(cell)只识别2014/02/02这种格式。
									// 如果是Date类型则，取得该Cell的Date值 // 对2014-02-02格式识别不出是日期格式
									Date date = cell.getDateCellValue();
									DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
									cellvalue = formater.format(date);
								} else { // 如果是纯数字
									// 取得当前Cell的数值
									cellvalue = NumberToTextConverter.toText(cell.getNumericCellValue());
								}
								break;
							}
							case XSSFCell.CELL_TYPE_STRING:
								cellvalue = cell.getStringCellValue();
								break;
							default:
								break;
							}
						}
						if (j == 0) {
							contractMaterialBean.setSuitCorpId(cellvalue);
						} else if (j == 1) {
							contractMaterialBean.setMaterilaCode(cellvalue);
						} else if (j == 2) {
							contractMaterialBean.setMaterialName(cellvalue);
						} else if (j == 3) {
							contractMaterialBean.setParentId(cellvalue);
						} else if (j == 4) {
							if (cellvalue == null || cellvalue == "") {
								continue;
							} else {
								contractMaterialBean.setQty(Double.parseDouble(cellvalue));
							}
						} else if (j == 5) {
							if (cellvalue == null || cellvalue == "") {
								continue;
							} else {
								contractMaterialBean.setPrice(new BigDecimal(cellvalue));
							}
						} else if (j == 6) {
							if (cellvalue == null || cellvalue == "") {
								continue;
							} else {
								SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
								contractMaterialBean.setDateFrom(formatter.parse(cellvalue));
							}
						} else if (j == 7) {
							if (cellvalue == null || cellvalue == "") {
								continue;
							} else {
								SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
								contractMaterialBean.setDateTo(formatter.parse(cellvalue));
							}
						} else if (j == 8) {
							contractMaterialBean.setRemark(cellvalue);
						}

					} // --->遍历列

//					recordDao.saveDownloadTemplate(recordDO);
				}
				list.add(contractMaterialBean);
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
