package com.bootdo.system.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.BuildTree;
import com.bootdo.system.dao.MenuDao;
import com.bootdo.system.dao.RoleMenuDao;
import com.bootdo.system.domain.MenuDO;
import com.bootdo.system.service.MenuService;

import javassist.expr.NewArray;

@SuppressWarnings("AlibabaRemoveCommentedCode")
@Service
public class MenuServiceImpl implements MenuService {
	@Autowired
	MenuDao menuMapper;
	@Autowired
	RoleMenuDao roleMenuMapper;

	/**
	 * @param //用户ID
	 * @return 树形菜单
	 */
	@Cacheable
	@Override
	public Tree<MenuDO> getSysMenuTree(Long id) {
		List<Tree<MenuDO>> trees = new ArrayList<Tree<MenuDO>>();
		List<MenuDO> menuDOs = menuMapper.listMenuByUserId(id);
		for (MenuDO sysMenuDO : menuDOs) {
			Tree<MenuDO> tree = new Tree<MenuDO>();
			tree.setId(sysMenuDO.getMenuId().toString());
			tree.setParentId(sysMenuDO.getParentId().toString());
			tree.setText(sysMenuDO.getName());
			Map<String, Object> attributes = new HashMap<>(16);
			attributes.put("url", sysMenuDO.getUrl());
			attributes.put("icon", sysMenuDO.getIcon());
			tree.setAttributes(attributes);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<MenuDO> t = BuildTree.build(trees);
		return t;
	}

	@Override
	public List<MenuDO> list() {
		List<MenuDO> menus = menuMapper.list(new HashMap<String,Object>(16));
		return menus;
	}

	@Override
	public int remove(Long id) {
		int result = menuMapper.remove(id);
		return result;
	}

	@Override
	public int save(MenuDO menu) {
		int r = menuMapper.save(menu);
		return r;
	}

	@Override
	public int update(MenuDO menu) {
		int r = menuMapper.update(menu);
		return r;
	}

	@Override
	public MenuDO get(Long id) {
		MenuDO menuDO = menuMapper.get(id);
		return menuDO;
	}

	@Override
	public Tree<MenuDO> getTree() {
		List<Tree<MenuDO>> trees = new ArrayList<Tree<MenuDO>>();
		List<MenuDO> menuDOs = menuMapper.list(new HashMap<String,Object>(16));
		for (MenuDO sysMenuDO : menuDOs) {
			Tree<MenuDO> tree = new Tree<MenuDO>();
			tree.setId(sysMenuDO.getMenuId().toString());
			tree.setParentId(sysMenuDO.getParentId().toString());
			tree.setText(sysMenuDO.getName());
			// Map<String, Object> state= new HashMap<>();
			// state.put("undetermined", true);
			// tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<MenuDO> t = BuildTree.build(trees);
		return t;
	}

	@Override
	public Tree<MenuDO> getTree(Long id) {
		// 根据roleId查询权限
		List<MenuDO> menus = menuMapper.list(new HashMap<String, Object>(16));
		List<Long> menuIds = roleMenuMapper.listMenuIdByRoleId(id);
		List<Long> temp = menuIds;
		for (MenuDO menu : menus) {
			if (temp.contains(menu.getParentId())) {
				menuIds.remove(menu.getParentId());
			}
		}
		List<Tree<MenuDO>> trees = new ArrayList<Tree<MenuDO>>();
		List<MenuDO> menuDOs = menuMapper.list(new HashMap<String, Object>(16));
		for (MenuDO sysMenuDO : menuDOs) {
			Tree<MenuDO> tree = new Tree<MenuDO>();
			tree.setId(sysMenuDO.getMenuId().toString());
			tree.setParentId(sysMenuDO.getParentId().toString());
			tree.setText(sysMenuDO.getName());
			Map<String, Object> state = new HashMap<>(16);
			Long menuId = sysMenuDO.getMenuId();
			if (menuIds.contains(menuId)) {
				state.put("selected", true);
			} else {
				state.put("selected", false);
			}
			tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<MenuDO> t = BuildTree.build(trees);
		return t;
	}

	@Override
	public Set<String> listPerms(Long userId) {
		List<String> perms = menuMapper.listUserPerms(userId);
		Set<String> permsSet = new HashSet<>();
		for (String perm : perms) {
			if (StringUtils.isNotBlank(perm)) {
				permsSet.addAll(Arrays.asList(perm.trim().split(",")));
			}
		}
		return permsSet;
	}

	@Override
	public List<Tree<MenuDO>> listMenuTree(Long id) {
		List<Tree<MenuDO>> trees = new ArrayList<Tree<MenuDO>>();
		List<MenuDO> menuDOs = menuMapper.listMenuByUserId(id);
		for (MenuDO sysMenuDO : menuDOs) {
			Tree<MenuDO> tree = new Tree<MenuDO>();
			tree.setId(sysMenuDO.getMenuId().toString());
			tree.setParentId(sysMenuDO.getParentId().toString());
			tree.setText(sysMenuDO.getName());
			Map<String, Object> attributes = new HashMap<>(16);
			attributes.put("url", sysMenuDO.getUrl());
			attributes.put("icon", sysMenuDO.getIcon());
			tree.setAttributes(attributes);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		List<Tree<MenuDO>> list = BuildTree.buildList(trees, "0");
		return list;
	}
	
	@Override
	public List<Tree<MenuDO>> listMenuTreeAll(Long id) {
		List<Tree<MenuDO>> allTrees = new ArrayList<Tree<MenuDO>>();
		List<MenuDO> menuDOs = menuMapper.listMenuByUserIdAll(id);
		long tmpParentId=0;
		long tmpParentIdTwo=0;
		for (MenuDO sysMenuDO : menuDOs) {	//一层		
			if(sysMenuDO.getParentId()==0){
				tmpParentId = sysMenuDO.getMenuId();
				Tree<MenuDO> tree = new Tree<MenuDO>();
				tree.setId(sysMenuDO.getMenuId().toString());
				tree.setParentId(sysMenuDO.getParentId().toString());
				tree.setText(sysMenuDO.getName());
				Map<String, Object> attributes = new HashMap<>(16);
				attributes.put("url", sysMenuDO.getUrl());
				attributes.put("icon", sysMenuDO.getIcon());
				tree.setAttributes(attributes);
				List<Tree<MenuDO>> treeOnes = new ArrayList<Tree<MenuDO>>();//一层子菜单
				for (MenuDO sysMenuDOTwo : menuDOs) { //二层
					if(sysMenuDOTwo.getParentId()==tmpParentId){
						tmpParentIdTwo = sysMenuDOTwo.getMenuId();
						Tree<MenuDO> treeTwo = new Tree<MenuDO>();
						treeTwo.setId(sysMenuDOTwo.getMenuId().toString());
						treeTwo.setParentId(sysMenuDOTwo.getParentId().toString());
						treeTwo.setText(sysMenuDOTwo.getName());
						Map<String, Object> attributesTwo = new HashMap<>(16);
						attributesTwo.put("url", sysMenuDOTwo.getUrl());
						attributesTwo.put("icon", sysMenuDOTwo.getIcon());
						treeTwo.setAttributes(attributesTwo);
						List<Tree<MenuDO>> treeTwos = new ArrayList<Tree<MenuDO>>();//二层子菜单
						for (MenuDO sysMenuDOThree : menuDOs) {//三层
							if(sysMenuDOThree.getParentId()==tmpParentIdTwo){
								Tree<MenuDO> treeThree = new Tree<MenuDO>();
								treeThree.setId(sysMenuDOThree.getMenuId().toString());
								treeThree.setParentId(sysMenuDOThree.getParentId().toString());
								treeThree.setText(sysMenuDOThree.getName());
								Map<String, Object> attributesThree = new HashMap<>(16);
								attributesThree.put("url", sysMenuDOThree.getUrl());
								attributesThree.put("icon", sysMenuDOThree.getIcon());
								treeThree.setAttributes(attributesThree);
								treeTwos.add(treeThree);
							}
						}
						treeTwo.setChildren(treeTwos);
						treeOnes.add(treeTwo);	
					}
				}
				List<Tree<MenuDO>> listOne = BuildTree.buildList(treeOnes, String.valueOf(tmpParentId));
				tree.setChildren(listOne);
				allTrees.add(tree);
			}		
		}
		List<Tree<MenuDO>> listAllTrees = BuildTree.buildList(allTrees, String.valueOf("0"));
		return listAllTrees;
	}
	//取得根目录菜单
	@Override
	public List<Tree<MenuDO>> listMenuTreeRoot(Long id) {
		List<Tree<MenuDO>> trees = new ArrayList<Tree<MenuDO>>();
		List<MenuDO> menuDOs = menuMapper.listMenuRootByUserId(id);
		for (MenuDO sysMenuDO : menuDOs) {
			Tree<MenuDO> tree = new Tree<MenuDO>();
			tree.setId(sysMenuDO.getMenuId().toString());
			tree.setParentId(sysMenuDO.getParentId().toString());
			tree.setText(sysMenuDO.getName());
			Map<String, Object> attributes = new HashMap<>(16);
			attributes.put("url", sysMenuDO.getUrl());
			attributes.put("icon", sysMenuDO.getIcon());
			tree.setAttributes(attributes);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		List<Tree<MenuDO>> list = BuildTree.buildList(trees, "0");
		return list;
	}
}
