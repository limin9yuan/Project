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
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.wxcl.amy.utils.common.ResultMsg;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.controller.BaseController;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.domain.Tree;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.FileType;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.service.DeptService;
import com.bootdo.system.service.UserService;
import com.dx.client.model.contract.ContractBean;
import com.dx.client.model.contract.ContractDeliverBean;
import com.dx.client.model.contract.ContractElementBean;
import com.dx.client.model.contract.ContractEnclosureBean;
import com.dx.client.model.contract.ContractHtmlBean;
import com.dx.client.model.contract.ContractMaterialBean;
import com.dx.client.model.contract.ContractSuitBean;
import com.dx.client.model.purchase.RequireApplyItemBean;
import com.dx.service.contract.service.api.IContractService;
import com.dx.service.datacenter.service.api.ICompanyService;
import com.dx.service.file.service.api.IContractFileService;

/**
 * 合同起草
 * 
 * @author Administrator
 * @param
 * 
 * 
 */
@Controller
@RequestMapping("/ContractCreation/ContractCreation")
public class ContractController extends BaseController {
	private static final String CELL_TYPE_NUMERIC = null;
	@Autowired
	UserService userService;
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	private FileService sysFileService;
	@Autowired
	private DeptService sysDeptService;
	@Autowired
	private ICompanyService iCompanyService;// 供货公司接口
	@Autowired
	private IContractService iContractService;// 合同接口
	@Autowired
	private IContractFileService iContractFileService;

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

	// 查询页面
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
	@RequestMapping("/listData")
	@ResponseBody
	@RequiresPermissions("ContractCreation:ContractCreation:list")
	public PageInfo listData(@RequestParam Map<String, Object> params) {
		// 测试数据

		ResultMsg rsm = iContractService.search(params.get("pageNumber").toString(), params.get("pageSize").toString(),
				"", params);

		List<ContractBean> list = new ArrayList<ContractBean>();
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

		int total = 20;// 调用接口
		PageInfo pageInfo = new PageInfo(list, Integer.parseInt(params.get("pageNumber").toString()));
		pageInfo.setTotal(total);
		// 测试数据 end
//        PageInfo pageInfo = (PageInfo)rsm.getData();//调用接口
		return pageInfo;
	}

//	@RequestMapping("/edit_ajax/{recordId}")
//	@ResponseBody
//	Map<String, Object> edit_ajax(@PathVariable("recordId") String recordId) {
//		RecordDO record = recordService.get(recordId);
//		Map<String, Object> returnData = new HashMap<String, Object>();
//		returnData.put("record", record);
//		return returnData;
//	}
	// 修改页数据
	@GetMapping("/edit/{id}")
	@RequiresPermissions("ContractCreation:ContractCreation:edit")
	String edit(@PathVariable("id") String id, Model model) {
		// RecordDO record = recordService.get(recordId);
		ResultMsg data = iContractService.primary(id);
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
		map.put("suitCorpId", "6");
		map.put("suitCorpName", "研发部");
		map.put("deliverCompanyName", "供货公司0");
		map.put("deliverCompanyId", "1230");
		map.put("authorCorpName", "研发部");
		map.put("authorCorpId", "6");
		map.put("authorDeptName", "研发部");
		map.put("authorDeptId", "6");
		map.put("performUserName", "王健林");
		map.put("performUserId", "135");
		map.put("authorUserName", "admin");
		beanlist.add(map);

		data = new ResultMsg();
		data.setData(beanlist);
		model.addAttribute("beanlist", data.getData());
		model.addAttribute("suitCorpId", 6);
		model.addAttribute("authorCorpId", 6);
		model.addAttribute("performUserId", 135);
		return "/contract/ContractCreation/edit";
	}

	/**
	 * 查看页数据
	 */
	@GetMapping("/see/{id}")
	@RequiresPermissions("ContractCreation:ContractCreation:see")
	String see(@PathVariable("id") String id, Model model) {
		ResultMsg data = iContractService.primary(id);
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
		map.put("suitCorpId", "6");
		map.put("suitCorpName", "研发部");
		map.put("deliverCompanyName", "供货公司0");
		map.put("authorCorpName", "研发部");
		map.put("authorCorpId", "6");
		map.put("authorDeptName", "研发部");
		map.put("authorDeptId", "6");
		map.put("performUserName", "王健林");
		map.put("performUserId", "135");
		map.put("authorUserName", "admin");
		beanlist.add(map);

		data = new ResultMsg();
		data.setData(beanlist);
		model.addAttribute("beanlist", data.getData());
		model.addAttribute("id", id);
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
		ResultMsg remove = iContractService.remove(id);
		if ("1".equals(remove.getCode())) {
			return R.ok();
		}
		return R.error();
//		return R.ok();
	}

//	/**
//	 * 批量删除
//	 */
//	@PostMapping("/batchRemove")
//	@ResponseBody
//	@RequiresPermissions("ContractCreation:ContractCreation:batchRemove")
//	public R remove(@RequestParam("ids[]") String[] contractCode) {
////		recordService.batchRemove(contractCode);
//		return R.ok();
//	}

	@GetMapping("/selectTree")
	@ResponseBody
	@RequiresPermissions("ContractCreation:ContractCreation")
	List<Map<String, Object>> selectTree(@RequestParam Map<String, Object> params, Model model) {

		// 适用机构 下拉列表测试查询数据
		List<Map<String, Object>> selectTree = new ArrayList<>();// 调用接口
//		for (int i = 0; i < 4; i++) {
//			ContractSuitBean contractSuitBean = new ContractSuitBean();
//			Map<String, Object> contractSuitBeans = new HashMap<>();
//			// 测试数据 调用接口前使用
//			contractSuitBeans.put("id", "1" + i); // 注意*前台页面接受的必须是id、pid即name,前台引用jquery
//			contractSuitBeans.put("pId", "0"); // 树形下拉框插件MultipleTreeSelect,也必须是json数据*
//			contractSuitBeans.put("text", "火之国");
//			JSONObject js = new JSONObject(contractSuitBeans); // 将map类型转成json数据
//			selectTree.add(js); // 将转换完成的json数据add到selectTree
//		}
//		model.addAttribute("selectTree", selectTree); // 将数据传到前台
		return selectTree;
	}

	//
	@GetMapping("/editTree")
	@ResponseBody
	public Tree<DeptDO> editTree(@RequestParam String deptIds) {
		Tree<DeptDO> tree = new Tree<DeptDO>();
		tree = sysDeptService.getTree(deptIds);
		return tree;
	}

	@GetMapping("/tree")
	@ResponseBody
	public Tree<DeptDO> tree() {
		Tree<DeptDO> tree = new Tree<DeptDO>();
		tree = sysDeptService.getTree();
		return tree;
	}

	@GetMapping("/editUserTree")
	@ResponseBody
	public Tree<DeptDO> editUserTree(@RequestParam String deptIds) {
		Tree<DeptDO> tree = new Tree<DeptDO>();
		tree = sysDeptService.getTreeU(deptIds);
		return tree;
	}

	@GetMapping("/userTree")
	@ResponseBody
	public Tree<DeptDO> userTree() {
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
	public R update(@RequestParam Map<String, Object> params) {
		ContractBean contractBean = new ContractBean();
		ContractHtmlBean contractHtmlBean = new ContractHtmlBean();
		// 附件
		List<ContractEnclosureBean> fileBena = new ArrayList<ContractEnclosureBean>();
		// 表单
		List<ContractBean> contractBeanl = new ArrayList<ContractBean>();
		// 供货公司
		List<ContractDeliverBean> contractDeliverBeans = new ArrayList<ContractDeliverBean>();
		// 适用机构
		List<ContractSuitBean> contractSuitBeans = new ArrayList<ContractSuitBean>();
		// 富文本
		List<ContractElementBean> richTextList = new ArrayList<ContractElementBean>();
		// 合同物资列表
		List<ContractMaterialBean> contractMaterialBeansList = new ArrayList<ContractMaterialBean>();
		// 附件
		JSONArray file = JSONArray.fromObject(params.get("fileInformation"));
		for (int i = 0; i < file.size(); i++) {
			ContractEnclosureBean signupFormModel = (ContractEnclosureBean) JSONObject.toBean((JSONObject) file.get(i),
					ContractEnclosureBean.class);
			fileBena.add(signupFormModel);
		}
		// 表单
		JSONArray signupForm = JSONArray.fromObject(params.get("signupForm"));
		for (int i = 0; i < signupForm.size(); i++) {
			ContractBean signupFormModel = (ContractBean) JSONObject.toBean((JSONObject) signupForm.get(i),
					ContractBean.class);
			contractBeanl.add(signupFormModel);
		}
		// 供货公司
		JSONArray DeliverBeans = JSONArray.fromObject(params.get("jsonCompanyName"));
		for (int i = 0; i < DeliverBeans.size(); i++) {
			ContractDeliverBean DeliverModel = (ContractDeliverBean) JSONObject.toBean((JSONObject) DeliverBeans.get(i),
					ContractDeliverBean.class);
			contractDeliverBeans.add(DeliverModel);
		}
		// 适用机构
		JSONArray SuitBeans = JSONArray.fromObject(params.get("jsonSuitBeans"));
		for (int i = 0; i < SuitBeans.size(); i++) {
			ContractSuitBean SuitBeansModel = (ContractSuitBean) JSONObject.toBean((JSONObject) SuitBeans.get(i),
					ContractSuitBean.class);
			contractSuitBeans.add(SuitBeansModel);
		}
		// 合同物资列表
		JSONArray contractMaterialBeans = JSONArray.fromObject(params.get("contractMaterialBeans"));
		for (int i = 0; i < contractMaterialBeans.size(); i++) {
			ContractMaterialBean contractModel = (ContractMaterialBean) JSONObject
					.toBean((JSONObject) contractMaterialBeans.get(i), ContractMaterialBean.class);
			contractMaterialBeansList.add(contractModel);
		}
		// 富文本
		JSONArray array = JSONArray.fromObject(params.get("richTextKVJson"));
		for (int i = 0; i < array.size(); i++) {
			ContractElementBean contractModelR = (ContractElementBean) JSONObject.toBean((JSONObject) array.get(i),
					ContractElementBean.class);
			richTextList.add(contractModelR);
		}
		ContractMaterialBean contractMaterialBean = new ContractMaterialBean();
		ContractElementBean contractElementBean = new ContractElementBean();
		contractMaterialBean.setContractId((String) params.get("contractId"));
		contractBean.setContractMaterialBeans(contractMaterialBeansList);
		contractBean.setContractDeliverBeans(contractDeliverBeans);
		contractBean.setContractSuitBeans(contractSuitBeans);
		contractBean.setContractEnclosureBeans(fileBena);
		contractElementBean.setContractId((String) params.get("contractId"));
		contractHtmlBean.setContractElementBeans(richTextList);
		contractHtmlBean.setHtmlText(new StringBuffer(params.get("htmlText").toString()));

		ResultMsg msg = iContractService.save(contractBean);
		if ("1".equals(msg.getCode())) {
			return R.ok();
		}
		return R.error();

	}

	/**
	 * 保存
	 * 
	 * @param params
	 * @return
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("ContractCreation:ContractCreation:add")
	public R save(@RequestParam Map<String, Object> params) {
		ContractBean contractBean = new ContractBean();
		ContractHtmlBean contractHtmlBean = new ContractHtmlBean();
		// 附件
		List<ContractEnclosureBean> fileBena = new ArrayList<ContractEnclosureBean>();
		// 表单
		List<ContractBean> contractBeanl = new ArrayList<ContractBean>();
		// 供货公司
		List<ContractDeliverBean> contractDeliverBeans = new ArrayList<ContractDeliverBean>();
		// 适用机构
		List<ContractSuitBean> contractSuitBeans = new ArrayList<ContractSuitBean>();
		// 富文本
		List<ContractElementBean> richTextList = new ArrayList<ContractElementBean>();
		// 合同物资列表
		List<ContractMaterialBean> contractMaterialBeansList = new ArrayList<ContractMaterialBean>();

		// 附件
		JSONArray file = JSONArray.fromObject(params.get("fileInformation"));
		for (int i = 0; i < file.size(); i++) {
			ContractEnclosureBean signupFormModel = (ContractEnclosureBean) JSONObject.toBean((JSONObject) file.get(i),
					ContractEnclosureBean.class);
			fileBena.add(signupFormModel);
		}
		// 表单
		JSONArray signupForm = JSONArray.fromObject(params.get("signupForm"));
		for (int i = 0; i < signupForm.size(); i++) {
			ContractBean signupFormModel = (ContractBean) JSONObject.toBean((JSONObject) signupForm.get(i),
					ContractBean.class);
			contractBeanl.add(signupFormModel);
		}
		// 供货公司
		JSONArray DeliverBeans = JSONArray.fromObject(params.get("jsonCompanyName"));
		for (int i = 0; i < DeliverBeans.size(); i++) {
			ContractDeliverBean DeliverModel = (ContractDeliverBean) JSONObject.toBean((JSONObject) DeliverBeans.get(i),
					ContractDeliverBean.class);
			contractDeliverBeans.add(DeliverModel);
		}
		// 适用机构
		JSONArray SuitBeans = JSONArray.fromObject(params.get("jsonSuitBeans"));
		for (int i = 0; i < SuitBeans.size(); i++) {
			ContractSuitBean SuitBeansModel = (ContractSuitBean) JSONObject.toBean((JSONObject) SuitBeans.get(i),
					ContractSuitBean.class);
			contractSuitBeans.add(SuitBeansModel);
		}
		// 合同物资列表
		JSONArray contractMaterialBeans = JSONArray.fromObject(params.get("contractMaterialBeans"));
		for (int i = 0; i < contractMaterialBeans.size(); i++) {
			ContractMaterialBean contractModel = (ContractMaterialBean) JSONObject
					.toBean((JSONObject) contractMaterialBeans.get(i), ContractMaterialBean.class);
			contractMaterialBeansList.add(contractModel);
		}
		// 富文本
		JSONArray array = JSONArray.fromObject(params.get("richTextKVJson"));
		for (int i = 0; i < array.size(); i++) {
			ContractElementBean contractModelR = (ContractElementBean) JSONObject.toBean((JSONObject) array.get(i),
					ContractElementBean.class);
			richTextList.add(contractModelR);
		}
		contractBean.setContractMaterialBeans(contractMaterialBeansList);
		contractBean.setContractDeliverBeans(contractDeliverBeans);
		contractBean.setContractSuitBeans(contractSuitBeans);
		contractBean.setContractEnclosureBeans(fileBena);
//		contractBean.setContractHtmlBeans(contractHtmlBeans);
		contractHtmlBean.setContractElementBeans(richTextList);
		contractHtmlBean.setHtmlText(new StringBuffer(params.get("htmlText").toString()));
		ResultMsg msg = iContractService.save(contractBean);
		String ids = (String) msg.getData();
		System.out.println(msg.getCode());
		if ("1".equals(msg.getCode())) {
			R r = R.ok();
			r.put("ids", ids);
			return r;
		}
		return R.error();
	}

	// 项目名称页面
	@GetMapping("/project")
//	@RequiresPermissions("ContractCreation:ContractCreation:add")
	String project() {
		return "/contract/ContractCreation/projectList";
	}

	// 项目名称弹出列表数据
	@GetMapping("/projectList")
	@RequiresPermissions("ContractCreation:ContractCreation:add")
	@ResponseBody
	public PageInfo projectList(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		ResultMsg rsm = iContractService.search(params.get("pageNumber").toString(), params.get("pageSize").toString(),
				"", params);

		List<Map<String, Object>> projectList = new ArrayList<>();// 调用接口
		for (int i = 1; i <= 10; i++) {
			// 做测试数据 调用接口前使用 begin
			Map<String, Object> map = new HashMap<>();
			map.put("projectId", "100" + i);
			map.put("projectName", "物资编码" + i);
			projectList.add(map);
		}
		int total = 20;// 调用接口
		PageInfo pageInfo = new PageInfo(projectList, Integer.parseInt(params.get("pageNumber").toString()));
		pageInfo.setTotal(total);
		// 测试数据 end
//        PageInfo pageInfo = (PageInfo)rsm.getData();//调用接口
		return pageInfo;
	}

	// 供货公司页面
	@GetMapping("/contractDeliverBeans")
//	@RequiresPermissions("ContractCreation:ContractCreation:add")
	String contractDeliversList() {
		return "/contract/ContractCreation/relevantParty";
	}

	// 供货公司弹出页面数据
	@GetMapping("/contractDeliversList")
	@RequiresPermissions("ContractCreation:ContractCreation:add")
	@ResponseBody
	public PageInfo contractDeliversList(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		ResultMsg rsm = iCompanyService.search(params.get("pageNumber").toString(), params.get("pageSize").toString(),
				"", params);

		List<ContractDeliverBean> contractDeliverlist = new ArrayList<ContractDeliverBean>();// 调用接口
		for (int i = 0; i <= 10; i++) {
			ContractDeliverBean aa = new ContractDeliverBean();
			Date nowDate = new Date();
			aa.setDeliverCompanyId("123" + i);
			aa.setDeliverCompanyName("供货公司" + i);
			aa.setId("1" + i);
			aa.setCreateDate(nowDate);
			contractDeliverlist.add(aa);
		}
		int total = 20;// 调用接口
		PageInfo pageInfo = new PageInfo(contractDeliverlist, Integer.parseInt(params.get("pageNumber").toString()));
		pageInfo.setTotal(total);
		// 测试数据 end
//        PageInfo pageInfo = (PageInfo)rsm.getData();//调用接口
		return pageInfo;
	}

	// 合同物料数据
	@GetMapping("/materila/{id}")
	@RequiresPermissions("ContractCreation:ContractCreation:add")
	@ResponseBody
	public PageInfo materila(@RequestParam Map<String, Object> params, @PathVariable("id") String contractId) {
		ResultMsg lists = iContractService.getMaterialsByContractId(contractId);// 调用接口

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

		int total = 20;// 调用接口
		PageInfo pageInfo = new PageInfo(list);
//		pageInfo.setTotal(total);
		return pageInfo;
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

	// 根据ID查看附件列表
	@ResponseBody
	@GetMapping("/listId")
	@RequiresPermissions("sales:companyCustomer:companyCustomer")
	public PageInfo listId(@RequestParam("id") String contractId, @RequestParam Map<String, Object> params) {
		// 查询列表数据
		ResultMsg msg = iContractFileService.getEnclosures(contractId);

		List<Map<String, Object>> fileList = new ArrayList<Map<String, Object>>();

//		for (int i = 0; i < sysFileList.size(); i++) {
//			FileDO a =sysFileList.get(i);
//			String urlFile=a.getUrl();
//			String suffix=urlFile.substring(urlFile.indexOf(".")+1);
//			if (suffix.equals("xls")||suffix.equals("xlsx")) {
//				a.setSuffix("1");//1代表xlsx表格
//			}else if (suffix.equals("docx")) {
//				a.setSuffix("2");//2代表word文档
//			}else if(suffix.equals("avi")||suffix.equals("wma")||suffix.equals("rmvb")||suffix.equals("rm")||suffix.equals("flash")||suffix.equals("mp4")||suffix.equals("mid")||suffix.equals("3GP")){
//				a.setSuffix("3");//3代表视频文件
//			}else if(suffix.equals("jpg")||suffix.equals("png")||suffix.equals("gif")||suffix.equals("tif")||suffix.equals("psd")||suffix.equals("dng")||suffix.equals("cr2")||suffix.equals("nef")){
//				a.setSuffix("4");//4代表图片
//			}else if(suffix.equals("rar")||suffix.equals("zip")){
//				a.setSuffix("5");//5代表压缩文件
//			}else {
//				a.setSuffix("6");//6除以判断的文件之外的文件
//			}
//			System.out.println("************************************************");
//			System.out.println(urlFile.substring(urlFile.indexOf(".")+1));
//			System.out.println("************************************************");
//		}
		int total = 20;
		PageInfo pageInfo = new PageInfo(fileList);
//		PageInfo pageInfo=(PageInfo)msg.getData();
		pageInfo.setTotal(total);
		return pageInfo;
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
		Date date = new Date();
		FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/" + fileName, date);
		try {
			FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath(), fileName);
		} catch (Exception e) {
			return R.error();
		}
		int ids = sysFileService.save(sysFile);
		System.out.println(ids);
		if (ids > 0) {
			R r = R.ok();
//			r.put("customerAttachment", ids);
			r.put("fileNameUrl", sysFile.getUrl());
			r.put("fileType", FileType.fileType(fileName));
			r.put("fileDate", date);

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

	/**
	 * exls表格导入实现方法
	 * 
	 * @param file
	 * @return
	 */
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
				//获取标题行
				Row rowTitleName = sheet.getRow(0);
				
				ContractMaterialBean contractMaterialBean = new ContractMaterialBean(); // 售前项目信息表

				// 行不为空
				if (row != null & rowTitleName != null) {
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
					//标题行名称
					String titleName = "";
					// String agentCode = null;
					String companyName = null;
					// 遍历列
					for (int j = 0; j < cellCount; j++) {
						titleName = "";
						cellvalue = ""; // 清空之前之前取到的列的值
						// 获取到列的值
						Cell cell = row.getCell(j);
						//获取标题到列的值
						Cell cellTitleName = rowTitleName.getCell(j);
						// String value = "";
						if (cell != null & cellTitleName != null) {
							switch (cell.getCellType() & cellTitleName.getCellType()) {
							case XSSFCell.CELL_TYPE_FORMULA:
								break;
							case XSSFCell.CELL_TYPE_NUMERIC: {
								short format = cell.getCellStyle().getDataFormat();
								short formatTitleName = cellTitleName.getCellStyle().getDataFormat();
								if (format == 14 || format == 31 || format == 57 || format == 58 || formatTitleName == 14
										|| formatTitleName == 31 || formatTitleName == 57 || formatTitleName == 58) { // excel中的时间格式
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
									double value = cell.getNumericCellValue();
									double values = cellTitleName.getNumericCellValue();
									Date date = DateUtil.getJavaDate(value);
									Date dateTitleName = DateUtil.getJavaDate(values);
									cellvalue = sdf.format(date);
									titleName = sdf.format(dateTitleName);
								} else if (HSSFDateUtil.isCellDateFormatted(cell)) { // 如果是纯数字
									Date date = cell.getDateCellValue();
									DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
									cellvalue = formater.format(date);
								} else {
									 // 如果是Date类型则，取得该Cell的Date值 // 对2014-02-02格式识别不出是日期格式
									// 取得当前Cell的数值
									cellvalue = NumberToTextConverter.toText(cell.getNumericCellValue());
									titleName = cellTitleName.getStringCellValue();

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

						if ("组织机构".equals(titleName.trim())) {
							contractMaterialBean.setSuitCorpId(cellvalue);
						} else if ("物资编码".equals(titleName.trim())) {
							contractMaterialBean.setMaterilaCode(cellvalue);
						} else if ("物资名称".equals(titleName.trim())) {
							contractMaterialBean.setMaterialName(cellvalue);
						} else if ("父物资编码".equals(titleName.trim())) {
							contractMaterialBean.setParentId(cellvalue);
						} else if ("数量".equals(titleName.trim())) {
							if (cellvalue == null || cellvalue == "") {
								continue;
							} else {
								contractMaterialBean.setQty(Double.parseDouble(cellvalue));
							}
						} else if ("单价".equals(titleName.trim())) {
							if (cellvalue == null || cellvalue == "") {
								continue;
							} else {
								contractMaterialBean.setPrice(new BigDecimal(cellvalue));
							}
						} else if ("有效起始日期".equals(titleName.trim())) {
							if (cellvalue == null || cellvalue == "") { 
								continue;
							} else {
								SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
								contractMaterialBean.setDateFrom(formatter.parse(cellvalue));
							}
						} else if ("有效截止日期".equals(titleName.trim())) {
							if (cellvalue == null || cellvalue == "") {
								continue;
							} else {
								SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
								contractMaterialBean.setDateTo(formatter.parse(cellvalue));
							}
						} else if ("备注".equals(titleName.trim())) {
							contractMaterialBean.setRemark(cellvalue);
						}

//						contractMaterialBean.setSuitCorpId(cellvalue);
//						else if (j == 1) {
//							contractMaterialBean.setMaterilaCode(cellvalue);
//						} else if (j == 2) {
//							contractMaterialBean.setMaterialName(cellvalue);
//						} else if (j == 3) {
//							contractMaterialBean.setParentId(cellvalue);
//						} else if (j == 4) {
//							if (cellvalue == null || cellvalue == "") {
//								continue;
//							} else {
//								contractMaterialBean.setQty(Double.parseDouble(cellvalue));
//							}
//						} else if (j == 5) {
//							if (cellvalue == null || cellvalue == "") {
//								continue;
//							} else {
//								contractMaterialBean.setPrice(new BigDecimal(cellvalue));
//							}
//						} else if (j == 6) {
//							if (cellvalue == null || cellvalue == "") {
//								continue;
//							} else {
//								SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//								contractMaterialBean.setDateFrom(formatter.parse(cellvalue));
//							}
//						} else if (j == 7) {
//							if (cellvalue == null || cellvalue == "") {
//								continue;
//							} else {
//								SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//								contractMaterialBean.setDateTo(formatter.parse(cellvalue));
//							}
//						} else if (j == 8) {
//							contractMaterialBean.setRemark(cellvalue);
//						}

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
