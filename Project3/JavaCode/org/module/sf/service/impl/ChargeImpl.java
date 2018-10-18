package org.module.sf.service.impl;

import org.eredlab.g4.arm.util.idgenerator.IDHelper;
import org.eredlab.g4.bmf.base.BaseServiceImpl;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.module.sf.service.ChargeService;

public class ChargeImpl  extends BaseServiceImpl implements ChargeService{
	/*收费*/
	public Dto charge(Dto inDto) {
		// TODO 自动生成方法存根
		
		Dto prcDto = new BaseDto();
		prcDto.put("b_id", inDto.getAsString("b_id"));
		prcDto.put("p_id", inDto.getAsString("p_id"));
		prcDto.put("house_code", inDto.getAsString("house_code"));
		prcDto.put("address", inDto.getAsString("address"));
		prcDto.put("work_unit", inDto.getAsString("work_unit"));
		prcDto.put("telphone", inDto.getAsString("telphone"));
		prcDto.put("invoice_number", inDto.getAsString("invoice_number"));
		prcDto.put("invoice_code", inDto.getAsString("invoice_code"));
		
		prcDto.put("price", inDto.getAsBigDecimal("price"));
		prcDto.put("charge_area", inDto.getAsBigDecimal("charge_area"));
		prcDto.put("pay_mode", inDto.getAsString("pay_mode"));
		prcDto.put("payman", inDto.getAsString("payman"));
		prcDto.put("paydate", inDto.getAsString("paydate"));
		prcDto.put("real_charge", inDto.getAsBigDecimal("real_charge"));
		prcDto.put("delzero", inDto.getAsBigDecimal("delzero"));
		
		prcDto.put("billman", inDto.getAsString("billman"));
		prcDto.put("billdate", inDto.getAsString("billdate"));
		prcDto.put("begin_prepay", inDto.getAsBigDecimal("begin_prepay"));
		prcDto.put("end_prepay", inDto.getAsBigDecimal("end_prepay"));
		prcDto.put("use_prepay", inDto.getAsBigDecimal("use_prepay"));
		prcDto.put("add_prepay", inDto.getAsBigDecimal("add_prepay"));
		
		prcDto.put("latefee_enddate", inDto.getAsString("latefee_enddate"));
		prcDto.put("latefee_realaccount", inDto.getAsBigDecimal("latefee_realaccount"));
		prcDto.put("latefee_charge", inDto.getAsBigDecimal("latefee_charge"));
		prcDto.put("minus_reason", inDto.getAsString("minus_reason"));
		prcDto.put("remark", inDto.getAsString("remark"));
		prcDto.put("operator", inDto.getAsString("operator"));		
        
		System.out.println(inDto);
		Dto outDto = new BaseDto();
		outDto.put("userCardVal", inDto.get("usercardval"));
		outDto.put("code", inDto.getAsString("house_code"));
		outDto.put("operator", inDto.getAsString("operator"));		
		g4Dao.update("updateVal", outDto);
		System.out.println(outDto);
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "批量修改开启度成功!");
		System.out.println(prcDto);
		g4Dao.callPrc("charge", prcDto);
		return prcDto;
	}
	
	
	/**
	 * 添加欠费
	 * @param pDto
	 * @return
	 */
	public Dto insertQf(Dto pDto) {
		Dto outDto = new BaseDto();
		Integer countInteger = (Integer) g4Dao.queryForObject("haveQfData", pDto);
		if (countInteger.intValue() > 0) {
			outDto.put("success", new Boolean(false));
			outDto.put("msg", (String)"编号为"+pDto.get("house_code") +"的用户"+pDto.get("charge_month")+"年度欠费已存在，未导入。");
			return outDto;
		}
		g4Dao.insert("insertQf", pDto);
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "房间添加成功!");
		return outDto;
	}

	public Dto importErrorQf(Dto pDto) {
		Dto outDto = new BaseDto();	
		g4Dao.insert("importErrorQf", pDto);
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "用户添加成功!");
		return outDto;
	}
}
