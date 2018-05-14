package com.bootdo.payment.controller;

import java.io.File;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bootdo.payment.domain.ContractApprovalDO;
import com.bootdo.payment.service.ContractApprovalService;
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
 * 合同审批 
 * 
 * @小平
 * @email 1992lcg@163.com
 * @date 2018-1-29
 */

@Controller
@RequestMapping("/payment/contractApproval")
public class ContractApprovalController extends BaseController {
	@Autowired
	private ContractApprovalService contractApprovalService;
	@Autowired
	private FileService sysFileService;
	@Autowired
	private BootdoConfig bootdoConfig;

	@GetMapping()
	@RequiresPermissions("payment:contractApproval:contractApproval")
	String Contract() {
		return "payment/contractApproval/contractApproval";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("payment:contractApproval:contractApproval")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		params.put("contractOperator", (getUserId()));
		params.put("Identification", (getIdentification()));
		if (params.get("contractName") != null && !"".equals(params.get("contractName"))) {
			params.put("contractName", "%" + (String) params.get("contractName") + "%");
		}
		Query query = new Query(params);
		List<ContractApprovalDO> contractApprovalList = (List<ContractApprovalDO>) contractApprovalService.list(query);
		int total = contractApprovalService.count(query);
		PageUtils pageUtils = new PageUtils(contractApprovalList, total);
		return pageUtils;
	}

	@ResponseBody
	@GetMapping("/listDic")
	public List<DictDO> listByType() {
		// 查询列表数据
		Map<String, Object> map = new HashMap<>(16);
		map.put("type", "");
		List<DictDO> dictList = contractApprovalService.listDic();
		return dictList;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("payment:contractApproval:add")
	String add() {
		return "payment/contractApproval/add";
	}

	@GetMapping("/import")
	@RequiresPermissions("payment:contractApproval:dataImport")
	String importFile() {
		return "payment/contractApproval/import";
	}

	@GetMapping("/edit/{contractId}")
	@RequiresPermissions("payment:contractApproval:edit")
	String edit(@PathVariable("contractId") String contractId, Model model) {
		ContractApprovalDO contractApproval = contractApprovalService.get(contractId);
		model.addAttribute("payment", contractApproval);
		return "payment/contractApproval/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("payment:contractApproval:add")
	public R save(ContractApprovalDO contractApproval) {
		contractApproval.setContractId(getUserId());
		if (contractApprovalService.save(contractApproval) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("payment:contractApproval:edit")
	public R update(ContractApprovalDO contractApproval) {
		contractApproval.setContractId(getUserId());
		contractApprovalService.update(contractApproval);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("payment:contractApproval:remove")
	public R remove(String contractId) {
		if (contractApprovalService.remove(contractId) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("payment:contractApproval:batchRemove")
	public R remove(@RequestParam("ids[]") String[] contractIds) {
		contractApprovalService.batchRemove(contractIds);
		return R.ok();
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
		contractApprovalService.dataImport(datafile, userid);
		return null;
	}
}
