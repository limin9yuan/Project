package org.module.fc.web;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.eredlab.g4.arm.util.ArmConstants;
import org.eredlab.g4.arm.vo.UserInfoVo;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.ccl.json.JsonHelper;
import org.eredlab.g4.ccl.util.G4Utils;
import org.eredlab.g4.ccl.util.GlobalConstants;
import org.eredlab.g4.rif.util.WebUtils;
import org.eredlab.g4.rif.web.BaseAction;
import org.eredlab.g4.rif.web.CommonActionForm;
import org.module.fc.service.PermissionService;
import org.module.sf.service.ChargeService;
import org.module.sys.util.idgenerator.IDHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mingyuan Li on 2018/11/27.
 * Package name: org.module.fc.web.
 * Project name: extgr.
 */
public class PermissionAction extends BaseAction {
    private PermissionService permissionService = (PermissionService) super.getService("PermissionService");
    /**
     * 表格页面初始化
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward permissionInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {
        //super.removeSessionAttribute(request, "GRIDACTION_QUERYBALANCEINFO_DTO");
        return mapping.findForward("permissionView");
    }

    /**
     * 查询用户列表
     *
     * @param
     * @return
     */
    public ActionForward queryUsersForPermission(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                             HttpServletResponse response) throws Exception {
        CommonActionForm aForm = (CommonActionForm)form;
        Dto dto = aForm.getParamAsDto(request);
        List userList = g4Reader.queryForList("queryUsersForPermission", dto);
        Integer pageCount = (Integer) g4Reader.queryForObject("queryUsersForPermissionForPageCount", dto);
        String jsonString = JsonHelper.encodeList2PageJson(userList, pageCount, null);
        write(jsonString, response);
        return mapping.findForward(null);
    }

    /**
     * 查询小区数据
     *
     * @param
     * @return
     */
    public ActionForward queryCommunityList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {

        CommonActionForm aForm = (CommonActionForm) form;
        Dto dto = aForm.getParamAsDto(request);
        List list = new ArrayList();
        list = g4Reader.queryForList("queryCommunityList", dto);
        Integer countInteger = (Integer) g4Reader.queryForObject("queryCommunityListForPageCount", dto);
        super.setSessionAttribute(request, "GRIDACTION_QUERYBALANCEINFO_DTO", dto);
        String jsonString = encodeList2PageJson(list, countInteger, GlobalConstants.FORMAT_Date);
        super.write(jsonString, response);
        return mapping.findForward(null);
    }

    /**
     * 小区授权
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward savePermission(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        CommonActionForm cForm = (CommonActionForm) form;
        Dto inDto = cForm.getParamAsDto(request);
        String ids = inDto.getAsString("communityRecord");
        String[] id = ids.split(",");
        Dto saveDto = new BaseDto();
        Dto deleteDto = new BaseDto();
        Dto outDto = new BaseDto();
        deleteDto.put("manager_id", inDto.getAsString("userRecord"));
        permissionService.deletePermission(deleteDto);
        for (int i = 0; i < id.length; i++){
            saveDto.put("manager_id", inDto.getAsString("userRecord"));
            saveDto.put("community_code", id[i]);
            saveDto.put("ID", IDHelper.getPermissionID());
            outDto = permissionService.savePermission(saveDto);
        }

        outDto.put("success", new Boolean(true));
        outDto.put("msg", "保存权限成功！");
        write(JsonHelper.encodeObject2Json(outDto), response);
        return mapping.findForward(null);
    }

    /**
     * 获取小区编号
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getCommunityCode(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {
        CommonActionForm stopForm = (CommonActionForm) form;
        Dto inDto = stopForm.getParamAsDto(request);
        List codeList = g4Reader.queryForList("getCommunityCode", inDto);
        Dto outDto = new BaseDto();
        Dto listDto = new BaseDto();
        String ids = "";
        for (int i =0; i < codeList.size(); i++){
            listDto = (BaseDto) codeList.get(i);
            ids += listDto.getAsString("community_code");
            if(i<codeList.size()-1){
                ids = ids + ",";
            }
        }
        outDto.put("community_code",ids);
        outDto.put("success", new Boolean(true));
        write(JsonHelper.encodeObject2Json(outDto), response);
        return mapping.findForward(null);
    }
}
