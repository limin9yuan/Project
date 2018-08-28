package com.bootdo.contract.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
