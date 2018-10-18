package org.module.sf.service;

import org.eredlab.g4.bmf.base.BaseService;
import org.eredlab.g4.ccl.datastructure.Dto;

public interface ImportQfService extends BaseService{
	/**
	 * 导入欠费
	 * @param pDto
	 * @return
	 */
	public Dto importQfExcel(Dto pDto);

}
