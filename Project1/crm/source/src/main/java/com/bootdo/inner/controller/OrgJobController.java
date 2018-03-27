package com.bootdo.inner.controller;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.JobName;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

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

import com.bootdo.inner.domain.InnerOrgEmployeeDO;
import com.bootdo.inner.domain.OrgJobDO;
import com.bootdo.inner.service.OrgJobService;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 内部组织机构_岗位
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-11-24 13:49:26
 */

@Controller
@RequestMapping("/inner/orgJob")
public class OrgJobController extends BaseController {

	@Autowired
	private OrgJobService orgJobService;
	@Autowired
	private BootdoConfig bootdoConfig;

	@GetMapping()
	@RequiresPermissions("inner:orgJob:orgJob")
	String OrgJob() {
		return "inner/orgJob/orgJob";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("inner:orgJob:orgJob")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		if (params.get("jobName") != null && !"".equals(params.get("jobName"))) {
			params.put("jobName", "%" + (String) params.get("jobName") + "%");
		}
		if (params.get("jobDept") != null && !"".equals(params.get("jobDept"))) {
			params.put("jobDept", "%" + (String) params.get("jobDept") + "%");
		}
		if (params.get("jobOperator") != null && !"".equals(params.get("jobOperator"))) {
			params.put("jobOperator", "%" + (String) params.get("jobOperator") + "%");
		}
		Query query = new Query(params);
		List<OrgJobDO> orgJobList = orgJobService.list(query);
		int total = orgJobService.count(query);
		PageUtils pageUtils = new PageUtils(orgJobList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("inner:orgJob:add")
	String add() {
		return "inner/orgJob/add";
	}

	@GetMapping("/import")
	String importFile() {
		return "/inner/orgJob/import";
	}

	@RequestMapping("/edit_ajax/{jobId}")
	@ResponseBody
	Map<String, Object> edit_ajax(@PathVariable("jobId") String jobId) {
		OrgJobDO job = orgJobService.get(jobId);
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("job", job);
		return returnData;
	}
	
	@GetMapping("/edit/{jobId}")
	@RequiresPermissions("inner:orgJob:edit")
	String edit(@PathVariable("jobId") String jobId, Model model) {
		model.addAttribute("jobId", jobId);
		return "inner/orgJob/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("inner:orgJob:add")
	public R save(OrgJobDO orgJob) {
		orgJob.setJobOperator(getUserId());
		if (orgJobService.save(orgJob) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("inner:orgJob:edit")
	public R update(OrgJobDO orgJob) {
		orgJob.setJobOperator(getUserId());
		orgJobService.update(orgJob);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("inner:orgJob:remove")
	public R remove(String jobId) {
		if (orgJobService.remove(jobId) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("inner:orgJob:batchRemove")
	public R remove(@RequestParam("ids[]") String[] jobIds) {
		orgJobService.batchRemove(jobIds);
		return R.ok();
	}

	@ResponseBody
	@GetMapping("/listDic")
	public List<DictDO> listByType() {
		// 查询列表数据
		Map<String, Object> map = new HashMap<>(16);
		map.put("type", "");
		List<DictDO> dictList = orgJobService.listDic();
		return dictList;
	}

	/**
	 * 导入
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
		orgJobService.dataImport(datafile, userid);
		return null;
	}
	
	/**
	 * export file
	 */
	@RequestMapping(value = "/export")
	public  @ResponseBody void export(
			@RequestParam(value = "jobId", required = false) String OrgJob_jobId,
			@RequestParam(value = "jobName", required = false) String OrgJob_jobName,
			@RequestParam(value = "jobDept", required = false) String OrgJob_jobDept,
			@RequestParam(value = "jobOperator", required = false) String OrgJob_jobOperator,
			HttpServletResponse response,HttpServletRequest request) throws ParseException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("jobId", OrgJob_jobId);
		params.put("jobName", (OrgJob_jobName != null && !"".equals(OrgJob_jobName))?"%"+OrgJob_jobName+"%" : OrgJob_jobName);
		params.put("jobDept", (OrgJob_jobDept != null & !"".equals(OrgJob_jobDept))?"%"+OrgJob_jobDept+"%" : OrgJob_jobDept);
		params.put("jobOperator", (OrgJob_jobOperator != null && !"".equals(OrgJob_jobOperator))?"%"+OrgJob_jobOperator+"%" : OrgJob_jobOperator);
 		List<OrgJobDO> list = orgJobService.getQuery(params);
		if(list.size()>0) {
			System.out.println("---------------------list.size------------------->" + list.size());
			response.setContentType("application/binary;charset=UTF-8");
			try {
				ServletOutputStream out = response.getOutputStream();
				String fileName = new String((new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date()).getBytes(),"UTF-8");
				response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
				String[] titles = { "岗位编号", "部门编号", "岗位名称", "所属部门", "级别名称", "级别描述", "岗位描述", "备注", "操作人", "操作时间", "岗位设立时间"};
				orgJobService.export(titles, out, list);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
