<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>  
<%   
String path = request.getContextPath();   
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";   
%>  
  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
<head>  
    <base href="<%=basePath%>">  
    <title>My JSP 'index.jsp' starting page</title>  
 	<meta http-equiv="pragma" content="no-cache">  
 	<meta http-equiv="cache-control" content="no-cache">  
 	<meta http-equiv="expires" content="0">       
 	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">  
 	<meta http-equiv="description" content="This is my page">  
</head>    
<script type="text/javascript">
	function openApp(){    
		var url = "/JasperPrintTest/TestServlet3";    
		document.write('<APPLET ID="JrPrt" CODE="com.defonds.test.JRPrinterApplet.class" CODEBASE = "./" ARCHIVE = "reportprint.jar" WIDTH = "0" HEIGHT = "0">');    
		document.write('<PARAM NAME = "type" VALUE="application/x-java-applet;version=1.2.2">');    
		document.write('<PARAM NAME = "scriptable" VALUE="false">');    
		document.write('<PARAM NAME = "REPORT_URL" VALUE ="'+url+'">');    
		document.write('</APPLET>');    
	}   
</script>
<body bgcolor="#FFFFFF">    
	<input type="button" value="²âÊÔ´òÓ¡" onclick="openApp();">    
</body>    
</html>   
