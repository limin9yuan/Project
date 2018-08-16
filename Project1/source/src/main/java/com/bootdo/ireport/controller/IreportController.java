package com.bootdo.ireport.controller;

import java.io.*;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

import com.bootdo.ireport.service.IreportService;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.design.*;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.*;
import org.apache.catalina.connector.ResponseFacade;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.ui.jasperreports.JasperReportsUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mysql.jdbc.Driver;
import com.bootdo.ireport.domain.IreportDO;
import com.bootdo.ireport.service.IreportService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import java.util.ArrayList;


import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.*;
 import net.sf.jasperreports.engine.export.*;
import net.sf.jasperreports.j2ee.servlets.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-07-26 10:04:49
 */
 
@Controller
@RequestMapping("/ireport")
public class IreportController {
	@Autowired
	private IreportService ireportService;



	@GetMapping()
	@RequiresPermissions("ireport:ireport:ireport")
	String Ireport(HttpServletRequest request, HttpServletResponse response) throws JRException, ClassNotFoundException, SQLException, IOException {
        //传入参数
		HashMap parameters = new HashMap();
		parameters.put("nm", new String("name1"));
//		parameters.put("project_id","130303001001001");
        //数据库连接
		Connection conn = null;
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://192.168.0.109:3306/bootdo?useUnicode=true&characterEncoding=utf8&useSSL=true", "root", "123456");
		//获取报表jasper路径
		InputStream inputStream = new FileInputStream("D:\\工作\\项目1\\source\\src\\main\\resources\\templates\\report\\bug.jasper");
		//核心代码  往报表里传参数
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parameters, conn);
		//直接打印模板传完参数的报表 放到地址里
//		JasperExportManager.exportReportToHtmlFile(jasperPrint, "D:\\工作\\项目1\\source\\src\\main\\resources\\templates\\report\\first6666.html");

        //打印成excel
		//如果只注明文件名字，默认会生成在user.dir

		String fileName = "asfdsf1.xlsx";
		JRXlsxExporter exporter = new JRXlsxExporter();
		//设置输入项
		ExporterInput exporterInput = new SimpleExporterInput(jasperPrint);
		exporter.setExporterInput(exporterInput);
		//设置输出项
//		OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(fileName);

		String generateFileName = "我的文件.xlsx";
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment; filename="
				+ URLEncoder.encode(generateFileName,"utf8"));


		OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(response.getOutputStream());

		exporter.setExporterOutput(exporterOutput);
		exporter.exportReport();


		return "ireport/ireport";
	}

//	@GetMapping("/bug")
//	@RequiresPermissions("ireport:ireport:ireport")
//	String bug(Model model) {
//     return "ireport/bug";
//	}
@GetMapping("/bug")
@RequiresPermissions("ireport:ireport:ireport")
public String bug(HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException, JRException {
	//传入参数
	Map<String, Object> parameters = new HashMap<String, Object>();
	parameters.put("project_id","130303001001001");
	parameters.put("tsDate","2018年8月16号");
	//数据库连接
	Connection conn = null;
	Class.forName("com.mysql.jdbc.Driver");
	conn = DriverManager.getConnection("jdbc:mysql://192.168.0.109:3306/bootdo?useUnicode=true&characterEncoding=utf8&useSSL=true", "root", "123456");
	//获取报表jasper路径
    InputStream inputStream = new FileInputStream("D:\\工作\\项目1\\source\\src\\main\\resources\\templates\\report\\bootdo7.jasper");
	//核心代码  往报表里传参数

	JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parameters, getConnection());
	//html预览
	JasperExportManager.exportReportToHtmlFile(jasperPrint, "D:/补充/bug222.html");
	//导出excel

	return null;
}

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		Class.forName("com.mysql.jdbc.Driver");

		conn = DriverManager.getConnection("jdbc:mysql://192.168.0.109:3306/bootdo?useUnicode=true&characterEncoding=utf8&useSSL=true", "root", "123456");
		return conn;
	}
	@GetMapping("/getlist/{style}")
	@RequiresPermissions("ireport:ireport:ireport")
//	String bug(Model model,HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException, JRException {
	 String bugstyle (@PathVariable("style") String style, HttpServletResponse response) throws JRException, ClassNotFoundException, SQLException, IOException {

		if(style.equals("XLS")==true){
			//传入参数
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("project_id","130303001001001");
			parameters.put("tsDate","2018年8月16号");
			//数据库连接
			Connection conn = null;
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://192.168.0.109:3306/bootdo?useUnicode=true&characterEncoding=utf8&useSSL=true", "root", "123456");
			//获取报表jasper路径
			InputStream inputStream = new FileInputStream("D:\\工作\\项目1\\source\\src\\main\\resources\\templates\\report\\bootdo7.jasper");
			//核心代码  往报表里传参数
			JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parameters, getConnection());
			//html预览
			JasperExportManager.exportReportToHtmlFile(jasperPrint, "D:/补充/bug1111.html");
			//导出excel
			JRXlsxExporter exporter = new JRXlsxExporter();
			ExporterInput exporterInput = new SimpleExporterInput(jasperPrint);
			exporter.setExporterInput(exporterInput);
			String generateFileName = "我的文件.xlsx";

			response.setContentType("application/vnd_ms-excel;charset=utf-8");
			response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(generateFileName,"utf8"));

			OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(response.getOutputStream());

			exporter.setExporterOutput(exporterOutput);
			exporter.exportReport();

		}
           return null;
	}
public 	String bug1(HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException, JRException {
		//往表里传值
		HashMap parameters = new HashMap();
		parameters.put("Bug_Category", "ceshi1");
		parameters.put("project_id","130303001001001");
		//数据库连接
		Connection conn = null;
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://192.168.0.109:3306/bootdo?useUnicode=true&characterEncoding=utf8&useSSL=true", "root", "123456");
		//获取报表jasper路径
		InputStream inputStream = new FileInputStream("D:\\之前的报表\\report\\bootdo6.jasper");
		//核心代码  往报表里传参数
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parameters, conn);
		//导出excel
		JRXlsxExporter exporter = new JRXlsxExporter();
		ExporterInput exporterInput = new SimpleExporterInput(jasperPrint);
		exporter.setExporterInput(exporterInput);
		String generateFileName = "我的文件.xlsx";

	    response.setContentType("application/vnd_ms-excel;charset=utf-8");
		response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(generateFileName,"utf8"));

		OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(response.getOutputStream());

		exporter.setExporterOutput(exporterOutput);
		exporter.exportReport();

		return null;
	}
}


