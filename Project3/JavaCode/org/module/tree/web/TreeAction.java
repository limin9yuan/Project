package org.module.tree.web;

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
import org.eredlab.g4.rif.web.BaseAction;
import org.eredlab.g4.rif.web.CommonActionForm;

public class TreeAction extends BaseAction{
	private OrganizationService organizationService = (OrganizationService) super.getService("organizationService");
	

	/**
	 * 查询房产数据(树)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryFCTree(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm)form;
		Dto dto = new BaseDto();
		String parentId = aForm.getTreeNodeUID4Clicked(request);
		dto.put("parentId", parentId);
		dto.put("length", parentId.length() + 2);
		List list = null;
		//20110903 lilei 修改 节点的判断方式
		if (parentId.equals("00")) {
			list = g4Reader.queryForList("queryCommunity", dto);
		}else if(parentId.length()>=2 && parentId.length()<=4){
			list = g4Reader.queryForList("queryBuilding", dto);
		}else if(parentId.length()>=6 && parentId.length()<=8){
			list = g4Reader.queryForList("queryHouse", dto);
		}
		//20110903 lilei modify
		String jsonString = "";
		if(list!=null){
			for (int i = 0; i < list.size(); i++) {
				Dto node = (BaseDto)list.get(i);
				node.put("checked", new Boolean(false));
				if (node.getAsString("id").length() > 8) {
					node.put("leaf", new Boolean(true));
				}else {
					node.put("leaf", new Boolean(false));
				}
			}
			jsonString = JsonHelper.encodeObject2Json(list);
		}
		super.write(jsonString, response);
		return mapping.findForward(null);
	}

}
