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
import org.module.sf.service.CreatPLanService;
import org.module.sf.service.StandardService;

public class StandardAction extends BaseAction{
	private StandardService standardService = (StandardService) super.getService("StandardService");
	/**
	 *收费标准页面初始化
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward standardInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//super.removeSessionAttribute(request, "GRIDACTION_QUERYBALANCEINFO_DTO");
		return mapping.findForward("standardView");
	}

	/**
	 * 查询收费标准表
	 * 
	 * @param
	 * @return
	 */
	public ActionForward queryStandards(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		Dto inDto = aForm.getParamAsDto(request);
		List standardList = g4Reader.queryForPage("getStandardListForPage", inDto);
		Integer totalCount = (Integer) g4Reader.queryForObject("getStandardListForPageCount", inDto);
		String jsonStrList = JsonHelper.encodeList2PageJson(standardList, totalCount, null);
		write(jsonStrList, response);
		return mapping.findForward(null);
	}

	/**
	 * 保存收费标准表
	 * 
	 * @param
	 * @return
	 */
	public ActionForward saveStandard(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		Dto dto = aForm.getParamAsDto(request);
		dto.put("operator", super.getSessionContainer(request).getUserInfo().getUserid());
		dto.put("cid", super.getSessionContainer(request).getUserInfo().getCustomId());
		
		Dto outDto = standardService.saveStandard(dto); 
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		write(jsonString, response);
		return mapping.findForward(null);
	}
	

	/**
	 * 删除收费标准表
	 * 
	 * @param
	 * @return
	 */
	public ActionForward deleteStandard(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strChecked = request.getParameter("strChecked");
		Dto inDto = new BaseDto();
		inDto.put("strChecked", strChecked);
		inDto.put("cid", super.getSessionContainer(request).getUserInfo().getCustomId());
		standardService.deleteStandard(inDto);
		Dto outDto = new BaseDto();
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "收费标准数据删除成功!");
		write(outDto.toJson(), response);
		return mapping.findForward(null);
	}

	/**
	 * 修改收费标准表
	 * 
	 * @param
	 * @return
	 */
	public ActionForward updateStandard(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		Dto inDto = aForm.getParamAsDto(request);
		inDto.put("operator", super.getSessionContainer(request).getUserInfo().getUserid());
		inDto.put("cid", super.getSessionContainer(request).getUserInfo().getCustomId());
		standardService.updateStandard(inDto);
		Dto outDto = new BaseDto();
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "收费标准数据修改成功!");
		write(outDto.toJson(), response);
		return mapping.findForward(null);
	}
	
	/**

	 * 取得当前年度
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getCurrentYear(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Dto dto = standardService.getCurrentYear(); 
		Dto outDto = new BaseDto();
		if (G4Utils.isEmpty(dto)) {
			outDto.put("msg", "没有查询到数据");
		} else {
			outDto.putAll(dto);
			outDto.put("msg", "ok");
		}
		write(JsonHelper.encodeDto2FormLoadJson(outDto, GlobalConstants.FORMAT_Date), response);
		return mapping.findForward(null);
	}
	
	/**
	 * 保存当前年度
	 * 
	 * @param
	 * @return
	 */
	public ActionForward saveCurrentYear(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		Dto dto = aForm.getParamAsDto(request);
		dto.put("operator", super.getSessionContainer(request).getUserInfo().getUserid());
		dto.put("cid", super.getSessionContainer(request).getUserInfo().getCustomId());
		
		Dto outDto = standardService.saveCurrentYear(dto); 
		if("on".equals(dto.getAsString("copyStandard"))){
			standardService.copyStandard(dto); 
		}
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		write(jsonString, response);
		return mapping.findForward(null);
	}
	
	
}
