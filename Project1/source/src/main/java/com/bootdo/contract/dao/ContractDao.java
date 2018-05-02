package com.bootdo.contract.dao;

import com.bootdo.common.domain.DictDO;
import com.bootdo.common.domain.MainDO;
import com.bootdo.contract.domain.ContractDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 合同信息表
 * @author sw
 * @email 1992lcg@163.com
 * @date 2017-11-29 14:50:54
 */
@Mapper
public interface ContractDao {

    ContractDO getMission(String employeeId);

	ContractDO get(String contractId);

	List<MainDO> getDataList(Map<String,Object> map);

	List<MainDO> getFinanceDataList(Map<String,Object> map);

	ContractDO view(String contractId);

	List<ContractDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ContractDO contract);
	
	int update(ContractDO contract);
	
	int remove(String Contract_ID);
	
	int batchRemove(String[] contractIds);
	
	List<DictDO> listDic();
}
