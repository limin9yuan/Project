
package org.module.fc.web;

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
import org.module.fc.service.HouseService;
import org.module.fc.service.UserCardService;

public class UserCardAction extends BaseAction{
	 private UserCardService userCardService = (UserCardService) super.getService("UserCardService");

	/**
	 * 查询读写器
	 * 
	 * @param
	 * @return
	 */
	public ActionForward query(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		CommonActionForm aForm = (CommonActionForm) form;
		Dto dto = aForm.getParamAsDto(request);
		List list = new ArrayList();
		list = g4Reader.queryForPage("queryUserCard", dto);
		Integer countInteger = (Integer) g4Reader.queryForObject("queryUserCardForPageCount", dto);
		super.setSessionAttribute(request, "GRIDACTION_QUERYBALANCEINFO_DTO", dto);
		String jsonString = encodeList2PageJson(list, countInteger, GlobalConstants.FORMAT_Date);
		super.write(jsonString, response);
		return mapping.findForward(null);
	}
	/**
	 * 汇总读写器数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sumUserCardDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Dto dto = (BaseDto)super.getSessionAttribute(request, "GRIDACTION_QUERYBALANCEINFO_DTO");
		Dto sumDto = new BaseDto();
		sumDto = (BaseDto)g4Reader.queryForObject("sumUserCard", dto);
		sumDto.put("success", new Boolean(true));
		String jsonString = JsonHelper.encodeObject2Json(sumDto);
		super.write(jsonString, response);
		return mapping.findForward(null);
	}
	/**
	 * 设置读写器页面初始化
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward userCardInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//super.removeSessionAttribute(request, "GRIDACTION_QUERYBALANCEINFO_DTO");
		return mapping.findForward("userCardView");
	}
	
	/**
	 * 设置读写器
	 * 
	 * @param
	 * @return
	 */
	public ActionForward userCard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm)form;
		Dto dto = aForm.getParamAsDto(request);
		dto.put("operator", super.getSessionContainer(request).getUserInfo().getUserid());
		dto.put("cid", super.getSessionContainer(request).getUserInfo().getCustomId());
		Dto outDto = userCardService.userCard(dto); 
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		write(jsonString, response);
		System.out.println(jsonString);
		return mapping.findForward(null);
	}
	
	public ActionForward getDate19(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		CommonActionForm cForm = (CommonActionForm) form;
		Dto inDto = cForm.getParamAsDto(request);
		Dto outDto = new BaseDto();
		Dto dto = (BaseDto) g4Reader.queryForObject("getDate19", inDto);
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
	 * 用户：加载用户信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward userCodeQuery(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		CommonActionForm cForm = (CommonActionForm) form;
		Dto inDto = cForm.getParamAsDto(request);
		Dto outDto = new BaseDto();
		Integer countInteger = (Integer) g4Reader.queryForObject("userCodeQueryCount", inDto);
		Dto dto =null;
		if(countInteger==1){
			outDto.put("recordCount", 1);
			dto = (BaseDto) g4Reader.queryForObject("userCodeQuery", inDto);
			outDto.putAll(dto);
			outDto.put("msg", "ok");
		}else if(countInteger==0){
			outDto.put("recordCount", 0);
			outDto.put("msg", "没有查询到数据");
		}else{
			outDto.put("recordCount", 2);
		}
		write(JsonHelper.encodeDto2FormLoadJson(outDto, GlobalConstants.FORMAT_Date), response);
		return mapping.findForward(null);
	}
	
	
	public ActionForward userCodeQuery2(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		Dto inDto = aForm.getParamAsDto(request);
		List houseList = g4Reader.queryForPage("userCodeQuery2", inDto);
		Integer totalCount = (Integer) g4Reader.queryForObject("userCodeQuery2ForPageCount", inDto);
		String jsonStrList = JsonHelper.encodeList2PageJson(houseList, totalCount, null);
		write(jsonStrList, response);
		return mapping.findForward(null);
	}

	
	
	/**
	 * 用户卡号编辑
	 * 
	 * @param
	 * @return
	 */
	public ActionForward updateCardId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm)form;
		Dto dto = aForm.getParamAsDto(request);
		dto.put("operator", super.getSessionContainer(request).getUserInfo().getUserid());
		
		Dto outDto = userCardService.updateCardId(dto); 
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		write(jsonString, response);
		return mapping.findForward(null);
	}
	
	/**
	 * 批量修改开启度
	 * 
	 * @param
	 * @return
	 */
	public ActionForward bantchUpdateVal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm)form;
		Dto dto = aForm.getParamAsDto(request);
		dto.put("operator", super.getSessionContainer(request).getUserInfo().getUserid());
		
		Dto outDto = userCardService.bantchUpdateVal(dto); 
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		write(jsonString, response);
		return mapping.findForward(null);
	}
	
	/**
	 * 批量修改开启度查询
	 * 
	 * @param
	 * @return
	 */
	public ActionForward cardValQuery(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {		
		CommonActionForm cForm = (CommonActionForm) form;
		Dto inDto = cForm.getParamAsDto(request);
		Dto outDto = new BaseDto();
		Integer countInteger = (Integer) g4Reader.queryForObject("cardValQueryForPageCount", inDto);
		Dto dto =null;
		if(countInteger==1){
			outDto.put("recordCount", 1);
			dto = (BaseDto) g4Reader.queryForObject("cardValQuery", inDto);
			outDto.putAll(dto);
			outDto.put("msg", "ok");
		}else if(countInteger==0){
			outDto.put("recordCount", 0);
			outDto.put("msg", "没有查询到数据");
		}else{
			outDto.put("recordCount", 2);
		}
		write(JsonHelper.encodeDto2FormLoadJson(outDto, GlobalConstants.FORMAT_Date), response);
		return mapping.findForward(null);
	}
	
	/**
	 * 查询冲账数据对应年度,房间编号,阀门号,冲账时间(关门时间)
	 * 
	 * @param
	 * @return
	 */
	public ActionForward selectChargeMonth(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		CommonActionForm cForm = (CommonActionForm) form;
		Dto inDto = cForm.getParamAsDto(request);
		Dto outDto = new BaseDto();
		Dto dto = (BaseDto) g4Reader.queryForObject("selectChargeMonth", inDto);
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
	 * 查询sf_bill表的收费时间，为开门时间
	 * 
	 * @param
	 * @return
	 */
	public ActionForward selectMinBillDate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		CommonActionForm cForm = (CommonActionForm) form;
		Dto inDto = cForm.getParamAsDto(request);
		Dto outDto = new BaseDto();
		Dto dto = (BaseDto) g4Reader.queryForObject("selectMinBillDate", inDto);
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
	 * 更新sf_charge表写卡标识(2)、写卡人、写卡时间
	 * 
	 * @param
	 * @return
	 */
	public ActionForward updateSfChargeWriteCardFlag(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm)form;
		Dto dto = aForm.getParamAsDto(request);
		dto.put("operator", super.getSessionContainer(request).getUserInfo().getUserid());
		
		Dto outDto = userCardService.updateSfChargeWriteCardFlag(dto); 
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		write(jsonString, response);
		return mapping.findForward(null);
	}
	
	/**
	 * 查询收费记录对应开启度
	 * 
	 * @param
	 * @return
	 */
	public ActionForward getUserCardVal(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		CommonActionForm cForm = (CommonActionForm) form;
		Dto inDto = cForm.getParamAsDto(request);
		Dto outDto = new BaseDto();
		Dto dto = (BaseDto) g4Reader.queryForObject("getUserCardVal", inDto);
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
	 * 查询阀门号
	 * 
	 * @param
	 * @return
	 */
	public ActionForward getvalveCode(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		CommonActionForm cForm = (CommonActionForm) form;
		Dto inDto = cForm.getParamAsDto(request);
		Dto outDto = new BaseDto();
		Dto dto = (BaseDto) g4Reader.queryForObject("getvalveCode", inDto);
		if (G4Utils.isEmpty(dto)) {
			outDto.put("msg", "没有查询到数据");
		} else {
			outDto.putAll(dto);
			outDto.put("msg", "ok");
		}
		write(JsonHelper.encodeDto2FormLoadJson(outDto, GlobalConstants.FORMAT_Date), response);
		return mapping.findForward(null);
	}
}
