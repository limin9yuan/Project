package com.bootdo.system.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.common.domain.MainDO;
import com.bootdo.common.utils.Query;
import com.bootdo.contract.domain.ContractDO;
import com.bootdo.contract.service.ContractService;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LogoutAware;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.common.annotation.Log;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.MD5Utils;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.domain.MenuDO;
import com.bootdo.system.service.MenuService;

@Controller
public class LoginController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MenuService menuService;
    @Autowired
    private ContractService contractService;

	@GetMapping({ "/", "" })
	String welcome(Model model) {
		return "redirect:/blog";
	}

	@Log("请求访问主页")
	@GetMapping({ "/index" })
	String index(Model model) {
		List<Tree<MenuDO>> menus = menuService.listMenuTreeAll(getUserId());
		List<Tree<MenuDO>> menuRoots = menuService.listMenuTreeRoot(getUserId());
		model.addAttribute("menus", menus);
		model.addAttribute("menuRoots", menuRoots);
		/*for(int i=0;i<menus.size();i++){
			Tree<MenuDO> v = (Tree<MenuDO>)menus.get(i);
			System.out.println("*******"+v.getText());
			List<Tree<MenuDO>> vt = v.getChildren();
			for(int j=0;j<vt.size();j++){
				Tree<MenuDO> vtt = (Tree<MenuDO>)vt.get(j);
				System.out.println("*******------"+vtt.getText());
				List<Tree<MenuDO>> vtv = vtt.getChildren();
				for(int s=0;s<vtv.size();s++){
					Tree<MenuDO> vt1 = (Tree<MenuDO>)vtv.get(s);
					System.out.println("**************-------------"+vt1.getText());
					
				}
				
			}
			
		}*/
		model.addAttribute("name", getUser().getName());
		logger.info(getUser().getName());
		return "index_v1";
	}

	@GetMapping("/login")
	String login() {
		return "login";
	}

	@Log("登录")
	@PostMapping("/login")
	@ResponseBody
	R ajaxLogin(String username, String password) {
		password = MD5Utils.encrypt(username, password);
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			return R.ok();
		} catch (AuthenticationException e) {
			return R.error("用户或密码错误");
		}
	}

	@GetMapping("/logout")
	String logout() {
		ShiroUtils.logout();
		return "redirect:/login";
	}

	@GetMapping("/main")
	String main(@RequestParam Map<String, Object> params, Model model) {
       Calendar date = Calendar.getInstance();
       String currentYear = String.valueOf(date.get(Calendar.YEAR));
       params.put("offset",1);
       params.put("limit",2);
       params.put("currentYear",currentYear);
       Query query = new Query(params);
       List<MainDO> contractGetDataList = contractService.getDataList(query);
       model.addAttribute("contractGetDataList", contractGetDataList);
		return "main";
	}

	@GetMapping("/403")
	String error403() {
		return "403";
	}

}
