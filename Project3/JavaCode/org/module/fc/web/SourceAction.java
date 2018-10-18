package org.module.fc.web;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.eredlab.g4.arm.service.OrganizationService;
import org.eredlab.g4.arm.util.ArmConstants;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.ccl.json.JsonHelper;
import org.eredlab.g4.ccl.util.G4Utils;
import org.eredlab.g4.ccl.util.GlobalConstants;
import org.eredlab.g4.rif.web.BaseAction;
import org.eredlab.g4.rif.web.CommonActionForm;
import org.module.fc.service.SourceService;
public class SourceAction extends BaseAction{
	private SourceService sourceService = (SourceService) super.getService("SourceService");
	private OrganizationService organizationService = (OrganizationService) super.getService("organizationService");
	
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
	public ActionForward sourceInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.removeSessionAttribute(request, "menuid");
		Dto dto = (Dto)g4Reader.queryForObject("queryEamenuByMenuID", "01");
		request.setAttribute("rootMenuName", dto.getAsString("menuname"));
		return mapping.findForward("sourceView");
	}
	/**
	 * 查询热源站所树
	 * 
	 * @param
	 * @return
	 */
	public ActionForward querySourceTree(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Dto dto = new BaseDto();
		String nodeid = request.getParameter("node");
		dto.put("parentid", nodeid);
		List menuList = g4Reader.queryForList("querySourceTree", dto);
		Dto menuDto = new BaseDto();
		for (int i = 0; i < menuList.size(); i++) {
			menuDto = (BaseDto) menuList.get(i);
			if (menuDto.getAsString("leaf").equals(ArmConstants.LEAF_Y)){
				menuDto.put("leaf", new Boolean(true));
			}else{
					menuDto.put("leaf", new Boolean(false));
					menuDto.put("expanded", new Boolean(true));
			}
		}
		write(JsonHelper.encodeObject2Json(menuList), response);
		return mapping.findForward(null);
	}
	/**
	 * 查询菜单项目 - 菜单管理
	 * 
	 * @param
	 * @return
	 */
	public ActionForward queryStation(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		Dto dto = aForm.getParamAsDto(request);
		String menuid = request.getParameter("menuid");
		if (G4Utils.isNotEmpty(menuid)) {
			super.setSessionAttribute(request, "menuid", menuid);
		}
		dto.put("menuid", super.getSessionAttribute(request, "menuid"));
		List menuList = g4Reader.queryForPage("queryStation", dto);
		Integer pageCount = (Integer) g4Reader.queryForObject("queryStationForPageCount", dto);
		String jsonString = JsonHelper.encodeList2PageJson(menuList, pageCount, null);
		write(jsonString, response);
		return mapping.findForward(null);
	}
	/**
	 * 热源：加载热源信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward loadSourceInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm cForm = (CommonActionForm) form;
		Dto inDto = cForm.getParamAsDto(request);
		Dto outDto = new BaseDto();
		Dto dto = (BaseDto) g4Reader.queryForObject("querySourceInfo", inDto);
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
	 * 加载热源字典
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getSourceDatas(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		Dto dto = aForm.getParamAsDto(request);
		List areaList = g4Reader.queryForList("getSourceDic", dto);
		String jsonString = JsonHelper.encodeObject2Json(areaList);
		super.write(jsonString, response);
		return mapping.findForward(null);
	}
	/**
	 * 添加热源
	 * 
	 * @param
	 * @return
	 */
	public ActionForward insertSource(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm)form;
		Dto dto = aForm.getParamAsDto(request);
		dto.put("userid", super.getSessionContainer(request).getUserInfo().getUserid());
		dto.put("cid", super.getSessionContainer(request).getUserInfo().getCustomId());
		Dto outDto = sourceService.insertSource(dto);
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		write(jsonString, response);
		return mapping.findForward(null);
	}
	/**
	 * 修改热源
	 * 
	 * @param
	 * @return
	 */
	public ActionForward updateSource(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm)form;
		Dto dto = aForm.getParamAsDto(request);
		dto.put("userid", super.getSessionContainer(request).getUserInfo().getUserid());
		dto.put("cid", super.getSessionContainer(request).getUserInfo().getCustomId());
		Dto outDto = sourceService.updateSource(dto);
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		write(jsonString, response);
		return mapping.findForward(null);
	}
	/**
	 * 删除热源
	 * 
	 * @param
	 * @return
	 */
	public ActionForward deleteSource(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm)form;
		Dto dto = aForm.getParamAsDto(request);
		dto.put("userid", super.getSessionContainer(request).getUserInfo().getUserid());
		dto.put("cid", super.getSessionContainer(request).getUserInfo().getCustomId());		
		Dto outDto = sourceService.deleteSource(dto);
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		write(jsonString, response);
		return mapping.findForward(null);
	}

}
