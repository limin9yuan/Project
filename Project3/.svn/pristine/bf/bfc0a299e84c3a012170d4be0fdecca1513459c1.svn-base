<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/include/taglib.jsp"%>
<eRedG4:html title="批量缴费" uxEnabled="true">
<eRedG4:ext.codeStore fields="PAY_MODE" showCode="false"/>
<eRedG4:ext.codeStore fields="MINUS_REASON" showCode="false"/>
<eRedG4:ext.myux uxType="gridsummary"/>
<eRedG4:import src="/module/sf/js/multiCharge.js" />
<eRedG4:import src="/module/sf/js/print.js" />
<eRedG4:div key="gridDiv"></eRedG4:div>
<eRedG4:div key="pFormDiv"></eRedG4:div>
<eRedG4:div key="noteDiv"></eRedG4:div>
<eRedG4:body>
<iframe id="iprint"></iframe>
<!--需要运行{读写卡控件 FOR SQL SERVER.exe}文件-->
	<OBJECT ID="SDWriteCARD"  codeBase="SDWriteCARD.ocx#version=1,0,0,1" CLASSID="clsid:CF09D769-5E36-49AB-B469-03733DBA0821" width=0 height=0></OBJECT>
</eRedG4:body>
</eRedG4:html>
