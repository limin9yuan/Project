package com.bootdo.payment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.contract.domain.ContractDO;
import com.bootdo.payment.dao.InvoiceDao;
import com.bootdo.payment.domain.InvoiceDO;
import com.bootdo.payment.service.InvoiceService;
import com.bootdo.project.domain.ProjectDO;



@Service
public class InvoiceServiceImpl implements InvoiceService {
	@Autowired
	private InvoiceDao invoiceDao;
	
	@Override
	public InvoiceDO get(String invoiceId){
		return invoiceDao.get(invoiceId);
	}
	
	@Override
	public List<InvoiceDO> list(Map<String, Object> map){
		return invoiceDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return invoiceDao.count(map);
	}
	
	@Override
	public int save(InvoiceDO invoice){
		invoiceDao.save(invoice);
		String invoiceId = invoice.getInvoiceId();
		return Integer.parseInt(invoiceId);
	}
	
	@Override
	public int update(InvoiceDO invoice){
		return invoiceDao.update(invoice);
	}
	
	@Override
	public int remove(String invoiceId){
		return invoiceDao.remove(invoiceId);
	}
	
	@Override
	public int batchRemove(String[] invoiceIds){
		return invoiceDao.batchRemove(invoiceIds);
	}
	@Override
	public ContractDO getContractId(String contractId) {
		return invoiceDao.getContractId(contractId);
	}

	@Override
	public int updateInvoiceAttachment(InvoiceDO invoice) {
		// TODO Auto-generated method stub
		return invoiceDao.updateInvoiceAttachment(invoice);
	}	
	
}
