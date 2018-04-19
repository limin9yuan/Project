package com.bootdo.project.controller;

import java.io.File;
import java.io.PrintWriter;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bootdo.project.domain.ProjectDO;
import com.bootdo.project.service.ProjectService;
import com.bootdo.sales.domain.SalesProjectDO;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.config.Constant;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 项目信息表
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-11-28 09:52:05
 */

@Controller
@RequestMapping("/project/project")
public class ProjectController extends BaseController {
	private String prefix = "project/project";
	@Autowired
	private ProjectService projectService;
	@Autowired
	private BootdoConfig bootdoConfig;

	@GetMapping()
	@RequiresPermissions("project:project:project")
	String Project() {
		return "project/project/project";
	}
	/*@GetMapping("/projectRevenue")
	@RequiresPermissions("project:project:projectRevenue")
	String projectRevenue() {
		return "project/project/projectRevenue";
	}*/

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("project:project:project")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		if (params.get("projectId") != null && !"".equals(params.get("projectId"))) {
			params.put("projectId", "%" + (String) params.get("projectId") + "%");
		}
		if (params.get("projectName") != null && !"".equals(params.get("projectName"))) {
			params.put("projectName", "%" + (String) params.get("projectName") + "%");
		}
		if (params.get("projectPhase") != null && !"".equals(params.get("projectPhase"))) {
			params.put("projectPhase", "%" + (String) params.get("projectPhase") + "%");
		}
		if (params.get("customerName") != null && !"".equals(params.get("customerName"))) {
			params.put("customerName", "%" + (String) params.get("customerName") + "%");
		}
		if (params.get("projectOwner") != null && !"".equals(params.get("projectOwner"))) {
			params.put("projectOwner", "%" + (String) params.get("projectOwner") + "%");
		}
		if (params.get("deptId") != null && !"".equals(params.get("deptId"))) {
			params.put("deptId", "%" + (String) params.get("deptId") + "%");
		}
		Query query = new Query(params);
		List<ProjectDO> projectList = projectService.list(query);
		int total = projectService.count(query);
		PageUtils pageUtils = new PageUtils(projectList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("project:project:add")
	String add() {
		return "project/project/add";
	}
	
	@GetMapping("/import")
	@RequiresPermissions("project:project:uploadExcel")
	String importFile() {
		return "project/project/import";
	}
	@RequestMapping("/edit_ajax/{projectId}")
	@ResponseBody
	Map<String, Object> edit_ajax(@PathVariable("projectId") String projectId) {
		ProjectDO project = projectService.get(projectId);
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("project", project);
		return returnData;
	}
	@GetMapping("/edit/{projectId}")
	@RequiresPermissions("project:project:edit")
	String edit(@PathVariable("projectId") String projectId, Model model) {
		//ProjectDO project = projectService.get(projectId);
		model.addAttribute("projectId", projectId);
		return "project/project/edit";
	}

	/**
	 * 查看项目信息
	 */
	@GetMapping("/examineP/{customerId}")
	@RequiresPermissions("project:project:project")
	String examineP(@PathVariable("customerId") String customerId, Model model) {
		model.addAttribute("customerId", customerId);
		
		return "sales/companyCustomer/examineProject";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("project:project:add")
	public R save(ProjectDO project) {
		Map<String, Object> params = new HashMap<>(16);
		params.put("projectId", project.getProjectRelatedId());
		params.put("offset", 10);
		params.put("page", 1);
		params.put("limit", 10);
		Query query = new Query(params);
		
		int total = projectService.count(query);
		
		if(total>0){
			return R.error("项目编号已存在，请重新选择关联项目。");
			
		}
		project.setProjectCreator(getUserId());

		project.setProjectOperator(getUserId());
		if (projectService.save(project) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("project:project:edit")
	public R update(ProjectDO project) {
		
		project.setProjectCreator(getUserId());

		project.setProjectOperator(getUserId());
		if (projectService.update(project) > 0) {
			return R.ok();
		}
		return R.error();
	//return projectDao.update(project);
}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("project:project:remove")
	public R remove(String projectId) {
		if (projectService.remove(projectId) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("project:project:batchRemove")
	public R remove(@RequestParam("ids[]") String[] projectIds) {
		projectService.batchRemove(projectIds);
		return R.ok();
	}

	@GetMapping("/viewDetail/{projectId}")
	@RequiresPermissions("project:project:viewDetail")
	String viewDetail(@PathVariable("projectId") String projectId, Model model) {
		ProjectDO project = projectService.get(projectId);
		model.addAttribute("project", project);
		return "project/project/viewDetail";
	}

	@GetMapping("/tree")
	@ResponseBody
	public Tree<ProjectDO> tree() {
		Tree<ProjectDO> tree = new Tree<ProjectDO>();
		tree = projectService.getTree();
		return tree;
	}

	@GetMapping("/treeView")
	String treeView() {
		return prefix + "/projectTree";
	}

	@ResponseBody
	@GetMapping("/listDic")
	public List<DictDO> listDic() {
		// 查询列表数据
		Map<String, Object> map = new HashMap<>(16);
		map.put("type", "");
		List<DictDO> dictList = projectService.listDic();
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
			projectService.uploadExcel(datafile, userid);
		
		return null;
	}
	
	/**
	 * 导出
	 */
	@RequestMapping(value = "/export")
	public  @ResponseBody void export(
			@RequestParam(value = "projectId", required = false) String projectId,
			@RequestParam(value = "projectName", required = false) String projectName,
			@RequestParam(value = "projectOwner", required = false) String projectOwner,
			@RequestParam(value = "projectPhase", required = false) String projectPhase,
			@RequestParam(value = "customerId", required = false) String customerId,
			@RequestParam(value = "deptId", required = false) String deptId, HttpServletResponse response,
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
		params.put("projectId", (projectId != null && !"".equals(projectId))?"%"+projectId+"%":projectId );
		params.put("projectName", (projectName!=null && !"".equals(projectName))?"%"+projectName+"%":projectName );
		params.put("projectOwner", (projectOwner != null && !"".equals(projectOwner))?"%"+projectOwner+"%":projectOwner );
		params.put("projectPhase", (projectPhase!=null && !"".equals(projectPhase))?"%"+projectPhase+"%":projectPhase );
		params.put("customerId", (customerId != null && !"".equals(customerId))?"%"+customerId+"%":customerId );
		params.put("deptId",(deptId != null && !"".equals(deptId))?"%"+deptId+"%":deptId );
		List<ProjectDO> list = projectService.getQuery(params);
		if(list.size()>0) {
			System.out.println("---------------------list.size------------------->" + list.size());
			response.setContentType("application/binary;charset=UTF-8");
			try {
				ServletOutputStream out = response.getOutputStream();
				String fileName = new String((new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date()).getBytes(),"UTF-8");
				response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
				String[] titles = { "项目编号", "企业客户编号", "项目名称", "销售负责人", "项目开始时间", "项目结束时间", "项目经理","研发经理","研发开始时间","研发结束时间",
						"项目类型", "项目阶段", "项目描述", "旧项目编号","备注", "操作人", "操作时间", "创建人", "创建时间", "流程种类","是否分包","是否签订合同","部门编号","项目集合编号" };
				projectService.export(titles, out, list);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
