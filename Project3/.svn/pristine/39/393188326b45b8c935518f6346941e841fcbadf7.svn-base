<%--
/*
 * ============================================================================
 * GNU Lesser General Public License
 * ============================================================================
 *
 * JasperReports - Free Java report-generating library.
 * Copyright (C) 2001-2006 JasperSoft Corporation http://www.jaspersoft.com
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 * 
 * JasperSoft Corporation
 * 303 Second Street, Suite 450 North
 * San Francisco, CA 94107
 * http://www.jaspersoft.com
 */
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="net.sf.jasperreports.engine.*" %>
<%@ page import="net.sf.jasperreports.engine.util.*" %>
<%@ page import="net.sf.jasperreports.engine.export.*" %>
<%@ page import="net.sf.jasperreports.j2ee.servlets.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.text.*" %>

<%@ page import="org.eredlab.g4.bmf.base.IDao" %>
<%@ page import="org.eredlab.g4.bmf.util.SpringBeanLoader" %>
<%@ page import="org.eredlab.g4.arm.vo.UserInfoVo" %>
<%@ page import="org.eredlab.g4.rif.web.*" %>
<%@ page import="org.eredlab.g4.rif.util.WebUtils" %>

<%
	JasperPrint jasperPrint = null;
	String fileName = request.getParameter("rpt_id")==null?"":request.getParameter("rpt_id");
	String strReq = "";
	String currentReport_name="";
	if (request.getParameter("reload") == null){
		request.setAttribute("reload","1");
		File reportFile = new File(application.getRealPath("report/jasper/module/"+fileName+".jasper"));
		if (!reportFile.exists()){
			out.println("<e>缺少报表文件，请与系统管理员联系</e></x>");
			return;
			//throw new JRRuntimeException("缺少报表文件，请与系统管理员联系?);
			
		}
		JasperReport jasperReport = (JasperReport)JRLoader.loadObject(reportFile.getPath());

		Map parameters = new HashMap();
		Enumeration enu = request.getParameterNames(); 
		
		int i=0;
		while (enu.hasMoreElements()) { 
			String key = (String) enu.nextElement(); 
			String str = request.getParameter(key).replaceAll("", "");
			//str = new String(str.getBytes("ISO-8859-1"), "utf-8");
			parameters.put(key, str); 		
			
			str=str.replaceAll("%","%25");
			str=str.replaceAll("\\+","%2B");
			str=str.replaceAll(" ","%20");
			str=str.replaceAll("=","%3D");
			str=str.replaceAll("#","%23");
			str=str.replaceAll("/","%2F");
			
			if(!key.equals("page")){
				if(i!=0){
					strReq = strReq + "&";
				}
				
				strReq = strReq + key + "=" + str; 				
				i++;
				System.out.println(key+"参数==="+str);
			}			
			
		} 
		
		UserInfoVo ui =WebUtils.getSessionContainer(request).getUserInfo();
		parameters.put("BaseDir", reportFile.getParentFile());
		parameters.put("SUB_DIR", request.getRealPath("/report/")+"/");
		
		parameters.put("user_id", ui.getUserid());
		parameters.put("user_name", ui.getUsername());
		parameters.put("company_id", ui.getCustomId());
		parameters.put("company_name", ui.getCustomName());

		parameters.put("ip_address", request.getRemoteAddr());
		parameters.put("thisDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));		
		IDao g4Dao = (IDao) SpringBeanLoader.getSpringBean("g4Dao");
		Connection conn = g4Dao.getConnection();
		try {
			//conn = Tools.getConn("oracle");  
			jasperPrint = 
				JasperFillManager.fillReport(
					jasperReport, 
					parameters, 
					conn
					);
		}catch (Exception e) {
			e.printStackTrace();
			out.println("<e>数据库配置错误！</e></x>");
			return;
		}finally{
			try {
				conn.close();
			}
			catch (Exception e) {
			}	
		}		
	
		//session.setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
	}
	String style = request.getParameter("style");
	int pageIndex = 0;
	int lastPageIndex = 0;	
	StringBuffer sbuffer = new StringBuffer();	
		
	if(style==null || style==""){//html
		JRHtmlExporter exporter = new JRHtmlExporter();
		if (jasperPrint.getPages() != null){
			lastPageIndex = jasperPrint.getPages().size() - 1;
		}
	
		String pageStr = request.getParameter("page");
		try{
			pageIndex = Integer.parseInt(pageStr);
		}
		catch(Exception e){
		}
		
		if (pageIndex < 0){
			pageIndex = 0;
		}
	
		if (pageIndex > lastPageIndex){
			pageIndex = lastPageIndex;
		}
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STRING_BUFFER, sbuffer);
		exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, request.getContextPath()+"/resource/rptImage?image=");
		exporter.setParameter(JRHtmlExporterParameter.IS_WRAP_BREAK_WORD, true);
		exporter.setParameter(JRHtmlExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, true);
		exporter.setParameter(JRExporterParameter.PAGE_INDEX, new Integer(pageIndex));	
		exporter.exportReport();
	}
%>		
<script>
	function printReport(){
		document.reportDemo.printReport();
	}
	function printPreviewReport(){
	printCanvas.innerHTML = "";
	var str = "";
		str += "<APPLET id='printReports'  WIDTH = '0' HEIGHT = '0'>";
		str += "<PARAM NAME = CODE VALUE = 'PrinterApplet.class' >";
		str += "<PARAM NAME = CODEBASE VALUE = '../../applets' >";
		str += "<PARAM NAME = ARCHIVE VALUE = 'jasperreports-1.3.4.jar,applets.jar,jasperreports-1.3.4-applet.jar,jfreechart-1.0.0.jar,jcommon-1.0.0.jar' >";
		str += "<PARAM NAME='type' VALUE='application/x-java-applet;version=1.5.0_06'>";
		str += "<PARAM NAME='scriptable' VALUE='false'>";
		str += "<PARAM NAME = 'REPORT_URL' VALUE =\"<%=request.getContextPath()%>/servlets/jasperprint?<%=strReq%>\">";
		str += "</APPLET>";
		printCanvas.innerHTML = str;
		document.getElementById("printReports").viewReport();
		
	}
	
	function viewReport(){
		printPreviewReport();
		//document.reportDemo.viewReport();
	}
</script>

<html>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
</head>
<body text="#000000" link="#000000" alink="#000000" vlink="#000000">
<form name="formUpResult">
<input type="hidden" name="reportURL">
<input type="hidden" name="reportMan">
</form>
<!--"CONVERTED_APPLET"-->
<!-- HTML CONVERTER -->
<SCRIPT LANGUAGE="JavaScript"><!--
    var _info = navigator.userAgent;
    var _ns = false;
    var _ns6 = false;
    var _ie = (_info.indexOf("MSIE") > 0 && _info.indexOf("Win") > 0 && _info.indexOf("Windows 3.1") < 0);
//--></SCRIPT>
    <COMMENT>
        <SCRIPT LANGUAGE="JavaScript1.1"><!--
        var _ns = (navigator.appName.indexOf("Netscape") >= 0 && ((_info.indexOf("Win") > 0 && _info.indexOf("Win16") < 0 && "<%=java.lang.System.getProperty("os.version")%>".indexOf("3.5") < 0) || (_info.indexOf("Sun") > 0) || (_info.indexOf("Linux") > 0) || (_info.indexOf("AIX") > 0) || (_info.indexOf("OS/2") > 0)));
        var _ns6 = ((_ns == true) && (_info.indexOf("Mozilla/5") >= 0));
//--></SCRIPT>
    </COMMENT>

<SCRIPT LANGUAGE="JavaScript"><!--
   // if (_ie == true) document.writeln('<OBJECT name="reportDemo" id="reportDemo" classid="clsid:8AD9C840-044E-11D1-B3E9-00805F499D93" WIDTH = "0" HEIGHT = "0"  codebase="http://java.sun.com/update/1.5.0/jinstall-1_5_0_06-windows-i586.cab#Version=5,0,6,5"><NOEMBED><XMP>');
  //  else if (_ns == true && _ns6 == false) document.writeln('<EMBED type="application/x-java-applet;version=1.5.0_06" CODE = "EmbeddedViewerApplet.class" CODEBASE = "applets" ARCHIVE = "jasperreports-1.3.4-applet.jar" WIDTH = "60" HEIGHT = "40" REPORT_URL = "../servlets/jasperprintKFQ" scriptable=false pluginspage="http://java.sun.com/javase/downloads/index.jsp"><NOEMBED><XMP>');
//--></SCRIPT>
<!--
<APPLET name="printReports"  WIDTH = "0" HEIGHT = "0"></XMP>
	<PARAM NAME = CODE VALUE = "PrinterApplet.class" >
	<PARAM NAME = CODEBASE VALUE = "../../applets" >
	<PARAM NAME = ARCHIVE VALUE = "applets.jar,jasperreports-1.3.4-applet.jar" >

    <PARAM NAME="type" VALUE="application/x-java-applet;version=1.5.0_06">
    <PARAM NAME="scriptable" VALUE="false">
    <PARAM NAME = "REPORT_URL" VALUE ="<%=request.getContextPath()%>/servlets/jasperprint?<%=strReq%>">
</APPLET>
</NOEMBED>
</OBJECT>
-->
<table width="100%" cellpadding="0" cellspacing="0" border="0">
<tr>
  <td width="50%"></td>
  <td align="left">
    <hr size="1" color="#000000">
    <table width="100%" cellpadding="0" cellspacing="0" border="0">
      <tr>
        <!--<td><a href="view.jsp?report=<%=fileName%>"><img src="<%=request.getContextPath()%>/resource/image/report/reload.GIF" border="0"></a></td>-->
        <td>&nbsp;&nbsp;&nbsp;</td>
		<!--<td>&nbsp;<input type="button" name="prints" value="print" onClick="printReport()">&nbsp;</td>
		<td>&nbsp;<input type="button" name="views" value="view" onClick="viewReport()">&nbsp;</td>-->
<%
	if (pageIndex > 0){
%>
        <td><a href="htmlView.jsp?<%=strReq%>&page=0"><img src="<%=request.getContextPath()%>/resource/image/report/first.GIF" border="0"></a></td>
        <td><a href="htmlView.jsp?<%=strReq%>&page=<%=pageIndex - 1%>"><img src="<%=request.getContextPath()%>/resource/image/report/previous.GIF" border="0"></a></td>
<%
	}else{
%>
        <td><img src="<%=request.getContextPath()%>/resource/image/report/first_grey.GIF" border="0"></td>
        <td><img src="<%=request.getContextPath()%>/resource/image/report/previous_grey.GIF" border="0"></td>
<%
	}

	if (pageIndex < lastPageIndex){
%>
        <td><a href="htmlView.jsp?<%=strReq%>&page=<%=pageIndex + 1%>"><img src="<%=request.getContextPath()%>/resource/image/report/next.GIF" border="0"></a></td>
        <td><a href="htmlView.jsp?<%=strReq%>&page=<%=lastPageIndex%>"><img src="<%=request.getContextPath()%>/resource/image/report/last.GIF" border="0"></a></td>
<%
	}else{
%>
        <td><img src="<%=request.getContextPath()%>/resource/image/report/next_grey.GIF" border="0"></td>
        <td><img src="<%=request.getContextPath()%>/resource/image/report/last_grey.GIF" border="0"></td>
<%
	}
%>
		<td width="60%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		<!--td>&nbsp;<img src="<%=request.getContextPath()%>/resource/image/report/print.gif" border="0"  style="cursor:hand;" onClick="printReport()">&nbsp;</td>-->
        <!--<td>&nbsp;<img src="<%=request.getContextPath()%>/resource/image/report/actualsize.GIF" border="0"  alt="打印预览" style="cursor:hand;" onClick="viewReport()">&nbsp;</td>-->
        <td>&nbsp;<a href="<%=request.getContextPath()%>/Report?<%=strReq%>&style=XLS"><img src="<%=request.getContextPath()%>/resource/image/report/xlsExtend.jpg" alt="保存为Excel文件" border="0"></a>&nbsp;</td>
        <td>&nbsp;<a href="<%=request.getContextPath()%>/Report?<%=strReq%>&style=RTF"><img src="<%=request.getContextPath()%>/resource/image/report/docExtend.jpg" alt="保存为word文件" border="0"></a>&nbsp;</td>
        <td>&nbsp;<a href="<%=request.getContextPath()%>/Report?<%=strReq%>&style=PDF"><img src="<%=request.getContextPath()%>/resource/image/report/pdfExtend.jpg" alt="保存为PDF文件" border="0"></a>&nbsp;</td>
        <td>&nbsp;<a href="<%=request.getContextPath()%>/Report?<%=strReq%>&style=XML"><img src="<%=request.getContextPath()%>/resource/image/report/DataList.gif" alt="保存为XML文件" border="0"></a>&nbsp;</td>
         <td>&nbsp;&nbsp;&nbsp;</td>
      </tr>
    </table>
    <hr size="1" color="#000000">
  </td>
  <td width="50%">&nbsp;</td>
</tr>
<tr>
  <td width="50%">&nbsp;</td>
  <td align="center">

<%=sbuffer.toString()%>

  </td>
  <td width="50%">&nbsp;</td>
</tr>
</table>
<div id="printCanvas" style="display:none"></div>
</body>
</html>

