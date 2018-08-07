package com.bootdo.payment.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.*;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;

import com.bootdo.common.domain.MainCopyPersonDO;
import com.bootdo.common.service.MainCopyPersonService;
import com.bootdo.contract.domain.ContractProjectDO;
import com.bootdo.contract.service.ContractProjectService;
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
import com.bootdo.sales.domain.CompanyCustomerDO;
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
import com.bootdo.common.utils.ZipUtils;
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
	@Autowired
	private MainCopyPersonService mainCopyPersonService;
	@Autowired
	private ContractProjectService contractProjectService;

	@GetMapping()
	@RequiresPermissions("payment:invoice:invoice")
	String Invoice() {
		return "payment/invoice/invoice";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("payment:invoice:invoice")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		params.put("userId", getUserId());
		params.put("userName", getUsername());
		params.put("tableName", "invoice");
		// 查询列表数据
		params.put("invoiceOperator", (getUserId()));
		params.put("Identification", (getIdentification()));
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
	String add() {
		return "payment/invoice/add";
	}

	@RequestMapping("/edit_ajax/{invoiceId}")
	@ResponseBody
	Map<String, Object> edit_ajax(@PathVariable("invoiceId") String invoiceId) {
		InvoiceDO invoice = invoiceService.get(invoiceId);
		String contractId = invoice.getContractId();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("offset",1);
		params.put("limit",2);
		params.put("contractId", contractId);
		Query queryGetMultiProject = new Query(params);
		List<ContractProjectDO> getMultiProject = contractProjectService.getMultiProject(queryGetMultiProject);
		ArrayList<String> projectNames = new ArrayList<>();
		for (int i = 0; i<getMultiProject.size(); i++){
			projectNames.add(getMultiProject.get(i).getProjectName());
		}
		invoice.setProjectId(StringUtils.strip(projectNames.toString(),"[]"));
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("invoice", invoice);
		return returnData;
	}

	@GetMapping("/edit/{invoiceId}")
	@RequiresPermissions("payment:invoice:edit")
	String edit(@PathVariable("invoiceId") String invoiceId, Model model) {
		// InvoiceDO invoice = invoiceService.get(invoiceId);
		model.addAttribute("invoiceId", invoiceId);
		return "payment/invoice/edit";
	}

	@GetMapping("/view/{invoiceId}")
	@RequiresPermissions("payment:invoice:edit")
	String view(@PathVariable("invoiceId") String invoiceId, Model model) {
		// InvoiceDO invoice = invoiceService.get(invoiceId);
		model.addAttribute("invoiceId", invoiceId);
		return "payment/invoice/view";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("payment:invoice:add")
	public R save(InvoiceDO invoice) {
		invoice.setInvoiceOperator(getUserId());
		if (invoice.getInvoiceReceiverTime().equals("")) {
			invoice.setInvoiceReceiverTime(null);
		}
		String invoiceId = invoice.getInvoiceId();
		InvoiceDO invoiceCheck = invoiceService.get(invoiceId);
		if (invoiceCheck != null){
			return R.error("发票序号已存在，请重新录入");
		}
		String invoiceIds = invoiceService.save(invoice);
		if (!"0".equals(invoiceIds)) {
			MainCopyPersonDO mcp = new MainCopyPersonDO();
			String mainPersonId = invoice.getMainPersonId();
			if (!"".equals(mainPersonId)) {
				String mainPersonIdArray[] = mainPersonId.split(",");
				for (int i = 0; i < mainPersonIdArray.length; i++) {
					mcp.setTId(invoice.getInvoiceId());
					mcp.setMainPerson(1);
					mcp.setEmployeeId(mainPersonIdArray[i]);
					mcp.setOperator(getUserId());
					mcp.setTableName("invoice");
					mainCopyPersonService.save(mcp);

				}
			}

			String copyPersonId = invoice.getCopyPersonId();
			if (!"".equals(copyPersonId)) {
				String copyPersonIdArray[] = copyPersonId.split(",");
				for (int i = 0; i < copyPersonIdArray.length; i++) {
					mcp.setTId(invoice.getInvoiceId());
					mcp.setMainPerson(0);
					mcp.setEmployeeId(copyPersonIdArray[i]);
					mcp.setOperator(getUserId());
					mcp.setTableName("invoice");
					mainCopyPersonService.save(mcp);
				}

			}
			R r = R.ok();
			r.put("invoiceId", invoiceIds);
			return r;
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("payment:invoice:edit")
	public R update(InvoiceDO invoice) {
		if (invoice.getInvoiceReceiverTime().equals("")) {
			invoice.setInvoiceReceiverTime(null);
		}
		invoice.setInvoiceOperator(getUserId());
		String invoiceIds = invoice.getInvoiceId();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("offset", 1);
		params.put("limit", 2);
		params.put("tId", invoiceIds);
		params.put("tableName", "invoice");
		invoiceService.update(invoice);
		mainCopyPersonService.remove(params);
		if (!invoiceIds.equals("")) {
			MainCopyPersonDO mcp = new MainCopyPersonDO();
			String mainPersonId = invoice.getMainPersonId();
			if (!"".equals(mainPersonId)) {
				String mainPersonIdArray[] = mainPersonId.split(",");

				for (int i = 0; i < mainPersonIdArray.length; i++) {
					mcp.setTId(invoiceIds);
					mcp.setMainPerson(1);
					mcp.setEmployeeId(mainPersonIdArray[i]);
					mcp.setOperator(getUserId());
					mcp.setTableName("invoice");
					mainCopyPersonService.save(mcp);

				}
			}

			String copyPersonId = invoice.getCopyPersonId();
			if (!"".equals(copyPersonId)) {
				String copyPersonIdArray[] = copyPersonId.split(",");
				for (int i = 0; i < copyPersonIdArray.length; i++) {
					mcp.setTId(invoiceIds);
					mcp.setMainPerson(0);
					mcp.setEmployeeId(copyPersonIdArray[i]);
					mcp.setOperator(getUserId());
					mcp.setTableName("invoice");
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
	@RequiresPermissions("payment:invoice:remove")
	public R remove(String invoiceId) {
		if (invoiceService.remove(invoiceId) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("payment:invoice:batchRemove")
	public R remove(@RequestParam("ids[]") String[] invoiceIds) {
		invoiceService.batchRemove(invoiceIds);
		return R.ok();
	}

	@RequestMapping("/getContractId/{contractId}")
	@ResponseBody
	Map<String, Object> getContractId(@PathVariable("contractId") String contractId) {
		ContractDO contract = invoiceService.getContractId(contractId);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("offset",1);
		params.put("limit",2);
		params.put("contractId", contractId);
		Query queryGetMultiProject = new Query(params);
		List<ContractProjectDO> getMultiProject = contractProjectService.getMultiProject(queryGetMultiProject);
		ArrayList<String> projectNames = new ArrayList<>();
		for (int i = 0; i<getMultiProject.size(); i++){
			projectNames.add(getMultiProject.get(i).getProjectName());
		}
		contract.setProjectId(StringUtils.strip(projectNames.toString(),"[]"));
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("contract", contract);
		return returnData;
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
		String fileName = file.getOriginalFilename();
		// fileName = FileUtil.renameToUUID(fileName);
		FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/" + fileName, new Date(), fileName);
		try {
			FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath(), fileName);
		} catch (Exception e) {
			return R.error();
		}
		int ids = sysFileService.save(sysFile);
		System.out.println(ids);
		if (ids > 0) {
			R r = R.ok();
			r.put("invoiceAttachment", ids);
			r.put("fileName", sysFile.getUrl());
			return r;
			// return R.ok().put("fileName", sysFile.getUrl());
		}
		return null;
	}

	// *************** 多文件下载级相关
	// *********************************************************
	/**
	 * 执行删除文件的时候同时删除Invoice_Attachment字段下的附件ID
	 */
	@ResponseBody
	@RequestMapping("/updateInvoiceAttachment")
	@RequiresPermissions("payment:invoice:edit")
	public R updateInvoiceAttachment(InvoiceDO invoice) {
		invoiceService.updateInvoiceAttachment(invoice);
		return R.ok();
	}

	// 根据ID查看附件列表
	@ResponseBody
	@GetMapping("/listId")
	@RequiresPermissions("payment:invoice:invoice")
	public PageUtils listId(@RequestParam("invoiceId") String invoiceId, @RequestParam Map<String, Object> params) {
		// String aa=request.getParameter("customerId");
		params.put("invoiceId", invoiceId);
		// 查询列表数据
		Query query = new Query(params);
		List<FileDO> sysFileList = sysFileService.listInvoiceAttachment(query);
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
//			System.out.println("************************************************");
//			System.out.println(urlFile.substring(urlFile.indexOf(".")+1));
//			System.out.println("************************************************");
		}
		int total = sysFileService.listInvoiceAttachmentCount(query);
		PageUtils pageUtils = new PageUtils(sysFileList, total);
		return pageUtils;
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

	/**
	 * 打包压缩下载文件
	 */
	@RequestMapping(value = "/downLoadZipFile")
	@ResponseBody
	public void downLoadZipFile(HttpServletResponse response, @RequestParam("id") String id) throws IOException {
		String[] ids = id.split(",");
		String zipName = "downLoadFile.zip";
		List<FileDO> fileList = sysFileService.downLoadListId(ids);// 查询数据库中记录
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename=" + zipName);
		ZipOutputStream out = new ZipOutputStream(response.getOutputStream());
		try {
			for (Iterator<FileDO> it = fileList.iterator(); it.hasNext();) {
				FileDO file = it.next();
				ZipUtils.doCompress("C:/var/uploaded_files/" + file.getFileName(), out);
				response.flushBuffer();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

}
