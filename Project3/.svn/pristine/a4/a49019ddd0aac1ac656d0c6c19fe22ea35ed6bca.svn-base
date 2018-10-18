package org.module.sf.service.impl;

import org.eredlab.g4.bmf.base.BaseServiceImpl;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.module.sf.service.BankService;

public class BankImpl extends BaseServiceImpl implements BankService{

	public Dto bank(Dto pDto) {
		// TODO 自动生成方法存根
		Dto prcDto = new BaseDto();
		prcDto.put("b_id", pDto.getAsString("b_id"));
		prcDto.put("invalid_invoice", pDto.getAsString("invalid_invoice"));
		prcDto.put("rollback_reason", pDto.getAsString("rollback_reason"));
		prcDto.put("rollback_remark", pDto.getAsString("rollback_remark"));
		prcDto.put("operator", pDto.getAsString("operator"));
		prcDto.put("cid", pDto.getAsString("cid"));
		System.out.println(pDto.getAsString("operator"));
		System.out.println( pDto.getAsString("b_id"));
		System.out.println(pDto.getAsString("invalid_invoice"));
		System.out.println(pDto.getAsString("rollback_reason"));
		System.out.println( pDto.getAsString("rollback_remark"));
		System.out.println(pDto.getAsString("cid"));
		g4Dao.callPrc("bank", prcDto);
		return prcDto;
	}

}
