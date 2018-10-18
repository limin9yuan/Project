
package org.module.iw.service;

import org.eredlab.g4.bmf.base.BaseService;
import org.eredlab.g4.ccl.datastructure.Dto;

public interface IntoWebService extends BaseService{
	/**
	 * 收费
	 * @param pDto
	 * @return
	 */
	public Dto intoWeb(Dto inDto);
	
	public Dto updateIntoWeb(Dto inDto);
	
	public Dto deleteWeb(Dto pDto); 
}
