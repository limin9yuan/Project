package com.bootdo.contract.dao;

import com.bootdo.common.domain.DictDO;
import com.bootdo.contract.domain.TravelDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 出差申请表
 * @author sw
 * @email 1992lcg@163.com
 * @date 2017-11-30 17:44:01
 */
@Mapper
public interface TravelDao {

	TravelDO view(String travelId);

	TravelDO get(String travelId);
	
	List<TravelDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(TravelDO travel);
	
	int update(TravelDO travel);
	
	int remove(String Travel_ID);
	
	int batchRemove(String[] travelIds);
	List<DictDO> listDic(); 
}
