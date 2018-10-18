package org.module.rpt.web;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eredlab.g4.arm.vo.UserInfoVo;
import org.eredlab.g4.bmf.base.IDao;
import org.eredlab.g4.bmf.util.SpringBeanLoader;
import org.eredlab.g4.rif.util.WebUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

public class JasperPrintServlet extends HttpServlet
{
  public void service(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
  {
    JasperPrint jasperPrint = null;
    String fileName = request.getParameter("rpt_id") == null ? "" : request.getParameter("rpt_id");
    File reportFile = null;
    JasperReport jasperReport = null;
    try
    {
    	reportFile = new File(getServletContext().getRealPath("/report/jasper/module/"+fileName+".jasper"));
      if (!reportFile.exists())
        throw new JRRuntimeException("缺少报表文件，请与系统管理员联系！");
      jasperReport = (JasperReport)JRLoader.loadObject(reportFile.getPath());
    } catch (JRException ee) {
      ee.printStackTrace();
    }

    Map parameters = new HashMap();
    Enumeration enu = request.getParameterNames();

    while (enu.hasMoreElements())
    {
      String key = (String)enu.nextElement();
      String str = request.getParameter(key).replaceAll("", "");
//System.out.println("============原始==========="+str);
//System.out.println("===========GBK============"+ new String(str.getBytes("ISO-8859-1"), "GBK"));
//System.out.println("===========utf-8============"+ new String(str.getBytes("ISO-8859-1"), "utf-8"));
	  //str = new String(str.getBytes("ISO-8859-1"), "utf-8");
      
      parameters.put(key, str);
    }
    //参数传递
	parameters.put("BaseDir", reportFile.getParentFile());
	parameters.put("SUB_DIR", request.getRealPath("/report/jasper/module/"));
	
	System.out.println(request.getRealPath("/reports/jasper/module/")+"/");
	UserInfoVo ui =WebUtils.getSessionContainer(request).getUserInfo();			

	parameters.put("user_id", ui.getUserid());
	parameters.put("user_name", ui.getUsername());
	parameters.put("company_id", ui.getCustomId());
	parameters.put("company_name", ui.getCustomName());
			
	parameters.put("ip_address", request.getRemoteAddr());
	parameters.put("thisTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));
	parameters.put("thisDate", new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));

    Connection conn = null;
    try {
    	IDao g4Dao = (IDao) SpringBeanLoader.getSpringBean("g4Dao");
		conn = g4Dao.getConnection();
      jasperPrint = 
        JasperFillManager.fillReport(
        jasperReport, 
        parameters, 
        conn);
    }
    catch (Exception e) {
      e.printStackTrace();
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      out.println("<html>");
      out.println("<head>");
      out.println("<title>立信供热管理信息系统</title>");
      out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../css.css\" title=\"Style\">");
      out.println("</head>");

      out.println("<body bgcolor=\"white\">");

      out.println("<span class=\"bnew\">报表加载错误，请与系统管理员联系 :</span>");
      out.println("<pre>");

      e.printStackTrace(out);

      out.println("</pre>");

      out.println("</body>");
      out.println("</html>");

      return;
    } finally {
      try {
    	  conn.close();
        
      }
      catch (Exception localException2)
      {
      }
    }
    if (jasperPrint != null)
    {
      response.setContentType("application/octet-stream");
      ServletOutputStream ouputStream = response.getOutputStream();

      ObjectOutputStream oos = new ObjectOutputStream(ouputStream);
      oos.writeObject(jasperPrint);
      oos.flush();
      oos.close();

      ouputStream.flush();
      ouputStream.close();
    }
    else
    {
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      out.println("<html>");
      out.println("<head>");
      out.println("<title>立信供热管理信息系统</title>");
      out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../css.css\" title=\"Style\">");
      out.println("</head>");

      out.println("<body bgcolor=\"white\">");

      out.println("<span class=\"bold\">报表输出错误，请与系统管理员联系......</span>");

      out.println("</body>");
      out.println("</html>");
    }
  }
}