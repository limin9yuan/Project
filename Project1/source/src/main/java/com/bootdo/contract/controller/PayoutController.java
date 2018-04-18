package com.bootdo.contract.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.activiti.utils.ActivitiUtils;
import com.bootdo.contract.domain.TravelDO;
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

import com.bootdo.contract.domain.PayoutDO;
import com.bootdo.contract.service.PayoutService;
import com.bootdo.sales.domain.RecordServiceDO;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 请款申请表
 * 
 * @author sw
 * @email 1992lcg@163.com
 * @date 2017-11-30 16:36:08
 */
 
@Controller
@RequestMapping("/contract/payout")
public class PayoutController extends BaseController {
	@Autowired
	private PayoutService payoutService;
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	ActivitiUtils activitiUtils;

	/**
	 * ********************** 审批流程相关  *********************************
	 */
	//申请页面
	@GetMapping("/form")
	@RequiresPermissions("contract:payout:add")
	String form(){
		return "contract/payout/add";
	}

	//审批处理页面
	@GetMapping("/form/{taskId}")
	@RequiresPermissions("contract:payout:add")
	String formTask(@PathVariable("taskId") String taskId,Model model){
		//取得流程表单数据
		PayoutDO payout = payoutService.get(activitiUtils.getBusinessKeyByTaskId(taskId));
		if(payout!=null){
			model.addAttribute("payout", payout);
			//model.addAttribute("taskId",taskId);
		}
		return "contract/payout/edit";
	}


	//审批处理保存
	@ResponseBody
	@RequestMapping("/form/update")
	@RequiresPermissions("contract:payout:edit")
	public R formUpdate( PayoutDO payout){

		payoutService.formUpdate(payout);
		return R.ok();
	}

	@GetMapping()
	@RequiresPermissions("contract:payout:payout")
	String Payout(){
	    return "contract/payout/payout";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("contract:payout:payout")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		if (params.get("payoutPerson") != null && params.get("payoutPerson") != "") {
			params.put("payoutPerson", "%" + params.get("payoutPerson") + "%");
		}
//		if (params.get("payoutCreateTime") != null && params.get("payoutCreateTime") != "") {
//			params.put("payoutCreateTime", params.get("payoutCreateTime") + " 00:00:00");
//		}
//		if (params.get("payoutOperateTime") != null && params.get("payoutOperateTime") != "") {
//			params.put("payoutOperateTime", params.get("payoutOperateTime") + " 23:59:59");
//		}
        Query query = new Query(params);
		List<PayoutDO> payoutList = payoutService.list(query);
		int total = payoutService.count(query);
		PageUtils pageUtils = new PageUtils(payoutList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("contract:payout:add")
	String add(){
	    return "contract/payout/add";
	}
	@GetMapping("/import")
	@RequiresPermissions("contract:payout:import")
	String importFile() {
		return "contract/payout/import";
	}
	@GetMapping("/edit/{payoutId}")
	@RequiresPermissions("contract:payout:edit")
	String edit(@PathVariable("payoutId") String payoutId,Model model){
		PayoutDO payout = payoutService.get(payoutId);
		model.addAttribute("payout", payout);
	    return "contract/payout/edit";
	}
	
	// edit数据绑定
		@RequestMapping("/edit_ajax/{payoutId}")
		@ResponseBody
		Map<String, Object> edit_ajax(@PathVariable("payoutId") String payoutId) {
			PayoutDO payout = payoutService.get(payoutId);
			Map<String, Object> returnData = new HashMap<String, Object>();
			returnData.put("payout", payout);
			return returnData;
		}
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("contract:payout:add")
	public R save( PayoutDO payout){
		payout.setPayoutOperator(Long.toString(getUserId()));
		if(payoutService.save(payout)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("contract:payout:edit")
	public R update( PayoutDO payout){
		payoutService.update(payout);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("contract:payout:batchRemove")
	public R remove( String payoutId){
		if(payoutService.remove(payoutId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("contract:payout:batchRemove")
	public R remove(@RequestParam("ids[]") String[] payoutIds){
		payoutService.batchRemove(payoutIds);
		return R.ok();
	}
	
	@ResponseBody
	@GetMapping("/listDic")
	public List<DictDO> listDic() {
		// 查询列表数据
		Map<String, Object> map = new HashMap<>(16);
		map.put("type", "");
		List<DictDO> dictList = payoutService.listDic();
		return dictList;
	}

@ResponseBody
@PostMapping("/importSubmit")
@RequiresPermissions("contract:payout:import")
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
		payoutService.Import(datafile, userid);
	
	return null;
}
}
