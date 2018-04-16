package com.bootdo.sales.controller;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bootdo.sales.domain.BusinessDO;
import com.bootdo.sales.service.BusinessService;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 业务信息表
 * 
 * @author xp
 * @email 1992lcg@163.com
 * @date 2017-11-21 17:28:12
 */

@Controller
@RequestMapping("/sales/business")
public class BusinessController extends BaseController {
	@Autowired
	private BusinessService businessService;
	@Autowired
	private BootdoConfig bootdoConfig;

	@GetMapping()
	@RequiresPermissions("sales:business:business")
	String Business() {
		return "sales/business/business";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("sales:business:business")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<BusinessDO> businessList = businessService.list(query);
		int total = businessService.count(query);
		PageUtils pageUtils = new PageUtils(businessList, total);
		return pageUtils;
	}
	
	@ResponseBody
	@GetMapping("/listDic")
	public List<DictDO> listByType() {
		// 查询列表数据
		Map<String, Object> map = new HashMap<>(16);
		map.put("type", "");
		List<DictDO> dictList = businessService.listDic();
		return dictList;
	}
	
	@ResponseBody
	@GetMapping("/listDicSale")
	public List<DictDO> listByType1() {
		// 查询列表数据
		Map<String, Object> map = new HashMap<>(16);
		map.put("type", "");
		List<DictDO> dictList = businessService.listDic();
		return dictList;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("sales:business:add")
	String add() {
		return "sales/business/add";
	}

	@GetMapping("/import")
	@RequiresPermissions("sales:business:dataImport")
	String importFile() {
		return "sales/business/import";
	}

	@RequestMapping("/edit_ajax/{businessId}")
	@ResponseBody
	Map<String, Object> edit_ajax(@PathVariable("businessId") String businessId) {
		BusinessDO business = businessService.get(businessId);
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("business", business);
		return returnData;
	}

	@GetMapping("/edit/{businessId}")
	@RequiresPermissions("sales:business:edit")
	String edit(@PathVariable("businessId") String businessId, Model model) {
		model.addAttribute("businessId", businessId);
		return "sales/business/edit";
	}

	/**
	 * 查看业务信息
	 */
	@GetMapping("/examineB/{customerId}")
	@RequiresPermissions("sales:business:business")
	String examineB(@PathVariable("customerId") String customerId, Model model) {
		model.addAttribute("customerId", customerId);
		
		return "sales/companyCustomer/examineBusiness";
	}
	
	/**
	 * 查看
	 */
	@GetMapping("/examine/{businessId}")
	@RequiresPermissions("sales:business:examine")
	String examine(@PathVariable("businessId") String businessId, Model model) {
		model.addAttribute("businessId", businessId);
		return "sales/business/examine";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("sales:business:add")
	public R save(BusinessDO business) {
		/*if (business.getBusinessId() == null) {
			String maxBusinessId = businessService.getMaxBusinessId();
			if (maxBusinessId == null) {
				maxBusinessId = "001";
			} else {
				maxBusinessId = maxBusinessId.substring(business.getCustomerId().length());
			}
			String BusinessId = String.valueOf(Long.parseLong(business.getCustomerId() + maxBusinessId)+1);
			business.setBusinessId(BusinessId);
		}*/
		if (businessService.save(business) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sales:business:edit")
	public R update(BusinessDO business) {
		businessService.update(business);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("sales:business:remove")
	public R remove(String businessId) {
		if (businessService.remove(businessId) > 0) {
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("sales:business:batchRemove")
	public R remove(@RequestParam("ids[]") String[] businessIds) {
		businessService.batchRemove(businessIds);
		return R.ok();
	}

	/**
	 * Import Microsoft Excel file.
	 */
	@ResponseBody
	@PostMapping("/dataImport")
	R upload2(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		String fileName = file.getOriginalFilename();
		fileName = FileUtil.renameToUUID(fileName);
		File datafile = null;
		try {
			FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath(), fileName);
			datafile = new File(bootdoConfig.getUploadPath() + fileName);
		} catch (Exception e) {
			return R.error();
		}
		long userid = getUserId(); // log数据保存 用户id
		businessService.dataImport(datafile, userid);
		return null;
	}
	
	/**
	 * Export Microsoft Excel file.
	 */
	@RequestMapping(value = "/export")
	public @ResponseBody void export(
			@RequestParam(value = "businessName", required = false) String Business_businessName,
			@RequestParam(value = "businessSales", required = false) String Business_businessSales,
			HttpServletResponse response, HttpServletRequest request) throws ParseException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("businessName", Business_businessName);
		params.put("businessSales", Business_businessSales);
		List<BusinessDO> list = businessService.getQuery(params);
		if (list.size() > 0) {
			System.out.println("---------------------list.size------------------->" + list.size());
			response.setContentType("application/binary;charset=UTF-8");
			try {
				ServletOutputStream out = response.getOutputStream();
				String fileName = new String((new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date()).getBytes(),"UTF-8");
				response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
				String[] titles = { "业务编号", "企业客户编号", "联系人编号", "业务名称", "业务类型", "业务状态", "销售负责人姓名", "旧业务编号", "业务描述", "备注",
						"业务修改人", "业务修改时间", "业务创建人", "业务创建人时间" };
				businessService.export(titles, out, list);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}