package com.bootdo.contract.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.bootdo.activiti.domain.SalaryDO;
import com.bootdo.activiti.service.impl.ActTaskServiceImpl;
import com.bootdo.activiti.utils.ActivitiUtils;
import com.bootdo.contract.domain.TravelDO;
import com.bootdo.contract.service.TravelService;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.sales.domain.BusinessDO;
import com.bootdo.common.controller.BaseController;

/**
 * 出差申请表
 * 
 * @author sw
 * @email 1992lcg@163.com
 * @date 2017-11-30 17:44:01
 */
 
@Controller
@RequestMapping("/contract/travel")
public class TravelController extends BaseController {
	@Autowired
	private TravelService travelService;
	
	@Autowired
	ActivitiUtils activitiUtils;
	
	@GetMapping()
	@RequiresPermissions("contract:travel:travel")
	String Travel(){
	    return "contract/travel/travel";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("contract:travel:travel")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TravelDO> travelList = travelService.list(query);
		int total = travelService.count(query);
		PageUtils pageUtils = new PageUtils(travelList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("contract:travel:add")
	String add(){
	    return "contract/travel/add";
	}
	
	
    
	@GetMapping("/edit/{travelId}")
	@RequiresPermissions("contract:travel:edit")
	String edit(@PathVariable("travelId") String travelId,Model model){
		TravelDO travel = travelService.get(travelId);
		model.addAttribute("travel", travel);
	    return "contract/travel/edit";
	}
	
	@RequestMapping("/edit_ajax/{travelId}")
	@ResponseBody
	Map<String, Object> edit_ajax(@PathVariable("travelId") String travelId) {
		TravelDO travel = travelService.get(travelId);
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("travel", travel);
		return returnData;
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("contract:travel:add")
	public R save( TravelDO travel){
		if(travelService.save(travel)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("contract:travel:edit")
	public R update( TravelDO travel){
		
		travelService.update(travel);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("contract:travel:batchRemove")
	public R remove( String travelId){
		if(travelService.remove(travelId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("contract:travel:batchRemove")
	public R remove(@RequestParam("ids[]") String[] travelIds){
		travelService.batchRemove(travelIds);
		return R.ok();
	}
	
	@ResponseBody
	@GetMapping("/listDic")
	public List<DictDO> listDic() {
		// 查询列表数据
		Map<String, Object> map = new HashMap<>(16);
		map.put("type", "");
		List<DictDO> dictList = travelService.listDic();
		return dictList;
	}
	
	
	/**
	 * ********************** 审批流程相关  *********************************
	 */
	//申请页面
	@GetMapping("/form")
	@RequiresPermissions("contract:travel:add")
	String form(){
	    return "contract/travel/add";
	}
	//审批处理页面
	@GetMapping("/form/{taskId}")
	@RequiresPermissions("contract:travel:add")
	String formTask(@PathVariable("taskId") String taskId,Model model){
		//取得流程表单数据
		TravelDO travel = travelService.view(activitiUtils.getBusinessKeyByTaskId(taskId));
		if(travel!=null){
			model.addAttribute("travel", travel);
			//model.addAttribute("taskId",taskId);
		}
	    return "contract/travel/viewTravel";
	}
	
	
	 //审批处理保存
	@ResponseBody
	@RequestMapping("/form/update")
	@RequiresPermissions("contract:travel:edit")
	public R formUpdate( TravelDO travel){
		
		travelService.formUpdate(travel);
		return R.ok();
	}
}
