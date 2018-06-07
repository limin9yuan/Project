package com.bootdo.payment.service;

import com.bootdo.common.domain.DictDO;
import com.bootdo.inner.domain.InnerOrgEmployeeDO;
import com.bootdo.payment.domain.ContractApprovalDO;
import com.bootdo.payment.domain.ProjectExpenditureDO;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

/**
 * 项目收支
 * 
 * @小平
 * @email 1992lcg@163.com
 * @date 2018-2-1
 */

public interface ProjectExpenditureService {

	ProjectExpenditureDO get(String projectId);

	List<ProjectExpenditureDO> list(Map<String, Object> map);

	List<ProjectExpenditureDO> listLaborCost(Map<String, Object> map);

	int count(Map<String, Object> map);

	int countLaborCost(Map<String, Object> map);

	int save(ProjectExpenditureDO projectExpenditure);

	int update(ProjectExpenditureDO projectExpenditure);

	int remove(String projectId);

	int batchRemove(String[] projectIds);

	List<ProjectExpenditureDO> getQuery(Map<String, Object> params);
	
	public void export(String[] titles, ServletOutputStream out, List<ProjectExpenditureDO> list);

	List<DictDO> listDic();
	
	List<DictDO> listDicManager();
  }
