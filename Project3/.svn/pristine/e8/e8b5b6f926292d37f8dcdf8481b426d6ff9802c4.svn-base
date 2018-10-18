package org.module.sf.service.impl;

import org.eredlab.g4.bmf.base.BaseServiceImpl;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.module.sf.service.InvoiceService;

public class InvoiceImpl extends BaseServiceImpl implements InvoiceService{

	public Dto insertInvoice(Dto inDto) {
		
		// TODO 自动生成方法存根
		Dto prcDto = new BaseDto();
		prcDto.put("invoice_number", inDto.getAsString("invoice_number"));
		prcDto.put("invoice_code", inDto.getAsString("invoice_code"));
		prcDto.put("invoice_account", inDto.getAsBigDecimal("invoice_account"));
		prcDto.put("item_code", inDto.getAsString("item_code"));
		prcDto.put("lead_man", inDto.getAsString("lead_man"));
		prcDto.put("invoice_bigtype", inDto.getAsString("invoice_bigtype"));
		prcDto.put("invoice_type", inDto.getAsString("invoice_type"));
		prcDto.put("book_code", inDto.getAsString("book_code"));
		prcDto.put("operator", inDto.getAsString("operator"));
		prcDto.put("p_cid", inDto.getAsString("cid"));
		g4Dao.callPrc("insertInvoice", prcDto);
		return prcDto;
	}
	/*删除票据*/
	public Dto deleteInvoice(Dto inDto) {
     	Dto outDto = new BaseDto();
		g4Dao.delete("deleteInvoice", inDto);
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "删除票据成功!");
		return outDto;
	}
	/*作废票据*/
	public Dto invalidInvoice(Dto inDto) {
     	Dto outDto = new BaseDto();
		g4Dao.update("invalidInvoice", inDto);
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "作废票据成功!");
		return outDto;
	}
	/*返还票据*/
	public Dto returnInvoice(Dto inDto) {
     	Dto outDto = new BaseDto();
		g4Dao.update("returnInvoice", inDto);
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "返还票据成功!");
		return outDto;
	}
	/*领用票据*/
	public Dto leadInvoice(Dto inDto) {
     	Dto outDto = new BaseDto();
		g4Dao.update("leadInvoice", inDto);
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "领用票据成功!");
		return outDto;
	}
}