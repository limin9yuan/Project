package org.module.sf.service.impl;

import org.eredlab.g4.arm.util.ArmConstants;
import org.eredlab.g4.arm.util.idgenerator.IDHelper;
import org.eredlab.g4.bmf.base.BaseServiceImpl;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.ccl.util.G4Utils;
import org.module.sf.service.DiscountService;

public class DiscountImpl extends BaseServiceImpl implements DiscountService {
	
	/*
	 * 新增优惠设置
	 * 
	 * @param pDto
	 * @return
	 */

	@Override
	public Dto saveDiscount(Dto pDto) {
		// TODO Auto-generated method stub
		Dto outDto = new BaseDto();
		
		Dto checkDto = (BaseDto) g4Dao.queryForObject("checkDiscountByIndex", pDto);
		if (G4Utils.isNotEmpty(checkDto)) {
			outDto.put("success", new Boolean(false));
			outDto.put("msg", "优惠已存在，请重新录入.");
			return outDto;
		} else {
			g4Dao.insert("insertDiscount", pDto);
			outDto.put("success", new Boolean(true));
		}
		return outDto;
	}
	
	

	/**
	 * 删除优惠设置
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto deleteDiscount(Dto pDto) {
		Dto dto = new BaseDto();
		String[] arrChecked = pDto.getAsString("strChecked").split(",");
		for (int i = 0; i < arrChecked.length; i++) {
			dto.put("discount_key", arrChecked[i]);
			Dto chechkDto = (BaseDto) g4Dao.queryForObject("getDiscountCountFromHouse", dto);
			if (chechkDto.getAsInteger("cnt")==0) {
				g4Dao.delete("deleteDiscount", dto);
			}
		}
		return null;
	}

	/**
	 * 修改优惠设置
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto updateDiscount(Dto pDto) {
		g4Dao.update("updateDiscount", pDto);
		return null;
	}


}
