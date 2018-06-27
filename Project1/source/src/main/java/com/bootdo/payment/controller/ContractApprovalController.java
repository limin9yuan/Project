package com.bootdo.payment.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.activiti.service.ActTaskService;
import com.bootdo.common.utils.*;
import org.activiti.engine.task.Task;
import org.activiti.engine.TaskService;
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
import org.springframework.web.servlet.ModelAndView;

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
	@Autowired
	private TaskService taskService;
	@Autowired
	private ActTaskService actTaskService;

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
		Task task = null;
		for (ContractApprovalDO contractApproval : contractApprovalList){
			if (contractApproval.getContractApprovalStatus().equals("0")){
				task = taskService.createTaskQuery().processDefinitionKey("contract").taskAssignee(ShiroUtils.getUser()
						.getUsername()).processInstanceBusinessKey(contractApproval.getId()).singleResult();
				if (task != null){
					contractApproval.setTaskId(task.getId());
					contractApproval.setPdId(task.getProcessDefinitionId());
				}
			}
		}
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

	@GetMapping("/edit/{procDefId}/{taskId}")
	@RequiresPermissions("payment:contractApproval:edit")
	ModelAndView edit(@PathVariable("procDefId") String procDefId,@PathVariable("taskId") String taskId, Model model) {
//		ContractApprovalDO contractApproval = contractApprovalService.get(contractId);
//		model.addAttribute("payment", contractApproval);
//		return "payment/contractApproval/edit";
		String formKey="";
		if ( procDefId != null && !"".equals(procDefId) && taskId != null && !"".equals(taskId))
		{
			formKey = actTaskService.getFormKey(procDefId, taskId);//获取流程表单
			model.addAttribute("taskId", taskId);
			model.addAttribute("formSrc", formKey+"/"+taskId);
			model.addAttribute("formSubmit", formKey+"/update");//流程审批处理保存
			Task task = taskService.createTaskQuery().taskId(taskId).singleResult();//根据任务id查询实例id
			model.addAttribute("taskName", task.getName());
			model.addAttribute("processDefinitionId", task.getProcessDefinitionId());
			model.addAttribute("executionId", task.getExecutionId());
			model.addAttribute("processInstanceId", task.getProcessInstanceId());
			return new ModelAndView("act/task/formComm");
		}
		return new ModelAndView("act/task/formComm");
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
//		fileName = FileUtil.renameToUUID(fileName);
		FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/" + fileName, new Date(),fileName);
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
