
package org.module.fc.service.impl;

import org.eredlab.g4.bmf.base.BaseServiceImpl;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.module.fc.service.StationService;

public class StationImpl extends BaseServiceImpl implements StationService{

	
	/**
	 * 添加换热站
	 * @param pDto
	 * @return
	 */
	public Dto insertStation(Dto pDto) {
		Dto outDto = new BaseDto();
		Integer countInteger = (Integer) g4Dao.queryForObject("isExistStatCode", pDto);
		if (countInteger.intValue() > 0) {
			outDto.put("failure", new Boolean(true));
			outDto.put("msg", (String)"编号为"+pDto.get("stat_id") +"的换热站已存在，不能添加。");
			return outDto;
		}
		g4Dao.insert("insertStation", pDto);
		g4Dao.insert("updateStationCount", pDto);		
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "换热站添加成功!");
		return outDto;
	}
	/**
	 * 修改换热站
	 * @param pDto
	 * @return
	 */	
	public Dto updateStation(Dto pDto) {
		Dto outDto = new BaseDto();
		g4Dao.update("updateStation", pDto);
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "换热站修改成功!");
		return outDto;
	}
	/**
	 * 删除换热站
	 * @param pDto
	 * @return
	 */	
	public Dto deleteStation(Dto pDto) {
		Dto outDto = new BaseDto();
		Integer countInteger = (Integer) g4Dao.queryForObject("getUseStatCount", pDto);
		if (countInteger.intValue() > 0) {
			outDto.put("success", new Boolean(false));
			outDto.put("msg", (String)pDto.get("stat_code")+(String)pDto.get("stat_name")+" 换热站下有大楼，不能删除！请先删除该换热站下的大楼。");
			return outDto;
		}
		g4Dao.delete("deleteStation", pDto);
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "换热站删除成功!");
		return outDto;
	}

}
