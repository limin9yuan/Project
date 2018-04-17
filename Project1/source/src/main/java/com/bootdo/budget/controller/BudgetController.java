package com.bootdo.budget.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bootdo.activiti.utils.ActivitiUtils;
import com.bootdo.contract.domain.ContractDO;
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

import com.bootdo.budget.domain.BudgetDO;
import com.bootdo.budget.service.BudgetService;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.project.domain.ProjectDO;

/**
 * 项目预算表
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-12-11 14:44:13
 */
 
@Controller
@RequestMapping("/budget/budget")
public class BudgetController extends BaseController {
	@Autowired
	private BudgetService budgetService;
	@Autowired
	ActivitiUtils activitiUtils;

	/**
	 * ********************** 审批流程相关  *********************************
	 */
	//申请页面
	@GetMapping("/form")
	@RequiresPermissions("budget:budget:add")
	String form(){
		return "budget/budget/add";
	}
	//审批处理页面
	@GetMapping("/form/{taskId}")
	@RequiresPermissions("budget:budget:add")
	String formTask(@PathVariable("taskId") String taskId,Model model){
		//取得流程表单数据
		BudgetDO budget = budgetService.get(activitiUtils.getBusinessKeyByTaskId(taskId));
		if(budget!=null){
			model.addAttribute("budget", budget);
			//model.addAttribute("taskId",taskId);
		}
		return "budget/budget/viewBudget";

	}


	//审批处理保存
	@ResponseBody
	@RequestMapping("/form/update")
	@RequiresPermissions("budget:budget:edit")
	public R formUpdate( BudgetDO budget){

		budgetService.formUpdate(budget);
		return R.ok();
	}

	
	@GetMapping()
	@RequiresPermissions("budget:budget:budget")
	String Budget(){
	    return "budget/budget/budget";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("budget:budget:budget")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
				if (params.get("projectOwner") != null && !"".equals(params.get("projectOwner"))) {
					params.put("projectOwner", "%" + (String) params.get("projectOwner") + "%");
				}
        Query query = new Query(params);
		List<BudgetDO> budgetList = budgetService.list(query);
		int total = budgetService.count(query);
		PageUtils pageUtils = new PageUtils(budgetList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("budget:budget:add")
	String add(){
	    return "budget/budget/add";
	}
	@RequestMapping("/edit_ajax/{budgetId}")
	@ResponseBody
	Map<String, Object> edit_ajax(@PathVariable("budgetId") String budgetId) {
		BudgetDO budget = budgetService.get(budgetId);
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("budget", budget);
		return returnData;
	}
	@GetMapping("/edit/{budgetId}")
	@RequiresPermissions("budget:budget:edit")
	String edit(@PathVariable("budgetId") String budgetId,Model model){
		//BudgetDO budget = budgetService.get(budgetId);
		model.addAttribute("budgetId", budgetId);
	    return "budget/budget/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("budget:budget:add")
	public R save( BudgetDO budget){
		//budgetId=budget.getBudgetId();
		budget.setBudgetOperator(getUserId());
		int resultBudgetId=budgetService.save(budget);
		if(resultBudgetId>0){  
			R r = R.ok();
			r.put("budgetId", resultBudgetId);
			System.out.println("resultBudgetId=="+resultBudgetId);
			System.out.println("r.get===="+r.get("budgetId"));
			return r;
			
		}
		return R.error(); 

	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("budget:budget:edit")
	public R update( BudgetDO budget){
		budget.setBudgetOperator(getUserId());
		budgetService.update(budget);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("budget:budget:remove")
	public R remove( String budgetId){
		if(budgetService.remove(budgetId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("budget:budget:batchRemove")
	public R remove(@RequestParam("ids[]") String[] budgetIds){
		budgetService.batchRemove(budgetIds);
		return R.ok();
	}
	@RequestMapping("/getProjectId/{projectId}")
	@ResponseBody
	Map<String, Object> getProjectId(@PathVariable("projectId") String projectId) {
		ProjectDO project = budgetService.getProjectId(projectId);
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("project", project);
		return returnData;
	}
	/**
	 * 导出
	 */
	@RequestMapping(value = "/export")
	public  @ResponseBody void export(
			//@RequestParam(value = "province", required = false) String province,
			//@RequestParam(value = "city", required = false) String city,
			//@RequestParam(value = "area", required = false) String area,
			@RequestParam(value = "projectId", required = false) String projectId,
			@RequestParam(value = "projectSales", required = false) String projectSales, HttpServletResponse response,
			HttpServletRequest request) throws ParseException {
		
		// String administrative1=request.getParameter(administrative);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		/*Date data;
		Date date;
		String endtime = null;
		String starttime = null;
		if (startTime != null && startTime != "") {
			try {
				Calendar calendar = new GregorianCalendar();
				date = sdf.parse(String.valueOf(startTime));
				calendar.setTime(date);
				calendar.add(calendar.DATE, -1);
				date = calendar.getTime();
				starttime = sdf.format(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (endTime != null && endTime != "") {
			try {
				Calendar calendar = new GregorianCalendar();
				data = sdf.parse(String.valueOf(endTime));
				calendar.setTime(data);
				calendar.add(calendar.DATE, 1);
				data = calendar.getTime();
				endtime = sdf.format(data);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}*/

		Map<String, Object> params = new HashMap<String, Object>();
		//params.put("province", province);
		//params.put("city", city);
		//params.put("area", area);
		params.put("projectId", projectId);
		params.put("projectSales", projectSales);
		List<BudgetDO> list = budgetService.getQuery(params);
		if(list.size()>0) {
			System.out.println("---------------------list.size------------------->" + list.size());
			response.setContentType("application/binary;charset=UTF-8");
			try {
				ServletOutputStream out = response.getOutputStream();
				String fileName = new String((new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date()).getBytes(),"UTF-8");
				response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
				String[] titles = { "项目预算编号", "企业客户编号", "业务编号", "项目编号", "项目计划利润率", "应收账款总额", "计划成本总额", "计划是否合规", "服务收入", 
						"税金", "服务净收入","采购成本", "人工成本", "差旅成本", "费用和支持（含税）", "利润", "操作人","操作时间","负责中心","部门名称","项目主管","项目类别","项目名称"};
				budgetService.export(titles, out, list);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
