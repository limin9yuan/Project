<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/include/taglib.jsp"%>
<eRedG4:html title="冲账" uxEnabled="true" >
<eRedG4:ext.codeStore fields="ROLLBACK_REASON" showCode="false"/>
<eRedG4:ext.myux uxType="gridsummary"/>
<eRedG4:import src="/module/sf/js/rollback.js" />
<eRedG4:ext.codeRender fields="QYBZ"/>
<eRedG4:body>
<eRedG4:div key="gridDiv" ></eRedG4:div>
<eRedG4:div key="rollbackDiv" ></eRedG4:div>
<!--需要运行{读写卡控件 FOR SQL SERVER.exe}文件-->
	<OBJECT ID="SDWriteCARD"  codeBase="SDWriteCARD.ocx#version=1,0,0,1" CLASSID="clsid:CF09D769-5E36-49AB-B469-03733DBA0821" width=0 height=0></OBJECT>
</eRedG4:body>
</eRedG4:html>