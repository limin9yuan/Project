package org.module.sf.web;


import java.util.ArrayList;
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
import org.eredlab.g4.rif.web.BaseAction;
import org.eredlab.g4.rif.web.CommonActionForm;
import org.module.sf.service.MinusService;

public class MinusAction extends BaseAction{
	private MinusService minusService = (MinusService) super.getService("MinusService");

	/**
	 * 查询减免数据
	 * 
	 * @param
	 * @return
	 */
	public ActionForward queryMinus(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		CommonActionForm aForm = (CommonActionForm) form;
		Dto dto = aForm.getParamAsDto(request);
		List list = new ArrayList();
		list = g4Reader.queryForPage("queryMinus", dto);
		Integer countInteger = (Integer) g4Reader.queryForObject("queryMinusForPageCount", dto);
		super.setSessionAttribute(request, "GRIDACTION_QUERYBALANCEINFO_DTO", dto);
		String jsonString = encodeList2PageJson(list, countInteger, GlobalConstants.FORMAT_Date);
		super.write(jsonString, response);
		return mapping.findForward(null);
	}
	/**
	 * 汇总应收数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sumMinus(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Dto dto = (BaseDto)super.getSessionAttribute(request, "GRIDACTION_QUERYBALANCEINFO_DTO");
		Dto sumDto = new BaseDto();
		sumDto = (BaseDto)g4Reader.queryForObject("sumMinus", dto);
		sumDto.put("success", new Boolean(true));
		String jsonString = JsonHelper.encodeObject2Json(sumDto);
		super.write(jsonString, response);
		return mapping.findForward(null);
	}
	/**
	 * 表格演示六页面初始化
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward minusInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//super.removeSessionAttribute(request, "GRIDACTION_QUERYBALANCEINFO_DTO");
		return mapping.findForward("minusView");
	}
	/**
	 * 减免
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward minusMoney(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm cForm = (CommonActionForm) form;
		Dto inDto = cForm.getParamAsDto(request);
		inDto.put("cid", getSessionContainer(request).getUserInfo().getCustomId());
		inDto.put("operator", getSessionContainer(request).getUserInfo().getUserid());
	    Dto outDto = minusService.minusMoney(inDto);
	    outDto.put("success", new Boolean(true));
		outDto.put("msg", "减免应收成功！");
		write(JsonHelper.encodeObject2Json(outDto), response);
		return mapping.findForward(null);
	}
	/**
	 * 删除减免
	 * 
	 * @param
	 * @return
	 */
	public ActionForward deleteMinus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm)form;
		Dto dto = aForm.getParamAsDto(request);
		dto.put("userid", super.getSessionContainer(request).getUserInfo().getUserid());
		dto.put("cid", super.getSessionContainer(request).getUserInfo().getCustomId());		
		Dto outDto = minusService.deleteMinus(dto);
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "减免删除成功!");
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		write(jsonString, response);
		return mapping.findForward(null);
	}
}
