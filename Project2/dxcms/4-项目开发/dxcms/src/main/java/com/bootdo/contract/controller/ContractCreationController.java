package com.bootdo.contract.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.common.utils.R;
/**
 * 合同起草
 * @author Administrator
 * 
 * 
 */
@Controller
@RequestMapping("/ContractCreation/ContractCreation")
public class ContractCreationController {
    
    @RequiresPermissions("ContractCreation:ContractCreation")
    @GetMapping()
    String ContractCreation() {
    	return "contract/ContractCreation/ContractCreation";
    }
    
    @PostMapping("/test")
    @ResponseBody
    @RequiresPermissions("ContractCreation:ContractCreation")
    R test(@RequestParam("ctrls[]") String[] ctrls) {
    	System.out.println("************************");
    	for (int i = 0; i < ctrls.length; i++) {//遍历前台页面的数据
			String data = ctrls[i];
			System.out.println(data+"************************");
		}
    	System.out.println("************************");
    	return R.ok();
    }
}
