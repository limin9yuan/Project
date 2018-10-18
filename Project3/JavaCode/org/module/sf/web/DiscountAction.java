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
import org.eredlab.g4.ccl.util.GlobalConstants;
import org.eredlab.g4.rif.web.BaseAction;
import org.eredlab.g4.rif.web.CommonActionForm;
import org.module.sf.service.CreatPLanService;
import org.module.sf.service.DiscountService;

public class DiscountAction extends BaseAction{
	private DiscountService discountService = (DiscountService) super.getService("DiscountService");
	/**
	 *优惠设置页面初始化
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward discountInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//super.removeSessionAttribute(request, "GRIDACTION_QUERYBALANCEINFO_DTO");
		return mapping.findForward("discountView");
	}

	/**
	 * 查询优惠设置表
	 * 
	 * @param
	 * @return
	 */
	public ActionForward queryDiscounts(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		Dto inDto = aForm.getParamAsDto(request);
		List discountList = g4Reader.queryForPage("getDiscountListForPage", inDto);
		Integer totalCount = (Integer) g4Reader.queryForObject("getDiscountListForPageCount", inDto);
		String jsonStrList = JsonHelper.encodeList2PageJson(discountList, totalCount, null);
		write(jsonStrList, response);
		return mapping.findForward(null);
	}

	/**
	 * 保存优惠设置表
	 * 
	 * @param
	 * @return
	 */
	public ActionForward saveDiscount(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		Dto dto = aForm.getParamAsDto(request);
		dto.put("operator", super.getSessionContainer(request).getUserInfo().getUserid());
		dto.put("cid", super.getSessionContainer(request).getUserInfo().getCustomId());
		
		Dto outDto = discountService.saveDiscount(dto); 
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		write(jsonString, response);
		return mapping.findForward(null);
	}
	

	/**
	 * 删除优惠设置表
	 * 
	 * @param
	 * @return
	 */
	public ActionForward deleteDiscount(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strChecked = request.getParameter("strChecked");
		Dto inDto = new BaseDto();
		inDto.put("strChecked", strChecked);
		inDto.put("cid", super.getSessionContainer(request).getUserInfo().getCustomId());
		discountService.deleteDiscount(inDto);
		Dto outDto = new BaseDto();
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "优惠数据删除成功!");
		write(outDto.toJson(), response);
		return mapping.findForward(null);
	}

	/**
	 * 修改优惠设置表
	 * 
	 * @param
	 * @return
	 */
	public ActionForward updateDiscount(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		Dto inDto = aForm.getParamAsDto(request);
		inDto.put("operator", super.getSessionContainer(request).getUserInfo().getUserid());
		inDto.put("cid", super.getSessionContainer(request).getUserInfo().getCustomId());
		discountService.updateDiscount(inDto);
		Dto outDto = new BaseDto();
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "优惠数据修改成功!");
		write(outDto.toJson(), response);
		return mapping.findForward(null);
	}
}
