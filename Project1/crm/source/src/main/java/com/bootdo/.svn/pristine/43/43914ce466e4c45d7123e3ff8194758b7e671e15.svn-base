package com.bootdo.payment.service;

import com.bootdo.common.domain.DictDO;
import com.bootdo.inner.domain.InnerOrgEmployeeDO;
import com.bootdo.payment.domain.ContractApprovalDO;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

/**
 * 合同审批 
 * 小平
 * @email 1992lcg@163.com
 * @date 2018-1-29
 */

public interface ContractApprovalService {

	ContractApprovalDO get(String contractId);

	List<ContractApprovalDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(ContractApprovalDO contractApproval);

	int update(ContractApprovalDO contractApproval);

	int remove(String contractId);

	int batchRemove(String[] contractIds);

	List<DictDO> listDic();Map<String, Object> dataImport(File file,long userid);
	
	List<ContractApprovalDO> getQuery(Map<String, Object> params);
	
	public void export(String[] titles, ServletOutputStream out, List<ContractApprovalDO> list);
  }
