package com.bootdo.sales.controller;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bootdo.inner.domain.InnerOrgEmployeeDO;
import com.bootdo.oa.domain.NotifyDO;
import com.bootdo.sales.domain.BusinessDO;
import com.bootdo.sales.domain.SalesProjectDO;
import com.bootdo.sales.service.SalesProjectService;
import com.bootdo.common.config.Constant;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.FileType;

/**
 * 售前项目信息表
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-11-16 11:25:36
 */
 
@Controller
@RequestMapping("/sales/salesProject")
public class SalesProjectController extends BaseController {
	@Autowired
	private FileService sysFileService;
	@Autowired
	private SalesProjectService salesProjectService;
	@Autowired
	private BootdoConfig bootdoConfig; 
	
	@GetMapping()
	@RequiresPermissions("sales:salesProject:salesProject")
	String SalesProject(){
	    return "sales/salesProject/salesProject";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("sales:salesProject:salesProject")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SalesProjectDO> salesProjectList = salesProjectService.list(query);
		int total = salesProjectService.count(query);
		PageUtils pageUtils = new PageUtils(salesProjectList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("sales:salesProject:add")
	String add(){
	    return "sales/salesProject/add";
	}
	@GetMapping("/import")
	@RequiresPermissions("sales:salesProject:uploadExcel")
	String importFile() {
		return "sales/salesProject/import";
	}
	
	@RequestMapping("/edit_ajax/{projectId}")
	@ResponseBody
	Map<String, Object> edit_ajax(@PathVariable("projectId") String projectId) {
		SalesProjectDO salesProject = salesProjectService.get(projectId);
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("salesProject", salesProject);
		return returnData;
	}
	@GetMapping("/edit/{projectId}")
	@RequiresPermissions("sales:salesProject:edit")
	String edit(@PathVariable("projectId") String projectId,Model model){
		//SalesProjectDO salesProject = salesProjectService.get(projectId);
		model.addAttribute("projectId", projectId);
	    return "sales/salesProject/edit";
	}
	
	
	/**
	 * 查看
	 */
	@GetMapping("/see/{projectId}")
	@RequiresPermissions("sales:salesProject:see")
	String see(@PathVariable("projectId") String projectId, Model model) {
		model.addAttribute("projectId", projectId);
		return "sales/salesProject/see";
	}
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("sales:salesProject:add")
	public R save( SalesProjectDO salesProject){
		/*if (salesProject.getProjectId() == null) {
		String maxProjectId = salesProjectService.getMaxProjectId();
		if (maxProjectId == null) {
			maxProjectId = "001";
		} else {
			maxProjectId = maxProjectId.substring(salesProject.getBusinessId().length());
		}
		String ProjectId = String.valueOf(Long.parseLong(salesProject.getCustomerId() + maxProjectId)+1);
		salesProject.setProjectId(ProjectId);
	}*/
		salesProject.setProjectCreator(getUserId());
		salesProject.setProjectOperator(getUserId());
		if(salesProjectService.save(salesProject)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sales:salesProject:edit")
	public R update( SalesProjectDO salesProject){
		salesProject.setProjectOperator(getUserId());
		salesProjectService.update(salesProject);
		return R.ok();
	}	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("sales:salesProject:remove")
	public R remove( String projectId){
		if(salesProjectService.remove(projectId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("sales:salesProject:batchRemove")
	public R remove(@RequestParam("ids[]") String[] projectIds){
		salesProjectService.batchRemove(projectIds);
		return R.ok();
	}
	
	@ResponseBody
	@GetMapping("/listAllDic")
	public List<DictDO> listAllByType() {
		// 查询列表数据
		Map<String, Object> map = new HashMap<>(16);
		map.put("type", "");
		List<DictDO> dictList = salesProjectService.listAllDic();
		return dictList;
	}
	@ResponseBody
	@GetMapping("/listDic")
	public List<DictDO> listByType() {
		// 查询列表数据
		Map<String, Object> map = new HashMap<>(16);
		map.put("type", "");
		List<DictDO> dictList = salesProjectService.listDic();
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
			salesProjectService.uploadExcel(datafile, userid);
		
		return null;
	}
	
	/**
	 * 导出
	 */
	@RequestMapping(value = "/export")
	public  @ResponseBody void export(
			@RequestParam(value = "province", required = false) String province,
			@RequestParam(value = "city", required = false) String city,
			@RequestParam(value = "area", required = false) String area,
			@RequestParam(value = "projectId", required = false) String export_projectId,
			@RequestParam(value = "projectSales", required = false) String export_projectSales, HttpServletResponse response,
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
		params.put("province", province);
		params.put("city", city);
		params.put("area", area);
		params.put("projectId", export_projectId);
		params.put("projectSales", export_projectSales);
		List<SalesProjectDO> list = salesProjectService.getQuery(params);
		if(list.size()>0) {
			System.out.println("---------------------list.size------------------->" + list.size());
			response.setContentType("application/binary;charset=UTF-8");
			try {
				ServletOutputStream out = response.getOutputStream();
				String fileName = new String((new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date()).getBytes(),"UTF-8");
				response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
				String[] titles = { "售前项目编号", "企业客户编号", "项目名称", "销售负责人", "项目开始时间", "项目结束时间", "售前经理", "项目类型", "项目阶段", "项目描述", "旧项目编号","备注", 
						"操作人", "操作时间", "创建人", "创建时间", "业务编号"};
				salesProjectService.export(titles, out, list);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
