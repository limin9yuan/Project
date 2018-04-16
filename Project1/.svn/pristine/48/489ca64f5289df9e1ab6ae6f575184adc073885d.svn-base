package com.bootdo.sales.dao;

import com.bootdo.common.domain.DictDO;
import com.bootdo.sales.domain.CompetitorDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 竞争对手信息表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-12-11 14:31:23
 */
@Mapper
public interface CompetitorDao {

	CompetitorDO get(String complaintId);

	List<CompetitorDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(CompetitorDO competitor);

	int update(CompetitorDO competitor);

	int remove(String Complaint_ID);

	int batchRemove(String[] complaintIds);

	List<DictDO> listDic();

	List<DictDO> listDicxmbh();
}
