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
import com.bootdo.contract.domain.ContractHardwareDetailDO;
import com.bootdo.contract.service.ContractHardwareDetailService;

/**
 * 硬件明细表
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/contract/contractHardwareDetail")
public class ContractHardwareDetailController extends BaseController{
	@Autowired
	private ContractHardwareDetailService contractHardwareDetailService;
	
	@GetMapping()
	@RequiresPermissions("contract:contractHardwareDetail:contractHardwareDetail")
	String contractHardwareDetail(){
	    return "contract/contractHardwareDetail/contractHardwareDetail";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("contract:contractHardwareDetail:contractHardwareDetail")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ContractHardwareDetailDO> contractHardwareDetailList = contractHardwareDetailService.list(query);
		int total = contractHardwareDetailService.count(query);
		PageUtils pageUtils = new PageUtils(contractHardwareDetailList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("contract:contractHardwareDetail:add")
	String add(){
	    return "contract/contractHardwareDetail/add";
	}
	
	@GetMapping("/edit/{hardwareDetailId}")
	@RequiresPermissions("contract:contractHardwareDetail:edit")
	String edit(@PathVariable("hardwareDetailId") String hardwareDetailId,Model model){
		ContractHardwareDetailDO contractHardwareDetail = contractHardwareDetailService.get(hardwareDetailId);
		model.addAttribute("contractHardwareDetail", contractHardwareDetail);
	    return "contract/contractHardwareDetail/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("contract:contractHardwareDetail:add")
	public R save( ContractHardwareDetailDO contractHardwareDetailDo){
		contractHardwareDetailDo.setHardwareDetailId(getUserId());
		if (contractHardwareDetailService.save(contractHardwareDetailDo)>0) {
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("contract:contractHardwareDetail:edit")
	public R update( ContractHardwareDetailDO contractHardwareDetailDo){
		contractHardwareDetailDo.setHardwareDetailOperator(Long.toString(getUserId()));
		contractHardwareDetailService.update(contractHardwareDetailDo);
		return R.ok();
	}
	
	/**
	 * 硬件明细更多
	 */
	@GetMapping("/contractHardwareDetai")
	@RequiresPermissions("contract:contractHardwareDetail:contractHardwareDetail")
	String contractHardwareDetai() {
		return "contract/contractHardwareDetail/contractHardwareDetail";
	}
	
	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("contract:contractHardwareDetail:remove")
	public R remove( String hardwareDetailId){
		if(contractHardwareDetailService.remove(hardwareDetailId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("contract:contractHardwareDetail:batchRemove")
	public R remove(@RequestParam("ids[]") String[] hardwareDetailIds){
		contractHardwareDetailService.batchRemove(hardwareDetailIds);
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

 			File file = new File("./src/main/resources/downloadTemplate/硬件明细.xls");
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




















