package org.module.sf.service;

import org.eredlab.g4.bmf.base.BaseService;
import org.eredlab.g4.ccl.datastructure.Dto;

public interface CreatPLanService  extends BaseService{
	/**
	 * 生成应收
	 * @param pDto
	 * @return
	 */
	public Dto creatPlan(Dto pDto);
	/**
	 * 删除应收
	 * @param pDto
	 * @return
	 */
	public Dto deletePlan(Dto pDto);
}
