package org.module.sf.service;

import org.eredlab.g4.ccl.datastructure.Dto;

public interface RollbackService {
	/**
	 * 冲账
	 * @param pDto
	 * @return
	 */
	public Dto rollback(Dto pDto);
}
