package com.bootdo.contract.dao;

import com.bootdo.common.domain.DictDO;
import com.bootdo.contract.domain.PayoutDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 请款申请表
 * @author sw
 * @email 1992lcg@163.com
 * @date 2017-11-30 16:36:08
 */
@Mapper
public interface PayoutDao {

	PayoutDO get(String payoutId);
	
	List<PayoutDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(PayoutDO payout);
	
	int update(PayoutDO payout);
	
	int remove(String Payout_ID);
	
	int batchRemove(String[] payoutIds);
	List<DictDO> listDic();
}
