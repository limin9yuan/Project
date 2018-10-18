package org.module.rpt.web;


import java.io.File;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.eredlab.g4.arm.vo.UserInfoVo;
import org.eredlab.g4.bmf.base.IDao;
import org.eredlab.g4.bmf.util.SpringBeanLoader;
import org.eredlab.g4.rif.util.WebUtils;
import org.eredlab.g4.rif.web.BaseAction;

public class PrintAction extends HttpServlet{
    public ActionForward executePrint(ActionMapping mapping,ActionForm form,    HttpServletRequest request,HttpServletResponse response)
        throws Exception {
        //已编译文件路径和格式
        String fileName = request.getParameter("rpt_id")==null?"":request.getParameter("rpt_id");
        String fp =getServletContext().getRealPath("/report/jasper/module/"+fileName+".jasper");
        File reportFile = new File(fp);

		Map parameters = new HashMap();
		Enumeration enu = request.getParameterNames(); 

		while (enu.hasMoreElements()){ 
			String key = (String) enu.nextElement(); 
			String str = request.getParameter(key);
			parameters.put(key, str); 
		} 
       
        JasperPrint jasperPrint=null;
        //参数传递
		parameters.put("BaseDir", reportFile.getParentFile());
		parameters.put("SUB_DIR", request.getRealPath("/report/jasper/module/"));
		
		System.out.println(request.getRealPath("/reports/jasper/module/")+"/");
        UserInfoVo ui =WebUtils.getSessionContainer(request).getUserInfo();
        parameters.put("user_id", ui.getUserid());
        parameters.put("user_name", ui.getUsername());
        parameters.put("company_id", ui.getCustomId());
        parameters.put("company_name", ui.getCustomName());
        parameters.put("thisDate", new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
        Connection conn = null;	
		try {
			IDao g4Dao = (IDao) SpringBeanLoader.getSpringBean("g4Dao");
			conn = g4Dao.getConnection(); 		
		          
            jasperPrint =JasperFillManager.fillReport(reportFile.getPath(),parameters,conn);//填充报表数据生成JasperPrint对象 
            JasperPrintManager.printReport(jasperPrint, false);//直接打印,不用预览PDF直接打印  true为弹出打印机选择.false为直接打印.
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			}
			catch (Exception e) {
			}	
		}
         //输出
            response.setContentType("application/octet-stream");
            ServletOutputStream ouputStream = response.getOutputStream();            
            ObjectOutputStream oos = new ObjectOutputStream(ouputStream);
            oos.writeObject(jasperPrint);//将JasperPrint对象写入对象输出流中 
            oos.flush();
            oos.close();            
            ouputStream.flush();
            ouputStream.close();
        return mapping.findForward(null);
    }
}