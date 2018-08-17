package com.bootdo.budget.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bootdo.activiti.service.ActTaskService;
import com.bootdo.activiti.utils.ActivitiUtils;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.domain.MainCopyPersonDO;
import com.bootdo.common.service.MainCopyPersonService;
import com.bootdo.contract.domain.ContractDO;

import com.bootdo.contract.domain.PayoutDO;
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
	@Autowired
	private MainCopyPersonService mainCopyPersonService;
	@Autowired
	private ActTaskService actTaskService;

	@RequestMapping("/listProjectByArea_ajax/")
	@ResponseBody
	Map<String, Object> listProjectByArea_ajax( String province,
															  String city,
															  String area) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("offset",1);
		params.put("limit",2);
		params.put("province", province);
		params.put("city", city);
		params.put("area", area);
		Query queryListProjectByArea = new Query(params);
		List<DictDO> listProjectByArea = budgetService.listProjectByArea(queryListProjectByArea);
		Map<String, Object> returnData = new HashMap<>();
		returnData.put("projectByArea", listProjectByArea);
		return returnData;
	}


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
		BudgetDO budget = budgetService.view(activitiUtils.getBusinessKeyByTaskId(taskId));
//		System.out.println(activitiUtils.getBusinessKeyByTaskId(taskId));
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
		params.put("budgetOperator", (getUserId()));
		params.put("Identification", (getIdentification()));
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
	
	@RequestMapping("/getTotal/{budgetId}")
	@ResponseBody
	Map<String, Object> getTotal(@PathVariable("budgetId") String budgetId) {
		BudgetDO budget = budgetService.getTotal(budgetId);
		Map<String, Object> returnData = new HashMap<String, Object>();
		//System.out.println(budget.getContractReceivablePrice());
		returnData.put("budget", budget);
		return returnData;
	}
	
	@RequestMapping("/setSoftware/{budgetId}")
	@ResponseBody
	Map<String, Object> setSoftware(@PathVariable("budgetId") String budgetId) {
		BudgetDO budget = budgetService.setSoftware(budgetId);
		Map<String, Object> returnData = new HashMap<String, Object>();
		//System.out.println(budget.getContractReceivablePrice());
		returnData.put("budget", budget);
		return returnData;
	}
	
	@RequestMapping("/setOldProject/{budgetId}")
	@ResponseBody
	Map<String, Object> setOldProject(@PathVariable("budgetId") String budgetId) {
		BudgetDO budget = budgetService.setOldProject(budgetId);
		Map<String, Object> returnData = new HashMap<String, Object>();
		//System.out.println(budget.getContractReceivablePrice());
		returnData.put("budget", budget);
		return returnData;
	}
	
	@RequestMapping("/setBlender/{budgetId}")
	@ResponseBody
	Map<String, Object> setBlender(@PathVariable("budgetId") String budgetId) {
		BudgetDO budget = budgetService.setBlender(budgetId);
		Map<String, Object> returnData = new HashMap<String, Object>();
		//System.out.println(budget.getContractReceivablePrice());
		returnData.put("budget", budget);
		return returnData;
	}
	
	@RequestMapping("/edit_ajax/{budgetId}")
	@ResponseBody
	Map<String, Object> edit_ajax(@PathVariable("budgetId") String budgetId) {
		BudgetDO budget = budgetService.get(budgetId);
		/*if(budget.getProjectGategory()=="老项目"){
			budgetService.setOldProject(budgetId);
		}
		if(budget.getProjectGategory()=="软件项目（技术开发类）"){
			budgetService.setSoftware(budgetId);		
		}*/
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
		String idName = budget.getProjectId();
		String projectID = idName.substring(0,15);
		budget.setProjectId(projectID);
		int resultBudgetId=budgetService.save(budget);
		if(resultBudgetId>0){
			MainCopyPersonDO mcp = new MainCopyPersonDO();
			String mainPersonId = budget.getMainPersonId();
			if (!"".equals(mainPersonId)) {
				String mainPersonIdArray[] = mainPersonId.split(",");
				for (int i = 0; i < mainPersonIdArray.length; i++) {
					mcp.setTId(budget.getBudgetId());
					mcp.setMainPerson(1);
					mcp.setEmployeeId(mainPersonIdArray[i]);
					mcp.setOperator(getUserId());
					mcp.setTableName("project_budget");
					mainCopyPersonService.save(mcp);

				}
			}

			String copyPersonId = budget.getCopyPersonId();
			if (!"".equals(copyPersonId)) {
				String copyPersonIdArray[] = copyPersonId.split(",");
				for (int i = 0; i < copyPersonIdArray.length; i++) {
					mcp.setTId(budget.getBudgetId());
					mcp.setMainPerson(0);
					mcp.setEmployeeId(copyPersonIdArray[i]);
					mcp.setOperator(getUserId());
					mcp.setTableName("project_budget");
					mainCopyPersonService.save(mcp);
				}


			}
			R r = R.ok();
			r.put("budgetId", resultBudgetId);
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
		String budgetIds = budget.getBudgetId();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("offset",1);
		params.put("limit",2);
		params.put("tId",budgetIds);
		params.put("tableName","project_budget");
		budgetService.update(budget);
		mainCopyPersonService.remove(params);
		if (!budgetIds.equals("")) {
			MainCopyPersonDO mcp = new MainCopyPersonDO();
			String mainPersonId = budget.getMainPersonId();
			if (!"".equals(mainPersonId)) {
				String mainPersonIdArray[] = mainPersonId.split(",");

				for (int i = 0; i < mainPersonIdArray.length; i++) {
					mcp.setTId(budgetIds);
					mcp.setMainPerson(1);
					mcp.setEmployeeId(mainPersonIdArray[i]);
					mcp.setOperator(getUserId());
					mcp.setTableName("project_budget");
					mainCopyPersonService.save(mcp);

				}
			}

			String copyPersonId = budget.getCopyPersonId();
			if (!"".equals(copyPersonId)) {
				String copyPersonIdArray[] = copyPersonId.split(",");
				for (int i = 0; i < copyPersonIdArray.length; i++) {
					mcp.setTId(budgetIds);
					mcp.setMainPerson(0);
					mcp.setEmployeeId(copyPersonIdArray[i]);
					mcp.setOperator(getUserId());
					mcp.setTableName("project_budget");
					mainCopyPersonService.save(mcp);

				}
			}

		}
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("budget:budget:remove")
	public R remove( String budgetId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("offset",1);
		params.put("limit",2);
		params.put("tId",budgetId);
		params.put("tableName","project_budget");
		BudgetDO budget = budgetService.get(budgetId);
		if (budget != null && budget.getProcessInstanceId()!= null){
			if (budget.getBudgetApprovalStatus().equals("2")){
				return R.error("流程正在审批，不允许删除");
			}
			if (budget.getBudgetApprovalStatus().equals("1")) {
				return R.error("流程已经审批完成，不允许删除");
			}
			actTaskService.deleteProcess(budget.getProcessInstanceId());
		}
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
		List<String> list = new ArrayList<String>();
		//级联删除流程相关
		for(int i=0;i<budgetIds.length;i++){
			BudgetDO budget= budgetService.get(budgetIds[i]);
			if(budget!=null&&budget.getProcessInstanceId()!=null){
				if(budget.getBudgetApprovalStatus().equals("2")){
					continue;
					//return R.error("流程正在审批，不允许删除");
				}else if(budget.getBudgetApprovalStatus().equals("1")){
					//return R.error("流程已经审批完成，不允许删除");
					continue;
				}
				actTaskService.deleteProcess(budget.getProcessInstanceId());
				list.add(budgetIds[i]);
			}
		}

		budgetService.batchRemove(list.toArray(new String[1]));
		if(list.size()<budgetIds.length){
			return R.ok("有部分流程正在审批或审批完成，不允许删除");
		}else{
			return R.ok();
		}
	}
	@RequestMapping("/getProjectId/{projectId}")
	@ResponseBody
	Map<String, Object> getProjectId(@PathVariable("projectId") String projectId) {
		ProjectDO project = budgetService.getProjectId(projectId);
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("project", project);
		return returnData;
	}
	
	@RequestMapping("/getBudgetServiceRevenue/{projectId}")
	@ResponseBody
	Map<String, Object> getBudgetServiceRevenue(@PathVariable("projectId") String projectId) {
		ProjectDO project = budgetService.getBudgetServiceRevenue(projectId);
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("project", project);
		return returnData;
	}
	
	
/*	
	
	@RequestMapping("/getBudgetLaborCost/{budgetId}")
	@ResponseBody
	Map<String, Object> getBudgetLaborCost(@PathVariable("budgetId") String budgetId) {
		//BudgetDO budget = budgetService.getBudgetLaborCost(budgetId);
		Map<String, Object> returnData = new HashMap<String, Object>();
		//System.out.println(budget.getContractReceivablePrice());
		//returnData.put("budget", budget);
		return returnData;
	}*/
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
