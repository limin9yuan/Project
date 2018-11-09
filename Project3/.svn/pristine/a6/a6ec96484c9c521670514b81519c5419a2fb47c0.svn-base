package org.module.tg.web;

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
import org.module.sf.service.ChargeService;
import org.module.tg.service.StopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mingyuan Li on 2018/11/5.
 * Package name: org.module.tg.web.
 * Project name: extgr.
 */
public class StopAction extends BaseAction {
    private StopService stopService = (StopService) super.getService("StopService");

    /**
     * 查询停供数据
     *
     * @param
     * @return
     */
    public ActionForward queryStop(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                         HttpServletResponse response) throws Exception {

        CommonActionForm aForm = (CommonActionForm) form;
        Dto dto = aForm.getParamAsDto(request);
        List list = new ArrayList();
        list = g4Reader.queryForPage("queryStop", dto);
        Integer countInteger = (Integer) g4Reader.queryForObject("queryStopForPageCount", dto);
        super.setSessionAttribute(request, "GRIDACTION_QUERYBALANCEINFO_DTO", dto);
        String jsonString = encodeList2PageJson(list, countInteger, GlobalConstants.FORMAT_Date);
        super.write(jsonString, response);
        return mapping.findForward(null);
    }
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
    public ActionForward stopInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {
        //super.removeSessionAttribute(request, "GRIDACTION_QUERYBALANCEINFO_DTO");
        return mapping.findForward("stopView");
    }

    /**
     * 保存停供
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward saveStop(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {
        CommonActionForm cForm = (CommonActionForm) form;
        Dto inDto = cForm.getParamAsDto(request);
        List list = new ArrayList();
        list = g4Reader.queryForList("dataCheck");
        Dto outDto = new BaseDto();
        for (int i = 0; i<list.size();i++){
            BaseDto baseDto = (BaseDto) list.get(i);
            if (baseDto.getAsString("house_code").equals(inDto.getAsString("house_code"))
                    && baseDto.getAsString("charge_month").equals(inDto.getAsString("charge_month"))
                    && baseDto.getAsString("meter_id").equals(inDto.getAsString("meter_id"))
                    && baseDto.getAsInteger("is_stop") == 1){
                outDto.put("error", new Boolean(true));
                outDto.put("msg", baseDto.getAsString("house_code")+"已经停供！");
                write(JsonHelper.encodeObject2Json(outDto), response);
                return mapping.findForward(null);
            }
        }
        inDto.put("cid", getSessionContainer(request).getUserInfo().getCustomId());
        inDto.put("operator", getSessionContainer(request).getUserInfo().getUserid());
        inDto.put("is_stop",1);
        stopService.deleteStop(inDto);
        outDto = stopService.saveStop(inDto);
        outDto.put("success", new Boolean(true));
        outDto.put("msg", "保存数据成功！");
        write(JsonHelper.encodeObject2Json(outDto), response);
        return mapping.findForward(null);
    }

    /**
     * update停供
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward updateStop(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {
        CommonActionForm cForm = (CommonActionForm) form;
        Dto inDto = cForm.getParamAsDto(request);
        Dto outDto = new BaseDto();
        inDto.put("cid", getSessionContainer(request).getUserInfo().getCustomId());
        inDto.put("operator", getSessionContainer(request).getUserInfo().getUserid());
        outDto = stopService.updateStop(inDto);
        outDto.put("success", new Boolean(true));
        outDto.put("msg", "保存数据成功！");
        write(JsonHelper.encodeObject2Json(outDto), response);
        return mapping.findForward(null);
    }

    /**
     * 恢复供热
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward startHeat(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {
        CommonActionForm cForm = (CommonActionForm) form;
        Dto inDto = cForm.getParamAsDto(request);
        Dto dto = (BaseDto) g4Reader.queryForObject("queryStopInfo", inDto);
        Dto outDto = new BaseDto();
        if (G4Utils.isEmpty(dto)) {
            outDto.put("error", new Boolean(true));
            outDto.put("msg", "没有查询到数据");
        }else {
            if (dto.getAsString("house_code").equals(inDto.getAsString("house_code"))
                    && dto.getAsString("charge_month").equals(inDto.getAsString("charge_month"))
                    && dto.getAsString("meter_id").equals(inDto.getAsString("meter_id"))
                    && dto.getAsInteger("is_stop") != 1){
                outDto.put("error", new Boolean(true));
                outDto.put("msg", "该用户没有停供");
            }else {
                inDto.put("cid", getSessionContainer(request).getUserInfo().getCustomId());
                inDto.put("operator", getSessionContainer(request).getUserInfo().getUserid());
                inDto.put("is_stop", 0);
                outDto = stopService.startHeat(inDto);
                outDto.put("success", new Boolean(true));
                outDto.put("msg", "恢复供热成功！");
            }
        }
        write(JsonHelper.encodeObject2Json(outDto), response);
        return mapping.findForward(null);
    }

    /**
     * 删除停供
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteStop(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {
        CommonActionForm cForm = (CommonActionForm) form;
        Dto inDto = cForm.getParamAsDto(request);
        Dto dto = (BaseDto) g4Reader.queryForObject("queryStopInfo", inDto);
        Dto outDto = new BaseDto();
        if (G4Utils.isEmpty(dto)) {
            outDto.put("error", new Boolean(true));
            outDto.put("msg", "没有查询到数据");
        }else {
            inDto.put("cid", getSessionContainer(request).getUserInfo().getCustomId());
            inDto.put("operator", getSessionContainer(request).getUserInfo().getUserid());
            inDto.put("is_stop",0);
            outDto = stopService.deleteStop(inDto);
            outDto.put("success", new Boolean(true));
            outDto.put("msg", "删除停供成功！");
        }
        write(JsonHelper.encodeObject2Json(outDto), response);
        return mapping.findForward(null);
    }


    /**
     * 修改停供
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward editStop(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {
        CommonActionForm stopForm = (CommonActionForm) form;
        Dto inDto = stopForm.getParamAsDto(request);
        Dto dto = (BaseDto) g4Reader.queryForObject("queryStopInfo", inDto);
        Dto outDto = new BaseDto();
        if (G4Utils.isEmpty(dto)) {
            outDto.put("error", new Boolean(true));
            outDto.put("msg", "没有查询到数据");
        } else {
            if (dto.getAsString("house_code").equals(inDto.getAsString("house_code"))
                    && dto.getAsString("charge_month").equals(inDto.getAsString("charge_month"))
                    && dto.getAsString("meter_id").equals(inDto.getAsString("meter_id"))
                    && dto.getAsInteger("is_stop") != 1){
                outDto.put("error", new Boolean(true));
                outDto.put("msg", "该用户没有停供");
            }else {
                outDto.putAll(dto);
                outDto.put("success", new Boolean(true));
            }
        }
        write(JsonHelper.encodeObject2Json(outDto), response);
        return mapping.findForward(null);
    }

    /**
     * 停供
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward startStop(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {
        CommonActionForm stopForm = (CommonActionForm) form;
        Dto inDto = stopForm.getParamAsDto(request);
        Dto dto = (BaseDto) g4Reader.queryForObject("queryStopInfo", inDto);
        Dto outDto = new BaseDto();
        if (G4Utils.isEmpty(dto)){
            outDto.put("success", new Boolean(true));
        }
        else {
            if (dto.getAsString("house_code").equals(inDto.getAsString("house_code"))
                    && dto.getAsString("charge_month").equals(inDto.getAsString("charge_month"))
                    && dto.getAsString("meter_id").equals(inDto.getAsString("meter_id"))
                    && dto.getAsInteger("is_stop") == 1){
                outDto.put("error", new Boolean(true));
                outDto.put("msg", dto.getAsString("house_code")+"已经停供！");
            }
            if (dto.getAsString("house_code").equals(inDto.getAsString("house_code"))
                    && dto.getAsString("charge_month").equals(inDto.getAsString("charge_month"))
                    && dto.getAsString("meter_id").equals(inDto.getAsString("meter_id"))
                    && dto.getAsInteger("is_stop") != 1){
                outDto.put("success", new Boolean(true));
            }
        }
        write(JsonHelper.encodeObject2Json(outDto), response);
        return mapping.findForward(null);
    }

}
