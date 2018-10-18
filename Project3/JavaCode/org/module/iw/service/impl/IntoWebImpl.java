package org.module.iw.service.impl;

import org.eredlab.g4.bmf.base.BaseServiceImpl;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.module.iw.service.IntoWebService;

public class IntoWebImpl extends BaseServiceImpl implements IntoWebService{
	/**
	 * 添加并网用户
	 * @param pDto
	 * @return
	 */	
	public Dto intoWeb(Dto pDto) {
		Dto outDto = new BaseDto();	
		Integer countInteger = (Integer) g4Dao.queryForObject("getIntoWebCodeCount", pDto);
		if (countInteger.intValue() > 0) {
			outDto.put("failure", new Boolean(true));
			outDto.put("msg", (String)"编号为"+pDto.get("web_code") +"的"+pDto.get("web_type")+"入网已存在，请勿重复入网。");
			return outDto;
		}
		if("用户".equals(pDto.get("web_type"))){
			g4Dao.update("intoWeb_updatehouse", pDto);
			g4Dao.insert("intoWeb", pDto);
		}else if("大楼".equals(pDto.get("web_type"))){
			g4Dao.insert("intoWeb_bu", pDto);
		}else if("小区".equals(pDto.get("web_type"))){
			g4Dao.insert("intoWeb_com", pDto);
		}/*else{
			g4Dao.update("intoWeb_updatehouse_byCB", pDto);
		}*/
		outDto.put("p_item_code", "B");
		outDto.put("p_charge_month", pDto.getAsString("intoweb_year"));
		outDto.put("p_range", "'"+pDto.getAsString("web_code")+"'");
		outDto.put("p_returnNumber", "0");
		outDto.put("p_operator", pDto.getAsString("operator"));
		g4Dao.callPrc("createPlan", outDto);
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "并网"+pDto.get("web_type")+"添加成功!");
		return outDto;
	}
	/**
	 * 修改并网用户
	 * @param pDto
	 * @return
	 */	
	public Dto updateIntoWeb(Dto pDto) {
		Dto outDto = new BaseDto();	
		g4Dao.update("intoWeb_updatehouse", pDto);
		g4Dao.update("updateIntoWeb", pDto);
		
		outDto.put("p_item_code", "B");
		outDto.put("p_charge_month", pDto.getAsString("intoweb_year"));
		outDto.put("p_range", "'"+pDto.getAsString("web_code")+"'");
		outDto.put("p_returnNumber", "0");
		outDto.put("p_operator", pDto.getAsString("operator"));
		g4Dao.callPrc("createPlan", outDto);
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "并网"+pDto.get("web_type")+"修改成功!");
		return outDto;
	}
	/**
	 * 删除并网用户
	 * @param pDto
	 * @return
	 */	
	public Dto deleteWeb(Dto pDto) {
		Dto outDto = new BaseDto();
		
		Integer countInteger = (Integer) g4Dao.queryForObject("haveWebcharge", pDto);
		if (countInteger.intValue() > 0) {
			outDto.put("success", new Boolean(false));
			outDto.put("msg", (String)pDto.get("web_name")+",该用户已经交费入网费,不能删除入网记录，请先冲账!");
			return outDto;
		}

		g4Dao.delete("deleteWeb", pDto);	
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "并网用户删除成功!");
		return outDto;
	}
}
