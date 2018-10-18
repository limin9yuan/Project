package org.module.fc.service;

import org.eredlab.g4.bmf.base.BaseService;
import org.eredlab.g4.ccl.datastructure.Dto;

public interface HouseService extends BaseService {
	/**
	 * 添加房间
	 * @param pDto
	 * @return
	 */
	public Dto insertHouse(Dto pDto);
	
	/**
	 * 修改房间
	 * @param pDto
	 * @return
	 */
	public Dto updateHouse(Dto pDto);
	
	/**
	 * 删除小区
	 * @param pDto
	 * @return
	 */
	public Dto deleteHouse(Dto pDto);
	
	public Dto batchDeleteHouse(Dto pDto);
	/**
	 * 添加房间面积2
	 * @param pDto
	 * @return
	 */
	public Dto insertArea(Dto pDto) ;
	/**
	 * 导入房间错误数据
	 * @param pDto
	 * @return
	 */
	public Dto importErrorHouse(Dto pDto);
	/**
	 * 修改面积
	 * @param pDto
	 * @return
	 */
	public Dto updateArea(Dto pDto);
	/**
	 * 添加面积
	 * @param pDto
	 * @return
	 */
	public Dto insertAreas(Dto pDto);
	/**
	 * 删除面积
	 * @param pDto
	 * @return
	 */
	public Dto deleteAreaSingle(Dto pDto);
	/**
	 * 导入阀门号的房间错误数据
	 * @param pDto
	 * @return
	 */
	public Dto importErrorFamen(Dto pDto);
	/**
	 * 添加阀门号，更新阀门号的房间数据
	 * @param pDto
	 * @return
	 */
	public Dto updateHouseFamen(Dto pDto);
}
