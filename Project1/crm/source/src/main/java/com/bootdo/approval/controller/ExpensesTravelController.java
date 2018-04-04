package com.bootdo.approval.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.activiti.utils.ActivitiUtils;
import com.bootdo.approval.domain.ExpensesNormalDO;
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

import com.bootdo.approval.domain.ExpensesTravelDO;
import com.bootdo.approval.service.ExpensesTravelService;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.sales.domain.CompanyCustomerDO;

/**
 * 差旅报销申请表
 * 
 * @author sw
 * @email 1992lcg@163.com
 * @date 2017-11-28 14:18:31
 */
 
@Controller
@RequestMapping("/approval/expensesTravel")
public class ExpensesTravelController extends BaseController  {
	@Autowired
	private ExpensesTravelService expensesTravelService;
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	ActivitiUtils activitiUtils;

	@GetMapping()
	@RequiresPermissions("approval:expensesTravel:expensesTravel")
	String ExpensesTravel(){
	    return "approval/expensesTravel/expensesTravel";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("approval:expensesTravel:expensesTravel")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		 if(params.get("expensesTravelName") != null && params.get("expensesTravelName") != "") {
				params.put("expensesTravelName", "%" + params.get("expensesTravelName") + "%");
			}
//		 if (params.get("payoutCreateTime") != null && params.get("payoutCreateTime") != "") {
//				params.put("payoutCreateTime", params.get("payoutCreateTime") + " 00:00:00");
//			}
//			if (params.get("payoutOperateTime") != null && params.get("payoutOperateTime") != "") {
//				params.put("payoutOperateTime", params.get("payoutOperateTime") + " 23:59:59");
//			}
        Query query = new Query(params);
		List<ExpensesTravelDO> expensesTravelList = expensesTravelService.list(query);
		int total = expensesTravelService.count(query);
		PageUtils pageUtils = new PageUtils(expensesTravelList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("approval:expensesTravel:add")
	String add(){
	    return "approval/expensesTravel/add";
	}
	@GetMapping("/import")
	@RequiresPermissions("approval:expensesTravel:import")
	String importFile() {
		return "approval/expensesTravel/import";
	}
	@GetMapping("/edit/{expensesTravelId}")
	@RequiresPermissions("approval:expensesTravel:edit")
	String edit(@PathVariable("expensesTravelId") String expensesTravelId,Model model){
		ExpensesTravelDO expensesTravel = expensesTravelService.get(expensesTravelId);
		model.addAttribute("expensesTravel", expensesTravel);
	    return "approval/expensesTravel/edit";
	}
	//ajax修改绑定数据
		@RequestMapping("/edit_ajax/{expensesTravelId}")
		@ResponseBody
		Map<String, Object> edit_ajax(@PathVariable("expensesTravelId") String expensesTravelId) {
			ExpensesTravelDO expensesTravel = expensesTravelService.get(expensesTravelId);
			Map<String, Object> returnData = new HashMap<String, Object>();
			returnData.put("expensesTravel", expensesTravel);
			return returnData;
		}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("approval:expensesTravel:add")
	public R save( ExpensesTravelDO expensesTravel){
		expensesTravel.setExpensesTravelOperator(Long.toString(getUserId()));
		if(expensesTravelService.save(expensesTravel)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("approval:expensesTravel:edit")
	public R update( ExpensesTravelDO expensesTravel){
		expensesTravelService.update(expensesTravel);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("approval:expensesTravel:batchRemove")
	public R remove( String expensesTravelId){
		if(expensesTravelService.remove(expensesTravelId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("approval:expensesTravel:batchRemove")
	public R remove(@RequestParam("ids[]") String[] expensesTravelIds){
		expensesTravelService.batchRemove(expensesTravelIds);
		return R.ok();
	}
	
	@ResponseBody
	@PostMapping("/importSubmit")
	@RequiresPermissions("approval:expensesTravel:import")
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
			expensesTravelService.Import(datafile, userid);
		
		return null;
	}

	/**
	 * ********************** 审批流程相关  *********************************
	 */
	//申请页面
	@GetMapping("/form")
	@RequiresPermissions("approval:expensesTravel:add")
	String form(){
		return "approval/expensesTravel/add";
	}
	//审批处理页面
	@GetMapping("/form/{taskId}")
	@RequiresPermissions("approval:expensesTravel:add")
	String formTask(@PathVariable("taskId") String taskId,Model model){
		//取得流程表单数据
		ExpensesTravelDO expensesTravelDO = expensesTravelService.get(activitiUtils.getBusinessKeyByTaskId(taskId));
		if(expensesTravelDO!=null){
			model.addAttribute("expensesTravel", expensesTravelDO);
			//model.addAttribute("taskId",taskId);
		}
		return "approval/expensesTravel/edit";
	}


	//审批处理保存
	@ResponseBody
	@RequestMapping("/form/update")
	@RequiresPermissions("approval:expensesTravel:edit")
	public R formUpdate( ExpensesTravelDO expensesTravelDO){

		expensesTravelService.formUpdate(expensesTravelDO);
		return R.ok();
	}
}
