
package org.module.fc.service;

import org.eredlab.g4.bmf.base.BaseService;
import org.eredlab.g4.ccl.datastructure.Dto;

/**
 * 换热站业务接口
 * @author smile
 * @since 2011-04-16
 */
public interface StationService extends BaseService {
	/**
	 * 添加换热站
	 * @param pDto
	 * @return
	 */
	public Dto insertStation(Dto pDto);
	
	/**
	 * 修改换热站
	 * @param pDto
	 * @return
	 */
	public Dto updateStation(Dto pDto);
	
	/**
	 * 删除换热站
	 * @param pDto
	 * @return
	 */
	public Dto deleteStation(Dto pDto);
	
	
}
