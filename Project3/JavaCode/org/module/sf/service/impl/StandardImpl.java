package org.module.sf.service.impl;

import org.eredlab.g4.arm.util.ArmConstants;
import org.eredlab.g4.arm.util.idgenerator.IDHelper;
import org.eredlab.g4.bmf.base.BaseServiceImpl;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.ccl.util.G4Utils;
import org.module.sf.service.StandardService;

public class StandardImpl extends BaseServiceImpl implements StandardService {
	
	/*
	 * 新增收费标准
	 * 
	 * @param pDto
	 * @return
	 */

	@Override
	public Dto saveStandard(Dto pDto) {
		// TODO Auto-generated method stub
		Dto outDto = new BaseDto();
		
		Dto checkDto = (BaseDto) g4Dao.queryForObject("checkStandardByIndex", pDto);
		if (G4Utils.isNotEmpty(checkDto)) {
			outDto.put("success", new Boolean(false));
			outDto.put("msg", "标准编号已存在，请重新录入.");
			return outDto;
		} else {
			g4Dao.insert("insertStandard", pDto);
			outDto.put("success", new Boolean(true));
		}
		return outDto;
	}
	
	

	/**
	 * 删除收费标准
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto deleteStandard(Dto pDto) {
		Dto dto = new BaseDto();
		String[] arrChecked = pDto.getAsString("strChecked").split(",");
		for (int i = 0; i < arrChecked.length; i++) {
			dto.put("standard_key", arrChecked[i]);
			Dto chechkDto = (BaseDto) g4Dao.queryForObject("getStandardCountFromHouse", dto);
			if (chechkDto.getAsInteger("cnt")==0) {
				g4Dao.delete("deleteStandard", dto);
			}
		}
		return null;
	}

	/**
	 * 修改收费标准
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto updateStandard(Dto pDto) {
		g4Dao.update("updateStandard", pDto);
		return null;
	}
	
	/*
	 * 设置当前年度
	 * 
	 * @param pDto
	 * @return
	 */

	@Override
	public Dto getCurrentYear() {	
		Dto outDto = (BaseDto) g4Dao.queryForObject("getCurrentYear", null);
		return outDto;
	}
	
	/*
	 * 设置当前年度
	 * 
	 * @param pDto
	 * @return
	 */

	@Override
	public Dto saveCurrentYear(Dto pDto) {
		// TODO Auto-generated method stub
		Dto outDto = new BaseDto();
		g4Dao.update("updateCurrentYear", pDto);
		outDto.put("success", new Boolean(true));
		return outDto;
	}



	@Override
	public Dto copyStandard(Dto pDto) {
		// TODO Auto-generated method stub
		Dto outDto = new BaseDto();
		g4Dao.insert("copyStandard", pDto);
		outDto.put("success", new Boolean(true));
		return outDto;
	}
	
	
}
