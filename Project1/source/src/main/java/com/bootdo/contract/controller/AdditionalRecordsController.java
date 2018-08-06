package com.bootdo.contract.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.bootdo.activiti.service.ActTaskService;
import com.bootdo.common.domain.MainCopyPersonDO;
import com.bootdo.common.service.MainCopyPersonService;
import com.bootdo.contract.domain.PayoutDO;
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

import com.bootdo.activiti.utils.ActivitiUtils;
import com.bootdo.contract.domain.AdditionalRecordsDO;
import com.bootdo.contract.domain.ContractHardwareDetailDO;
import com.bootdo.contract.domain.ContractSoftwareDetailDo;
import com.bootdo.contract.service.AdditionalRecordsService;
import com.bootdo.contract.service.ContractHardwareDetailService;
import com.bootdo.contract.service.ContractSoftwareDetailService;
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
import com.bootdo.common.utils.ZipUtils;

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
	private ContractSoftwareDetailService contractSoftwareDetailService;
	@Autowired
	private FileService sysFileService;
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	private MainCopyPersonService mainCopyPersonService;
	@Autowired
	private ContractHardwareDetailService contractHardwareDetailService;
	@Autowired
	private ActTaskService actTaskService;
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
		AdditionalRecordsDO additionalRecords = additionalRecordsService.get(recordId);
		if (additionalRecords != null && additionalRecords.getProcessInstanceId()!= null){
			if (additionalRecords.getRecordApprovalStatus().equals("2")){
				return R.error("流程正在审批，不允许删除");
			}
			if (additionalRecords.getRecordApprovalStatus().equals("1")) {
				return R.error("流程已经审批完成，不允许删除");
			}
			actTaskService.deleteProcess(additionalRecords.getProcessInstanceId());
		}
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
		List<String> list = new ArrayList<String>();
		//级联删除流程相关
		for(int i=0;i<recordIds.length;i++){
			AdditionalRecordsDO additionalRecords= additionalRecordsService.get(recordIds[i]);
			if(additionalRecords!=null&&additionalRecords.getProcessInstanceId()!=null){
				if(additionalRecords.getRecordApprovalStatus().equals("2")){
					continue;
					//return R.error("流程正在审批，不允许删除");
				}else if(additionalRecords.getRecordApprovalStatus().equals("1")){
					//return R.error("流程已经审批完成，不允许删除");
					continue;
				}
				actTaskService.deleteProcess(additionalRecords.getProcessInstanceId());
				list.add(recordIds[i]);
			}
		}

		additionalRecordsService.batchRemove(list.toArray(new String[1]));
		if(list.size()<recordIds.length){
			return R.ok("有部分流程正在审批或审批完成，不允许删除");
		}else{
			return R.ok();
		}
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
	R  uploadExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request,AdditionalRecordsDO additionalRecords){
		String fileName = file.getOriginalFilename();
		fileName = FileUtil.renameToUUID(fileName);
		File datafile = null;
		try {
			FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath(), fileName);
			datafile = new File( bootdoConfig.getUploadPath()+fileName);
			// log数据保存
			long userid = getUserId(); // 用户id
			
			Map<String, Object> errorMsgs=additionalRecordsService.uploadExcel(datafile, userid);
			if ("success".equals(errorMsgs.get("result"))) {
				return R.ok();
			} else {
				return R.error();
			}
		} catch (Exception e) {
			return R.error();
		}
	}
	//模板下载
    @ResponseBody
 	@RequestMapping("/downloadTemplate")
    public void download(HttpServletResponse response,HttpServletRequest request) {
 		try {
 			
// 			 File files = new File(".\\src\\main\\resources\\downloadTemplate\\企业客户导入摸板.xls");
// 			System.out.println("getAbsolutePath:"+files.getAbsolutePath());  //getAbsolutePath()会将.认为是一个以.命名的文件
// 			System.out.println("getCanonicalPath:"+files.getCanonicalPath());//getCanonicalPath()得到的是一个规范路径没有.
// 			

 			File file = new File("./src/main/resources/downloadTemplate/合同增补导入模板.xls");
 			// 取得文件名。
 			String filename = file.getName();
 			
 			// 以流的形式下载文件。
 			InputStream fis = new BufferedInputStream(new FileInputStream(file.getCanonicalPath()));
 			byte[] buffer = new byte[fis.available()];
 			fis.read(buffer);
 			fis.close();
 			// 清空response
 			response.reset();
 			// 设置response的Header
 			// 设置文件名
 			response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes(),"ISO-8859-1"));
 			// 设置文件打下
 			response.addHeader("Content-Length", "" + file.length());
 			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
 			response.setContentType("application/octet-stream");
 			toClient.write(buffer);
 			toClient.flush();
 			toClient.close();
 		} catch (IOException ex) {
 			ex.printStackTrace();
 		}
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
	
//	****************************文件相关代码***************************
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
		
//		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
//		chars.charAt((int)(Math.random() * 52))+
		String fileName = file.getOriginalFilename();
//		fileName = FileUtil.renameToUUID(fileName);
		FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/" + fileName, new Date(),fileName);
		try {
			FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath(), fileName);
		} catch (Exception e) {
			return R.error();
		}
		int ids = sysFileService.save(sysFile);
		System.out.println(ids);
		if (ids > 0) {
			R r = R.ok();
			r.put("recordAttachment", ids);
			r.put("fileName", sysFile.getUrl());
			return r;
//			return R.ok().put("fileName", sysFile.getUrl());
		}
		return R.error();
	}
	/**
	 * 执行删除文件的时候同时删除Record_Attachment字段下的附件ID
	 */
	@ResponseBody
	@RequestMapping("/updateRecordAttachment")
	@RequiresPermissions("contract:additionalRecords:edit")
	public R updateRecordAttachment(AdditionalRecordsDO additionalRecords) {
		additionalRecordsService.updateRecordAttachment(additionalRecords);
		return R.ok();
	}
	//根据ID查看附件列表
			@ResponseBody
			@GetMapping("/listId")
			@RequiresPermissions("common:sysFile:sysFile")
			public PageUtils listId(@RequestParam("recordId")String recordId,@RequestParam Map<String, Object> params) {
//				String aa=request.getParameter("customerId");
				params.put("recordId", recordId);
				// 查询列表数据
				Query query = new Query(params);
				List<FileDO> sysFileList = sysFileService.listAdditionalRecordAttachment(query);
				for (int i = 0; i < sysFileList.size(); i++) {
					FileDO a =sysFileList.get(i);
					String urlFile=a.getUrl();
					String suffix=urlFile.substring(urlFile.indexOf(".")+1);
					if (suffix.equals("xls")||suffix.equals("xlsx")) {
						a.setSuffix("1");//1代表xlsx表格
					}else if (suffix.equals("docx")) {
						a.setSuffix("2");//2代表word文档
					}else if(suffix.equals("avi")||suffix.equals("wma")||suffix.equals("rmvb")||suffix.equals("rm")||suffix.equals("flash")||suffix.equals("mp4")||suffix.equals("mid")||suffix.equals("3GP")){
						a.setSuffix("3");//3代表视频文件
					}else if(suffix.equals("jpg")||suffix.equals("png")||suffix.equals("gif")||suffix.equals("tif")||suffix.equals("psd")||suffix.equals("dng")||suffix.equals("cr2")||suffix.equals("nef")){
						a.setSuffix("4");//4代表图片
					}else if(suffix.equals("rar")||suffix.equals("zip")){
						a.setSuffix("5");//5代表压缩文件
					}else {
						a.setSuffix("6");//6除以判断的文件之外的文件
					}
//					System.out.println("************************************************");
//					System.out.println(urlFile.substring(urlFile.indexOf(".")+1));
//					System.out.println("************************************************");
				}
				int total = sysFileService.listAdditionalRecordAttachmentCount(query);
				PageUtils pageUtils = new PageUtils(sysFileList, total);
				return pageUtils;
			}
 // 根据文件名称下载相关代码
 	@ResponseBody
 	@RequestMapping("/down")
 	public void download(HttpServletResponse response,@RequestParam("fileName") String fileName) {
 		try {
 			// path是指欲下载的文件的路径。
 			String path = "C:/var/uploaded_files/"+fileName;

 			File file = new File(path);
 			// 取得文件名。
 			String filename = file.getName();
 			
 			// 以流的形式下载文件。
 			InputStream fis = new BufferedInputStream(new FileInputStream(path));
 			byte[] buffer = new byte[fis.available()];
 			fis.read(buffer);
 			fis.close();
 			// 清空response
 			response.reset();
 			// 设置response的Header
 			// 设置文件名
 			response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes(),"ISO-8859-1"));
 			// 设置文件打下
 			response.addHeader("Content-Length", "" + file.length());
 			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
 			response.setContentType("application/octet-stream");
 			toClient.write(buffer);
 			toClient.flush();
 			toClient.close();
 		} catch (IOException ex) {
 			ex.printStackTrace();
 		}
 	}
 	
 	
 	
 	
 	  /**
     * 打包压缩下载文件
     */
    @RequestMapping(value = "/downLoadZipFile")
    @ResponseBody
    public void downLoadZipFile(HttpServletResponse response,@RequestParam("id")String id) throws IOException{
    	String[] ids=id.split(",");
        String zipName = "downLoadFile.zip";
        List<FileDO> fileList = sysFileService.downLoadListId(ids);//查询数据库中记录
        response.setContentType("APPLICATION/OCTET-STREAM");  
        response.setHeader("Content-Disposition","attachment; filename="+zipName);
        ZipOutputStream out = new ZipOutputStream(response.getOutputStream());
        try {
            for(Iterator<FileDO> it = fileList.iterator();it.hasNext();){
            	FileDO file = it.next();
                ZipUtils.doCompress("C:/var/uploaded_files/"+file.getFileName(), out);
                response.flushBuffer();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            out.close();
        }
    }
	//根据id查询硬件信息
    @ResponseBody
	@GetMapping("/listY/{tmpCustomerId}")
	@RequiresPermissions("contract:contractHardwareDetail:contractHardwareDetail")
	public PageUtils listY(@RequestParam Map<String, Object> params,@PathVariable("tmpCustomerId")String recordId){
		//查询列表数据
		params.put("recordId", recordId);
        Query query = new Query(params);
		List<ContractHardwareDetailDO> contractHardwareDetailList = contractHardwareDetailService.listY(query);
		int total = contractHardwareDetailService.countY(query);
		PageUtils pageUtils = new PageUtils(contractHardwareDetailList, total);
		return pageUtils;
	}
    //根据id查看软件信息
    @ResponseBody
	@GetMapping("/listR/{tmpCustomerId}")
	@RequiresPermissions("contract:contractSoftwareDetail:ContractSoftwareDetail")
	public PageUtils listR(@RequestParam Map<String, Object> params,@PathVariable("tmpCustomerId") String recordId) {
		//查询列表数据
		params.put("recordId", recordId);
		Query query = new Query(params);
		List<ContractSoftwareDetailDo> contractSoftwareDetailList=contractSoftwareDetailService.listR(query);
		int total = contractSoftwareDetailService.countR(query);
		PageUtils pageUtils = new PageUtils(contractSoftwareDetailList, total);
		return pageUtils;
	}
    /**
	 * 硬件明细详情
	 */
	@RequestMapping("/contractHardwareDetai")
	@RequiresPermissions("contract:additionalRecords:additionalRecords")
	String contractHardwareDetai() {
		return "contract/contractHardwareDetail/recordsContractHardwareDetail";
	}

	/**
	 * 软件功能详情
	 */
	@RequestMapping("/contractSoftwareDetail")
	@RequiresPermissions("contract:additionalRecords:additionalRecords")
	String contractSoftwareDetail() {
		return "contract/contractSoftwareDetail/recordsContractSoftwareDetail";
	}
}
