package com.bootdo.sales.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.bootdo.common.domain.*;
import com.bootdo.common.service.FieldService;
import com.bootdo.common.service.MainCopyPersonService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
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
import org.springframework.web.multipart.MultipartFile;

import com.bootdo.sales.domain.BusinessDO;
import com.bootdo.sales.domain.CompanyCustomerDO;
import com.bootdo.sales.domain.CustomerContactDO;
import com.bootdo.sales.service.BusinessService;
import com.bootdo.sales.service.CompanyCustomerService;
import com.bootdo.sales.service.CustomerContactService;
import com.bootdo.approval.domain.PurchaseDO;
import com.bootdo.approval.service.PurchaseService;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.FileType;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.ZipUtils;
import com.bootdo.contract.domain.ContractDO;
import com.bootdo.contract.domain.ReceivableDO;
import com.bootdo.contract.service.ContractService;
import com.bootdo.contract.service.ReceivableService;
import com.bootdo.payment.domain.ReceivedDO;
import com.bootdo.payment.service.ReceivedService;
import com.bootdo.project.domain.ProjectDO;
import com.bootdo.project.service.ProjectService;

/**
 * 企业客户信息表
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-11-16 11:25:16
 */

@Controller
@RequestMapping("/sales/companyCustomer")
public class CompanyCustomerController<GuideInfo, IPageModule> extends BaseController {
	@Autowired
	private CompanyCustomerService companyCustomerService;
	@Autowired
	private MainCopyPersonService mainCopyPersonService;
	@Autowired
	private FileService sysFileService;
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	private BusinessService businessService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private ContractService contractService;
	@Autowired
	private CustomerContactService customerContactService;
	@Autowired
	private ReceivableService receivableService;
	@Autowired
	private ReceivedService receivedService;
	@Autowired
	private PurchaseService purchaseService;
	@Autowired
	private FieldService fieldService;
	/***** 自定义字段相关 *******/
	@ResponseBody
	@GetMapping("/listField")
	@RequiresPermissions("sales:companyCustomer:companyCustomer")

	public PageUtils listField(@RequestParam Map<String, Object> params) {
		params.put("tableName", "sales_company_customer");
		params.put("t_id", params.get("t_id"));
		// 查询列表数据

		Query query = new Query(params);
		List<FieldDO> fieldList = fieldService.list(query);
		int total = fieldService.count(query);
		PageUtils pageUtils = new PageUtils(fieldList, total);
		return pageUtils;
	}

	@GetMapping("/addField")
	@RequiresPermissions("sales:companyCustomer:addField")
	String addField() {
		return "common/customField/addCompanyField";
	}

	@ResponseBody
	@PostMapping("/saveField")
	@RequiresPermissions("sales:companyCustomer:addField")
	public R saveField(FieldDO field, CompanyCustomerDO companyCustomer) {
		String customerIds = companyCustomer.getCustomerId();
		if (!customerIds.equals("")) {
			field.setT_id(String.valueOf(customerIds));
			field.setTableName("sales_company_customer");
			fieldService.save(field);
			return R.ok();
		}
		return R.error();
	}

	@GetMapping("/editField/{id}")
	@RequiresPermissions("sales:companyCustomer:editField")
	String editField(@PathVariable("id") Integer id, Model model) {
		FieldDO field = fieldService.get(id);
		model.addAttribute("field", field);
		return "common/customField/editCompanyField";
	}

	@ResponseBody
	@GetMapping("/editField_ajax/{id}")
	@RequiresPermissions("sales:companyCustomer:editField")
	Map<String, Object> editField_ajax(@PathVariable("id") Integer id) {
		FieldDO field = fieldService.get(id);
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("fieldList", field);

		return returnData;

	}

	@ResponseBody
	@RequestMapping("/updateField")
	@RequiresPermissions("sales:companyCustomer:editField")
	public R update(FieldDO field) {
		fieldService.update(field);
		return R.ok();
	}

	@PostMapping("/removeField")
	@ResponseBody
	@RequiresPermissions("sales:companyCustomer:removeField")
	public R remove(Integer id) {
		if (fieldService.remove(id) > 0) {
			return R.ok();
		}
		return R.error();
	}

	@PostMapping("/batchRemoveField")
	@ResponseBody
	@RequiresPermissions("sales:companyCustomer:batchRemoveField")
	public R remove(@RequestParam("ids[]") Integer[] ids) {
		fieldService.batchRemove(ids);
		return R.ok();
	}

	@GetMapping()
	@RequiresPermissions("sales:companyCustomer:companyCustomer")
	String CompanyCustomer(Model model) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("offset", 0);
		params.put("limit", 2);
		Query query = new Query(params);
		// *****************热点客户、本月回款情况、本月计划回款*********************************
		// 新客户
		List<CompanyCustomerDO> newCustomer = companyCustomerService.newCustomer(query);
		model.addAttribute("newCustomer", newCustomer);
		// 旧客户
		List<CompanyCustomerDO> oldCustomer = companyCustomerService.oldCustomer(query);
		model.addAttribute("oldCustomer", oldCustomer);
		// 实际回款
		List<ReceivedDO> receivedDO = receivedService.sumReceivedPrice(query);
		model.addAttribute("receivedDO", receivedDO);
		// 未回款
		List<ReceivedDO> moneyNumber = receivedService.moneyNumber(query);
		model.addAttribute("moneyNumber", moneyNumber);
		// 计划回款
		List<ReceivableDO> planCunstomerNumber = receivableService.planCunstomerNumber(query);
		model.addAttribute("planCunstomerNumber", planCunstomerNumber);
		// **********************END******************************************************
		return "sales/companyCustomer/companyCustomer";
	}

	// 列表
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("sales:companyCustomer:companyCustomer")
	public PageUtils list(@RequestParam Map<String, Object> params, Model model, HttpServletRequest request) {
		// companyCustomer.setCurrentUser((Long.toString(getUserId())));
		// 查询列表数据
		params.put("customerOperator", (Long.toString(getUserId())));
		params.put("Identification", (getIdentification()));
		params.put("userName", (getUsername()));
		// 排序方式
		String orders = request.getParameter("sortOrder");
		params.put("order", orders);
		// 排序字段
		String sortnames = request.getParameter("sortName");
		params.put("sort", sortnames);

		Query query = new Query(params);
		List<CompanyCustomerDO> companyCustomerList = companyCustomerService.list(query);
		int total = companyCustomerService.count(query);
		PageUtils pageUtils = new PageUtils(companyCustomerList, total);

		return pageUtils;
	}

	// 热点列表
	@ResponseBody
	@GetMapping("/listHot")
	@RequiresPermissions("sales:companyCustomer:companyCustomer")
	public PageUtils listHot(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<CompanyCustomerDO> companyCustomerList = companyCustomerService.list(query);
		int total = companyCustomerService.count(query);
		PageUtils pageUtils = new PageUtils(companyCustomerList, total);
		return pageUtils;
	}

	// ajax修改绑定数据
	@RequestMapping("/edit_ajax/{customerId}")
	@ResponseBody
	Map<String, Object> edit_ajax(@PathVariable("customerId") String customerId) {
		CompanyCustomerDO companyCustomer = companyCustomerService.get(customerId);
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("companyCustomer", companyCustomer);
		return returnData;
	}

	@GetMapping("/viewDetail/{customerId}")
	@RequiresPermissions("sales:companyCustomer:viewDetail")
	String viewDetail(@PathVariable("customerId") String customerId, Model model) {
		CompanyCustomerDO customer = companyCustomerService.get(customerId);
		model.addAttribute("customer", customer);
		return "sales/companyCusyomer/viewDetail";
	}

	@GetMapping("/tree")
	@ResponseBody
	public Tree<CompanyCustomerDO> tree() {
		Tree<CompanyCustomerDO> tree = new Tree<CompanyCustomerDO>();
		tree = companyCustomerService.getTree();
		return tree;
	}

	@GetMapping("/add")
	@RequiresPermissions("sales:companyCustomer:add")
	String add() {
		return "sales/companyCustomer/add";
	}

	@GetMapping("/import")
	@RequiresPermissions("sales:companyCustomer:import")
	String importFile() {
		return "sales/companyCustomer/import";
	}

	// @GetMapping("/export")
	// @RequiresPermissions("sales:companyCustomer:export")
	// String exportFile() {
	// return "sales/companyCustomer/import";
	// }
	@GetMapping("/edit/{customerId}")
	@RequiresPermissions("sales:companyCustomer:edit")
	String edit(@PathVariable("customerId") String customerId, Model model) {
		CompanyCustomerDO companyCustomer = companyCustomerService.get(customerId);
		model.addAttribute("companyCustomer", companyCustomer);
		return "sales/companyCustomer/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("sales:companyCustomer:add")
	public R save(CompanyCustomerDO companyCustomer) {
		String customerIdss=companyCustomerService.getcustomerId(companyCustomer);
		System.out.println(customerIdss);
		companyCustomer.setCustomerId(customerIdss);
		companyCustomer.setCustomerOperator(Long.toString(getUserId()));
		
		int customerIds = companyCustomerService.save(companyCustomer);
		if (customerIds > 0) {
			MainCopyPersonDO mcp = new MainCopyPersonDO();
			String mainPersonId = companyCustomer.getMainPersonId();
			if (!"".equals(mainPersonId)) {
				String mainPersonIdArray[] = mainPersonId.split(",");
				for (int i = 0; i < mainPersonIdArray.length; i++) {
					mcp.setTId(companyCustomer.getCustomerId());
					mcp.setMainPerson(1);
					mcp.setEmployeeId(mainPersonIdArray[i]);
					mcp.setOperator(getUserId());
					mcp.setTableName("sales_company_customer");
					mainCopyPersonService.save(mcp);

				}
			}

			String copyPersonId = companyCustomer.getCopyPersonId();
			if (!"".equals(copyPersonId)) {
				String copyPersonIdArray[] = copyPersonId.split(",");
				for (int i = 0; i < copyPersonIdArray.length; i++) {
					mcp.setTId(companyCustomer.getCustomerId());
					mcp.setMainPerson(0);
					mcp.setEmployeeId(copyPersonIdArray[i]);
					mcp.setOperator(getUserId());
					mcp.setTableName("sales_company_customer");
					mainCopyPersonService.save(mcp);
				}
			}
			R r = R.ok();
			r.put("customerId", customerIds);
			return r;
		}
		return R.error();
	}
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/saveFile")
	@RequiresPermissions("sales:companyCustomer:add")
	public R saveFile(CompanyCustomerDO companyCustomer) {
		companyCustomer.setCustomerOperator(Long.toString(getUserId()));
		int customerIds = companyCustomerService.saveFile(companyCustomer);
		if (customerIds > 0) {
			MainCopyPersonDO mcp = new MainCopyPersonDO();
			String mainPersonId = companyCustomer.getMainPersonId();
			if (!"".equals(mainPersonId)) {
				String mainPersonIdArray[] = mainPersonId.split(",");
				for (int i = 0; i < mainPersonIdArray.length; i++) {
					mcp.setTId(companyCustomer.getCustomerId());
					mcp.setMainPerson(1);
					mcp.setEmployeeId(mainPersonIdArray[i]);
					mcp.setOperator(getUserId());
					mcp.setTableName("sales_company_customer");
					mainCopyPersonService.save(mcp);

				}
			}

			String copyPersonId = companyCustomer.getCopyPersonId();
			if (!"".equals(copyPersonId)) {
				String copyPersonIdArray[] = copyPersonId.split(",");
				for (int i = 0; i < copyPersonIdArray.length; i++) {
					mcp.setTId(companyCustomer.getCustomerId());
					mcp.setMainPerson(0);
					mcp.setEmployeeId(copyPersonIdArray[i]);
					mcp.setOperator(getUserId());
					mcp.setTableName("sales_company_customer");
					mainCopyPersonService.save(mcp);
				}
			}
			R r = R.ok();
			r.put("customerId", customerIds);
			return r;
		}
		return R.error();
	}
	// *****************************列表页Top热点客户块*******************************************************************************
	// 新客户要更多信息页面
	@GetMapping("/newCustomerMore")
	@RequiresPermissions("sales:companyCustomer:newCustomerMore")
	String newCustomerMore() {
		return "/sales/companyCustomer/examineHotCustomers/newCustomerMore";
	}

	// 新客户更多信息列表
	@ResponseBody
	@GetMapping("/listNewCustomerMore")
	@RequiresPermissions("sales:companyCustomer:newCustomerMore")
	public PageUtils listNewCustomerMore(@RequestParam Map<String, Object> params, Model model) {
		// 查询新客户列表数据
		params.put("offset", 0);
		params.put("limit", 10);
		Query query = new Query(params);
		List<CompanyCustomerDO> companyCustomerList = companyCustomerService.newCustomer(query);
		int total = companyCustomerService.countNewCustomer(query);
		PageUtils pageUtils = new PageUtils(companyCustomerList, total);
		return pageUtils;
	}

	// 旧客户更多信息页面
	@GetMapping("/oldCustomerMore")
	@RequiresPermissions("sales:companyCustomer:oldCustomerMore")
	String oldCustomerMore() {
		return "/sales/companyCustomer/examineHotCustomers/oldCustomerMore";
	}

	// 旧客户要更多信息
	@ResponseBody
	@GetMapping("/listOldCustomerMore")
	@RequiresPermissions("sales:companyCustomer:oldCustomerMore")
	public PageUtils listOldCustomerMore(@RequestParam Map<String, Object> params, Model model) {
		// 查询新客户列表数据
		// params.put("offset", 0);
		// params.put("limit", 10);
		Query query = new Query(params);
		List<CompanyCustomerDO> companyCustomerList = companyCustomerService.oldCustomer(query);
		int total = companyCustomerService.countOldCustomer(query);
		PageUtils pageUtils = new PageUtils(companyCustomerList, total);
		return pageUtils;
	}
	// *****************************END***************************************************************************************

	// *****************************本月回款情况块*********************************************************************************
	// 已回款企业数目页面
	@GetMapping("/examineReimbursementEnterprise")
	@RequiresPermissions("sales:companyCustomer:companyCustomer")
	String examineReimbursementEnterprise() {
		return "/sales/companyCustomer/examinePlannedReturn/examineReimbursementEnterprise";
	}

	// 已回款企业数目列表页面
	@ResponseBody
	@GetMapping("/priceNumberExamine")
	@RequiresPermissions("sales:companyCustomer:companyCustomer")
	public PageUtils priceNumberExamine(@RequestParam Map<String, Object> params, Model model) {
		// 查询新客户列表数据
		params.put("offset", 1);
		params.put("limit", 10);
		Query query = new Query(params);
		List<ReceivedDO> companyCustomerList = receivedService.priceNumberExamine(query);
		int total = receivedService.countPriceNumberExamine(query);
		PageUtils pageUtils = new PageUtils(companyCustomerList, total);
		return pageUtils;
	}

	// 未回款企业数目页面
	@GetMapping("/examineNonPaymentEnterprise")
	@RequiresPermissions("sales:companyCustomer:companyCustomer")
	String examineNonPaymentEnterprise() {
		return "/sales/companyCustomer/examinePlannedReturn/examineNonPaymentEnterprise";
	}

	// 未回款企业数目列表页面
	@ResponseBody
	@GetMapping("/noPriceNumberExamine")
	@RequiresPermissions("sales:companyCustomer:companyCustomer")
	public PageUtils noPriceNumberExamine(@RequestParam Map<String, Object> params, Model model) {
		// 查询新客户列表数据
		params.put("offset", 1);
		params.put("limit", 10);
		Query query = new Query(params);
		List<ReceivedDO> companyCustomerList = receivedService.noPriceNumberExamine(query);
		int total = receivedService.countNoPriceNumberExamine(query);
		PageUtils pageUtils = new PageUtils(companyCustomerList, total);
		return pageUtils;
	}

	// *****************************END***************************************************************************************

	// *****************************本月计划回款*********************************************************************************
	// 本月计划回款客户数目页面
	@GetMapping("/examineNumberPlannedReturns")
	@RequiresPermissions("sales:companyCustomer:companyCustomer")
	String examineNumberPlannedReturns() {
		return "/sales/companyCustomer/examineReimbursementSituation/examineNumberPlannedReturns";
	}

	// 本月计划回款客户数目列表页面
	@ResponseBody
	@GetMapping("/plannedReturn")
	@RequiresPermissions("sales:companyCustomer:companyCustomer")
	public PageUtils plannedReturn(@RequestParam Map<String, Object> params, Model model) {
		// 查询新客户列表数据
		params.put("offset", 1);
		params.put("limit", 10);
		Query query = new Query(params);
		List<ReceivableDO> companyCustomerList = receivableService.examinePlanCunstomerNumber(query);
		int total = receivableService.countExaminePlanCunstomerNumber(query);
		PageUtils pageUtils = new PageUtils(companyCustomerList, total);
		return pageUtils;
	}
	// *****************************END***************************************************************************************

	// *****************************列表页详情内查看********************************************************************************
	/**
	 * 查看
	 */
	@GetMapping("/examine/{customerId}")
	@RequiresPermissions("sales:companyCustomer:examine")
	String examine(@PathVariable("customerId") String customerId, Model model) {
		model.addAttribute("customerId", customerId);
		// 查询列表数据
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("offset", 0);
		params.put("limit", 2);
		Query query = new Query(params);
		Map<String, Object> paramsContact = new HashMap<String, Object>();
		paramsContact.put("offset", 0);
		paramsContact.put("limit", 5);
		Query queryContact = new Query(paramsContact);
		// 业务信息
		List<BusinessDO> businessList = businessService.list(query);
		model.addAttribute("businessList", businessList);
		// 项目信息
		List<ProjectDO> projectList = projectService.list(query);
		model.addAttribute("projectList", projectList);
		// 合同信息
		List<ContractDO> contractList = contractService.list(query);
		model.addAttribute("contractList", contractList);
		// 联系人信息
		List<CustomerContactDO> lianxiList = customerContactService.list(queryContact);
		model.addAttribute("lianxiList", lianxiList);
		// 详情页应收信息回款计划Receivable_Price字段求和
		List<ReceivableDO> price = receivableService.sumReceivablePrice(query);
			model.addAttribute("price", price);
		
		// 详情页应收信息实际回款
		List<ReceivedDO> receivedDO = receivedService.sumReceivedPrice(query);
		model.addAttribute("receivedDO", receivedDO);
		// 详情页应收信息回款率
		List<ReceivedDO> reimbursementRate = receivedService.reimbursementRate(query);
		model.addAttribute("reimbursementRate", reimbursementRate);
		// 审批总金额
		List<PurchaseDO> purchaseApprovalStatus = purchaseService.purchaseApprovalStatus(query);
		model.addAttribute("purchaseApprovalStatus", purchaseApprovalStatus);
		return "sales/companyCustomer/examineCompanyCustomer";
	}

	/**
	 * 业务信息更多
	 */
	@GetMapping("/examineBusiness")
	@RequiresPermissions("sales:companyCustomer:examineBusiness")
	String examineBusiness() {
		return "sales/companyCustomer/examineBusinessCustomer";
	}
	
	/**
	 * 项目信息更多
	 */
	@GetMapping("/examineProject")
	@RequiresPermissions("sales:companyCustomer:examineProject")
	String examineProject() {
		return "sales/companyCustomer/examineProjectCustomer";
	}

	/**
	 * 合同信息更多
	 */
	@GetMapping("/examineContract")
	@RequiresPermissions("sales:companyCustomer:examineContract")
	String examineContract() {
		return "sales/companyCustomer/examineContractCustomer";
	}

	/**
	 * 联系人信息更多
	 */
	@GetMapping("/examineContact")
	@RequiresPermissions("sales:companyCustomer:examineContact")
	String examineContact() {
		return "sales/companyCustomer/exaimeContactCustomer";
	}

	// **************************END****************************************************************************************
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sales:companyCustomer:edit")
	public R update(CompanyCustomerDO companyCustomer) {
		String customerIds = companyCustomer.getCustomerId();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("offset", 1);
		params.put("limit", 2);
		params.put("tId", customerIds);
		params.put("tableName", "sales_company_customer");
		companyCustomerService.update(companyCustomer);
		mainCopyPersonService.remove(params);
		if (!customerIds.equals("")) {
			MainCopyPersonDO mcp = new MainCopyPersonDO();
			String mainPersonId = companyCustomer.getMainPersonId();
			if (!"".equals(mainPersonId)) {
				String mainPersonIdArray[] = mainPersonId.split(",");
				for (int i = 0; i < mainPersonIdArray.length; i++) {
					mcp.setTId(companyCustomer.getCustomerId());
					mcp.setMainPerson(1);
					mcp.setEmployeeId(mainPersonIdArray[i]);
					mcp.setOperator(getUserId());
					mcp.setTableName("sales_company_customer");
					mainCopyPersonService.save(mcp);

				}
			}

			String copyPersonId = companyCustomer.getCopyPersonId();
			if (!"".equals(copyPersonId)) {
				String copyPersonIdArray[] = copyPersonId.split(",");
				int result = 0;
				for (int i = 0; i < copyPersonIdArray.length; i++) {
					mcp.setTId(companyCustomer.getCustomerId());
					mcp.setMainPerson(0);
					mcp.setEmployeeId(copyPersonIdArray[i]);
					mcp.setOperator(getUserId());
					mcp.setTableName("sales_company_customer");
					mainCopyPersonService.save(mcp);

				}
			}
			

		}
		return R.ok();
	}
	

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("sales:companyCustomer:batchRemove")
	public R remove(String customerId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("offset", 1);
		params.put("limit", 2);
		params.put("tId", customerId);
		params.put("tableName", "sales_company_customer");
		if (companyCustomerService.remove(customerId) > 0) {
			mainCopyPersonService.remove(params);
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("sales:companyCustomer:batchRemove")
	public R remove(@RequestParam("ids[]") String[] customerIds) {
		companyCustomerService.batchRemove(customerIds);
		return R.ok();
	}

	@ResponseBody
	@GetMapping("/listDic")
	public List<DictDO> listDic() {
		// 查询列表数据
		Map<String, Object> map = new HashMap<>(16);
		map.put("type", "");
		List<DictDO> dictList = companyCustomerService.listDic();
		return dictList;
	}

	@ResponseBody
	@GetMapping("/listAllDicByArea")
	public List<DictDO> listAllDicByArea(@PathVariable("areaId") String areaId) {
		// 查询列表数据
		Map<String, Object> map = new HashMap<>(16);
		map.put("type", "");
		List<DictDO> dictList = companyCustomerService.listAllDicByArea(areaId);
		return dictList;
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
		FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/" + fileName, new Date(),fileName);
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
//导入
	@ResponseBody
	@PostMapping("/importSubmit")
	@RequiresPermissions("sales:companyCustomer:import")
	R Import(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		String fileName = file.getOriginalFilename();
		fileName = FileUtil.renameToUUID(fileName);
		File datafile = null;
		try {
			FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath(), fileName);
			datafile = new File(bootdoConfig.getUploadPath() + fileName);
			
			// log数据保存
			long userid = getUserId(); // 用户id
			
			Map<String, Object> errorMsgs=companyCustomerService.Import(datafile, userid);
			if ("success".equals(errorMsgs.get("result"))) {
				return R.ok();
			} else {
				return R.error();
			}
		} catch (Exception e) {
			return R.error();
		}
	}

	/**
	 * 导出
	 */
	@RequestMapping(value = "/export")
	public @ResponseBody void export(@RequestParam(value = "administrative", required = false) String administrative,
			@RequestParam(value = "province", required = false) String province,
			@RequestParam(value = "city", required = false) String city,
			@RequestParam(value = "area", required = false) String area,
			@RequestParam(value = "customerName", required = false) String customerName,
			@RequestParam(value = "customerId", required = false) String customerId,
			@RequestParam(value = "customerSales", required = false) String customerSales,
			@RequestParam(value = "customerLevel", required = false) String customerLevel, HttpServletResponse response,
			HttpServletRequest request) throws ParseException {

		// String administrative1=request.getParameter(administrative);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// Date data;
		// Date date;
		// String endtime = null;
		// String starttime = null;

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("province", province);
		params.put("city", city);
		params.put("area", area);
		params.put("customerName",
				(customerName != null && !"".equals(customerName)) ? "%" + customerName + "%" : customerName);
		params.put("customerId", (customerId != null && !"".equals(customerId)) ? customerId + "%" : customerId);
		params.put("customerSales", customerSales);
		params.put("customerLevel", customerLevel);
		List<CompanyCustomerDO> list = companyCustomerService.getQuery(params);
		if (list.size() > 0) {
			System.out.println("---------------------list.size------------------->" + list.size());
			response.setContentType("application/binary;charset=UTF-8");
			try {
				ServletOutputStream out = response.getOutputStream();
				String fileName = new String((new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date()).getBytes(),
						"UTF-8");
				response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
				String[] titles = { "企业客户编号", "省份", "城市", "区县", "企业名称", "助记简称", "客户所有者", "销售负责人", "产品需求", "需求简要描述",
						"需求调研附件", "客户类别", "企业性质", "客户状态", "客户级别", "销售阶段", "客户内部阶段", "来源", "行业", "人员规模", "信用等级", "客户潜力",
						"员工数量", "上级单位", "公司简介", "拜访交通方式", "旧客户编号", "子公司名称", "热点客户", "热度", "热点客户分类", "预期成交金额", "热点说明",
						"开票名称", "纳税人税号", "开户行", "账号", "联系情况", "企业办公地址", "电话号码", "传真", "邮箱", "官网地址", "邮编", "企业负责人", "职务",
						"收费收缴率", "企业占全市热化率", "投诉率", "供热面积", "热力站数量", "蒸汽面积", "热水面积", "自有热源", "外购热源", "热耗", "水耗", "电耗",
						"未来一年新增规划", "未来两年新增规划", "未来三年新增规划", "备注", "创建人", "创建时间" };
				companyCustomerService.export(titles, out, list);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// *************** 多文件下载级相关  *********************************************************
	/**
	 * 执行删除文件的时候同时删除Customer_Attachment字段下的附件ID
	 */
	@ResponseBody
	@RequestMapping("/updateCustomerAttachment")
	@RequiresPermissions("sales:companyCustomer:edit")
	public R updateCustomerAttachment(CompanyCustomerDO companyCustomer) {
		companyCustomerService.updateCustomerAttachment(companyCustomer);
		return R.ok();
	}
	//根据ID查看附件列表
			@ResponseBody
			@GetMapping("/listId")
			@RequiresPermissions("sales:companyCustomer:companyCustomer")
			public PageUtils listId(@RequestParam("customerId")String customerId,@RequestParam Map<String, Object> params) {
//				String aa=request.getParameter("customerId");
				params.put("customerId", customerId);
				// 查询列表数据
				Query query = new Query(params);
				List<FileDO> sysFileList = sysFileService.listId(query);
				for (int i = 0; i < sysFileList.size(); i++) {
					FileDO a =sysFileList.get(i);
					String urlFile=a.getUrl();
					String suffix=urlFile.substring(urlFile.indexOf(".")+1);
					if (suffix.equals("xls")||suffix.equals("xlsx")) {
						a.setSuffix("1");//1代表xlsx表格
					}else if (suffix.equals("docx")) {
						a.setSuffix("2");//2代表word文档
					}else if(suffix.equals("avi")||suffix.equals("wma")||suffix.equals("rmvb")||suffix.equals("rm")||suffix.equals("flash")||suffix.equals("mp4")||suffix.equals("mid")||suffix.equals("3GP")){
						a.setSuffix("3");//3代表视频文件
					}else if(suffix.equals("jpg")||suffix.equals("png")||suffix.equals("gif")||suffix.equals("tif")||suffix.equals("psd")||suffix.equals("dng")||suffix.equals("cr2")||suffix.equals("nef")){
						a.setSuffix("4");//4代表图片
					}else if(suffix.equals("rar")||suffix.equals("zip")){
						a.setSuffix("5");//5代表压缩文件
					}else {
						a.setSuffix("6");//6除以判断的文件之外的文件
					}
//					System.out.println("************************************************");
//					System.out.println(urlFile.substring(urlFile.indexOf(".")+1));
//					System.out.println("************************************************");
				}
				int total = sysFileService.countId(query);
				PageUtils pageUtils = new PageUtils(sysFileList, total);
				return pageUtils;
			}
 // 根据文件名称下载相关代码
 	@ResponseBody
 	@RequestMapping("/down")
 	public void download(HttpServletResponse response,@RequestParam("fileName") String fileName) {
 		try {
 			// path是指欲下载的文件的路径。
 			String path = "C:/var/uploaded_files/"+fileName;

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
 			response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes(),"ISO-8859-1"));
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
 	
 	
 	
 	
 	  /**
     * 打包压缩下载文件
     */
    @RequestMapping(value = "/downLoadZipFile")
    @ResponseBody
    public void downLoadZipFile(HttpServletResponse response,@RequestParam("id")String id) throws IOException{
    	String[] ids=id.split(",");
        String zipName = "downLoadFile.zip";
        List<FileDO> fileList = sysFileService.downLoadListId(ids);//查询数据库中记录
        response.setContentType("APPLICATION/OCTET-STREAM");  
        response.setHeader("Content-Disposition","attachment; filename="+zipName);
        ZipOutputStream out = new ZipOutputStream(response.getOutputStream());
        try {
            for(Iterator<FileDO> it = fileList.iterator();it.hasNext();){
            	FileDO file = it.next();
                ZipUtils.doCompress("C:/var/uploaded_files/"+file.getFileName(), out);
                response.flushBuffer();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            out.close();
        }
    }
 	
 	
 	//模板下载
    @ResponseBody
 	@RequestMapping("/downloadTemplate")
    public void download(HttpServletResponse response,HttpServletRequest request) {
 		try {
 			
// 			 File files = new File(".\\src\\main\\resources\\downloadTemplate\\企业客户导入摸板.xls");
// 			System.out.println("getAbsolutePath:"+files.getAbsolutePath());  //getAbsolutePath()会将.认为是一个以.命名的文件
// 			System.out.println("getCanonicalPath:"+files.getCanonicalPath());//getCanonicalPath()得到的是一个规范路径没有.
// 			

 			File file = new File(".\\src\\main\\resources\\downloadTemplate\\企业客户导入摸板.xls");
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
 			response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes(),"ISO-8859-1"));
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
 	
 	
 	
 	
 	
 	
 	
 	
 	
    //全部文件大小
    @ResponseBody
	@RequestMapping("/getFileSize")
    public String getFileSize(String path,Model model,String fileSizeString){
    	path="C:\\var\\uploaded_files";
        //传入文件路径
        File file = new File(path);
        //测试此文件是否存在
        if(file.exists()){
            //如果是文件夹
            //这里只检测了文件夹中第一层 如果有需要 可以继续递归检测
        	DecimalFormat df1 = new DecimalFormat("0.00");
            if(file.isDirectory()){
                int size = 0;
                for(File zf : file.listFiles()){
                    if(zf.isDirectory()) continue;
                    size += zf.length();
                    //文件大小转换
                    if (size < 1024) {
                        fileSizeString = df1.format((double) size) + "B";
                     } else if (size < 1048576) {
                        fileSizeString = df1.format((double) size / 1024) + "K";
                     } else if (size < 1073741824) {
                        fileSizeString = df1.format((double) size / 1048576) + "M";
                     } else {
                        fileSizeString = df1.format((double) size / 1073741824) + "G";
                     }
                }
                System.out.println(file.getName()+fileSizeString);
            }else{
                System.out.println(file.getName()+" Size: "+(fileSizeString.length()/1024f)+"kb");
            }
        //如果文件不存在
        }else{
            System.out.println("此文件不存在");
        }
        return fileSizeString;
    }
 // ****************** END ************************************************************
}
