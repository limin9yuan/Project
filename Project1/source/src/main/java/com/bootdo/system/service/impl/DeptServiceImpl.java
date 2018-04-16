package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.common.domain.DictDO;
import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.BuildTree;
import com.bootdo.sales.dao.CompanyCustomerDao;
import com.bootdo.system.dao.DeptDao;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.service.DeptService;



@Service
public class DeptServiceImpl implements DeptService {
	@Autowired
	private DeptDao sysDeptMapper;

//	@Autowired
//	private DeptDao deptDao;

	@Override
	public DeptDO get(Long deptId){
		return sysDeptMapper.get(deptId);
	}

	@Override
	public List<DeptDO> list(Map<String, Object> map){
		return sysDeptMapper.list(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return sysDeptMapper.count(map);
	}

	@Override
	public int save(DeptDO sysDept){
		return sysDeptMapper.save(sysDept);
	}

	@Override
	public int update(DeptDO sysDept){
		return sysDeptMapper.update(sysDept);
	}

	@Override
	public int remove(Long deptId){
		return sysDeptMapper.remove(deptId);
	}

	@Override
	public int batchRemove(Long[] deptIds){
		return sysDeptMapper.batchRemove(deptIds);
	}

	@Override
	public Tree<DeptDO> getTree() {
		List<Tree<DeptDO>> trees = new ArrayList<Tree<DeptDO>>();
		List<DeptDO> sysDepts = sysDeptMapper.list(new HashMap<String,Object>(16));
		for (DeptDO sysDept : sysDepts) {
			Tree<DeptDO> tree = new Tree<DeptDO>();
			tree.setId(sysDept.getDeptId().toString());
			tree.setParentId(sysDept.getParentId().toString());
			tree.setText(sysDept.getName());
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", true);
			tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<DeptDO> t = BuildTree.build(trees);
		return t;
	}
//	@Override
//	public List<DeptDO> getTreeList(Map<String, Object> params) {
//
//		List<DeptDO> sysDepts = sysDeptMapper.customerList(params);
//
//		return sysDepts;
//	}
	
	
	@Override	
	public List<DictDO> listDic(){
		return sysDeptMapper.listDic();
	}

}