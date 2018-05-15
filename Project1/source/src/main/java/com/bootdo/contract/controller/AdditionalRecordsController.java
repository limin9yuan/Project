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

import com.bootdo.common.domain.MainCopyPersonDO;
import com.bootdo.common.service.MainCopyPersonService;
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

import com.bootdo.activiti.utils.ActivitiUtils;
import com.bootdo.approval.domain.PurchaseDO;
import com.bootdo.contract.domain.AdditionalRecordsDO;
import com.bootdo.contract.service.AdditionalRecordsService;
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
import com.bootdo.sales.domain.SalesProjectDO;

/**
 * 合同增补记录
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2018-01-04 13:10:31
 */
 
@Controller
@RequestMapping("/contract/additionalRecords")
public class AdditionalRecordsController extends BaseController {
	@Autowired
	private AdditionalRecordsService additionalRecordsService;
	@Autowired
	private ActivitiUtils activitiUtils;
	
	@Autowired
	private FileService sysFileService;
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	private MainCopyPersonService mainCopyPersonService;
	
	@GetMapping()
	@RequiresPermissions("contract:additionalRecords:additionalRecords")
	String AdditionalRecords(){
	    return "contract/additionalRecords/additionalRecords";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("contract:additionalRecords:additionalRecords")
	public PageUtils list(@RequestParam Map<String, Object> params){
		params.put("userId", getUserId());
		params.put("userName", getUsername());
		params.put("tableName", "contract_additional_records");
		//查询列表数据
        Query query = new Query(params);
		List<AdditionalRecordsDO> additionalRecordsList = additionalRecordsService.list(query);
		int total = additionalRecordsService.count(query);
		PageUtils pageUtils = new PageUtils(additionalRecordsList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("contract:additionalRecords:add")
	String add(){
	    return "contract/additionalRecords/add";
	}
	
	@GetMapping("/import")
	@RequiresPermissions("contract:additionalRecords:uploadExcel")
	String importFile() {
		return "contract/additionalRecords/import";
	}
	@RequestMapping("/edit_ajax/{recordId}")
	@ResponseBody
	Map<String, Object> edit_ajax(@PathVariable("recordId") String recordId) {
		AdditionalRecordsDO additionalRecords = additionalRecordsService.get(recordId);
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("additionalRecords", additionalRecords);
		return returnData;
	}
	@GetMapping("/edit/{recordId}")
	@RequiresPermissions("contract:additionalRecords:edit")
	String edit(@PathVariable("recordId") String recordId,Model model){
		//AdditionalRecordsDO additionalRecords = additionalRecordsService.get(recordId);
		model.addAttribute("recordId", recordId);
	    return "contract/additionalRecords/edit";
	}

	@GetMapping("/view/{recordId}")
	@RequiresPermissions("contract:additionalRecords:view")
	String view(@PathVariable("recordId") String recordId,Model model){
		//AdditionalRecordsDO additionalRecords = additionalRecordsService.get(recordId);
		model.addAttribute("recordId", recordId);
		return "contract/additionalRecords/view";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("contract:additionalRecords:add")
	public R save( AdditionalRecordsDO additionalRecords){
		additionalRecords.setRecordOperator(getUserId());
		int resultRecordId=additionalRecordsService.save(additionalRecords);
		if (resultRecordId > 0) {
			MainCopyPersonDO mcp = new MainCopyPersonDO();
			String mainPersonId = additionalRecords.getMainPersonId();
			if (!"".equals(mainPersonId)) {
				String mainPersonIdArray[] = mainPersonId.split(",");
				for (int i = 0; i < mainPersonIdArray.length; i++) {
					mcp.setTId(additionalRecords.getRecordId());
					mcp.setMainPerson(1);
					mcp.setEmployeeId(mainPersonIdArray[i]);
					mcp.setOperator(getUserId());
					mcp.setTableName("contract_additional_records");
					mainCopyPersonService.save(mcp);

				}
			}

			String copyPersonId = additionalRecords.getCopyPersonId();
			if (!"".equals(copyPersonId)) {
				String copyPersonIdArray[] = copyPersonId.split(",");
				for (int i = 0; i < copyPersonIdArray.length; i++) {
					mcp.setTId(additionalRecords.getRecordId());
					mcp.setMainPerson(0);
					mcp.setEmployeeId(copyPersonIdArray[i]);
					mcp.setOperator(getUserId());
					mcp.setTableName("contract_additional_records");
					mainCopyPersonService.save(mcp);
				}


			}
			R r = R.ok();
			r.put("recordId", resultRecordId);
			return r;
		}
		return R.error(); 
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("contract:additionalRecords:edit")
	public R update( AdditionalRecordsDO additionalRecords){
		additionalRecords.setRecordOperator(getUserId());
		String recordIds = additionalRecords.getRecordId();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("offset",1);
		params.put("limit",2);
		params.put("tId",recordIds);
		params.put("tableName","contract_additional_records");
		additionalRecordsService.update(additionalRecords);
		mainCopyPersonService.remove(params);
		if (!recordIds.equals("")) {
			MainCopyPersonDO mcp = new MainCopyPersonDO();
			String mainPersonId = additionalRecords.getMainPersonId();
			if (!"".equals(mainPersonId)) {
				String mainPersonIdArray[] = mainPersonId.split(",");

				for (int i = 0; i < mainPersonIdArray.length; i++) {
					mcp.setTId(additionalRecords.getRecordId());
					mcp.setMainPerson(1);
					mcp.setEmployeeId(mainPersonIdArray[i]);
					mcp.setOperator(getUserId());
					mcp.setTableName("contract_additional_records");
					mainCopyPersonService.save(mcp);

				}
			}

			String copyPersonId = additionalRecords.getCopyPersonId();
			if (!"".equals(copyPersonId)) {
				String copyPersonIdArray[] = copyPersonId.split(",");
				for (int i = 0; i < copyPersonIdArray.length; i++) {
					mcp.setTId(additionalRecords.getRecordId());
					mcp.setMainPerson(0);
					mcp.setEmployeeId(copyPersonIdArray[i]);
					mcp.setOperator(getUserId());
					mcp.setTableName("contract_additional_records");
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
	@RequiresPermissions("contract:additionalRecords:remove")
	public R remove( String recordId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("offset",1);
		params.put("limit",2);
		params.put("tId",recordId);
		params.put("tableName","contract_additional_records");
		if(additionalRecordsService.remove(recordId)>0){
			mainCopyPersonService.remove(params);
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("contract:additionalRecords:batchRemove")
	public R remove(@RequestParam("ids[]") String[] recordIds){
		additionalRecordsService.batchRemove(recordIds);
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
		List<DictDO> dictList = additionalRecordsService.listDic();
		return dictList;
	}
	
	/**
	 * Excel导入
	 * 
	 * @param file
	 * @param request
	 * @return
	 */
	@ResponseBody
	@PostMapping("/uploadExcel")
	R  uploadExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
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
			additionalRecordsService.uploadExcel(datafile, userid);
		
		return null;
	}
	/**
	 * 导出
	 */
	@RequestMapping(value = "/export")
	public  @ResponseBody void export(
			//@RequestParam(value = "province", required = false) String province,
			//@RequestParam(value = "city", required = false) String city,
			//@RequestParam(value = "area", required = false) String area,
			@RequestParam(value = "customerId", required = false) String customerId,
			@RequestParam(value = "businessId", required = false) String businessId,
			@RequestParam(value = "projectId", required = false) String projectId, HttpServletResponse response,
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
		params.put("customerId", customerId);
		params.put("businessId", businessId);
		params.put("projectId", projectId);
		List<AdditionalRecordsDO> list = additionalRecordsService.getQuery(params);
		if(list.size()>0) {
			System.out.println("---------------------list.size------------------->" + list.size());
			response.setContentType("application/binary;charset=UTF-8");
			try {
				ServletOutputStream out = response.getOutputStream();
				String fileName = new String((new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date()).getBytes(),"UTF-8");
				response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
				String[] titles = { "合同增补记录编号", "合同编号", "申请人姓名", "建设单位", "增补总金额", "增补内容描述", "增补原因", "销售负责人", "关联合同编号",
						"备注", "审批状态","创建人", "提交评审时间", "项目名称", "合同名称","预计开发票时间" };
				additionalRecordsService.export(titles, out, list);
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
	@RequiresPermissions("contract:additionalRecords:add")
	String form(){
	    return "contract/additionalRecords/add";
	}
	//审批处理页面
	@GetMapping("/form/{taskId}")
	@RequiresPermissions("contract:additionalRecords:add")
	String formTask(@PathVariable("taskId") String taskId,Model model){
		//取得流程表单数据
		AdditionalRecordsDO additionalRecords = additionalRecordsService.view(activitiUtils.getBusinessKeyByTaskId(taskId));
		if(additionalRecords!=null){
			model.addAttribute("additionalRecords", additionalRecords);
			//model.addAttribute("taskId",taskId);
		}
	    return "contract/additionalRecords/ViewAdditionalRecords";
	}
	
	
	 //审批处理保存
	@ResponseBody
	@RequestMapping("/form/update")
	@RequiresPermissions("contract:additionalRecords:edit")
	public R formUpdate( AdditionalRecordsDO additionalRecords){
		
		additionalRecordsService.formUpdate(additionalRecords);
		return R.ok();
	}
}
