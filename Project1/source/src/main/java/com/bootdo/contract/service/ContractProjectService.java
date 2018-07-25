package com.bootdo.contract.service;

import com.bootdo.contract.domain.ContractProjectDO;

import java.util.List;
import java.util.Map;

/**
 * 合同项目表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-07-25 13:58:59
 */
public interface ContractProjectService {
	
	ContractProjectDO get(Integer contractProjectId);
	
	List<ContractProjectDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ContractProjectDO project);
	
	int update(ContractProjectDO project);
	
	int remove(Integer contractProjectId);
	
	int batchRemove(Integer[] contractProjectIds);
}
