package org.module.sf.service.impl;

import org.eredlab.g4.bmf.base.BaseServiceImpl;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.module.sf.service.ImportQfService;

public class ImportQfImpl extends BaseServiceImpl implements ImportQfService{
	
	public Dto importQfExcel(Dto inDto) {
		// TODO 自动生成方法存根
		Dto prcDto = new BaseDto();
		//prcDto.put("p_item_code", inDto.getAsString("p_item_code"));
		prcDto.put("p_charge_month", inDto.getAsString("p_charge_month"));
		//prcDto.put("p_range", inDto.getAsString("p_range"));
		//prcDto.put("owner_name", inDto.getAsString("owner_name"));
		//prcDto.put("p_returnNumber", inDto.getAsString("p_returnNumber"));
		prcDto.put("p_operator", inDto.getAsString("p_operator"));
		g4Dao.callPrc("importQfExcel", prcDto);
		return prcDto;
	}

}
