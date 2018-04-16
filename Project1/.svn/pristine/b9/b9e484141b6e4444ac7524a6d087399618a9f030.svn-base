package com.bootdo.payment.dao;

import com.bootdo.common.domain.DictDO;
import com.bootdo.payment.domain.ContractApprovalDO;
import com.bootdo.payment.domain.ProjectExpenditureDO;

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
public interface ProjectExpenditureDao {

	ProjectExpenditureDO get(String projectId);

	List<ProjectExpenditureDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(ProjectExpenditureDO projectExpenditure);

	int update(ProjectExpenditureDO projectExpenditure);

	int remove(String projectId);

	int batchRemove(String[] projectIds);

	List<DictDO> listDic();

	List<DictDO> listDicManager();
}
