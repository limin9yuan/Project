package org.module.sf.service;

import org.eredlab.g4.bmf.base.BaseService;
import org.eredlab.g4.ccl.datastructure.Dto;

public interface MinusService extends BaseService{
	/**
	 * 减免应收
	 * @param pDto
	 * @return
	 */
	public Dto minusMoney(Dto pDto);
	/**
	 * 删除减免
	 * @param pDto
	 * @return
	 */
	public Dto deleteMinus(Dto pDto);
}
