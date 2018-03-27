package com.bootdo.payment.dao;

import com.bootdo.common.domain.DictDO;
import com.bootdo.payment.domain.ContractApprovalDO;
import com.bootdo.payment.domain.ProjectExpenditureDO;
import com.bootdo.payment.domain.SummaryOfIncomeAndExpenditureDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 项目收支
 * 
 * @小平
 * @email 1992lcg@163.com
 * @date 2018-2-1
 */

@Mapper
public interface SummaryOfIncomeAndExpenditureDao {

	SummaryOfIncomeAndExpenditureDO get(String projectId);

	List<SummaryOfIncomeAndExpenditureDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(SummaryOfIncomeAndExpenditureDO summaryOfIncomeAndExpenditure);

	int update(SummaryOfIncomeAndExpenditureDO summaryOfIncomeAndExpenditure);

	int remove(String projectId);

	int batchRemove(String[] projectIds);

	List<DictDO> listDic();

	List<DictDO> listDicManager();
}
