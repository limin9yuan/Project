package org.module.sf.web;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

import sun.misc.BASE64Encoder;

public class CreatPlanAction extends BaseAction{
	private CreatPLanService creatPLanService = (CreatPLanService) super.getService("CreatPLanService");

	/**
	 * 查询应收数据
	 * 
	 * @param
	 * @return
	 */
	public ActionForward queryChargePlan(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		CommonActionForm aForm = (CommonActionForm) form;
		Dto dto = aForm.getParamAsDto(request);
		List list = new ArrayList();
		list = g4Reader.queryForPage("queryChargePlan", dto);
		Integer countInteger = (Integer) g4Reader.queryForObject("queryChargePlanForPageCount", dto);
		super.setSessionAttribute(request, "GRIDACTION_QUERYBALANCEINFO_DTO", dto);
		String jsonString = encodeList2PageJson(list, countInteger, GlobalConstants.FORMAT_Date);
		super.write(jsonString, response);
		return mapping.findForward(null);
	}
	/**
	 * 汇总应收数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sumChargePlan(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Dto dto = (BaseDto)super.getSessionAttribute(request, "GRIDACTION_QUERYBALANCEINFO_DTO");
		Dto sumDto = new BaseDto();
		sumDto = (BaseDto)g4Reader.queryForObject("sumChargePlan", dto);
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
	public ActionForward creatPlanInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//super.removeSessionAttribute(request, "GRIDACTION_QUERYBALANCEINFO_DTO");
		return mapping.findForward("creatPlanView");
	}
	/**
	 * 生成应收
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward creatPlan(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		InetAddress ia;
		try {
			ia = InetAddress.getLocalHost();
			String v = getLocalMac(ia);
			v = EncoderByMd5(v);
			/*if(!"9z/Kln2uWGanEf3HGjneTA==".equals(v)){
				System.out.println("生成应收出了错。");
				return null;
			}*/
			
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		CommonActionForm cForm = (CommonActionForm) form;
		Dto inDto = cForm.getParamAsDto(request);
		inDto.put("p_operator", getSessionContainer(request).getUserInfo().getUserid());
	    Dto outDto = creatPLanService.creatPlan(inDto);
	    outDto.put("success", new Boolean(true));
		outDto.put("msg", "生成应收成功！");
		write(JsonHelper.encodeObject2Json(outDto), response);
		return mapping.findForward(null);
	}
	/**
	 * 删除应收
	 * 
	 * @param
	 * @return
	 */
	public ActionForward deletePlan(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm)form;
		Dto dto = aForm.getParamAsDto(request);
		dto.put("userid", super.getSessionContainer(request).getUserInfo().getUserid());
		dto.put("cid", super.getSessionContainer(request).getUserInfo().getCustomId());		
		Dto outDto = creatPLanService.deletePlan(dto);
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "应收删除成功!");
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		write(jsonString, response);
		return mapping.findForward(null);
	}
	/**
	 * 查询删除应收记录数
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward countDeletePlan(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		InetAddress ia;
		try {
			ia = InetAddress.getLocalHost();
			String v = getLocalMac(ia);
			v = EncoderByMd5(v);
			/*if(!"hM1OiGxOmkes61DuMcxgZA==".equals(v)){
				System.out.println("生成应收出了错。");
				return null;
			}*/
			
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		CommonActionForm aForm = (CommonActionForm)form;
		Dto countDto = aForm.getParamAsDto(request);
		Integer countInteger = (Integer)g4Reader.queryForObject("countDeletePlan", countDto);
		countDto.put("success", new Boolean(true));
		countDto.put("countDeletePlan", countInteger);
		String jsonString = JsonHelper.encodeObject2Json(countDto);
		super.write(jsonString, response);
		return mapping.findForward(null);
	}
	private static String getLocalMac(InetAddress ia) throws SocketException {
		// TODO Auto-generated method stub
		//获取网卡，获取地址
		byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
		
		StringBuffer sb = new StringBuffer("");
		for(int i=0; i<mac.length; i++) {
			if(i!=0) {
				sb.append("-");
			}
			//字节转换为整数
			int temp = mac[i]&0xff;
			String str = Integer.toHexString(temp);
			if(str.length()==1) {
				sb.append("0"+str);
			}else {
				sb.append(str);
			}
		}
		System.out.println("********");
		return sb.toString().toUpperCase();
	}
	public String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
	    //确定计算方法
	    MessageDigest md5=MessageDigest.getInstance("MD5");
	    BASE64Encoder base64en = new BASE64Encoder();
	    //加密后的字符串
	    String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
	    return newstr;
	}
}
