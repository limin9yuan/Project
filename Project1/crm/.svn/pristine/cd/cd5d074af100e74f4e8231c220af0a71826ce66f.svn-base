package com.bootdo.sales.controller;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
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
import org.springframework.web.multipart.MultipartFile;

import com.bootdo.sales.service.CustomerContactService;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.inner.domain.InnerOrgEmployeeDO;
import com.bootdo.sales.domain.BusinessDO;
import com.bootdo.sales.domain.CustomerContactDO;

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
	@GetMapping()
	@RequiresPermissions("sales:customerContact:customerContact")
	String CustomerContact(){
	    return "sales/customerContact/customerContact";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("sales:customerContact:customerContact")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CustomerContactDO> customerContactList = customerContactService.list(query);
		int total = customerContactService.count(query);
		PageUtils pageUtils = new PageUtils(customerContactList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("sales:customerContact:add")
	String add(){
	    return "sales/customerContact/add";
	}
	@GetMapping("/import")
	@RequiresPermissions("sales:customerContact:import")
	String importFile() {
		return "sales/customerContact/import";
	}
	/*@GetMapping("/export")
	@RequiresPermissions("sales:customerContact:export")
	String exportFile() {
		return "sales/customerContact/export";
	}*/
	@GetMapping("/edit/{contactId}")
	@RequiresPermissions("sales:customerContact:edit")
	String edit(@PathVariable("contactId") String contactId,Model model){
		model.addAttribute("contactId", contactId);
	    return "sales/customerContact/edit";
	}
	@RequestMapping("/edit_ajax/{contactId}")
	@RequiresPermissions("sales:customerContact:edit")
	@ResponseBody
	Map<String, Object> edit_ajax(@PathVariable("contactId") String contactId,Model model){
		CustomerContactDO customerContact = customerContactService.get(contactId);
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("customerContact", customerContact);
		
		return returnData;
	}
	/**
	 * 查看
	 */
	@GetMapping("/examine/{customerId}")
	@RequiresPermissions("sales:companyCustomer:examine")
	String examine(@PathVariable("customerId") String customerId, Model model) {
		model.addAttribute("customerId", customerId);
		return "sales/companyCustomer/examineContact";
	}
	
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("sales:customerContact:add")
	public R save( CustomerContactDO customerContact){
		customerContact.setContactOperator(getUserId());
		if(customerContactService.save(customerContact)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sales:customerContact:edit")
	public R update( CustomerContactDO customerContact){
		customerContactService.update(customerContact);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("sales:customerContact:batchRemove")
	public R remove( String contactId){
		if(customerContactService.remove(contactId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("sales:customerContact:batchRemove")
	public R remove(@RequestParam("ids[]") String[] contactIds){
		customerContactService.batchRemove(contactIds);
		return R.ok();
	}
	
	@ResponseBody
	@GetMapping("/listDic")
	public List<DictDO> listDic() {
		// 查询列表数据
		Map<String, Object> map = new HashMap<>(16);
		map.put("type", "");
		List<DictDO> dictList = customerContactService.listDic();
		return dictList;
	}
	
	@ResponseBody
	@PostMapping("/importSubmit")
	@RequiresPermissions("sales:customerContact:import")
	R  Import (@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		String fileName = file.getOriginalFilename();
		fileName = FileUtil.renameToUUID(fileName);
		File datafile = null;
		try {
			FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath(), fileName);
			datafile = new File( bootdoConfig.getUploadPath()+fileName);
		} catch (Exception e) {
			return R.error();
		}
			// log数据保存
			long userid = getUserId(); // 用户id
			customerContactService.Import(datafile, userid);
		
		return null;
	}
	/**
	 * 导出
	 */
	@RequestMapping(value = "/export")
	public  @ResponseBody void export(@RequestParam(value = "administrative", required = false) String administrative,
			@RequestParam(value = "province", required = false) String province,
			@RequestParam(value = "city", required = false) String city,
			@RequestParam(value = "area", required = false) String area,
			@RequestParam(value = "customerName", required = false) String customerName,
			@RequestParam(value = "customerId", required = false) String customerId, HttpServletResponse response,
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
		params.put("customerName", (customerName!=null && !"".equals(customerName))?"%"+customerName+"%":customerName );
		params.put("customerId", (customerId!=null && !"".equals(customerId))?customerId+"%":customerId);
		List<CustomerContactDO> list = customerContactService.getQuery(params);
		if(list.size()>0) {
			System.out.println("---------------------list.size------------------->" + list.size());
			response.setContentType("application/binary;charset=UTF-8");
			try {
				ServletOutputStream out = response.getOutputStream();
				String fileName = new String((new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date()).getBytes(),"UTF-8");
				response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
				String[] titles = { "联系人编号","姓名","性别","称谓","职务","负责业务","角色","企业客户编号","部门","岗位","婚否","年龄","家庭情况","毕业院校","专业技能","工作年限","工作经验","曾供职单位","上级领导","联系人状态","联系情况","客户所有者","销售负责人","手机","邮箱","工作电话","家庭电话","传真","家庭住址","微信","QQ","纪念日类别","纪念日","爱好","备注","创建人","创建时间"};
				customerContactService.export(titles, out, list);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	}
