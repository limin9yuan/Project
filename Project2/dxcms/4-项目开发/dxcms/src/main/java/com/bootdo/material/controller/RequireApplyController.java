package com.bootdo.material.controller;


import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.wxcl.amy.model.MaterialModel;
import org.wxcl.amy.model.RequireApplyDetailModel;
import org.wxcl.amy.model.RequireApplyModel;

import java.util.*;

@RequestMapping("/material/requireApply")
@Controller
public class RequireApplyController extends BaseController {
    private String prefix="material/requireApply"  ;

    @RequiresPermissions("material:requireApply:requireApply")
    @GetMapping("")
    String requireApply(Model model) {
        return prefix + "/requireApply";
    }

    @GetMapping("/add")
    @RequiresPermissions("material:requireApply:add")
    String addRequireApply(Model model){
        String deptName =getUser().getDeptName();
        Long deptId =getUser().getDeptId();
        String planNo = "0001001009";
        String businessDate = DateUtils.format(new Date(),DateUtils.DATE_PATTERN);

        model.addAttribute("name", planNo);//名称
        model.addAttribute("planNo", planNo);//编号
        model.addAttribute("authorCorpName", deptName); //编制机构名称
        model.addAttribute("businessDate", businessDate); //编制机构名称

        model.addAttribute("authorCorpId", deptId); //编制部门Id

        return prefix + "/add";
    }

    @GetMapping("/list")
    @ResponseBody
    PageUtils list(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        Query query = new Query(params);
        List<RequireApplyModel> requireApplyList = new ArrayList();//调用接口
        //做测试数据 调用接口前使用 begin
        RequireApplyModel requireApplyModel = new RequireApplyModel();
        requireApplyModel.setName("测试");
        requireApplyList.add(requireApplyModel);
        //做测试数据 end
        int total = 1;//调用接口
        PageUtils pageUtil = new PageUtils(requireApplyList, total);
        return pageUtil;
    }
    @GetMapping("/requireMaterialDetailList")
    @ResponseBody
    PageUtils requireMaterialDetailList(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        Query query = new Query(params);
        List<RequireApplyDetailModel> requireMaterialDetailList = new ArrayList();//调用接口
        //做测试数据 调用接口前使用 begin
        /*RequireApplyDetailModel requireApplyDetailModel = new  RequireApplyDetailModel();
        MaterialModel ma = new MaterialModel();
        ma.setName("测试物资");
        requireApplyDetailModel.setMaterial(ma);
        requireMaterialDetailList.add(requireApplyDetailModel);*/
        //做测试数据 end
        int total = 1;//调用接口
        PageUtils pageUtil = new PageUtils(requireMaterialDetailList, total);
        return pageUtil;
    }

    @RequiresPermissions("material:requireApply:add")
    @RequestMapping("/test")
    @ResponseBody()
    String test() {
        System.out.println("已请求");
        String jsondata = "{\"page\":\"1\"," +
                "      \"total\":2," +
                "      \"records\":\"13\"," +
                "      \"rows\":" +
                "          [" +
                "            {" +
                "              \"id\":\"13\"," +
                "              \"cell\":" +
                "                  [\"13\",\"2007-10-06\",\"Client 3\",\"1000.00\",\"0.00\",\"1000.00\",null]" +
                "            }," +
                "            {" +
                "              \"id\":\"12\"," +
                "              \"cell\":" +
                "                  [\"12\",\"2007-10-06\",\"Client 2\",\"700.00\",\"140.00\",\"840.00\",null]" +
                "            }," +
                "            {" +
                "              \"id\":\"11\"," +
                "              \"cell\":" +
                "                  [\"11\",\"2007-10-06\",\"Client 1\",\"600.00\",\"120.00\",\"720.00\",null]" +
                "            }," +
                "            {" +
                "              \"id\":\"10\"," +
                "              \"cell\":" +
                "                  [\"10\",\"2007-10-06\",\"Client 2\",\"100.00\",\"20.00\",\"120.00\",null]" +
                "            }," +
                "            {" +
                "              \"id\":\"9\"," +
                "              \"cell\":" +
                "                  [\"9\",\"2007-10-06\",\"Client 1\",\"200.00\",\"40.00\",\"240.00\",null]" +
                "            }," +
                "            {" +
                "              \"id\":\"8\"," +
                "              \"cell\":" +
                "                  [\"8\",\"2007-10-06\",\"Client 3\",\"200.00\",\"0.00\",\"200.00\",null]" +
                "            }," +
                "            {" +
                "              \"id\":\"7\"," +
                "              \"cell\":" +
                "                  [\"7\",\"2007-10-05\",\"Client 2\",\"120.00\",\"12.00\",\"134.00\",null]" +
                "            }," +
                "            {" +
                "              \"id\":\"6\"," +
                "              \"cell\":" +
                "                  [\"6\",\"2007-10-05\",\"Client 1\",\"50.00\",\"10.00\",\"60.00\",\"\"]" +
                "            }," +
                "            {" +
                "              \"id\":\"5\"," +
                "              \"cell\":" +
                "                  [\"5\",\"2007-10-05\",\"Client 3\",\"100.00\",\"0.00\",\"100.00\",\"no tax at all\"]" +
                "            }," +
                "            {" +
                "              \"id\":\"4\"," +
                "              \"cell\":" +
                "                  [\"4\",\"2007-10-04\",\"Client 3\",\"150.00\",\"0.00\",\"150.00\",\"no tax\"]" +
                "            }" +
                "          ]," +
                "      \"userdata\":{\"amount\":3220,\"tax\":342,\"total\":3564,\"name\":\"Totals:\"}" +
                "    }";
        return jsondata;
    }




}
