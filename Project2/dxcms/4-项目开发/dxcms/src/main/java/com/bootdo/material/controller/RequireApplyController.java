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
        Long createUserId =getUser().getUserId();
        String createUserName =getUser().getUsername();
        String deptName =getUser().getDeptName();
        Long deptId =getUser().getDeptId();
        String planNo = "0001001009";
        String businessDate = DateUtils.format(new Date(),DateUtils.DATE_PATTERN);
        String name = businessDate.substring(0,4)+"年"+businessDate.substring(5,7)+"月采购申请";

        model.addAttribute("name", name);//名称
        model.addAttribute("planNo", planNo);//编号
        model.addAttribute("authorCorpName", deptName); //编制机构名称
        model.addAttribute("businessDate", businessDate); //编制机构名称
        model.addAttribute("authorCorpName", deptName); //编制部门名称
        model.addAttribute("authorCorpId", deptId); //编制部门Id

        model.addAttribute("createUserId", createUserId); //编制人Id
        model.addAttribute("createUserName", createUserName); //编制人姓名
        model.addAttribute("createDate", businessDate);//编制日期

        return prefix + "/add";
    }
    @GetMapping("/materialList")
    @RequiresPermissions("material:requireApply:add")
    String materialList(Model model){
        return prefix + "/materialList";
    }

    //取得选择物资列表
    @GetMapping("/getMaterialList")
    @RequiresPermissions("material:requireApply:add")
    @ResponseBody
    PageUtils getMaterialList(@RequestParam Map<String, Object> params){
        // 查询列表数据
        //Query query = new Query(params);
        List<Map<String, Object>> materialList = new ArrayList();//调用接口
        for(int i=1;i<11;i++){
            //做测试数据 调用接口前使用 begin
            Map<String, Object> materialMap = new HashMap<>();
            materialMap.put("name","物资A"+i);
            materialMap.put("materialClassName","物资类别"+i);
            materialMap.put("code",i);
            materialMap.put("brand","规格型号"+i);
            materialMap.put("texture","材质"+i);
            materialMap.put("materialUnitId","单位"+i);
            materialList.add(materialMap);
        }
        //做测试数据 end
        int total = 20;//调用接口
        PageUtils pageUtil = new PageUtils(materialList, total);
        return pageUtil;
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
    @ResponseBody
    @GetMapping("/getMaterialDetailByCode/{code}")
    @RequiresPermissions("material:requireApply:add")
    Map<String, Object> getMaterialDetailByCode(@PathVariable("code") String code){
        //做测试数据 调用接口前使用 begin
        Map<String, Object> materialMap = new HashMap<>();
        materialMap.put("name","物资A"+code);
        materialMap.put("materialClassName","物资类别"+code);
        materialMap.put("code","物资编码"+code);
        materialMap.put("materialUnitId","单位"+code);
        materialMap.put("specification","规格型号"+code);
        materialMap.put("texture","材质"+code);
        materialMap.put("materialSubArray","包装物资"+code);
        materialMap.put("budgetQty","1000");
        materialMap.put("budgetPrice","900.24");
        materialMap.put("referencePrice","890.45");
        materialMap.put("stockQty","200");
        materialMap.put("acceptUserName","张三");
        materialMap.put("acceptUserId","zhangsan");
        Map<String, Object> returnData = new HashMap<String, Object>();
        returnData.put("materialDetail", materialMap);

        return returnData;

    }



}
