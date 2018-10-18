package org.module.sys.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.ccl.json.JsonHelper;
import org.eredlab.g4.rif.web.BaseAction;
import org.eredlab.g4.rif.web.CommonActionForm;

public class SysAction extends BaseAction{
	/**
	 * 加载收费标准
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getStandard(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		Dto dto = aForm.getParamAsDto(request);
		List areaList = g4Reader.queryForList("getStandard", dto);
		String jsonString = JsonHelper.encodeObject2Json(areaList);
		super.write(jsonString, response);
		return mapping.findForward(null);
	}
	/**
	 * 加载面积
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getAreaList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		Dto dto = aForm.getParamAsDto(request);
		List areaList = g4Reader.queryForList("getAreaList", dto);
		String jsonString = JsonHelper.encodeObject2Json(areaList);
		super.write(jsonString, response);
		return mapping.findForward(null);
	}
	/**
	 * 加载收费标准+单价
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getStandardAndPrice(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		Dto dto = aForm.getParamAsDto(request);
		List areaList = g4Reader.queryForList("getStandardAndPrice", dto);
		String jsonString = JsonHelper.encodeObject2Json(areaList);
		super.write(jsonString, response);
		return mapping.findForward(null);
	}
	
	/**
	 * 查询收费员(树)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getOperator(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm)form;
		Dto dto = new BaseDto();
		String parentId = aForm.getTreeNodeUID4Clicked(request);
		dto.put("parentId", parentId);
		List list = null;
		//20110903 lilei 修改 节点的判断方式
		if (parentId.equals("00")) {
			list = g4Reader.queryForList("getOperator", dto);
		}
		//20110903 lilei modify
		String jsonString = "";
		if(list!=null){
			for (int i = 0; i < list.size(); i++) {
				Dto node = (BaseDto)list.get(i);
				node.put("checked", new Boolean(false));
				if (node.getAsString("id").length()==2) {
					node.put("leaf", new Boolean(false));
				}else {
					node.put("leaf", new Boolean(true));
				}
			}
			jsonString = JsonHelper.encodeObject2Json(list);
		}
		super.write(jsonString, response);
		return mapping.findForward(null);
	}
	/**
	 * 加载默认时间
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getCurrentDate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		Dto dto = aForm.getParamAsDto(request);
		List areaList = g4Reader.queryForList("getCurrentDate", dto);
		String jsonString = JsonHelper.encodeObject2Json(areaList);
		super.write(jsonString, response);
		return mapping.findForward(null);
	}
	
}
