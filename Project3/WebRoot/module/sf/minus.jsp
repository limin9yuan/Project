<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/include/taglib.jsp"%>
<eRedG4:html title="减免管理" uxEnabled="true" >
<eRedG4:ext.codeStore fields="MINUS_REASON" showCode="false"/>
<eRedG4:ext.myux uxType="gridsummary"/>
<eRedG4:import src="/module/sf/js/minus.js" />
<eRedG4:ext.codeRender fields="QYBZ"/>
<eRedG4:body>
<eRedG4:div key="gridDiv" ></eRedG4:div>
<eRedG4:div key="minusDiv" ></eRedG4:div>
</eRedG4:body>
</eRedG4:html>