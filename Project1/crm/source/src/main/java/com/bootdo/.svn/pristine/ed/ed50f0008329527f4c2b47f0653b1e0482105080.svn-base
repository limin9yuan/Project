package com.bootdo.sales.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.sales.domain.ProvinceDO;
import com.bootdo.sales.service.ProvinceService;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author smile
 * @email 1992lcg@163.com
 * @date 2017-11-23 20:15:31
 */
 
@Controller
@RequestMapping("/sales/province")
public class ProvinceController {
	@Autowired
	private ProvinceService provinceService;

	//取
	@ResponseBody
	@GetMapping("/listDic")
	public List<DictDO> listDic() {
		List<DictDO> dictList = provinceService.listDic();
		return dictList;
	}
	@ResponseBody
	@GetMapping("/listCityDic/{provinceId}")
	public List<DictDO> listCityDic(@PathVariable("provinceId") String provinceId,Model model) {
		System.out.println(provinceId);
		List<DictDO> dictList = provinceService.listCityDic(provinceId);
		model.addAttribute("provinceId", provinceId);
		System.out.println(dictList.size());
		return dictList;
	}
	@ResponseBody
	@GetMapping("/listAreaDic/{cityId}")
	public List<DictDO> listAreaDic(@PathVariable("cityId") String cityId,Model model) {
		List<DictDO> dictList = provinceService.listAreaDic(cityId);
		model.addAttribute("cityId", cityId);
		return dictList;
	}
	
	
}
