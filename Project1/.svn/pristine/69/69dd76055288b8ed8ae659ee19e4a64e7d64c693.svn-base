package com.bootdo.inner.service;

import com.bootdo.common.domain.DictDO;
import com.bootdo.inner.domain.InnerOrgEmployeeDO;
import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

/**
 * 内部组织机构_员工表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-11-22 13:27:13
 */
public interface InnerOrgEmployeeService {
	
	InnerOrgEmployeeDO get(String employeeId);
	
	List<InnerOrgEmployeeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(InnerOrgEmployeeDO innerOrgEmployee);
	
	int update(InnerOrgEmployeeDO innerOrgEmployee);
	
	int remove(String employeeId);
	String  getname(String employeeId);
	int batchRemove(String[] employeeIds);
	
	List<DictDO> listDic();

	Map<String, Object> dataImport(File file,long userid);
	
	List<InnerOrgEmployeeDO> getQuery(Map<String, Object> params);

	public void export(String[] titles, ServletOutputStream out, List<InnerOrgEmployeeDO> list);
}
