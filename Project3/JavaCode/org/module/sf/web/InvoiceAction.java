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
import org.module.sf.service.InvoiceService;

public class InvoiceAction extends BaseAction{
	private InvoiceService invoiceService = (InvoiceService) super.getService("InvoiceService");

	/**
	 * 查询票据列表
	 * 
	 * @param
	 * @return
	 */
	public ActionForward queryInvoice(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		CommonActionForm aForm = (CommonActionForm) form;
		Dto dto = aForm.getParamAsDto(request);
		List list = new ArrayList();
		list = g4Reader.queryForPage("queryInvoice", dto);
		Integer countInteger = (Integer) g4Reader.queryForObject("queryInvoiceForPageCount", dto);
		super.setSessionAttribute(request, "GRIDACTION_QUERYBALANCEINFO_DTO", dto);
		String jsonString = encodeList2PageJson(list, countInteger, GlobalConstants.FORMAT_Date);
		super.write(jsonString, response);
		return mapping.findForward(null);
	}
	/**
	 * 汇总票据数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sumInvoice(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Dto dto = (BaseDto)super.getSessionAttribute(request, "GRIDACTION_QUERYBALANCEINFO_DTO");
		Dto sumDto = new BaseDto();
		sumDto = (BaseDto)g4Reader.queryForObject("sumInvoice", dto);
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
	public ActionForward invoiceInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//super.removeSessionAttribute(request, "GRIDACTION_QUERYBALANCEINFO_DTO");
		return mapping.findForward("invoiceView");
	}
	/**
	 * 添加发票
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward insertInvoice(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm cForm = (CommonActionForm) form;
		Dto inDto = cForm.getParamAsDto(request);
		inDto.put("cid", getSessionContainer(request).getUserInfo().getCustomId());
		inDto.put("operator", getSessionContainer(request).getUserInfo().getUserid());
	    Dto outDto = invoiceService.insertInvoice(inDto);
	    outDto.put("success", new Boolean(true));
		outDto.put("msg", "发票入库成功！");
		write(JsonHelper.encodeObject2Json(outDto), response);
		return mapping.findForward(null);
	}
	/**
	 * 删除票据
	 * 
	 * @param
	 * @return
	 */
	public ActionForward deleteInvoice(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm)form;
		Dto dto = aForm.getParamAsDto(request);
		dto.put("userid", super.getSessionContainer(request).getUserInfo().getUserid());
		dto.put("cid", super.getSessionContainer(request).getUserInfo().getCustomId());		
		Dto outDto = invoiceService.deleteInvoice(dto);
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "票据删除成功!");
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		write(jsonString, response);
		return mapping.findForward(null);
	}
	/**
	 * 作废票据
	 * 
	 * @param
	 * @return
	 */
	public ActionForward invalidInvoice(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm)form;
		Dto dto = aForm.getParamAsDto(request);
		dto.put("operator", super.getSessionContainer(request).getUserInfo().getUserid());
		dto.put("cid", super.getSessionContainer(request).getUserInfo().getCustomId());		
		Dto outDto = invoiceService.invalidInvoice(dto);
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "作废票据成功!");
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		write(jsonString, response);
		return mapping.findForward(null);
	}
	/**
	 * 返还票据
	 * 
	 * @param
	 * @return
	 */
	public ActionForward returnInvoice(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm)form;
		Dto dto = aForm.getParamAsDto(request);
		dto.put("operator", super.getSessionContainer(request).getUserInfo().getUserid());
		dto.put("cid", super.getSessionContainer(request).getUserInfo().getCustomId());		
		Dto outDto = invoiceService.returnInvoice(dto);
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "票据返还成功!");
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		write(jsonString, response);
		return mapping.findForward(null);
	}
	/**
	 * 领用票据
	 * 
	 * @param
	 * @return
	 */
	public ActionForward leadInvoice(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm)form;
		Dto dto = aForm.getParamAsDto(request);
		dto.put("operator", super.getSessionContainer(request).getUserInfo().getUserid());
		dto.put("cid", super.getSessionContainer(request).getUserInfo().getCustomId());		
		Dto outDto = invoiceService.leadInvoice(dto);
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "票据领用成功!");
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		write(jsonString, response);
		return mapping.findForward(null);
	}
}
