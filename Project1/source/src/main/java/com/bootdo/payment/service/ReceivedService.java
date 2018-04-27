package com.bootdo.payment.service;

import com.bootdo.payment.domain.ReceivedDO;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 回款信息表 
 * 开发者：小平
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-2-24
 */

public interface ReceivedService {
	
	ReceivedDO get(String receivedId);
	
	List<ReceivedDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int countNoPriceNumberExamine(Map<String, Object>map);
	
	int countPriceNumberExamine(Map<String, Object>map);
	
	List<ReceivedDO> noPriceNumberExamine(Map<String, Object>map);
	
	List<ReceivedDO> priceNumberExamine(Map<String, Object>map);
	
	List<ReceivedDO> moneyNumber(Map<String, Object>map);
	
	List<ReceivedDO> sumReceivedPrice(Map<String, Object>map);
	
	List<ReceivedDO> reimbursementRate(Map<String, Object>map);
	
	int save(ReceivedDO received);
	
	int update(ReceivedDO received);
	
	int remove(String receivedId);
	
	int batchRemove(String[] receivedIds);
	
	Map<String, Object> dataImport(File file,long userid) ;
}
