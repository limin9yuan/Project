package com.bootdo.payment.dao;
import com.bootdo.common.domain.DictDO;
import com.bootdo.payment.domain.ContractApprovalDO;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 合同审批
 * 小平
 * @email 1992lcg@163.com
 * @date 2018-1-29
 */
@Mapper
public interface ContractApprovalDao {
	
	ContractApprovalDO get(String contractId);
	
	List<ContractApprovalDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ContractApprovalDO contractApproval);
	
	int update(ContractApprovalDO contractApproval);
	
	int remove(String contractId);
	
	int batchRemove(String[] contractIds);
	
	List<DictDO> listDic();
	
	
}
