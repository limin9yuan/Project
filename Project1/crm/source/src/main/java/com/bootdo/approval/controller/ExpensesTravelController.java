package com.bootdo.approval.controller;

import java.io.File;
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

import com.bootdo.approval.domain.ExpensesTravelDO;
import com.bootdo.approval.service.ExpensesTravelService;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

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
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("approval:expensesTravel:add")
	public R save( ExpensesTravelDO expensesTravel){
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
	}
