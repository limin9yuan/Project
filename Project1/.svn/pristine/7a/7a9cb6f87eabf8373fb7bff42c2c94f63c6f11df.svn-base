package com.bootdo.contract.service;

import com.bootdo.common.domain.DictDO;
import com.bootdo.common.domain.MainDO;
import com.bootdo.contract.domain.ContractDO;
import com.bootdo.contract.domain.TravelDO;
import com.bootdo.payment.domain.ContractApprovalDO;
import com.bootdo.sales.domain.BusinessDO;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

/**
 * 合同信息表
 * 
 * @author sw
 * @email 1992lcg@163.com
 * @date 2017-11-29 14:50:54
 */
public interface ContractService {
	
	ContractDO get(String contractId);

	List<MainDO> getDataList(Map<String, Object> map);

	List<ContractDO> list(Map<String, Object> map);

	ContractDO view(String contractId);
	
	int count(Map<String, Object> map);
	
	int save(ContractDO contract);
	
	int update(ContractDO contract);
	
	int remove(String contractId);
	
	int batchRemove(String[] contractIds);
	
	List<DictDO> listDic();

	int formUpdate(ContractDO contract);
	
	Map<String, Object> dataImport(File file,long userid) ;
	
	List<ContractDO> getQuery(Map<String, Object> params);
	
	public void export(String[] titles, ServletOutputStream out, List<ContractDO> list);
}
