package com.bootdo.contract.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.contract.domain.ContractSoftwareDetailDo;
import com.bootdo.contract.service.ContractSoftwareDetailService;

/**
 * 软件功能
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/contract/contractSoftwareDetail")
public class ContractSoftwareDetailController extends BaseController{
	@Autowired
	private ContractSoftwareDetailService contractSoftwareDetailService;
	
	@GetMapping()
	@RequiresPermissions("contract:contractSoftwareDetail:ContractSoftwareDetail")
	String ContractSoftwareDetail() {
		return "contract/contractSoftwareDetail/ContractSoftwareDetail";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("contract:contractSoftwareDetail:ContractSoftwareDetail")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		//查询列表数据
		Query query = new Query(params);
		List<ContractSoftwareDetailDo> contractSoftwareDetailList=contractSoftwareDetailService.list(query);
		int total = contractSoftwareDetailService.count(query);
		PageUtils pageUtils = new PageUtils(contractSoftwareDetailList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("contract:contractSoftwareDetail:add")
	String add() {
		return "contract/contractSoftwareDetail/add";
	}
	
	@GetMapping("/edit/{softwaresetailId}")
	@RequiresPermissions("contract:contractSoftwareDetail:edit")
	String edit(@PathVariable("softwaresetailId") String softwaresetailId,Model model) {
		ContractSoftwareDetailDo contractSoftwareDetail = contractSoftwareDetailService.get(softwaresetailId);
		model.addAttribute("contractSoftwareDetail", contractSoftwareDetail);
		return "contract/contractSoftwareDetail/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("contract:contractSoftwareDetail:add")
	public R save( ContractSoftwareDetailDo contractSoftwareDetailDo){
		contractSoftwareDetailDo.setSoftwareDetailId(getUserId());
		if (contractSoftwareDetailService.save(contractSoftwareDetailDo)>0) {
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("contract:contractSoftwareDetail:edit")
	public R update( ContractSoftwareDetailDo contractSoftwareDetailDo){
		contractSoftwareDetailDo.setSoftwareDetailOperator(Long.toString(getUserId()));
		contractSoftwareDetailService.update(contractSoftwareDetailDo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("contract:contractSoftwareDetail:remove")
	public R remove( String softwaresetailId){
		if(contractSoftwareDetailService.remove(softwaresetailId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("contract:contractSoftwareDetail:batchRemove")
	public R remove(@RequestParam("ids[]") String[] softwaresetailIds){
		contractSoftwareDetailService.batchRemove(softwaresetailIds);
		return R.ok();
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

 			File file = new File("./src/main/resources/downloadTemplate/软件功能.xls");
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
}














