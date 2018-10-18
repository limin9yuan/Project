package org.module.sf.service.impl;

import org.eredlab.g4.bmf.base.BaseServiceImpl;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.module.sf.service.MinusService;

public class MinusImpl extends BaseServiceImpl implements MinusService{

	public Dto minusMoney(Dto inDto) {
		
		// TODO 自动生成方法存根
		Dto prcDto = new BaseDto();
		prcDto.put("p_id", inDto.getAsString("p_id"));
		prcDto.put("minus_type", inDto.getAsString("minus_type"));
		prcDto.put("this_minus_money", inDto.getAsBigDecimal("this_minus_money"));
		prcDto.put("this_minus_rate", inDto.getAsBigDecimal("this_minus_rate"));
		prcDto.put("minus_kind", inDto.getAsString("minus_kind"));
		prcDto.put("minus_days", inDto.getAsBigDecimal("minus_days"));
		prcDto.put("minus_area", inDto.getAsBigDecimal("minus_area"));
		prcDto.put("minus_reason", inDto.getAsString("minus_reason"));
		prcDto.put("remark", inDto.getAsString("remark"));
		prcDto.put("operator", inDto.getAsString("operator"));
		prcDto.put("p_cid", inDto.getAsString("p_cid"));
		g4Dao.callPrc("minusMoney", prcDto);
		return prcDto;
	}

	public Dto deleteMinus(Dto inDto) {
//		 TODO 自动生成方法存根
		Dto prcDto = new BaseDto();
		prcDto.put("m_id", inDto.getAsString("m_id"));
		prcDto.put("charge_month", inDto.getAsString("charge_month"));
		prcDto.put("p_range", inDto.getAsString("p_range"));
		prcDto.put("operator", inDto.getAsString("operator"));
		prcDto.put("p_cid", inDto.getAsString("p_cid"));
		g4Dao.callPrc("deleteMinus", prcDto);
		return prcDto;
	}
}