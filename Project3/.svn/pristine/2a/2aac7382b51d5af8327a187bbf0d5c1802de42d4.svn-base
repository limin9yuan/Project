package org.module.fc.service.impl;

import org.eredlab.g4.bmf.base.BaseServiceImpl;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.module.fc.service.UserCardService;

public class UserCardImpl extends BaseServiceImpl implements UserCardService{
	

	/**
	 * 添加大楼
	 * @param pDto
	 * @return
	 */
	public Dto userCard(Dto pDto) {
		Dto outDto = new BaseDto();
		g4Dao.insert("insertUserCardLog", pDto);
		System.out.println(pDto);
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "读写器设置成功!");
		return outDto;
	}
	
	public Dto updateCardId(Dto pDto) {
		Dto outDto = new BaseDto();
		g4Dao.update("updateCardId", pDto);
		System.out.println(pDto);
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "用户卡编辑成功!");
		return outDto;
	}
	
	public Dto bantchUpdateVal(Dto pDto) {
		Dto outDto = new BaseDto();
		g4Dao.update("bantchUpdateVal", pDto);
		System.out.println(pDto);
		String strID=(String)pDto.getAsString("strID");
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "批量修改开启度成功!");
		return outDto;
	}
	/**
	 * 更新sf_charge表写卡标识(2)、写卡人、写卡时间
	 * @param pDto
	 * @return
	 */
	public Dto updateSfChargeWriteCardFlag(Dto pDto) {
		Dto outDto = new BaseDto();
		g4Dao.update("updateSfChargeWriteCardFlag", pDto);
		System.out.println(pDto);
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "写卡标识(2)、写卡人、写卡时间更新成功!");
		return outDto;
	}

}
