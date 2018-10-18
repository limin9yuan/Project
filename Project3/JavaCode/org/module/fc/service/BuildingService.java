package org.module.fc.service;

import org.eredlab.g4.bmf.base.BaseService;
import org.eredlab.g4.ccl.datastructure.Dto;

/**
 * 小区业务接口
 * @author smile
 * @since 2011-04-16
 */
public interface BuildingService extends BaseService {
	/**
	 * 添加小区
	 * @param pDto
	 * @return
	 */
	public Dto insertBuilding(Dto pDto);
	
	/**
	 * 修改小区
	 * @param pDto
	 * @return
	 */
	public Dto updateBuilding(Dto pDto);
	
	/**
	 * 删除小区
	 * @param pDto
	 * @return
	 */
	public Dto deleteBuilding(Dto pDto);
	
	
}
