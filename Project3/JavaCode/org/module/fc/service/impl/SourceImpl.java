package org.module.fc.service.impl;

import org.eredlab.g4.bmf.base.BaseServiceImpl;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.module.fc.service.SourceService;

public class SourceImpl extends BaseServiceImpl implements SourceService{

	
	/**
	 * 添加热源
	 * @param pDto
	 * @return
	 */
	public Dto insertSource(Dto pDto) {
		Dto outDto = new BaseDto();
		Integer countInteger = (Integer) g4Dao.queryForObject("isExistSourceCode", pDto);
		if (countInteger.intValue() > 0) {
			outDto.put("failure", new Boolean(true));
			outDto.put("msg", (String)"编号为"+pDto.get("source_id") +"的热源已存在，不能添加。");
			return outDto;
		}
		g4Dao.insert("insertSource", pDto);
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "热源添加成功!");
		return outDto;
	}
	/**
	 * 修改热源
	 * @param pDto
	 * @return
	 */	
	public Dto updateSource(Dto pDto) {
		Dto outDto = new BaseDto();
		g4Dao.update("updateSource", pDto);
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "热源修改成功!");
		return outDto;
	}
	/**
	 * 删除热源
	 * @param pDto
	 * @return
	 */	
	public Dto deleteSource(Dto pDto) {
		Dto outDto = new BaseDto();
		Integer countInteger = (Integer) g4Dao.queryForObject("getSourceStatCount", pDto);
		if (countInteger.intValue() > 0) {
			outDto.put("success", new Boolean(false));
			outDto.put("msg", (String)pDto.get("source_id")+" 热源下有换热站，不能删除！请先删除该热源下的换热站。");
			return outDto;
		}
		g4Dao.delete("deleteSource", pDto);
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "热源删除成功!");
		return outDto;
	}


}
