package com.bootdo.sales.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.bootdo.common.domain.FieldDO;
import com.bootdo.common.service.FieldService;
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

import com.bootdo.sales.service.CustomerContactService;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.domain.MainCopyPersonDO;
import com.bootdo.common.domain.Tree;
import com.bootdo.common.service.MainCopyPersonService;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.sales.domain.CustomerContactDO;
import com.bootdo.sales.domain.CustomerDeptDO;
import com.bootdo.sales.domain.CustomerJobDO;

/**
 * 联系人信息表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-11-02 18:57:25
 */

@Controller
@RequestMapping("/sales/customerContact")
public class CustomerContactController extends BaseController {
	@Autowired
	private CustomerContactService customerContactService;
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	private MainCopyPersonService mainCopyPersonService;
	@Autowired
	private FieldService fieldService;

	@GetMapping()
	@RequiresPermissions("sales:customerContact:customerContact")
	String CustomerContact() {
		return "sales/customerContact/customerContact";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("sales:customerContact:customerContact")
	public PageUtils list(@RequestParam Map<String, Object> params,HttpServletRequest request) {
		// 查询列表数据
		params.put("contactOperator", (Long.toString(getUserId())));
		params.put("Identification", (getIdentification()));
		params.put("userName", (getUsername()));
		// 排序方式
		String orders = request.getParameter("sortOrder");
		params.put("order", orders);
		// 排序字段
		String sortnames = request.getParameter("sortName");
		params.put("sort", sortnames);
		
		Query query = new Query(params);
		List<CustomerContactDO> customerContactList = customerContactService.list(query);
		int total = customerContactService.count(query);
		PageUtils pageUtils = new PageUtils(customerContactList, total);
		return pageUtils;
	}
	/*****自定义字段相关*******/
	@ResponseBody
	@GetMapping("/listField")
	@RequiresPermissions("sales:customerContact:customerContact")
	public PageUtils listField(@RequestParam Map<String, Object> params){
		params.put("tableName","sales_customer_contact");
		params.put("t_id",params.get("t_id"));
		
		//查询列表数据
		Query query = new Query(params);
		List<FieldDO> fieldList = fieldService.list(query);
		int total = fieldService.count(query);
		PageUtils pageUtils = new PageUtils(fieldList, total);
		return pageUtils;
	}
	@GetMapping("/addField")
	@RequiresPermissions("sales:customerContact:addField")
	String addField() {
		return "common/customField/addContactField";
	}
	
	@GetMapping("/deptTree")
	@RequiresPermissions("sales:customerContact:customerContact")
	String deptTree() {
		return "sales/customerContact/deptTree";
	}
	
	@GetMapping("/tree")
	@ResponseBody
	public Tree<DeptDO> tree() {
		Tree<DeptDO> tree = new Tree<DeptDO>();
		tree = customerContactService.getTree();
		return tree;
	}
	@ResponseBody
	@PostMapping("/saveField")
	@RequiresPermissions("sales:customerContact:addField")
	public R saveField( FieldDO field,CustomerContactDO customerContact){
		String contactIds = customerContact.getContactId();
		if (!contactIds.equals("")){
			field.setT_id(String.valueOf(contactIds));
			field.setTableName("sales_customer_contact");
			fieldService.save(field);
			return R.ok();
		}
		return R.error();
	}

    @GetMapping("/editField/{id}")
    @RequiresPermissions("sales:customerContact:editField")
    String editField(@PathVariable("id") Integer id,Model model){
        FieldDO field = fieldService.get(id);
        model.addAttribute("field", field);
        return "common/customField/editContactField";
    }
	@ResponseBody
	@GetMapping("/editField_ajax/{id}")
	@RequiresPermissions("sales:customerContact:editField")
	Map<String, Object> editField_ajax(@PathVariable("id") Integer id){
		FieldDO field = fieldService.get(id);
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("fieldList", field);

		return returnData;

	}

	@ResponseBody
	@RequestMapping("/updateField")
	@RequiresPermissions("sales:customerContact:editField")
	public R update( FieldDO field){
		fieldService.update(field);
		return R.ok();
	}

	
	
	@PostMapping( "/removeField")
	@ResponseBody
	@RequiresPermissions("sales:customerContact:removeField")
	public R remove( Integer id){
		if(fieldService.remove(id)>0){
			return R.ok();
		}
		return R.error();
	}

	@PostMapping( "/batchRemoveField")
	@ResponseBody
	@RequiresPermissions("sales:customerContact:batchRemoveField")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		fieldService.batchRemove(ids);
		return R.ok();
	}


	@GetMapping("/add")
	@RequiresPermissions("sales:customerContact:add")
	String add() {
		return "sales/customerContact/add";
	}

	@GetMapping("/import")
	@RequiresPermissions("sales:customerContact:import")
	String importFile() {
		return "sales/customerContact/import";
	}

	/*
	 * @GetMapping("/export")
	 * 
	 * @RequiresPermissions("sales:customerContact:export") String exportFile() {
	 * return "sales/customerContact/export"; }
	 */
	@GetMapping("/edit/{contactId}")
	@RequiresPermissions("sales:customerContact:edit")
	String edit(@PathVariable("contactId") String contactId, Model model) {
		model.addAttribute("contactId", contactId);
		return "sales/customerContact/edit";
	}

	@RequestMapping("/edit_ajax/{contactId}")
	@RequiresPermissions("sales:customerContact:edit")
	@ResponseBody
	Map<String, Object> edit_ajax(@PathVariable("contactId") String contactId) {
		CustomerContactDO customerContact = customerContactService.get(contactId);
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("customerContact", customerContact);

		return returnData;
	}

	/**
	 * 查看信息
	 */
	@GetMapping("/examine/{contactId}")
	@RequiresPermissions("sales:customerContact:customerContact")
	String examine(@PathVariable("contactId") String contactId, Model model) {
		model.addAttribute("contactId", contactId);
		return "sales/customerContact/examineContact";
	}
	/**
	 * 查看联系人信息
	 */
	@GetMapping("/examineC/{customerId}")
	@RequiresPermissions("sales:customerContact:customerContact")
	String examineC(@PathVariable("customerId") String customerId, Model model) {
		model.addAttribute("customerId", customerId);
		return "sales/companyCustomer/examineContact";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("sales:customerContact:add")
	public R save(CustomerContactDO customerContact) {
		customerContact.setContactOperator(Long.toString(getUserId()));
		int contactIds = customerContactService.save(customerContact);
		if (contactIds > 0) {
			MainCopyPersonDO mcp = new MainCopyPersonDO();
			String mainPersonId = customerContact.getMainPersonId();
			if (!"".equals(mainPersonId)) {
				String mainPersonIdArray[] = mainPersonId.split(",");
				for (int i = 0; i < mainPersonIdArray.length; i++) {
					mcp.setTId(customerContact.getContactId());
					mcp.setMainPerson(1);
					mcp.setEmployeeId(mainPersonIdArray[i]);
					mcp.setOperator(getUserId());
					mcp.setTableName("sales_customer_contact");
					mainCopyPersonService.save(mcp);

				}
			}

			String copyPersonId = customerContact.getCopyPersonId();
			if (!"".equals(copyPersonId)) {
				String copyPersonIdArray[] = copyPersonId.split(",");
				for (int i = 0; i < copyPersonIdArray.length; i++) {
					mcp.setTId(customerContact.getContactId());
					mcp.setMainPerson(0);
					mcp.setEmployeeId(copyPersonIdArray[i]);
					mcp.setOperator(getUserId());
					mcp.setTableName("sales_customer_contact");
					mainCopyPersonService.save(mcp);
				}


			}
			R r = R.ok();
			r.put("contactId", contactIds);
			return r;
		}
		return R.error();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sales:customerContact:edit")
	public R update(CustomerContactDO customerContact) {
		String contactIds = customerContact.getContactId();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("offset",1);
		params.put("limit",2);
		params.put("tId",contactIds);
		params.put("tableName","sales_customer_contact");
		customerContactService.update(customerContact);
		mainCopyPersonService.remove(params);
		if (!contactIds.equals("")) {
			MainCopyPersonDO mcp = new MainCopyPersonDO();
			String mainPersonId = customerContact.getMainPersonId();
			if (!"".equals(mainPersonId)) {
				String mainPersonIdArray[] = mainPersonId.split(",");

				for (int i = 0; i < mainPersonIdArray.length; i++) {
					mcp.setTId(customerContact.getContactId());
					mcp.setMainPerson(1);
					mcp.setEmployeeId(mainPersonIdArray[i]);
					mcp.setOperator(getUserId());
					mcp.setTableName("sales_customer_contact");
					mainCopyPersonService.save(mcp);

				}
			}

			String copyPersonId = customerContact.getCopyPersonId();
			if (!"".equals(copyPersonId)) {
				String copyPersonIdArray[] = copyPersonId.split(",");
				int result = 0;
				for (int i = 0; i < copyPersonIdArray.length; i++) {
					mcp.setTId(customerContact.getContactId());
					mcp.setMainPerson(0);
					mcp.setEmployeeId(copyPersonIdArray[i]);
					mcp.setOperator(getUserId());
					mcp.setTableName("sales_customer_contact");
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
	@RequiresPermissions("sales:customerContact:batchRemove")
	public R remove(String contactId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("offset",1);
		params.put("limit",2);
		params.put("tId",contactId);
		params.put("tableName","sales_customer_contact");
		if (customerContactService.remove(contactId) > 0) {
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
	@RequiresPermissions("sales:customerContact:batchRemove")
	public R remove(@RequestParam("ids[]") String[] contactIds) {
		customerContactService.batchRemove(contactIds);
		return R.ok();
	}

	@ResponseBody
	@GetMapping("/listDic/{customerId}")
	public List<DictDO> listDic(@PathVariable("customerId")String customerId,@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Map<String, Object> map = new HashMap<>(16);
		map.put("type", "");
		map.put("customerId", customerId);
		List<DictDO> dictList = customerContactService.listDic(map);
		return dictList;
	}
	@ResponseBody
	@GetMapping("/listDicContact")
	public List<DictDO> listDic() {
		// 查询列表数据
		Map<String, Object> map = new HashMap<>(16);
		map.put("type", "");
		List<DictDO> dictList = customerContactService.listDicContact();
		return dictList;
	}
	@ResponseBody
	@GetMapping("/listDicjob/{customerId}")
	public List<DictDO> listDicjob(@PathVariable("customerId")String customerId,@RequestParam Map<String, Object> params) {
		System.out.println("********************************************************");
		System.out.println(customerId);
		// 查询列表数据
		Map<String, Object> map = new HashMap<>(16);
		map.put("type", "");
		map.put("customerId", customerId);
		List<DictDO> dictList = customerContactService.listDicjob(map);
		return dictList;
	}
	@ResponseBody
	@PostMapping("/importSubmit")
	@RequiresPermissions("sales:customerContact:import")
	R Import(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		String fileName = file.getOriginalFilename();
		fileName = FileUtil.renameToUUID(fileName);
		File datafile = null;
		try {
			FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath(), fileName);
			datafile = new File(bootdoConfig.getUploadPath() + fileName);
			
			// log数据保存
			long userid = getUserId(); // 用户id
			
			Map<String, Object> errorMsgs=customerContactService.Import(datafile, userid);
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
			@RequestParam(value = "contactName", required = false) String contactName,
			@RequestParam(value = "businessName", required = false) String businessName,
			@RequestParam(value = "projectName", required = false) String projectName,
			@RequestParam(value = "contactSales", required = false) String contactSales,
			
			HttpServletResponse response,
			HttpServletRequest request) throws ParseException {

		// String administrative1=request.getParameter(administrative);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date data;
		Date date;
		String endtime = null;
		String starttime = null;

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("province", province);
		params.put("city", city);
		params.put("area", area);
		params.put("customerName",
				(customerName != null && !"".equals(customerName)) ? "%" + customerName + "%" : customerName);
		params.put("customerId", (customerId != null && !"".equals(customerId)) ? customerId + "%" : customerId);
		params.put("contactName", (contactName!=null && !"".equals(contactName))?contactName+"%":contactName);
		params.put("businessName", (businessName!=null && !"".equals(businessName))?businessName+"%":businessName);
		params.put("projectName", (projectName!=null && !"".equals(projectName))?projectName+"%":projectName);
		params.put("contactSales", (contactSales!=null && !"".equals(contactSales))?contactSales+"%":contactSales);
		List<CustomerContactDO> list = customerContactService.getQuery(params);
		if (list.size() > 0) {
			System.out.println("---------------------list.size------------------->" + list.size());
			response.setContentType("application/binary;charset=UTF-8");
			try {
				ServletOutputStream out = response.getOutputStream();
				String fileName = new String((new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date()).getBytes(),
						"UTF-8");
				response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
				String[] titles = { "联系人编号", "姓名", "性别", "称谓", "职务", "负责业务", "角色", "企业客户编号", "部门", "岗位", "婚否", "出生日期",
						"年龄", "家庭情况", "毕业院校", "专业技能", "工作年限", "工作经验", "曾供职单位", "上级领导", "联系人状态", "联系情况", "客户所有者",
						"销售负责人", "手机", "邮箱", "工作电话", "家庭电话", "传真", "家庭住址", "微信", "QQ", "纪念日类别", "纪念日", "爱好", "备注",
						"创建人", "创建时间", "修改时间" };
				customerContactService.export(titles, out, list);
			} catch (Exception e) {
				e.printStackTrace();
			}
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

 			File file = new File("./src/main/resources/downloadTemplate/联系人信息导入模板.xls");
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
    //
    @ResponseBody
	@GetMapping("/listJob")
	@RequiresPermissions("sales:customerContact:customerContact")
	public PageUtils listJob(@RequestParam Map<String, Object> params,@RequestParam("customerId")String customerId) {
    	params.put("customerId", customerId);
    	Query query=new Query(params);
    	List<CustomerJobDO> list=customerContactService.listJob(query);
    	PageUtils pageUtils=new PageUtils(list, 0);
    	return pageUtils;
    }
}
