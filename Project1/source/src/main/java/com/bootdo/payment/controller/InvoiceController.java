package com.bootdo.payment.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.bootdo.payment.domain.InvoiceDO;
import com.bootdo.payment.service.InvoiceService;
import com.bootdo.project.domain.ProjectDO;
import com.bootdo.sales.domain.RequirementCategoryDO;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.FileType;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.contract.domain.ContractDO;

/**
 * 开票信息表
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-12-05 14:35:41
 */
 
@Controller
@RequestMapping("/payment/invoice")
public class InvoiceController extends BaseController {
	@Autowired
	private InvoiceService invoiceService;
	@Autowired
	private FileService sysFileService;
	@Autowired
	private BootdoConfig bootdoConfig;
	
	@GetMapping()
	@RequiresPermissions("payment:invoice:invoice")
	String Invoice(){
	    return "payment/invoice/invoice";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("payment:invoice:invoice")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		if (params.get("projectOwner") != null && !"".equals(params.get("projectOwner"))) {
			params.put("projectOwner", "%" + (String) params.get("projectOwner") + "%");
		}
        Query query = new Query(params);
		List<InvoiceDO> invoiceList = invoiceService.list(query);
		int total = invoiceService.count(query);
		PageUtils pageUtils = new PageUtils(invoiceList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("payment:invoice:add")
	String add(){
	    return "payment/invoice/add";
	}
	@RequestMapping("/edit_ajax/{invoiceId}")
	@ResponseBody
	Map<String, Object> edit_ajax(@PathVariable("invoiceId") String invoiceId) {
		InvoiceDO invoice = invoiceService.get(invoiceId);
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("invoice", invoice);
		return returnData;
	}
	@GetMapping("/edit/{invoiceId}")
	@RequiresPermissions("payment:invoice:edit")
	String edit(@PathVariable("invoiceId") String invoiceId,Model model){
		//InvoiceDO invoice = invoiceService.get(invoiceId);
		model.addAttribute("invoiceId", invoiceId);
	    return "payment/invoice/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("payment:invoice:add")
	public R save( InvoiceDO invoice){
		invoice.setInvoiceOperator(getUserId());
		if(invoiceService.save(invoice)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("payment:invoice:edit")
	public R update( InvoiceDO invoice){
		invoice.setInvoiceOperator(getUserId());
		invoiceService.update(invoice);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("payment:invoice:remove")
	public R remove( String invoiceId){
		if(invoiceService.remove(invoiceId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("payment:invoice:batchRemove")
	public R remove(@RequestParam("ids[]") String[] invoiceIds){
		invoiceService.batchRemove(invoiceIds);
		return R.ok();
	}
	
	@RequestMapping("/getContractId/{contractId}")
	@ResponseBody
	Map<String, Object> getContractId(@PathVariable("contractId") String contractId) {
		ContractDO contract = invoiceService.getContractId(contractId);
		Map<String, Object> returnData = new HashMap<String, Object>();
		System.out.println(contract.getContractReceivablePrice());
		returnData.put("contract", contract);
		return returnData;
	}
	/**
	 * 上传文件
	 * @param file
	 * @param request
	 * @return
	 */
	@ResponseBody
	@PostMapping("/upload")
	R upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		String fileName = file.getOriginalFilename();
		fileName = FileUtil.renameToUUID(fileName);
		FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/" + fileName, new Date());
		try {
			FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath(), fileName);
		} catch (Exception e) {
			return R.error();
		}
		if (sysFileService.save(sysFile) > 0) {
			return R.ok().put("fileName", sysFile.getUrl());
		}
		return null;
	}
}
