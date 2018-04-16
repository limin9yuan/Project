package com.bootdo.payment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.payment.dao.InvoiceDao;
import com.bootdo.payment.domain.InvoiceDO;
import com.bootdo.payment.service.InvoiceService;



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
		return invoiceDao.save(invoice);
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
	
}