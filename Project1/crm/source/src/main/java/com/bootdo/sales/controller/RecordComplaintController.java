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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bootdo.sales.domain.BusinessDO;
import com.bootdo.sales.domain.RecordComplaintDO;
import com.bootdo.sales.domain.RecordServiceDO;
import com.bootdo.sales.service.RecordComplaintService;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.FileType;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 客户投诉信息表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-11-28 18:06:03
 */
 
@Controller
@RequestMapping("/sales/recordComplaint")
public class RecordComplaintController extends BaseController {
	@Autowired
	private FileService sysFileService;
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	private RecordComplaintService recordComplaintService;
	
	@GetMapping()
	@RequiresPermissions("sales:recordComplaint:recordComplaint")
	String RecordComplaint(){
	    return "sales/recordComplaint/recordComplaint";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("sales:recordComplaint:recordComplaint")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		if (params.get("deliveryContent") != null && params.get("deliveryContent") != "") {
			params.put("deliveryContent", "%" + params.get("deliveryContent") + "%");
		}
		if (params.get("timeMin") != null && params.get("timeMin") != "") {
			params.put("timeMin", params.get("timeMin") + " 00:00:00");
		}
		if (params.get("timeMax") != null && params.get("timeMax") != "") {
			params.put("timeMax", params.get("timeMax") + " 23:59:59");
		}
		if (params.get("saleEmployee") != null && params.get("saleEmployee") != "") {
			params.put("saleEmployee", "%" + params.get("saleEmployee") + "%");
		}
		if (params.get("saleManager") != null && params.get("saleManager") != "") {
			params.put("saleManager", "%" + params.get("saleManager") + "%");
		}
		if (params.get("customerPhoneNumber") != null && params.get("customerPhoneNumber") != "") {
			params.put("customerPhoneNumber", "%" + params.get("customerPhoneNumber") + "%");
		}
		if (params.get("serverName") != null && params.get("serverName") != "") {
			params.put("serverName", "%" + params.get("serverName") + "%");
		}
		Query query = new Query(params);
		List<RecordComplaintDO> recordComplaintList = recordComplaintService.list(query);
		int total = recordComplaintService.count(query);
		PageUtils pageUtils = new PageUtils(recordComplaintList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("sales:recordComplaint:add")
	String add(){
	    return "sales/recordComplaint/add";
	}
	
	@GetMapping("/import")
	@RequiresPermissions("sales:recordComplaint:dataImport")
	String importFile() {
		return "sales/recordComplaint/import";
	}
	
	// edit数据绑定
	@RequestMapping("/edit_ajax/{complaintId}")
	@ResponseBody
	Map<String, Object> edit_ajax(@PathVariable("complaintId") String complaintId) {
		RecordComplaintDO recordComplaint = recordComplaintService.get(complaintId);
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("recordComplaint", recordComplaint);
		return returnData;
	}
	
	@GetMapping("/edit/{complaintId}")
	@RequiresPermissions("sales:recordComplaint:edit")
	String edit(@PathVariable("complaintId") String complaintId,Model model){
//		RecordComplaintDO complaintId = recordComplaintService.get(complaintId);
		model.addAttribute("complaintId", complaintId);
	    return "sales/recordComplaint/edit";
	}
	
	/**
	 * 保存（添加）
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("sales:recordComplaint:add")
	public R save( RecordComplaintDO recordComplaint){
		recordComplaint.setComplaintRecorder(getUserId());
		if(recordComplaintService.save(recordComplaint)>0){
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sales:recordComplaint:edit")
	public R update( RecordComplaintDO recordComplaint){
		recordComplaint.setComplaintRecorder(getUserId());
		recordComplaintService.update(recordComplaint);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("sales:recordComplaint:remove")
	public R remove( String complaintId){
		if(recordComplaintService.remove(complaintId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("sales:recordComplaint:batchRemove")
	public R remove(@RequestParam("ids[]") String[] complaintIds){
		recordComplaintService.batchRemove(complaintIds);
		return R.ok();
	}
	
	@ResponseBody
	@GetMapping("/listDic")
	public List<DictDO> listByType() {
		// 查询列表数据
		Map<String, Object> map = new HashMap<>(16);
		map.put("type", "");
		List<DictDO> dictList = recordComplaintService.listDic();
		return dictList;
	}
	
	@ResponseBody
	@GetMapping("/listDicxmbh")
	public List<DictDO> listByType1() {
		// 查询列表数据
		Map<String, Object> map = new HashMap<>(16);
		map.put("type", "");
		List<DictDO> dictList = recordComplaintService.listDicxmbh();
		return dictList;
	}
	
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
	
	/**
	 * 导入文件
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
		recordComplaintService.dataImport(datafile, userid);
		return null;
	}
	
	/**
	 * 导出
	 */
	@RequestMapping(value = "/export")
	public @ResponseBody void export(
			HttpServletResponse response, HttpServletRequest request) throws ParseException {
		Map<String, Object> params = new HashMap<String, Object>();
		List<RecordComplaintDO> list = recordComplaintService.getQuery(params);
		if (list.size() > 0) {
			System.out.println("---------------------list.size------------------->" + list.size());
			response.setContentType("application/binary;charset=UTF-8");
			try {
				ServletOutputStream out = response.getOutputStream();
				String fileName = new String((new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date()).getBytes(),"UTF-8");
				response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
				String[] titles = { "客户投诉记录编号", "项目编号", "企业客户编号", "使用产品", "投诉人姓名", "投诉时间", "所在单位", "投诉方式", "投诉内容", "客服发送资料","问题描述备注", "售后服务类型", "问题描述", "处理人","处理过程","处理结果","售后备注","客户电话","客户邮箱","操作人","修改时间","创建时间"};
				recordComplaintService.export(titles, out, list);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}