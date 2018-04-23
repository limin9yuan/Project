package com.bootdo.payment.dao;

import com.bootdo.contract.domain.ContractDO;
import com.bootdo.payment.domain.InvoiceDO;
import com.bootdo.project.domain.ProjectDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 开票信息表
 * @author sjr
 * @email 1992lcg@163.com
 * @date 2017-12-05 14:35:41
 */
@Mapper
public interface InvoiceDao {

	InvoiceDO get(String invoiceId);
	
	List<InvoiceDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(InvoiceDO invoice);
	
	int update(InvoiceDO invoice);
	
	int remove(String Invoice_ID);
	
	int batchRemove(String[] invoiceIds);
	
	ContractDO getContractId(String contractId);  
}
