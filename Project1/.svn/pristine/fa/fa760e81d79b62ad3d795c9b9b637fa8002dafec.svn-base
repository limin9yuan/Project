package com.bootdo.contract.controller;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.bootdo.activiti.utils.ActivitiUtils;
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

import com.bootdo.contract.domain.ContractDO;
import com.bootdo.contract.service.ContractService;
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
 * 合同信息表
 * 
 * @author JiJo
 * @email 1992lcg@163.com
 * @date 2017-11-29 14:50:54
 */

@Controller
@RequestMapping("/contract/contract")
public class ContractController extends BaseController {
	@Autowired
	private ContractService contractService;
	@Autowired
	private FileService sysFileService;
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	ActivitiUtils activitiUtils;

	@GetMapping()
	@RequiresPermissions("contract:contract:contract")
	String Contract() {
		return "contract/contract/contract";
	}
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("contract:contract:contract")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<ContractDO> contractList = contractService.list(query);
		int total = contractService.count(query);
		PageUtils pageUtils = new PageUtils(contractList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("contract:contract:add")
	String add() {
		return "contract/contract/add";
	}
	
	@GetMapping("/import")
	@RequiresPermissions("contract:contract:dataImport")
	String importFile() {
		return "contract/contract/import";
	}

	// edit数据绑定
	@RequestMapping("/edit_ajax/{contractId}")
	@ResponseBody
	Map<String, Object> edit_ajax(@PathVariable("contractId") String contractId) {
		ContractDO contract = contractService.get(contractId);
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("contract", contract);
		return returnData;
	}
	
	@GetMapping("/edit/{contractId}")
	@RequiresPermissions("contract:contract:edit")
	String edit(@PathVariable("contractId") String contractId, Model model) {
		model.addAttribute("contractId", contractId);
		return "contract/contract/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("contract:contract:add")
	public R save(ContractDO contract) {
		contract.setContractOperator(Long.toString(getUserId()));
		if (contractService.save(contract) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("contract:contract:edit")
	public R update(ContractDO contract) {
		contract.setContractOperator(Long.toString(getUserId()));
		contractService.update(contract);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("contract:contract:batchRemove")
	public R remove(String contractId) {
		if (contractService.remove(contractId) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("contract:contract:batchRemove")
	public R remove(@RequestParam("ids[]") String[] contractIds) {
		contractService.batchRemove(contractIds);
		return R.ok();
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
	
	@ResponseBody
	@GetMapping("/listDic")
	public List<DictDO> listByType() {
		// 查询列表数据
		Map<String, Object> map = new HashMap<>(16);
		map.put("type", "");
		List<DictDO> dictList = contractService.listDic();
		return dictList;
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
		contractService.dataImport(datafile, userid);
		return null;
	}
	
	/**
	 * Export Microsoft Excel file.
	 */
	@RequestMapping(value = "/export")
	public @ResponseBody void export(
			@RequestParam(value = "customerId", required = false) String contract_customerId,
			@RequestParam(value = "businessId", required = false) String contract_businessId,
			@RequestParam(value = "projectId", required = false) String contract_projectId,
			HttpServletResponse response, HttpServletRequest request) throws ParseException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("customerId", contract_customerId);
		params.put("businessId", contract_businessId);
		params.put("projectId", contract_projectId);
		List<ContractDO> list = contractService.getQuery(params);
		if (list.size() > 0) {
			System.out.println("---------------------list.size------------------->" + list.size());
			response.setContentType("application/binary;charset=UTF-8");
			try {
				ServletOutputStream out = response.getOutputStream();
				String fileName = new String((new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date()).getBytes(),"UTF-8");
				response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
				String[] titles = { "合同编号", "企业客户编号", "业务编号", "合同名称", "申请人姓名", "建设单位", "合同种类", "合同总金额", "合同发起部门", "业务发起人","合同拟定人", "销售负责人", "提交评审时间", "关联合同编号", "发票类型", "预计开具发票时间", "硬件设备明细表", "软件功能列表", "项目经理", "合同信息备注", "附件","审批状态", "操作人", "操作时间", "項目编号"};
				contractService.export(titles, out, list);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * ********************** 审批流程相关  *********************************
	 */
	//申请页面
	@GetMapping("/form")
	@RequiresPermissions("contract:contract:add")
	String form(){
		return "contract/contract/add";
	}
	//审批处理页面
	@GetMapping("/form/{taskId}")
	@RequiresPermissions("contract:contract:add")
	String formTask(@PathVariable("taskId") String taskId,Model model){
		//取得流程表单数据
		ContractDO contract = contractService.get(activitiUtils.getBusinessKeyByTaskId(taskId));
		if(contract!=null){
			model.addAttribute("contract", contract);
			//model.addAttribute("taskId",taskId);
		}
		return "contract/contract/ViewContract";

	}


	//审批处理保存
	@ResponseBody
	@RequestMapping("/form/update")
	@RequiresPermissions("contract:contract:edit")
	public R formUpdate( ContractDO contract){

		contractService.formUpdate(contract);
		return R.ok();
	}
}
