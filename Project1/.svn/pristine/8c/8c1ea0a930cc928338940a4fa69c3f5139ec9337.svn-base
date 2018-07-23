package com.bootdo.project.controller;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

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

import com.bootdo.project.domain.ProductCategoryDO;
import com.bootdo.project.domain.ProjectDO;
import com.bootdo.project.service.ProductCategoryService;
import com.bootdo.sales.domain.RecordDO;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.config.Constant;
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
 * 产品分类信息表
 * 
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-11-29 11:15:42
 */
 
@Controller
@RequestMapping("/project/productCategory")
public class ProductCategoryController extends BaseController {
	@Autowired
	private ProductCategoryService productCategoryService;
	@Autowired
	private FileService sysFileService;
	@Autowired
	private BootdoConfig bootdoConfig;
	
	@GetMapping()
	@RequiresPermissions("project:productCategory:productCategory")
	String ProductCategory(){
	    return "project/productCategory/productCategory";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("project:productCategory:productCategory")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ProductCategoryDO> productCategoryList = productCategoryService.list(query);
		int total = productCategoryService.count(query);
		PageUtils pageUtils = new PageUtils(productCategoryList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("project:productCategory:add")
	String add(){
	    return "project/productCategory/add";
	}
	@RequestMapping("/edit_ajax/{productId}")
	@ResponseBody
	Map<String, Object> edit_ajax(@PathVariable("productId") String productId) {
		ProductCategoryDO productCategory = productCategoryService.get(productId);
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("productCategory", productCategory);
		return returnData;
	}
	@GetMapping("/edit/{productId}")
	@RequiresPermissions("project:productCategory:edit")
	String edit(@PathVariable("productId") String productId,Model model){
		//ProductCategoryDO productCategory = productCategoryService.get(productId);
		model.addAttribute("productId", productId);
	    return "project/productCategory/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("project:productCategory:add")
	public R save( ProductCategoryDO productCategory){
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		productCategory.setProductCreator(getUserId());
		productCategory.setProductRecorder(getUserId());
		if(productCategoryService.save(productCategory)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("project:productCategory:edit")
	public R update( ProductCategoryDO productCategory){
		productCategory.setProductRecorder(getUserId());
		productCategoryService.update(productCategory);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("project:productCategory:remove")
	public R remove( String productId){
		if(productCategoryService.remove(productId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("project:productCategory:batchRemove")
	public R remove(@RequestParam("ids[]") String[] productIds){
		productCategoryService.batchRemove(productIds);
		return R.ok();
	}
	
	@ResponseBody
	@GetMapping("/listDic")
	public List<DictDO> listByType() {
		// 查询列表数据
		Map<String, Object> map = new HashMap<>(16);
		map.put("type", "");
		List<DictDO> dictList = productCategoryService.listDic();
		return dictList;
	}
	/**
	 * 查看
	 */
	@GetMapping("/examine/{productId}")
	@RequiresPermissions("project:productCategory:productCategory")
	String see(@PathVariable("productId") String productId, Model model) {
		model.addAttribute("productId", productId);
		return "/project/productCategory/examine";
	}
	// 根据ID查看附件列表
		@ResponseBody
		@GetMapping("/listAttachment")
		@RequiresPermissions("common:sysFile:sysFile")
		public PageUtils listAttachment(@RequestParam("productId") String productId,
				@RequestParam Map<String, Object> params) {
			// String aa=request.getParameter("customerId");
			params.put("productId", productId);
			System.out.println(productId);
			// 查询列表数据
			Query query = new Query(params);
			List<FileDO> sysFileList = sysFileService.listAttachment(query);
			int total = sysFileService.listAttachmentCount(query);
			PageUtils pageUtils = new PageUtils(sysFileList, total);
			return pageUtils;
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
		// String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		// chars.charAt((int)(Math.random() * 52))+
		String fileName = file.getOriginalFilename();
		// fileName = FileUtil.renameToUUID(fileName);
		FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/" + fileName, new Date(), fileName);
		try {
			FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath(), fileName);
		} catch (Exception e) {
			return R.error();
		}
		int ids = sysFileService.save(sysFile);
		System.out.println(ids);
		if (ids > 0) {
			R r = R.ok();
			r.put("productAttachment", ids);
			r.put("fileName", sysFile.getUrl());
			return r;
			// return R.ok().put("fileName", sysFile.getUrl());
		}
		return R.error();
	}
	
	/**
	 * 执行删除文件的时候同时删除字段下的附件ID
	 */
	@ResponseBody
	@RequestMapping("/updateAttachment")
	@RequiresPermissions("sales:recordService:edit")
	public R updateAttachment(ProductCategoryDO productCategory) {
		productCategoryService.updateAttachment(productCategory);
		return R.ok();
	}

	// 根据文件名称下载相关代码
	@ResponseBody
	@RequestMapping("/down")
	public void download(HttpServletResponse response, @RequestParam("fileName") String fileName) {
		try {
			// path是指欲下载的文件的路径。
			String path = "C:/var/uploaded_files/" + fileName;

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

			response.addHeader("Content-Disposition",
					"attachment;filename=" + new String(filename.getBytes(), "ISO-8859-1"));
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
	public void downLoadZipFile(HttpServletResponse response, @RequestParam("id") String id) throws IOException {
		String[] ids = id.split(",");
		String zipName = "downLoadFile.zip";
		List<FileDO> fileList = sysFileService.downLoadListId(ids);// 查询数据库中记录
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename=" + zipName);
		ZipOutputStream out = new ZipOutputStream(response.getOutputStream());
		try {
			for (Iterator<FileDO> it = fileList.iterator(); it.hasNext();) {
				FileDO file = it.next();
				ZipUtils.doCompress("C:/var/uploaded_files/" + file.getFileName(), out);
				response.flushBuffer();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
}
