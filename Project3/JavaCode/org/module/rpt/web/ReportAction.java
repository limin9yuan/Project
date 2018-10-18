package org.module.rpt.web;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.ccl.json.JsonHelper;
import org.eredlab.g4.ccl.util.G4Utils;
import org.eredlab.g4.ccl.util.GlobalConstants;
import org.eredlab.g4.rif.report.jasper.ReportData;
import org.eredlab.g4.rif.web.BaseAction;
import org.eredlab.g4.rif.web.CommonActionForm;

public class ReportAction extends BaseAction{
	/**
	 * 基本输入组件页面初始化(属性设置)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward reportInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String rpt_id =request.getParameter("rpt_id");
		System.out.println("----------------"+rpt_id+"----------------");
		return mapping.findForward(rpt_id);
	}
	
	/**
	 * 构造报表数据对象
	 */
	public ActionForward buildReportDataObject(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Dto dto = new BaseDto();
		dto.put("reportTitle", "北京市第一人民医院收费项目明细报表(演示)");
		dto.put("jbr", getSessionContainer(request).getUserInfo().getUsername());
		dto.put("jbsj", G4Utils.getCurrentTime());
		Dto inDto = (BaseDto)getSessionAttribute(request, "QUERYCATALOGS4PRINT_QUERYDTO");
		System.out.println(inDto);
		inDto.put("rownum", "100");
		List catalogList = null;
		catalogList = g4Reader.queryForList("rpt01050201", inDto);
		
		ReportData reportData = new ReportData();
		reportData.setParametersDto(dto);
		reportData.setFieldsList(catalogList);
		reportData.setReportFilePath("/report/jasper/demo/hisCatalogReport.jasper");
		getSessionContainer(request).setReportData("rpt01050201", reportData);
		return mapping.findForward(null);
	}
	/**
	 * 打开html报表
	 */
	public ActionForward forwardToHtml(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("htmlView");
	}
	/**
	 * 报表数据列表
	 * 
	 * @param
	 * @return
	 */
	public ActionForward rptList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm)form;
		Dto inDto = aForm.getParamAsDto(request);
		String rpt_id =request.getParameter("rpt_id");
		System.out.println("--------"+rpt_id+"----------");
		List catalogList = g4Reader.queryForPage("list"+rpt_id, inDto);
		Integer pageCount = (Integer) g4Reader.queryForObject("list"+rpt_id+"ForPageCount", inDto);
		String jsonString = encodeList2PageJson(catalogList, pageCount, GlobalConstants.FORMAT_DateTime);
		write(jsonString, response);
		return mapping.findForward(null);
	}
	/**
	 * 汇总报表数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sumRptList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm)form;
		Dto inDto = aForm.getParamAsDto(request);
		Dto sumDto = new BaseDto();
		String rpt_id =request.getParameter("rpt_id");
		String p_range =request.getParameter("p_range");
		System.out.println("--------"+p_range+"----------");
		sumDto = (BaseDto)g4Reader.queryForObject("sum"+rpt_id, inDto);
		sumDto.put("success", new Boolean(true));
		String jsonString = JsonHelper.encodeObject2Json(sumDto);
		new BigDecimal(1).divide(new BigDecimal(2));
		super.write(jsonString, response);
		return mapping.findForward(null);
	}
	/**
	 * 收费明细表分类统计数据
	 * 
	 * @param
	 * @return
	 */
	public ActionForward sumRptList_rpt(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm)form;
		Dto inDto = aForm.getParamAsDto(request);
		String rpt_id =request.getParameter("rpt_id");
		System.out.println("--------"+rpt_id+"----------");
		List catalogList = g4Reader.queryForList(rpt_id, inDto);
		String jsonString = JsonHelper.encodeObject2Json(catalogList);
		write(jsonString, response);
		return mapping.findForward(null);
	}
	/**
	 * 汇总并数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward countIntowebUsers(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm)form;
		Dto inDto = aForm.getParamAsDto(request);
		Dto sumDto = new BaseDto();
		String rpt_id =request.getParameter("rpt_id");
		String p_range =request.getParameter("p_range");
		System.out.println("--------"+p_range+"----------");
		sumDto = (BaseDto)g4Reader.queryForObject("countIntowebUsers", inDto);
		sumDto.put("success", new Boolean(true));
		String jsonString = JsonHelper.encodeObject2Json(sumDto);
		new BigDecimal(1).divide(new BigDecimal(2));
		super.write(jsonString, response);
		return mapping.findForward(null);
	}
	/**
	 * 汇总并数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward countIntowebUsers_year(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm)form;
		Dto inDto = aForm.getParamAsDto(request);
		Dto sumDto = new BaseDto();
		String rpt_id =request.getParameter("rpt_id");
		String p_range =request.getParameter("p_range");
		System.out.println("--------"+p_range+"----------");
		sumDto = (BaseDto)g4Reader.queryForObject("countIntowebUsers_year", inDto);
		sumDto.put("success", new Boolean(true));
		String jsonString = JsonHelper.encodeObject2Json(sumDto);
		new BigDecimal(1).divide(new BigDecimal(2));
		super.write(jsonString, response);
		return mapping.findForward(null);
	}
}
