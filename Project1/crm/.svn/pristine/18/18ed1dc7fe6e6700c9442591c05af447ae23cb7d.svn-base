package com.bootdo.sales.controller;

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

import com.bootdo.sales.domain.OnlineFeedbackDO;
import com.bootdo.sales.service.OnlineFeedbackService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 客户在线反馈信息表
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-12-27 11:51:50
 */
 
@Controller
@RequestMapping("/sales/onlineFeedback")
public class OnlineFeedbackController {
	@Autowired
	private OnlineFeedbackService onlineFeedbackService;
	
	@GetMapping()
	@RequiresPermissions("sales:onlineFeedback:onlineFeedback")
	String OnlineFeedback(){
	    return "sales/onlineFeedback/onlineFeedback";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("sales:onlineFeedback:onlineFeedback")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
				if (params.get("feedbackId") != null
						&& !"".equals(params.get("feedbackId"))) {
					params.put("feedbackId", "%" + (String) params.get("feedbackId")
							+ "%");
				}
        Query query = new Query(params);
		List<OnlineFeedbackDO> onlineFeedbackList = onlineFeedbackService.list(query);
		int total = onlineFeedbackService.count(query);
		PageUtils pageUtils = new PageUtils(onlineFeedbackList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("sales:onlineFeedback:add")
	String add(){
	    return "sales/onlineFeedback/add";
	}

	@GetMapping("/edit/{feedbackId}")
	@RequiresPermissions("sales:onlineFeedback:edit")
	String edit(@PathVariable("feedbackId") String feedbackId,Model model){
		OnlineFeedbackDO onlineFeedback = onlineFeedbackService.get(feedbackId);
		model.addAttribute("onlineFeedback", onlineFeedback);
	    return "sales/onlineFeedback/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("sales:onlineFeedback:add")
	public R save( OnlineFeedbackDO onlineFeedback){
		if(onlineFeedbackService.save(onlineFeedback)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sales:onlineFeedback:edit")
	public R update( OnlineFeedbackDO onlineFeedback){
		onlineFeedbackService.update(onlineFeedback);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("sales:onlineFeedback:remove")
	public R remove( String feedbackId){
		if(onlineFeedbackService.remove(feedbackId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("sales:onlineFeedback:batchRemove")
	public R remove(@RequestParam("ids[]") String[] feedbackIds){
		onlineFeedbackService.batchRemove(feedbackIds);
		return R.ok();
	}
	
}
