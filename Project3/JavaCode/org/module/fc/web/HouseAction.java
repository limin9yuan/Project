
package org.module.fc.web;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.eredlab.g4.arm.service.OrganizationService;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.ccl.json.JsonHelper;
import org.eredlab.g4.ccl.util.G4Utils;
import org.eredlab.g4.ccl.util.GlobalConstants;
import org.eredlab.g4.rif.web.BaseAction;
import org.eredlab.g4.rif.web.CommonActionForm;
import org.module.fc.service.HouseService;
public class HouseAction extends BaseAction{
	private HouseService houseService = (HouseService) super.getService("HouseService");


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
	public ActionForward loadHouseInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		CommonActionForm cForm = (CommonActionForm) form;
		Dto inDto = cForm.getParamAsDto(request);
		Dto outDto = new BaseDto();
		Dto dto = (BaseDto) g4Reader.queryForObject("queryHouseInfo", inDto);
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
	 * 用户：加载面积信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward loadAreaInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		CommonActionForm cForm = (CommonActionForm) form;
		Dto inDto = cForm.getParamAsDto(request);
		Dto outDto = new BaseDto();
		List list = new ArrayList();
		list = g4Reader.queryForList("queryAreaInfo", inDto);
		Integer countInteger = (Integer) g4Reader.queryForObject("queryAreaInfoForPageCount", inDto);
		if (G4Utils.isEmpty(inDto)) {
			outDto.put("msg", "没有查询到数据");
		} else {
			outDto.putAll(inDto);
			outDto.put("msg", "ok");
		}
		outDto.put("success", new Boolean(true));
		String jsonString = encodeList2PageJson(list, countInteger, GlobalConstants.FORMAT_Date);
		super.write(jsonString, response);
		return mapping.findForward(null);
	}
	/**
	 * 添加用户
	 * 
	 * @param
	 * @return
	 */
	public ActionForward insertHouse(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm)form;
		Dto indto = aForm.getParamAsDto(request);		
		indto.put("operator", super.getSessionContainer(request).getUserInfo().getUserid());
		indto.put("cid", super.getSessionContainer(request).getUserInfo().getCustomId());
		Dto outDto = houseService.insertHouse(indto); 
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		write(jsonString, response);
		return mapping.findForward(null);
	}
	/**
	 * 修改房间
	 * 
	 * @param
	 * @return
	 */
	public ActionForward updateHouse(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm)form;
		Dto dto = aForm.getParamAsDto(request);
		dto.put("operator", super.getSessionContainer(request).getUserInfo().getUserid());
		dto.put("cid", super.getSessionContainer(request).getUserInfo().getCustomId());
		Dto outDto = houseService.updateHouse(dto); 
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		write(jsonString, response);
		return mapping.findForward(null);
	}

	/**
	 * 删除房间
	 * 
	 * @param
	 * @return
	 */
	public ActionForward deleteHouse(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm)form;
		Dto dto = aForm.getParamAsDto(request);
		dto.put("operator", super.getSessionContainer(request).getUserInfo().getUserid());
		dto.put("cid", super.getSessionContainer(request).getUserInfo().getCustomId());		
		Dto outDto = houseService.deleteHouse(dto);
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		write(jsonString, response);
		return mapping.findForward(null);
	}
	
	/**
	 * 批量删除房间
	 * 
	 * @param
	 * @return
	 */
	public ActionForward batchDeleteHouse(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm)form;
		Dto dto = aForm.getParamAsDto(request);
		dto.put("operator", super.getSessionContainer(request).getUserInfo().getUserid());
		dto.put("cid", super.getSessionContainer(request).getUserInfo().getCustomId());		
		Dto outDto = houseService.batchDeleteHouse(dto);
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		write(jsonString, response);
		return mapping.findForward(null);
	}
	/**
	 * 导入房间错误数据
	 * 
	 * @param
	 * @return
	 */
	public ActionForward importErrorHouse(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm)form;
		Dto indto = aForm.getParamAsDto(request);		
		indto.put("userid", super.getSessionContainer(request).getUserInfo().getUserid());
		indto.put("cid", super.getSessionContainer(request).getUserInfo().getCustomId());
		Dto outDto = houseService.importErrorHouse(indto); 
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		write(jsonString, response);
		return mapping.findForward(null);
	}
	/**
	 * 修改面积
	 * 
	 * @param
	 * @return
	 */
	public ActionForward updateArea(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm)form;
		Dto dto = aForm.getParamAsDto(request);
		dto.put("operator", super.getSessionContainer(request).getUserInfo().getUserid());
		Dto outDto = houseService.updateArea(dto); 
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		write(jsonString, response);
		return mapping.findForward(null);
	}
	/**
	 * 添加面积
	 * 
	 * @param
	 * @return
	 */
	public ActionForward insertArea(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm)form;
		Dto dto = aForm.getParamAsDto(request);
		dto.put("operator", super.getSessionContainer(request).getUserInfo().getUserid());
		dto.put("cid", super.getSessionContainer(request).getUserInfo().getCustomId());
		Dto outDto = houseService.insertAreas(dto); 
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		write(jsonString, response);
		return mapping.findForward(null);
	}
	/**
	 * 删除面积
	 * 
	 * @param
	 * @return
	 */
	public ActionForward deleteAreaSingle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm)form;
		Dto dto = aForm.getParamAsDto(request);
		dto.put("operator", super.getSessionContainer(request).getUserInfo().getUserid());
		dto.put("cid", super.getSessionContainer(request).getUserInfo().getCustomId());		
		Dto outDto = houseService.deleteAreaSingle(dto);
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		write(jsonString, response);
		return mapping.findForward(null);
	}
}

