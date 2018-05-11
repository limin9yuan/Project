package com.bootdo.contract.controller;

import java.io.File;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.bootdo.activiti.utils.ActivitiUtils;
import com.bootdo.common.domain.MainCopyPersonDO;
import com.bootdo.common.domain.MainDO;
import com.bootdo.common.service.MainCopyPersonService;
import com.bootdo.common.utils.*;
import javafx.concurrent.Task;
import org.activiti.engine.HistoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.TaskQuery;
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
	@Autowired
   TaskService taskService;
	@Autowired
   HistoryService historyService;
	@Autowired
   private MainCopyPersonService mainCopyPersonService;

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

    @GetMapping("/view/{contractId}")
    @RequiresPermissions("contract:contract:view")
    String view(@PathVariable("contractId") String contractId, Model model) {
        model.addAttribute("contractId", contractId);
        return "contract/contract/view";
    }

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("contract:contract:add")
	public R save(ContractDO contract) {
		contract.setContractOperator(Long.toString(getUserId()));
		int contractIds = contractService.save(contract);
       if (contractIds > 0) {
           MainCopyPersonDO mcp = new MainCopyPersonDO();
           String mainPersonId = contract.getMainPersonId();
           if (!"".equals(mainPersonId)) {
               String mainPersonIdArray[] = mainPersonId.split(",");
               for (int i = 0; i < mainPersonIdArray.length; i++) {
                   mcp.setTId(contract.getContractId());
                   mcp.setMainPerson(1);
                   mcp.setEmployeeId(mainPersonIdArray[i]);
                   mcp.setOperator(getUserId());
                   mcp.setTableName("contract");
                   mainCopyPersonService.save(mcp);

               }
           }

           String copyPersonId = contract.getCopyPersonId();
           if (!"".equals(copyPersonId)) {
               String copyPersonIdArray[] = copyPersonId.split(",");
               for (int i = 0; i < copyPersonIdArray.length; i++) {
                   mcp.setTId(contract.getContractId());
                   mcp.setMainPerson(0);
                   mcp.setEmployeeId(copyPersonIdArray[i]);
                   mcp.setOperator(getUserId());
                   mcp.setTableName("contract");
                   mainCopyPersonService.save(mcp);
               }


           }
           R r = R.ok();
           r.put("contractId", contractIds);
           return r;
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
       String contractIds = contract.getContractId();
       Map<String, Object> params = new HashMap<String, Object>();
       params.put("offset",1);
       params.put("limit",2);
       params.put("tId",contractIds);
       params.put("tableName","contract");
       contractService.update(contract);
       mainCopyPersonService.remove(params);
       if (!contractIds.equals("")) {
           MainCopyPersonDO mcp = new MainCopyPersonDO();
           String mainPersonId = contract.getMainPersonId();
           if (!"".equals(mainPersonId)) {
               String mainPersonIdArray[] = mainPersonId.split(",");

               for (int i = 0; i < mainPersonIdArray.length; i++) {
                   mcp.setTId(contract.getContractId());
                   mcp.setMainPerson(1);
                   mcp.setEmployeeId(mainPersonIdArray[i]);
                   mcp.setOperator(getUserId());
                   mcp.setTableName("contract");
                   mainCopyPersonService.save(mcp);

               }
           }

           String copyPersonId = contract.getCopyPersonId();
           if (!"".equals(copyPersonId)) {
               String copyPersonIdArray[] = copyPersonId.split(",");
               int result = 0;
               for (int i = 0; i < copyPersonIdArray.length; i++) {
                   mcp.setTId(contract.getContractId());
                   mcp.setMainPerson(0);
                   mcp.setEmployeeId(copyPersonIdArray[i]);
                   mcp.setOperator(getUserId());
                   mcp.setTableName("contract");
                   mainCopyPersonService.save(mcp);

               }
           }

       }
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("contract:contract:remove")
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
		ContractDO contract = contractService.view(activitiUtils.getBusinessKeyByTaskId(taskId));
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

	@ResponseBody
   @GetMapping("/getRecipient_ajax")
   Map<String, Object> getMission_ajax(){

       Map<String, Object> params = new HashMap<String, Object>();
       params.put("offset",1);
       params.put("limit",2);
       params.put("Recipient",getUserId().toString());

       Query queryGetRecipient = new Query(params);
       List<MainDO> getRecipient = contractService.getRecipient(queryGetRecipient);



       Map<String, Object> returnData = new HashMap<>();
       returnData.put("recipient", getRecipient);
       return returnData;
   }

    @ResponseBody
    @GetMapping("/getPrincipal_ajax")
    Map<String, Object> getPrincipal_ajax(){

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("offset",1);
        params.put("limit",2);
        params.put("Principal",getUserId().toString());

        Query queryGetPrincipal = new Query(params);
        List<MainDO> getPrincipal = contractService.getPrincipal(queryGetPrincipal);


        Map<String, Object> returnData = new HashMap<>();
        returnData.put("principal", getPrincipal);
        return returnData;
    }

    @ResponseBody
    @GetMapping("/getLog_ajax")
    Map<String, Object> getLog_ajax(){
        Calendar date = Calendar.getInstance();
        String currentYear = String.valueOf(date.get(Calendar.YEAR));
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("offset",1);
        params.put("limit",2);
        params.put("currentYear",currentYear);

        Query queryGetLog = new Query(params);
        List<MainDO> getLog = contractService.getLog(queryGetLog);


        Map<String, Object> returnData = new HashMap<>();
        returnData.put("log", getLog);
        return returnData;
    }

    @ResponseBody
    @GetMapping("/getUndoLog_ajax")
    Map<String, Object> getUndoLog_ajax(){
        Calendar date = Calendar.getInstance();
        String currentYear = String.valueOf(date.get(Calendar.YEAR));
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("offset",1);
        params.put("limit",2);
        params.put("currentYear",currentYear);

        Query queryGetUndoLog = new Query(params);
        List<MainDO> getUndoLog = contractService.getUndoLog(queryGetUndoLog);


        Map<String, Object> returnData = new HashMap<>();
        returnData.put("undoLog", getUndoLog);
        return returnData;
    }

    @ResponseBody
    @GetMapping("/getDoneActiviti_ajax")
    Map<String, Object> getDoneActiviti_ajax(){
        long travelTaskCount = historyService.createHistoricProcessInstanceQuery().
                processDefinitionKey("travel").finished().includeProcessVariables().
                involvedUser(ShiroUtils.getUser().getUsername()).count();
        long payoutTaskCount = historyService.createHistoricProcessInstanceQuery().
                processDefinitionKey("payout").finished().includeProcessVariables().
                involvedUser(ShiroUtils.getUser().getUsername()).count();
        long expensesTravelTaskCount = historyService.createHistoricProcessInstanceQuery().
                processDefinitionKey("expensesTravel").finished().includeProcessVariables().
                involvedUser(ShiroUtils.getUser().getUsername()).count();
        long expensesNormalTaskCount = historyService.createHistoricProcessInstanceQuery().
                processDefinitionKey("expensesNormal").finished().includeProcessVariables().
                involvedUser(ShiroUtils.getUser().getUsername()).count();
        long contractTaskCount = historyService.createHistoricProcessInstanceQuery().
                processDefinitionKey("contract").finished().includeProcessVariables().
                involvedUser(ShiroUtils.getUser().getUsername()).count();
        long additionalRecordsTaskCount = historyService.createHistoricProcessInstanceQuery().
                processDefinitionKey("additionalRecords").finished().includeProcessVariables().
                involvedUser(ShiroUtils.getUser().getUsername()).count();
        long purchaseTaskCount = historyService.createHistoricProcessInstanceQuery().
                processDefinitionKey("purchase").finished().includeProcessVariables().
                involvedUser(ShiroUtils.getUser().getUsername()).count();
        long approvalAssignmentTaskCount = historyService.createHistoricProcessInstanceQuery().
                processDefinitionKey("approvalAssignment").finished().includeProcessVariables().
                involvedUser(ShiroUtils.getUser().getUsername()).count();
        long timeSheetTaskCount = historyService.createHistoricProcessInstanceQuery().
                processDefinitionKey("timeSheet").finished().includeProcessVariables().
                involvedUser(ShiroUtils.getUser().getUsername()).count();
        long budgetTaskCount = historyService.createHistoricProcessInstanceQuery().
                processDefinitionKey("budget").finished().includeProcessVariables().
                involvedUser(ShiroUtils.getUser().getUsername()).count();

        ArrayList<Long> countList= new ArrayList<>();
        countList.add(travelTaskCount);
        countList.add(payoutTaskCount);
        countList.add(expensesTravelTaskCount);
        countList.add(expensesNormalTaskCount);
        countList.add(contractTaskCount);
        countList.add(additionalRecordsTaskCount);
        countList.add(purchaseTaskCount);
        countList.add(approvalAssignmentTaskCount);
        countList.add(timeSheetTaskCount);
        countList.add(budgetTaskCount);
        Map<String, Object> returnData = new HashMap<>();
        returnData.put("countList", countList);
        return returnData;
    }

    @ResponseBody
    @GetMapping("/getWaitActiviti_ajax")
    Map<String, Object> getWaitActiviti_ajax(){
        long travelTaskCount = taskService.createTaskQuery().processDefinitionKey("travel").
                               taskAssignee(ShiroUtils.getUser().getUsername()).count();
        long payoutTaskCount = taskService.createTaskQuery().processDefinitionKey("payout").
                               taskAssignee(ShiroUtils.getUser().getUsername()).count();
        long expensesTravelTaskCount = taskService.createTaskQuery().processDefinitionKey("expensesTravel").
                                       taskAssignee(ShiroUtils.getUser().getUsername()).count();
        long expensesNormalTaskCount = taskService.createTaskQuery().processDefinitionKey("expensesNormal").
                                       taskAssignee(ShiroUtils.getUser().getUsername()).count();
        long contractTaskCount = taskService.createTaskQuery().processDefinitionKey("contract").
                                 taskAssignee(ShiroUtils.getUser().getUsername()).count();
        long additionalRecordsTaskCount = taskService.createTaskQuery().processDefinitionKey("additionalRecords").
                                          taskAssignee(ShiroUtils.getUser().getUsername()).count();
        long purchaseTaskCount = taskService.createTaskQuery().processDefinitionKey("purchase").
                                 taskAssignee(ShiroUtils.getUser().getUsername()).count();
        long approvalAssignmentTaskCount = taskService.createTaskQuery().processDefinitionKey("approvalAssignment").
                                           taskAssignee(ShiroUtils.getUser().getUsername()).count();
        long timeSheetTaskCount = taskService.createTaskQuery().processDefinitionKey("timeSheet").
                                  taskAssignee(ShiroUtils.getUser().getUsername()).count();
        long budgetTaskCount = taskService.createTaskQuery().processDefinitionKey("budget").
                               taskAssignee(ShiroUtils.getUser().getUsername()).count();

        ArrayList<Long> countList= new ArrayList<>();
        countList.add(travelTaskCount);
        countList.add(payoutTaskCount);
        countList.add(expensesTravelTaskCount);
        countList.add(expensesNormalTaskCount);
        countList.add(contractTaskCount);
        countList.add(additionalRecordsTaskCount);
        countList.add(purchaseTaskCount);
        countList.add(approvalAssignmentTaskCount);
        countList.add(timeSheetTaskCount);
        countList.add(budgetTaskCount);
        Map<String, Object> returnData = new HashMap<>();
        returnData.put("countList", countList);
        return returnData;
    }

    @ResponseBody
    @GetMapping("/getApplyActiviti_ajax")
    Map<String, Object> getApplyActiviti_ajax(){
        long travelTaskCount = historyService.createHistoricProcessInstanceQuery().
                processDefinitionKey("travel").unfinished().includeProcessVariables().
                startedBy(ShiroUtils.getUser().getUsername()).count();
        long payoutTaskCount = historyService.createHistoricProcessInstanceQuery().
                processDefinitionKey("payout").unfinished().includeProcessVariables().
                startedBy(ShiroUtils.getUser().getUsername()).count();
        long expensesTravelTaskCount = historyService.createHistoricProcessInstanceQuery().
                processDefinitionKey("expensesTravel").unfinished().includeProcessVariables().
                startedBy(ShiroUtils.getUser().getUsername()).count();
        long expensesNormalTaskCount = historyService.createHistoricProcessInstanceQuery().
                processDefinitionKey("expensesNormal").unfinished().includeProcessVariables().
                startedBy(ShiroUtils.getUser().getUsername()).count();
        long contractTaskCount = historyService.createHistoricProcessInstanceQuery().
                processDefinitionKey("contract").unfinished().includeProcessVariables().
                startedBy(ShiroUtils.getUser().getUsername()).count();
        long additionalRecordsTaskCount = historyService.createHistoricProcessInstanceQuery().
                processDefinitionKey("additionalRecords").unfinished().includeProcessVariables().
                startedBy(ShiroUtils.getUser().getUsername()).count();
        long purchaseTaskCount = historyService.createHistoricProcessInstanceQuery().
                processDefinitionKey("purchase").unfinished().includeProcessVariables().
                startedBy(ShiroUtils.getUser().getUsername()).count();
        long approvalAssignmentTaskCount = historyService.createHistoricProcessInstanceQuery().
                processDefinitionKey("approvalAssignment").unfinished().includeProcessVariables().
                startedBy(ShiroUtils.getUser().getUsername()).count();
        long timeSheetTaskCount = historyService.createHistoricProcessInstanceQuery().
                processDefinitionKey("timeSheet").unfinished().includeProcessVariables().
                startedBy(ShiroUtils.getUser().getUsername()).count();
        long budgetTaskCount = historyService.createHistoricProcessInstanceQuery().
                processDefinitionKey("budget").unfinished().includeProcessVariables().
                startedBy(ShiroUtils.getUser().getUsername()).count();
        ArrayList<Long> countList= new ArrayList<>();
        countList.add(travelTaskCount);
        countList.add(payoutTaskCount);
        countList.add(expensesTravelTaskCount);
        countList.add(expensesNormalTaskCount);
        countList.add(contractTaskCount);
        countList.add(additionalRecordsTaskCount);
        countList.add(purchaseTaskCount);
        countList.add(approvalAssignmentTaskCount);
        countList.add(timeSheetTaskCount);
        countList.add(budgetTaskCount);
        Map<String, Object> returnData = new HashMap<>();
        returnData.put("countList", countList);
        return returnData;
    }

    @RequestMapping("/getDataList_ajax/{year}")
    @ResponseBody
    Map<String, Object> getDataList_ajax(@PathVariable("year") String year) {
        double janIncome=0; double febIncome=0; double marIncome=0; double aprIncome=0; double mayIncome=0;
        double junIncome=0; double julIncome=0; double augIncome=0; double sepIncome=0; double octIncome=0;
        double novIncome=0; double decIncome=0; double totalIncome=0;
        double janCost=0; double febCost=0; double marCost=0; double aprCost=0; double mayCost=0;
        double junCost=0; double julCost=0; double augCost=0; double sepCost=0; double octCost=0;
        double novCost=0; double decCost=0; double totalCost=0;
        double janNet=0; double febNet=0; double marNet=0; double aprNet=0; double mayNet=0;
        double junNet=0; double julNet=0; double augNet=0; double sepNet=0; double octNet=0;
        double novNet=0; double decNet=0; double totalNet=0;
        Map<String, Object> params = new HashMap<String, Object>();
        Calendar date = Calendar.getInstance();
        String currentYear = String.valueOf(date.get(Calendar.YEAR));
        params.put("offset",1);
        params.put("limit",2);
        if (year == null) {
            params.put("currentYear", currentYear);
        }else {
            params.put("currentYear", year);
        }
        Query queryGetDataList = new Query(params);
        List<MainDO> getDataList = contractService.getDataList(queryGetDataList);
        for (int i=0; i<getDataList.size();i++){
            if (i == 0){

                janIncome = Double.parseDouble(getDataList.get(0).getJanuary()==null?
                        "0" : getDataList.get(0).getJanuary());
                febIncome = Double.parseDouble( getDataList.get(0).getFebruary()==null?
                        "0" : getDataList.get(0).getFebruary());
                marIncome = Double.parseDouble( getDataList.get(0).getMarch()==null?
                        "0" : getDataList.get(0).getMarch());
                aprIncome = Double.parseDouble( getDataList.get(0).getApril()==null?
                        "0" : getDataList.get(0).getApril());
                mayIncome = Double.parseDouble( getDataList.get(0).getMay()==null?
                        "0" : getDataList.get(0).getMay());
                junIncome = Double.parseDouble( getDataList.get(0).getJune()==null?
                        "0" : getDataList.get(0).getJune());
                julIncome = Double.parseDouble( getDataList.get(0).getJuly()==null?
                        "0" : getDataList.get(0).getJuly());
                augIncome = Double.parseDouble( getDataList.get(0).getAugust()==null?
                        "0" : getDataList.get(0).getAugust());
                sepIncome = Double.parseDouble( getDataList.get(0).getSeptember()==null?
                        "0" : getDataList.get(0).getSeptember());
                octIncome = Double.parseDouble( getDataList.get(0).getOctober()==null?
                        "0" : getDataList.get(0).getOctober());
                novIncome = Double.parseDouble( getDataList.get(0).getNovember()==null?
                        "0" : getDataList.get(0).getNovember());
                decIncome = Double.parseDouble( getDataList.get(0).getDecember()==null?
                        "0" : getDataList.get(0).getDecember());
                totalIncome = Double.parseDouble( getDataList.get(0).getTotal()==null?
                        "0" : getDataList.get(0).getTotal());
            }
            if (i == 2){
                janCost = Double.parseDouble( getDataList.get(2).getJanuary()==null?
                        "0":getDataList.get(2).getJanuary());
                febCost = Double.parseDouble( getDataList.get(2).getFebruary()==null?
                        "0":getDataList.get(2).getFebruary());
                marCost = Double.parseDouble( getDataList.get(2).getMarch()==null?
                        "0":getDataList.get(2).getMarch());
                aprCost = Double.parseDouble( getDataList.get(2).getApril()==null?
                        "0":getDataList.get(2).getApril());
                mayCost = Double.parseDouble( getDataList.get(2).getMay()==null?
                        "0":getDataList.get(2).getMay());
                junCost = Double.parseDouble( getDataList.get(2).getJune()==null?
                        "0":getDataList.get(2).getJune());
                julCost = Double.parseDouble( getDataList.get(2).getJuly()==null?
                        "0":getDataList.get(2).getJuly());
                augCost = Double.parseDouble( getDataList.get(2).getAugust()==null?
                        "0":getDataList.get(2).getAugust());
                sepCost = Double.parseDouble( getDataList.get(2).getSeptember()==null?
                        "0":getDataList.get(2).getSeptember());
                octCost = Double.parseDouble( getDataList.get(2).getOctober()==null?
                        "0":getDataList.get(2).getOctober());
                novCost = Double.parseDouble( getDataList.get(2).getNovember()==null?
                        "0":getDataList.get(2).getNovember());
                decCost = Double.parseDouble( getDataList.get(2).getDecember()==null?
                        "0":getDataList.get(2).getDecember());
                totalCost = Double.parseDouble( getDataList.get(2).getTotal()==null?
                        "0":getDataList.get(2).getTotal());
            }
            if (i == 3){
                janCost = janCost + Double.parseDouble( getDataList.get(3).getJanuary()==null?
                        "0":getDataList.get(3).getJanuary());
                febCost = febCost + Double.parseDouble( getDataList.get(3).getFebruary()==null?
                        "0":getDataList.get(3).getFebruary());
                marCost = marCost + Double.parseDouble( getDataList.get(3).getMarch()==null?
                        "0":getDataList.get(3).getMarch());
                aprCost = aprCost + Double.parseDouble( getDataList.get(3).getApril()==null?
                        "0":getDataList.get(3).getApril());
                mayCost = mayCost + Double.parseDouble( getDataList.get(3).getMay()==null?
                        "0":getDataList.get(3).getMay());
                junCost = junCost + Double.parseDouble( getDataList.get(3).getJune()==null?
                        "0":getDataList.get(3).getJune());
                julCost = julCost + Double.parseDouble( getDataList.get(3).getJuly()==null?
                        "0":getDataList.get(3).getJuly());
                augCost = augCost + Double.parseDouble( getDataList.get(3).getAugust()==null?
                        "0":getDataList.get(3).getAugust());
                sepCost = sepCost + Double.parseDouble( getDataList.get(3).getSeptember()==null?
                        "0":getDataList.get(3).getSeptember());
                octCost = octCost + Double.parseDouble( getDataList.get(3).getOctober()==null?
                        "0":getDataList.get(3).getOctober());
                novCost = novCost + Double.parseDouble( getDataList.get(3).getNovember()==null?
                        "0":getDataList.get(3).getNovember());
                decCost = decCost + Double.parseDouble( getDataList.get(3).getDecember()==null?
                        "0":getDataList.get(3).getDecember());
                totalCost = totalCost + Double.parseDouble( getDataList.get(3).getTotal()==null?
                        "0":getDataList.get(3).getTotal());
            }
            if (i == 4){
                janCost = janCost + Double.parseDouble( getDataList.get(4).getJanuary()==null?
                        "0":getDataList.get(4).getJanuary());
                febCost = febCost + Double.parseDouble( getDataList.get(4).getFebruary()==null?
                        "0":getDataList.get(4).getFebruary());
                marCost = marCost + Double.parseDouble( getDataList.get(4).getMarch()==null?
                        "0":getDataList.get(4).getMarch());
                aprCost = aprCost + Double.parseDouble( getDataList.get(4).getApril()==null?
                        "0":getDataList.get(4).getApril());
                mayCost = mayCost + Double.parseDouble( getDataList.get(4).getMay()==null?
                        "0":getDataList.get(4).getMay());
                junCost = junCost + Double.parseDouble( getDataList.get(4).getJune()==null?
                        "0":getDataList.get(4).getJune());
                julCost = julCost + Double.parseDouble( getDataList.get(4).getJuly()==null?
                        "0":getDataList.get(4).getJuly());
                augCost = augCost + Double.parseDouble( getDataList.get(4).getAugust()==null?
                        "0":getDataList.get(4).getAugust());
                sepCost = sepCost + Double.parseDouble( getDataList.get(4).getSeptember()==null?
                        "0":getDataList.get(4).getSeptember());
                octCost = octCost + Double.parseDouble( getDataList.get(4).getOctober()==null?
                        "0":getDataList.get(4).getOctober());
                novCost = novCost + Double.parseDouble( getDataList.get(4).getNovember()==null?
                        "0":getDataList.get(4).getNovember());
                decCost = decCost + Double.parseDouble( getDataList.get(4).getDecember()==null?
                        "0":getDataList.get(4).getDecember());
                totalCost = totalCost + Double.parseDouble( getDataList.get(4).getTotal()==null?
                        "0":getDataList.get(4).getTotal());

            }
            if (i == 5){
                janCost = janCost + Double.parseDouble( getDataList.get(5).getJanuary()==null?
                        "0":getDataList.get(5).getJanuary());
                febCost = febCost + Double.parseDouble( getDataList.get(5).getFebruary()==null?
                        "0":getDataList.get(5).getFebruary());
                marCost = marCost + Double.parseDouble( getDataList.get(5).getMarch()==null?
                        "0":getDataList.get(5).getMarch());
                aprCost = aprCost + Double.parseDouble( getDataList.get(5).getApril()==null?
                        "0":getDataList.get(5).getApril());
                mayCost = mayCost + Double.parseDouble( getDataList.get(5).getMay()==null?
                        "0":getDataList.get(5).getMay());
                junCost = junCost + Double.parseDouble( getDataList.get(5).getJune()==null?
                        "0":getDataList.get(5).getJune());
                julCost = julCost + Double.parseDouble( getDataList.get(5).getJuly()==null?
                        "0":getDataList.get(5).getJuly());
                augCost = augCost + Double.parseDouble( getDataList.get(5).getAugust()==null?
                        "0":getDataList.get(5).getAugust());
                sepCost = sepCost + Double.parseDouble( getDataList.get(5).getSeptember()==null?
                        "0":getDataList.get(5).getSeptember());
                octCost = octCost + Double.parseDouble( getDataList.get(5).getOctober()==null?
                        "0":getDataList.get(5).getOctober());
                novCost = novCost + Double.parseDouble( getDataList.get(5).getNovember()==null?
                        "0":getDataList.get(5).getNovember());
                decCost = decCost + Double.parseDouble( getDataList.get(5).getDecember()==null?
                        "0":getDataList.get(5).getDecember());
                totalCost = totalCost + Double.parseDouble( getDataList.get(5).getTotal()==null?
                        "0":getDataList.get(5).getTotal());

            }
            if (i == 6){
                janCost = janCost + Double.parseDouble( getDataList.get(6).getJanuary()==null?
                        "0":getDataList.get(6).getJanuary());
                febCost = febCost + Double.parseDouble( getDataList.get(6).getFebruary()==null?
                        "0":getDataList.get(6).getFebruary());
                marCost = marCost + Double.parseDouble( getDataList.get(6).getMarch()==null?
                        "0":getDataList.get(6).getMarch());
                aprCost = aprCost + Double.parseDouble( getDataList.get(6).getApril()==null?
                        "0":getDataList.get(6).getApril());
                mayCost = mayCost + Double.parseDouble( getDataList.get(6).getMay()==null?
                        "0":getDataList.get(6).getMay());
                junCost = junCost + Double.parseDouble( getDataList.get(6).getJune()==null?
                        "0":getDataList.get(6).getJune());
                julCost = julCost + Double.parseDouble( getDataList.get(6).getJuly()==null?
                        "0":getDataList.get(6).getJuly());
                augCost = augCost + Double.parseDouble( getDataList.get(6).getAugust()==null?
                        "0":getDataList.get(6).getAugust());
                sepCost = sepCost + Double.parseDouble( getDataList.get(6).getSeptember()==null?
                        "0":getDataList.get(6).getSeptember());
                octCost = octCost + Double.parseDouble( getDataList.get(6).getOctober()==null?
                        "0":getDataList.get(6).getOctober());
                novCost = novCost + Double.parseDouble( getDataList.get(6).getNovember()==null?
                        "0":getDataList.get(6).getNovember());
                decCost = decCost + Double.parseDouble( getDataList.get(6).getDecember()==null?
                        "0":getDataList.get(6).getDecember());
                totalCost = totalCost + Double.parseDouble( getDataList.get(6).getTotal()==null?
                        "0":getDataList.get(6).getTotal());

            }
        }
        DecimalFormat df = new DecimalFormat("#.00");
        janNet = janIncome - janCost;
        febNet = febIncome - febCost;
        marNet = marIncome - marCost;
        aprNet = aprIncome - aprCost;
        mayNet = mayIncome - mayCost;
        junNet = junIncome - junCost;
        julNet = julIncome - julCost;
        augNet = augIncome - augCost;
        sepNet = sepIncome - sepCost;
        octNet = octIncome - octCost;
        novNet = novIncome - novCost;
        decNet = decIncome - decCost;
        totalNet = totalIncome - totalCost;
        MainDO  netIncome = new MainDO();
        netIncome.setName("未扣除其他成本利润");
        netIncome.setJanuary(String.valueOf(df.format(janNet)));
        netIncome.setFebruary(String.valueOf(df.format(febNet)));
        netIncome.setMarch(String.valueOf(df.format(marNet)));
        netIncome.setApril(String.valueOf(df.format(aprNet)));
        netIncome.setMay(String.valueOf(df.format(mayNet)));
        netIncome.setJune(String.valueOf(df.format(junNet)));
        netIncome.setJuly(String.valueOf(df.format(julNet)));
        netIncome.setAugust(String.valueOf(df.format(augNet)));
        netIncome.setSeptember(String.valueOf(df.format(sepNet)));
        netIncome.setOctober(String.valueOf(df.format(octNet)));
        netIncome.setNovember(String.valueOf(df.format(novNet)));
        netIncome.setDecember(String.valueOf(df.format(decNet)));
        netIncome.setTotal(String.valueOf(df.format(totalNet)));
        getDataList.add(7,netIncome);
        Map<String, Object> returnData = new HashMap<String, Object>();
        returnData.put("getDataList", getDataList);
        return returnData;
    }

    @RequestMapping("/getFinanceDataList_ajax/{projectId}/{pickYear}")
    @ResponseBody
    Map<String, Object> getFinanceDataList_ajax(@PathVariable("projectId") String projectId,@PathVariable("pickYear")  String pickYear, Model model) {
        double janIncome=0; double febIncome=0; double marIncome=0; double aprIncome=0; double mayIncome=0;
        double junIncome=0; double julIncome=0; double augIncome=0; double sepIncome=0; double octIncome=0;
        double novIncome=0; double decIncome=0; double totalIncome=0;
        double janCost=0; double febCost=0; double marCost=0; double aprCost=0; double mayCost=0;
        double junCost=0; double julCost=0; double augCost=0; double sepCost=0; double octCost=0;
        double novCost=0; double decCost=0; double totalCost=0;
        double janNet=0; double febNet=0; double marNet=0; double aprNet=0; double mayNet=0;
        double junNet=0; double julNet=0; double augNet=0; double sepNet=0; double octNet=0;
        double novNet=0; double decNet=0; double totalNet=0;
        Map<String, Object> params = new HashMap<String, Object>();
        Calendar date = Calendar.getInstance();
        String currentYear = String.valueOf(date.get(Calendar.YEAR));
        params.put("offset",1);
        params.put("limit",2);
        params.put("projectId", projectId);
        if (pickYear ==null) {
            params.put("currentYear", currentYear);
        }else{
            params.put("currentYear", pickYear);
        }
        Query queryFinanceDataList = new Query(params);
        List<MainDO> financeDataList = contractService.getFinanceDataList(queryFinanceDataList);
        for (int i=0; i<financeDataList.size();i++){
            if (i == 0){

                janIncome = Double.parseDouble(financeDataList.get(0).getJanuary()==null?
                        "0" : financeDataList.get(0).getJanuary());
                febIncome = Double.parseDouble( financeDataList.get(0).getFebruary()==null?
                        "0" : financeDataList.get(0).getFebruary());
                marIncome = Double.parseDouble( financeDataList.get(0).getMarch()==null?
                        "0" : financeDataList.get(0).getMarch());
                aprIncome = Double.parseDouble( financeDataList.get(0).getApril()==null?
                        "0" : financeDataList.get(0).getApril());
                mayIncome = Double.parseDouble( financeDataList.get(0).getMay()==null?
                        "0" : financeDataList.get(0).getMay());
                junIncome = Double.parseDouble( financeDataList.get(0).getJune()==null?
                        "0" : financeDataList.get(0).getJune());
                julIncome = Double.parseDouble( financeDataList.get(0).getJuly()==null?
                        "0" : financeDataList.get(0).getJuly());
                augIncome = Double.parseDouble( financeDataList.get(0).getAugust()==null?
                        "0" : financeDataList.get(0).getAugust());
                sepIncome = Double.parseDouble( financeDataList.get(0).getSeptember()==null?
                        "0" : financeDataList.get(0).getSeptember());
                octIncome = Double.parseDouble( financeDataList.get(0).getOctober()==null?
                        "0" : financeDataList.get(0).getOctober());
                novIncome = Double.parseDouble( financeDataList.get(0).getNovember()==null?
                        "0" : financeDataList.get(0).getNovember());
                decIncome = Double.parseDouble( financeDataList.get(0).getDecember()==null?
                        "0" : financeDataList.get(0).getDecember());
                totalIncome = Double.parseDouble( financeDataList.get(0).getTotal()==null?
                        "0" : financeDataList.get(0).getTotal());
            }
            if (i == 2){
                janCost = Double.parseDouble( financeDataList.get(2).getJanuary()==null?
                        "0":financeDataList.get(2).getJanuary());
                febCost = Double.parseDouble( financeDataList.get(2).getFebruary()==null?
                        "0":financeDataList.get(2).getFebruary());
                marCost = Double.parseDouble( financeDataList.get(2).getMarch()==null?
                        "0":financeDataList.get(2).getMarch());
                aprCost = Double.parseDouble( financeDataList.get(2).getApril()==null?
                        "0":financeDataList.get(2).getApril());
                mayCost = Double.parseDouble( financeDataList.get(2).getMay()==null?
                        "0":financeDataList.get(2).getMay());
                junCost = Double.parseDouble( financeDataList.get(2).getJune()==null?
                        "0":financeDataList.get(2).getJune());
                julCost = Double.parseDouble( financeDataList.get(2).getJuly()==null?
                        "0":financeDataList.get(2).getJuly());
                augCost = Double.parseDouble( financeDataList.get(2).getAugust()==null?
                        "0":financeDataList.get(2).getAugust());
                sepCost = Double.parseDouble( financeDataList.get(2).getSeptember()==null?
                        "0":financeDataList.get(2).getSeptember());
                octCost = Double.parseDouble( financeDataList.get(2).getOctober()==null?
                        "0":financeDataList.get(2).getOctober());
                novCost = Double.parseDouble( financeDataList.get(2).getNovember()==null?
                        "0":financeDataList.get(2).getNovember());
                decCost = Double.parseDouble( financeDataList.get(2).getDecember()==null?
                        "0":financeDataList.get(2).getDecember());
                totalCost = Double.parseDouble( financeDataList.get(2).getTotal()==null?
                        "0":financeDataList.get(2).getTotal());
            }
            if (i == 3){
                janCost = janCost + Double.parseDouble( financeDataList.get(3).getJanuary()==null?
                        "0":financeDataList.get(3).getJanuary());
                febCost = febCost + Double.parseDouble( financeDataList.get(3).getFebruary()==null?
                        "0":financeDataList.get(3).getFebruary());
                marCost = marCost + Double.parseDouble( financeDataList.get(3).getMarch()==null?
                        "0":financeDataList.get(3).getMarch());
                aprCost = aprCost + Double.parseDouble( financeDataList.get(3).getApril()==null?
                        "0":financeDataList.get(3).getApril());
                mayCost = mayCost + Double.parseDouble( financeDataList.get(3).getMay()==null?
                        "0":financeDataList.get(3).getMay());
                junCost = junCost + Double.parseDouble( financeDataList.get(3).getJune()==null?
                        "0":financeDataList.get(3).getJune());
                julCost = julCost + Double.parseDouble( financeDataList.get(3).getJuly()==null?
                        "0":financeDataList.get(3).getJuly());
                augCost = augCost + Double.parseDouble( financeDataList.get(3).getAugust()==null?
                        "0":financeDataList.get(3).getAugust());
                sepCost = sepCost + Double.parseDouble( financeDataList.get(3).getSeptember()==null?
                        "0":financeDataList.get(3).getSeptember());
                octCost = octCost + Double.parseDouble( financeDataList.get(3).getOctober()==null?
                        "0":financeDataList.get(3).getOctober());
                novCost = novCost + Double.parseDouble( financeDataList.get(3).getNovember()==null?
                        "0":financeDataList.get(3).getNovember());
                decCost = decCost + Double.parseDouble( financeDataList.get(3).getDecember()==null?
                        "0":financeDataList.get(3).getDecember());
                totalCost = totalCost + Double.parseDouble( financeDataList.get(3).getTotal()==null?
                        "0":financeDataList.get(3).getTotal());
            }
            if (i == 4){
                janCost = janCost + Double.parseDouble( financeDataList.get(4).getJanuary()==null?
                        "0":financeDataList.get(4).getJanuary());
                febCost = febCost + Double.parseDouble( financeDataList.get(4).getFebruary()==null?
                        "0":financeDataList.get(4).getFebruary());
                marCost = marCost + Double.parseDouble( financeDataList.get(4).getMarch()==null?
                        "0":financeDataList.get(4).getMarch());
                aprCost = aprCost + Double.parseDouble( financeDataList.get(4).getApril()==null?
                        "0":financeDataList.get(4).getApril());
                mayCost = mayCost + Double.parseDouble( financeDataList.get(4).getMay()==null?
                        "0":financeDataList.get(4).getMay());
                junCost = junCost + Double.parseDouble( financeDataList.get(4).getJune()==null?
                        "0":financeDataList.get(4).getJune());
                julCost = julCost + Double.parseDouble( financeDataList.get(4).getJuly()==null?
                        "0":financeDataList.get(4).getJuly());
                augCost = augCost + Double.parseDouble( financeDataList.get(4).getAugust()==null?
                        "0":financeDataList.get(4).getAugust());
                sepCost = sepCost + Double.parseDouble( financeDataList.get(4).getSeptember()==null?
                        "0":financeDataList.get(4).getSeptember());
                octCost = octCost + Double.parseDouble( financeDataList.get(4).getOctober()==null?
                        "0":financeDataList.get(4).getOctober());
                novCost = novCost + Double.parseDouble( financeDataList.get(4).getNovember()==null?
                        "0":financeDataList.get(4).getNovember());
                decCost = decCost + Double.parseDouble( financeDataList.get(4).getDecember()==null?
                        "0":financeDataList.get(4).getDecember());
                totalCost = totalCost + Double.parseDouble( financeDataList.get(4).getTotal()==null?
                        "0":financeDataList.get(4).getTotal());

            }
            if (i == 5){
                janCost = janCost + Double.parseDouble( financeDataList.get(5).getJanuary()==null?
                        "0":financeDataList.get(5).getJanuary());
                febCost = febCost + Double.parseDouble( financeDataList.get(5).getFebruary()==null?
                        "0":financeDataList.get(5).getFebruary());
                marCost = marCost + Double.parseDouble( financeDataList.get(5).getMarch()==null?
                        "0":financeDataList.get(5).getMarch());
                aprCost = aprCost + Double.parseDouble( financeDataList.get(5).getApril()==null?
                        "0":financeDataList.get(5).getApril());
                mayCost = mayCost + Double.parseDouble( financeDataList.get(5).getMay()==null?
                        "0":financeDataList.get(5).getMay());
                junCost = junCost + Double.parseDouble( financeDataList.get(5).getJune()==null?
                        "0":financeDataList.get(5).getJune());
                julCost = julCost + Double.parseDouble( financeDataList.get(5).getJuly()==null?
                        "0":financeDataList.get(5).getJuly());
                augCost = augCost + Double.parseDouble( financeDataList.get(5).getAugust()==null?
                        "0":financeDataList.get(5).getAugust());
                sepCost = sepCost + Double.parseDouble( financeDataList.get(5).getSeptember()==null?
                        "0":financeDataList.get(5).getSeptember());
                octCost = octCost + Double.parseDouble( financeDataList.get(5).getOctober()==null?
                        "0":financeDataList.get(5).getOctober());
                novCost = novCost + Double.parseDouble( financeDataList.get(5).getNovember()==null?
                        "0":financeDataList.get(5).getNovember());
                decCost = decCost + Double.parseDouble( financeDataList.get(5).getDecember()==null?
                        "0":financeDataList.get(5).getDecember());
                totalCost = totalCost + Double.parseDouble( financeDataList.get(5).getTotal()==null?
                        "0":financeDataList.get(5).getTotal());

            }
            if (i == 6){
                janCost = janCost + Double.parseDouble( financeDataList.get(6).getJanuary()==null?
                        "0":financeDataList.get(6).getJanuary());
                febCost = febCost + Double.parseDouble( financeDataList.get(6).getFebruary()==null?
                        "0":financeDataList.get(6).getFebruary());
                marCost = marCost + Double.parseDouble( financeDataList.get(6).getMarch()==null?
                        "0":financeDataList.get(6).getMarch());
                aprCost = aprCost + Double.parseDouble( financeDataList.get(6).getApril()==null?
                        "0":financeDataList.get(6).getApril());
                mayCost = mayCost + Double.parseDouble( financeDataList.get(6).getMay()==null?
                        "0":financeDataList.get(6).getMay());
                junCost = junCost + Double.parseDouble( financeDataList.get(6).getJune()==null?
                        "0":financeDataList.get(6).getJune());
                julCost = julCost + Double.parseDouble( financeDataList.get(6).getJuly()==null?
                        "0":financeDataList.get(6).getJuly());
                augCost = augCost + Double.parseDouble( financeDataList.get(6).getAugust()==null?
                        "0":financeDataList.get(6).getAugust());
                sepCost = sepCost + Double.parseDouble( financeDataList.get(6).getSeptember()==null?
                        "0":financeDataList.get(6).getSeptember());
                octCost = octCost + Double.parseDouble( financeDataList.get(6).getOctober()==null?
                        "0":financeDataList.get(6).getOctober());
                novCost = novCost + Double.parseDouble( financeDataList.get(6).getNovember()==null?
                        "0":financeDataList.get(6).getNovember());
                decCost = decCost + Double.parseDouble( financeDataList.get(6).getDecember()==null?
                        "0":financeDataList.get(6).getDecember());
                totalCost = totalCost + Double.parseDouble( financeDataList.get(6).getTotal()==null?
                        "0":financeDataList.get(6).getTotal());

            }
        }
        DecimalFormat df = new DecimalFormat("#.00");
        janNet = janIncome - janCost;
        febNet = febIncome - febCost;
        marNet = marIncome - marCost;
        aprNet = aprIncome - aprCost;
        mayNet = mayIncome - mayCost;
        junNet = junIncome - junCost;
        julNet = julIncome - julCost;
        augNet = augIncome - augCost;
        sepNet = sepIncome - sepCost;
        octNet = octIncome - octCost;
        novNet = novIncome - novCost;
        decNet = decIncome - decCost;
        totalNet = totalIncome - totalCost;
        MainDO  netIncome = new MainDO();
        netIncome.setName("未扣除其他成本利润");
        netIncome.setJanuary(String.valueOf(df.format(janNet)));
        netIncome.setFebruary(String.valueOf(df.format(febNet)));
        netIncome.setMarch(String.valueOf(df.format(marNet)));
        netIncome.setApril(String.valueOf(df.format(aprNet)));
        netIncome.setMay(String.valueOf(df.format(mayNet)));
        netIncome.setJune(String.valueOf(df.format(junNet)));
        netIncome.setJuly(String.valueOf(df.format(julNet)));
        netIncome.setAugust(String.valueOf(df.format(augNet)));
        netIncome.setSeptember(String.valueOf(df.format(sepNet)));
        netIncome.setOctober(String.valueOf(df.format(octNet)));
        netIncome.setNovember(String.valueOf(df.format(novNet)));
        netIncome.setDecember(String.valueOf(df.format(decNet)));
        netIncome.setTotal(String.valueOf(df.format(totalNet)));
        financeDataList.add(7,netIncome);
        Map<String, Object> returnData = new HashMap<String, Object>();
        returnData.put("financeDataList", financeDataList);
        model.addAttribute("currentYear", currentYear);
        return returnData;
    }


}
