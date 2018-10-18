
package org.module.sf.service;

import org.eredlab.g4.bmf.base.BaseService;
import org.eredlab.g4.ccl.datastructure.Dto;

public interface InvoiceService extends BaseService{
	/**
	 * 添加票据
	 * @param pDto
	 * @return
	 */
	public Dto insertInvoice(Dto pDto);
	/**
	 * 删除票据
	 * @param pDto
	 * @return
	 */
	public Dto deleteInvoice(Dto pDto);
	/**
	 * 作废票据
	 * @param pDto
	 * @return
	 */
	public Dto invalidInvoice(Dto pDto);
	/**
	 * 返还票据
	 * @param pDto
	 * @return
	 */
	public Dto returnInvoice(Dto pDto);
	/**
	 * 领用票据
	 * @param pDto
	 * @return
	 */
	public Dto leadInvoice(Dto pDto);
}
