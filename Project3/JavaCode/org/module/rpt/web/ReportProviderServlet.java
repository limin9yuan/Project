package org.module.rpt.web;

 

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.base.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.*;
import net.sf.jasperreports.engine.util.*;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.engine.print.*;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.text.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.eredlab.g4.arm.vo.UserInfoVo;
import org.eredlab.g4.bmf.base.IDao;
import org.eredlab.g4.bmf.util.SpringBeanLoader;
import org.eredlab.g4.rif.util.SessionContainer;
import org.eredlab.g4.rif.util.WebUtils;
import org.eredlab.g4.rif.web.BaseAction;

public class ReportProviderServlet extends HttpServlet
{

    //Initialize: Setup DataSourceManager
    public void init() throws javax.servlet.ServletException
    {

    }

    //Process the HTTP request
    public void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
		JasperPrint jasperPrint = null;//(JasperPrint)session.getAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE);
		String fileName = request.getParameter("rpt_id")==null?"":request.getParameter("rpt_id");
		String style = request.getParameter("style");
		if (request.getParameter("reload") == null)
		{
			//compile
			File reportFile = null;
			JasperReport jasperReport = null;
			try{
				reportFile = new File(getServletContext().getRealPath("/report/jasper/module/"+fileName+".jasper"));
				if (!reportFile.exists())
					throw new JRRuntimeException("缺少报表文件，请与系统管理员联系！");
				if(style!=null && style.equals("XLS")){
					JasperDesign jasperDesign = JRXmlLoader.load(new File(getServletContext().getRealPath("/report/jasper/module/"+fileName+".jrxml")));
					jasperDesign.setPageHeight(65535*30);
					jasperReport = JasperCompileManager.compileReport(jasperDesign);
				}else{
					jasperReport = (JasperReport)JRLoader.loadObject(reportFile.getPath());
				}
			}catch(JRException ee){
				ee.printStackTrace();
			}
			
			Map parameters = new HashMap();
			Enumeration enu = request.getParameterNames(); 
	
			while (enu.hasMoreElements()) 
			{ 
				String key = (String) enu.nextElement(); 
				String str = request.getParameter(key).replaceAll("", "");
				//str = new String(str.getBytes("ISO-8859-1"), "utf-8");
				System.out.println(key+"==="+str);
				parameters.put(key, str); 
			} 
			parameters.put("BaseDir", reportFile.getParentFile());
			parameters.put("SUB_DIR", request.getRealPath("/reports/jasper/module/"));
			
			System.out.println(request.getRealPath("/report/jasper/module/")+"/");
			
			UserInfoVo ui =WebUtils.getSessionContainer(request).getUserInfo();			

			parameters.put("user_id", ui.getUserid());
			parameters.put("user_name", ui.getUsername());
			parameters.put("company_id", ui.getCustomId());
			parameters.put("company_name", ui.getCustomName());
					
			parameters.put("ip_address", request.getRemoteAddr());
			parameters.put("thisDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));
			
			Connection conn = null;	
			try {
				IDao g4Dao = (IDao) SpringBeanLoader.getSpringBean("g4Dao");
				conn = g4Dao.getConnection(); 
				jasperPrint = 
					JasperFillManager.fillReport(
						jasperReport, 
						parameters, 
						conn
						);
				
			}
			catch (Exception e) {
				e.printStackTrace();
				return;
			}finally{
				try {
					conn.close();
				}
				catch (Exception e) {
				}	
			}		
	
		}
		//export

		int pageIndex = 0;
		int lastPageIndex = 0;
		StringBuffer sbuffer = new StringBuffer();
		OutputStream httpOut = response.getOutputStream();
		if(style==null || style.equals("")){//html

		}else if(style.equals("XLS")){
		 	try{
				response.setContentType("application/vnd.ms-excel");
		                response.setHeader("Content-Disposition",
		                    "attachment;filename=\"" + fileName + ".xls\"");
		        JExcelApiExporter exporter = new JExcelApiExporter();
		        
		        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, httpOut);
		        exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE);
		        exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,Boolean.FALSE);
		        exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,Boolean.FALSE);
		        exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,Boolean.TRUE);
		        exporter.exportReport();
			}catch(JRException ee){
				ee.printStackTrace();
			}
	
		}else if(style.equals("RTF")){
		 	try{
		 		response.setContentType("application/vnd.ms-word");
		 		response.setHeader("Content-Disposition",
		 				"attachment;filename=\"" + fileName + ".doc\"");
				JRRtfExporter exporter = new JRRtfExporter();
		        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, httpOut);
		        exporter.exportReport();
			}catch(JRException ee){
				ee.printStackTrace();
			}
		}else if(style.equals("PDF")){
		 	try{
		 		response.setContentType("application/pdf");
		 		response.setHeader("Content-Disposition",
		 				"attachment;filename=\"" + fileName + ".pdf\"");
				JRPdfExporter exporter = new JRPdfExporter();
		        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, httpOut);
		        exporter.exportReport();
			}catch(JRException ee){
				ee.printStackTrace();
			}		
		}else if(style.equals("XML")){
		 	try{
		 		response.setContentType("text/xml");
		 		response.setHeader("Content-Disposition",
		 				"attachment;filename=\"" + fileName + ".xml\"");
		 		JRXmlExporter exporter = new JRXmlExporter();
		        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, httpOut);
		        exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "GB2312");
		        exporter.exportReport();
			}catch(JRException ee){
				ee.printStackTrace();
			}			
		}else if(style.equals("TXT")){
		 	try{
		 		response.setContentType("text/plain");
		 		response.setHeader("Content-Disposition",
		 				"attachment;filename=\"" + fileName + ".txt\"");
		 		JRTextExporter exporter = new JRTextExporter();
		        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, httpOut);
		        exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "GB2312");
		        exporter.setParameter(JRTextExporterParameter.LINE_SEPARATOR, "-");
		        exporter.exportReport();
			}catch(JRException ee){
				ee.printStackTrace();
			}			
		}
    }
}