package org.module.fc.web;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.ccl.util.G4Utils;
import org.eredlab.g4.ccl.util.GlobalConstants;
import org.eredlab.g4.rif.report.excel.ExcelExporter;
import org.eredlab.g4.rif.web.BaseAction;
import org.eredlab.g4.rif.web.CommonActionForm;
import org.module.fc.excel.ExcelReader;

import sun.misc.BASE64Encoder;

/**
 * Excel导入导出标准范例暨教程Action
 * 
 * @author XiongChun
 * @since 2010-10-13
 * @see BaseAction
 */
public class ImportHouseAction extends BaseAction {

	
	/**
	 * 查询导入错误数据
	 * 
	 * @param
	 * @return
	 */
	public ActionForward queryErrorImport(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		Dto inDto = aForm.getParamAsDto(request);
		super.setSessionAttribute(request, "QUERYCATALOGS4EXPORT_QUERYDTO", inDto);
		List catalogList = g4Reader.queryForPage("queryErrorImport", inDto);
		Integer pageCount = (Integer) g4Reader.queryForObject("queryErrorImportForPageCount", inDto);
		String jsonString = encodeList2PageJson(catalogList, pageCount, GlobalConstants.FORMAT_DateTime);
		write(jsonString, response);
		return mapping.findForward(null);
	}

	/**
	 * 导入Excel
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 84457210
	 */
	public ActionForward importHouseExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		InetAddress ia;
		try {
			ia = InetAddress.getLocalHost();
			String v = getLocalMac(ia);
			v = EncoderByMd5(v);
			/*if(!"uvQ6b6AeTchxrFACaneBHA==".equals(v)){//长治
				return null;
			}*/
			/*if(!"9z/Kln2uWGanEf3HGjneTA==".equals(v)){//长治// 新服务器 0C-C4-7A-57-00-B2
				return null;
			}*/
			
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		CommonActionForm actionForm = (CommonActionForm) form;
		Dto outDto = new BaseDto();
		FormFile theFile = actionForm.getTheFile();
		String metaData =
			    "community_code,community_name,building_code,building_name," +
			    "cell_code,floor,door_code,address,enter_date,owner_name," +
			    "telephone,mobilephone,work_unit," +
			    "use_type,standard_id,build_area,use_area,charge_area," +
				"use_type2,standard_id2,build_area2,use_area2,charge_area2," +
		        "super_area,super_area2,card_id,cluster_code,valvecode,balcony";
		System.out.println(metaData);
		ExcelReader excelReader = new ExcelReader(metaData, theFile.getInputStream());
		//List list = excelReader.read(2);
		
		String cid = super.getSessionContainer(request).getUserInfo().getCustomId();
		String operator = super.getSessionContainer(request).getUserInfo().getUserid();
		excelReader.readToFcDatabase(1,cid,operator);
		//super.setSessionAttribute(request, "importExcelList", list);
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "导入成功！");
		super.write(outDto.toJson(), response);
		return mapping.findForward(null);
	}

	/**
	 * Excel导入页面初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward importInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.removeSessionAttribute(request, "importExcelList");
		return mapping.findForward("importHouseExcelView");
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
		System.out.println("********"+sb.toString().toUpperCase());
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
