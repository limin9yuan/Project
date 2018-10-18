
package org.module.fc.web;
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
import org.module.fc.service.StationService;
public class StationAction extends BaseAction{
	private StationService stationService = (StationService) super.getService("StationService");
	
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
	public ActionForward StationInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("stationView");
	}
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
	public ActionForward buildingInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("buildingView");
	}

	/**
	 * 换热站：加载换热站信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward loadStationInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		/*Dto dto = new BaseDto();
		String nodeid = request.getParameter("node");
		dto.put("parentid", nodeid);
		Dto outDto = new BaseDto();
		outDto.putAll((BaseDto) g4Dao.queryForObject("queryStationInfo", dto));
		outDto.put("success", new Boolean(true));
		String jsonString = JsonHelper.encodeDto2FormLoadJson(outDto, null);
		super.write(jsonString, response);*/
		
		
		CommonActionForm cForm = (CommonActionForm) form;
		Dto inDto = cForm.getParamAsDto(request);
		Dto outDto = new BaseDto();
		Dto dto = (BaseDto) g4Reader.queryForObject("queryStationInfo", inDto);
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
	 * 加载换热站字典
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getStationDatas(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		Dto dto = aForm.getParamAsDto(request);
		List areaList = g4Reader.queryForList("getStationDic", dto);
		String jsonString = JsonHelper.encodeObject2Json(areaList);
		super.write(jsonString, response);
		return mapping.findForward(null);
	}
	/**
	 * 加载房产管理员字典
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getManagerDatas(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		Dto dto = aForm.getParamAsDto(request);
		List areaList = g4Reader.queryForList("getManagerDic", dto);
		String jsonString = JsonHelper.encodeObject2Json(areaList);
		super.write(jsonString, response);
		return mapping.findForward(null);
	}
	/**
	 * 添加换热站
	 * 
	 * @param
	 * @return
	 */
	public ActionForward insertStation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm)form;
		Dto dto = aForm.getParamAsDto(request);
		dto.put("userid", super.getSessionContainer(request).getUserInfo().getUserid());
		dto.put("cid", super.getSessionContainer(request).getUserInfo().getCustomId());
		Dto outDto = stationService.insertStation(dto);
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		write(jsonString, response);
		return mapping.findForward(null);
	}
	/**
	 * 修改换热站
	 * 
	 * @param
	 * @return
	 */
	public ActionForward updateStation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm)form;
		Dto dto = aForm.getParamAsDto(request);
		dto.put("userid", super.getSessionContainer(request).getUserInfo().getUserid());
		dto.put("cid", super.getSessionContainer(request).getUserInfo().getCustomId());
		System.out.println(stationService);
		Dto outDto = stationService.updateStation(dto);
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		write(jsonString, response);
		return mapping.findForward(null);
	}
	/**
	 * 删除换热站
	 * 
	 * @param
	 * @return
	 */
	public ActionForward deleteStation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm)form;
		Dto dto = aForm.getParamAsDto(request);
		dto.put("userid", super.getSessionContainer(request).getUserInfo().getUserid());
		dto.put("cid", super.getSessionContainer(request).getUserInfo().getCustomId());		
		Dto outDto = stationService.deleteStation(dto);
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		write(jsonString, response);
		return mapping.findForward(null);
	}

}
