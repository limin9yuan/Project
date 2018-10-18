package org.module.fc.service.impl;


import org.eredlab.g4.bmf.base.BaseServiceImpl;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.module.fc.service.BuildingService;

public class BuildingImpl extends BaseServiceImpl implements BuildingService{

	
	/**
	 * 添加大楼
	 * @param pDto
	 * @return
	 */
	public Dto insertBuilding(Dto pDto) {
		Dto outDto = new BaseDto();
		Integer countInteger = (Integer) g4Dao.queryForObject("queryBuildingNum", pDto);
		if (countInteger.intValue() > 0) {
			outDto.put("failure", new Boolean(true));
			outDto.put("msg", (String)pDto.get("community_code")+"小区下，编号为"+pDto.get("building_code") +"的大楼已存在，不能添加。");
			return outDto;
		}
		g4Dao.insert("insertBuilding", pDto);
		g4Dao.insert("updateBuildingCount", pDto);		
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "大楼添加成功!");
		return outDto;
	}
	/**
	 * 修改大楼
	 * @param pDto
	 * @return
	 */	
	public Dto updateBuilding(Dto pDto) {
		Dto outDto = new BaseDto();
		g4Dao.update("updateBuilding", pDto);
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "大楼修改成功!");
		return outDto;
	}
	/**
	 * 删除大楼
	 * @param pDto
	 * @return
	 */	
	public Dto deleteBuilding(Dto pDto) {
		Dto outDto = new BaseDto();
		//Integer countInteger = (Integer) g4Dao.queryForObject("getHouseCount", pDto);
		Integer countInteger = (Integer) g4Dao.queryForObject("getChaHouseCount", pDto);
		if (countInteger.intValue() > 0) {
			outDto.put("success", new Boolean(false));
			//outDto.put("msg", (String)pDto.get("building_code")+(String)pDto.get("building_name")+" 大楼下有用户，不能删除！请先删除该大楼下的用户。");
			outDto.put("msg", (String)pDto.get("building_code")+(String)pDto.get("building_name")+" 大楼下的房间有应收，不能删除！请先删除该大楼下用户的应收。");
			return outDto;
		}
		g4Dao.delete("batchDeleteChaHouse", pDto);
		g4Dao.delete("batchDeleteChaOwner", pDto);
		g4Dao.delete("batchDeleteChaArea", pDto);
		g4Dao.delete("deleteBuilding", pDto);
		g4Dao.insert("updateBuildingCount", pDto);	
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "大楼删除成功!");
		return outDto;
	}


}
