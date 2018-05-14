package com.bootdo.sales.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.w3c.dom.events.EventException;

import com.bootdo.sales.domain.BusinessDO;
import com.bootdo.sales.domain.RecordServiceDO;
import com.bootdo.sales.service.RecordServiceService;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

//import javafx.scene.control.Alert;
import sun.security.jca.ServiceId;

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
 * 客服记录信息表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-11-28 09:25:19
 */
@Controller
@RequestMapping("/sales/recordService")
public class RecordServiceController extends BaseController {
	@Autowired
	private FileService sysFileService;
	@Autowired
	private RecordServiceService recordServiceService;
	@Autowired
	private BootdoConfig bootdoConfig;

	@GetMapping()
	@RequiresPermissions("sales:recordService:recordService")
	String RecordService() {
		return "sales/recordService/recordService";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("sales:recordService:recordService")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		params.put("serviceRecorder", getUserId());
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
		List<RecordServiceDO> recordServiceList = recordServiceService.list(query);
		int total = recordServiceService.count(query);
		PageUtils pageUtils = new PageUtils(recordServiceList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("sales:recordService:add")
	String add() {
		return "sales/recordService/add";
	}

	@GetMapping("/import")
	@RequiresPermissions("sales:recordService:dataImport")
	String importFile() {
		return "sales/recordService/import";
	}

	// edit数据绑定
	@RequestMapping("/edit_ajax/{serviceId}")
	@ResponseBody
	Map<String, Object> edit_ajax(@PathVariable("serviceId") String serviceId) {
		RecordServiceDO service = recordServiceService.get(serviceId);
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("service", service);
		return returnData;
	}
	
	@GetMapping("/edit/{serviceId}")
	@RequiresPermissions("sales:recordService:edit")
	String edit(@PathVariable("serviceId") String serviceId, Model model) {
		model.addAttribute("serviceId", serviceId);
		return "sales/recordService/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("sales:recordService:add")
	public R save(RecordServiceDO recordService) {
		recordService.setServiceRecorder(getUserId());
		if (recordServiceService.save(recordService) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sales:recordService:edit")
	public R update(RecordServiceDO recordService) {
		recordService.setServiceRecorder(getUserId());
		recordServiceService.update(recordService);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("sales:recordService:remove")
	public R remove(String serviceId) {
		if (recordServiceService.remove(serviceId) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("sales:recordService:batchRemove")
	public R remove(@RequestParam("ids[]") String[] serviceIds) {
		recordServiceService.batchRemove(serviceIds);
		return R.ok();
	}

	/**
	 * 查询列表数据
	 */
	@ResponseBody
	@GetMapping("/listDic")
	public List<DictDO> listByType() {
		Map<String, Object> map = new HashMap<>(16);
		map.put("type", "");
		List<DictDO> dictList = recordServiceService.listDic();
		return dictList;
	}

	@ResponseBody
	@GetMapping("/listDicxmbh")
	public List<DictDO> listByType1() {
		// 查询列表数据
		Map<String, Object> map = new HashMap<>(16);
		map.put("type", "");
		List<DictDO> dictList = recordServiceService.listDicxmbh();
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
		recordServiceService.dataImport(datafile, userid);
		return null;
	}
}
