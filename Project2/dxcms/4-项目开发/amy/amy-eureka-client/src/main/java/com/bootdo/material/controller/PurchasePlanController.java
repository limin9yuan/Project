package com.bootdo.material.controller;


import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.bootdo.common.utils.DateUtils;
import com.bootdo.common.utils.FileUtil;
import com.github.pagehelper.PageInfo;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.controller.BaseController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


import com.dx.client.model.purchase.PurchasePlanItemBean;
import com.dx.client.model.purchase.RequireApplyItemBean;
import com.dx.client.model.purchase.PurchasePlanBean;
import com.dx.client.model.purchase.RequirePlanItemBean;
import com.dx.client.model.purchase.RequirePlanBean;
import org.wxcl.amy.utils.common.ResultMsg;
import com.dx.service.datacenter.service.api.IMaterialService;
import com.dx.service.purchase.service.api.IPurchasePlanService;
import com.dx.service.purchase.service.api.IRequirePlanService;

@Controller
@RequestMapping("/material/purchasePlan")

public class PurchasePlanController extends BaseController {

    @Autowired
    private IPurchasePlanService purchasePlanService;
    @Autowired
    private IMaterialService  materialService;
    @Autowired
    private IRequirePlanService  requirePlanService;
    private String prefix="material/purchasePlan"  ;
    @GetMapping()
    @RequiresPermissions("material:purchasePlan:purchasePlan")
    String purchasePlan(){
        return "material/purchasePlan/purchasePlan";
    }
    @GetMapping("/add")
    @RequiresPermissions("material:purchasePlan:add")
    String add(){
        return "material/purchasePlan/add";
    }

    @GetMapping("/addMore")
    @RequiresPermissions("material:purchasePlan:add")
    String addMore(){
        return "material/purchasePlan/addMore";
    }

    @GetMapping("/nextStep/{requirePlanid}")
    @RequiresPermissions("material:purchasePlan:add")
    String nextStep(@PathVariable("requirePlanid") String requirePlanid, Model model){

        Long createUserId =getUser().getUserId();
        String createUserName =getUser().getUsername();
        String deptName =getUser().getDeptName();
        Long deptId =getUser().getDeptId();
        String code = "";
        String businessDate = DateUtils.format(new Date(),DateUtils.DATE_PATTERN);
        String name = businessDate.substring(0,4)+"年"+businessDate.substring(5,7)+"月采购申请";


        model.addAttribute("requirePlanid",requirePlanid);

        model.addAttribute("name", name);//名称
        model.addAttribute("code", code);//编号
        model.addAttribute("authorCorpName", deptName); //编制机构名称
        model.addAttribute("businessDate", businessDate); //计划日期
        model.addAttribute("authorDeptName", deptName); //编制部门名称
        model.addAttribute("authorCorpId", deptId); //编制机构Id

        model.addAttribute("authorUserId", createUserId); //编制人Id
        model.addAttribute("authorUserName", createUserName); //编制人姓名
        model.addAttribute("createDate", businessDate);//编制日期

        return "material/purchasePlan/nextStep";
    }

    //查看begin
    @GetMapping("/check/{id}")
    @RequiresPermissions("material:purchasePlan:check")
    String check(@PathVariable("id") String id, Model model){
        //调用接口
        ResultMsg rm = purchasePlanService.primary(id);
        //做测试数据 begin
        PurchasePlanBean purchasePlanModel = new PurchasePlanBean();//此处为接口取得数据
        purchasePlanModel.setId(id);
        purchasePlanModel.setName("2018年8月采购申请");
        purchasePlanModel.setCode(id);
        //purchasePlanModel.setType("类型");//类型（没有）
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
        	Date createTime = sdf.parse("2018-10-16");
            Date createDate = new java.sql.Date(createTime.getTime());
            purchasePlanModel.setCreateDate(createDate);
            Date businessTime = sdf.parse("2018-10-16");
            Date businessDate = new java.sql.Date(businessTime.getTime());
            purchasePlanModel.setBusinessDate(businessDate);
       }catch (Exception e){
           e.printStackTrace();
       }
        purchasePlanModel.setAuthorUserName("张三");//编制人姓名
        purchasePlanModel.setRemark("备注");

        rm = new ResultMsg();
        rm.setData(purchasePlanModel);
        //做测试数据 end
        model.addAttribute("purchasePlanModel", rm.getData());
        return "material/purchasePlan/check";
    }

    @RequestMapping("/check_ajax")
    @RequiresPermissions("material:purchasePlan:check")
    @ResponseBody
    Map<String, Object> check_ajax(@RequestParam("id") String id){
        //调用接口
        ResultMsg rsm = purchasePlanService.detail(id);
        //做测试数据 调用接口前使用 begin
        List<PurchasePlanItemBean> purchasePlanDetailList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            //做测试数据 begin
            PurchasePlanItemBean purchasePlanDetailBean = new PurchasePlanItemBean();//（正式的）
            if(i<=3){
                //purchasePlanDetailBean.setMaterialType("物料类别1");//物料类别（没有）
                purchasePlanDetailBean.setMaterialUnitName("单位1");//单位
            }else{
                //purchasePlanDetailBean.setMaterialType("物料类别2");//物料类别（没有）
                purchasePlanDetailBean.setMaterialUnitName("单位2");//单位
            }

            purchasePlanDetailBean.setMaterialName("物资A" + i);//物资名称
            purchasePlanDetailBean.setMaterilaCode("物资编码" + i);//物资编码
            purchasePlanDetailBean.setSpecification("规格" + i);//规格
            purchasePlanDetailBean.setMaterialSubArray("包装物料" + i);//包装物料
            purchasePlanDetailBean.setRequireQty(Double.valueOf("100"));
            purchasePlanDetailBean.setPurchaseQty(Double.valueOf("5"));
            //purchasePlanDetailBean.setStockQty(Double.valueOf("47"));//库存数量（没有）
            purchasePlanDetailBean.setPurchaseQty(Double.valueOf("5"));
            purchasePlanDetailBean.setBudgetQty(Double.valueOf("7"));
            purchasePlanDetailBean.setReferencePrice(new BigDecimal(100));//参考单价
            purchasePlanDetailBean.setBudgetPrice(new BigDecimal(200));//预算价格
            //purchasePlanDetailBean.setReferenceAmount(new BigDecimal(200));//参考金额(没有)
            purchasePlanDetailBean.setArriveDate(new Date("2018/8/27"));
            //purchasePlanDetailBean.setRequireDept("需求部门" + i);//需求部门(没有)
            purchasePlanDetailBean.setRequirePlanItemId("i");//需求计划明细id
            // purchaseMap.put("requirePlanid", i);
            purchasePlanDetailBean.setDescription("sb");
            purchasePlanDetailList.add(purchasePlanDetailBean);
        }

        Map<String, Object> returnData = new HashMap<String, Object>();
        returnData.put("checkList", purchasePlanDetailList);

        return returnData;
    }


    @RequestMapping("/checkPurchasePlanGroup")
    @RequiresPermissions("material:purchasePlan:check")
    @ResponseBody
    Map<String, Object> checkPurchasePlanGroup(@RequestParam("id") String id){
        //调用接口
        ResultMsg rsm = purchasePlanService.detail(id);
        List<PurchasePlanItemBean> checkPurchasePlanGroup = new ArrayList<>();//(正式的)
        //做测试数据 调用接口前使用 begin
        Double type1=0.00;
        int type1cnt=0;
        int budgetAmount=0;
        for (int i = 0; i < 6; i++) {
            //做测试数据 begin
            PurchasePlanItemBean purchasePlanGroupBean = new PurchasePlanItemBean();//正式
            ///正式
            if(i<=3) {
                type1 = type1+ 5;
                type1cnt = type1cnt  + 500;
                budgetAmount = budgetAmount+1400;
            }
            if(i==3){
                //purchasePlanGroupBean.setMaterialType("物料类别1");//物料类别（没有）
                purchasePlanGroupBean.setMaterialUnitName("单位1");//单位
                purchasePlanGroupBean.setPurchaseQty((double)type1);
                //purchasePlanGroupBean.setReferenceAmount(new BigDecimal("type1cnt"));//参考金额(没有)
                //purchasePlanGroupBean.setBudgetAmount(new BigDecimal("budgetAmount"));//预算金额(没有)
                checkPurchasePlanGroup.add(purchasePlanGroupBean);
                type1 = 0.00;
                type1cnt = 0;
                budgetAmount = 0;
            }
            if(i>3) {
                type1 = type1+ 5;
                type1cnt = type1cnt  + 500;
                budgetAmount = budgetAmount+1400;
                //purchasePlanGroupBean.setMaterialType("物料类别2");//物料类别（没有）
                purchasePlanGroupBean.setMaterialUnitName("单位2");//单位
                purchasePlanGroupBean.setPurchaseQty((double)type1);
                //purchasePlanGroupBean.setReferenceAmount(new BigDecimal("type1cnt"));//参考金额(没有)
                //purchasePlanGroupBean.setBudgetAmount(new BigDecimal("budgetAmount"));//预算金额(没有)
                checkPurchasePlanGroup.add(purchasePlanGroupBean);
            }
        }

        Map<String, Object> returnData = new HashMap<String, Object>();
        returnData.put("checkPurchasePlanGroup", checkPurchasePlanGroup);
        return returnData;
    }

    //查看end
    //修改begin    (三个已完)
    @GetMapping("/edit/{id}")
    @RequiresPermissions("material:purchasePlan:edit")
    String edit(@PathVariable("id") String id, Model model){

        //正式的
        ResultMsg rm = purchasePlanService.primary(id);
        //测试数据 begin
        rm = new ResultMsg();
        //做测试数据 begin
        PurchasePlanBean purchasePlanModel = new PurchasePlanBean();//此处为接口取得数据
        purchasePlanModel.setId(id);
        purchasePlanModel.setName("2018年8月采购申请");
        purchasePlanModel.setCode(id);
        //purchasePlanModel.setType("类型");//类型（没有）
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//        	Date createTime = sdf.parse("2018-10-16");
//            Date createDate = new java.sql.Date(createTime.getTime());
//            purchasePlanModel.setCreateDate(createDate);
//            Date businessTime = sdf.parse("2018-10-16");
//            Date businessDate = new java.sql.Date(businessTime.getTime());
//            purchasePlanModel.setBusinessDate(businessDate);
//       }catch (Exception e){
//           e.printStackTrace();
//       }
        purchasePlanModel.setAuthorUserName("张三");//编制人姓名
        purchasePlanModel.setRemark("备注");
        rm.setData(purchasePlanModel);
        //测试数据 end

        model.addAttribute("purchasePlanModel", rm.getData());
        return "material/purchasePlan/edit";
    }

    @RequestMapping("/edit_ajax")
    @RequiresPermissions("material:purchasePlan:edit")
    @ResponseBody
    Map<String, Object> edit_ajax(@RequestParam("id") String id){

        //调用接口
        ResultMsg rsm = purchasePlanService.detail(id);
        //做测试数据 调用接口前使用 begin
        List<PurchasePlanItemBean> purchasePlanDetailList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            //做测试数据 begin
            PurchasePlanItemBean purchasePlanDetailBean = new PurchasePlanItemBean();//（正式的）
            if(i<=3){
                //purchasePlanDetailBean.setMaterialType("物料类别1");//物料类别（没有）
                purchasePlanDetailBean.setMaterialUnitName("单位1");//单位
            }else{
                //purchasePlanDetailBean.setMaterialType("物料类别2");//物料类别（没有）
                purchasePlanDetailBean.setMaterialUnitName("单位2");//单位
            }

            purchasePlanDetailBean.setMaterialName("物资A" + i);//物资名称
            purchasePlanDetailBean.setMaterilaCode("物资编码" + i);//物资编码
            purchasePlanDetailBean.setSpecification("规格" + i);//规格
            purchasePlanDetailBean.setMaterialSubArray("包装物料" + i);//包装物料
            purchasePlanDetailBean.setRequireQty(Double.valueOf("100"));
            purchasePlanDetailBean.setPurchaseQty(Double.valueOf("5"));
            //purchasePlanDetailBean.setStockQty(Double.valueOf("47"));//库存数量（没有）
            purchasePlanDetailBean.setPurchaseQty(Double.valueOf("5"));
            purchasePlanDetailBean.setBudgetQty(Double.valueOf("7"));
            purchasePlanDetailBean.setReferencePrice(new BigDecimal(100));//参考单价
            purchasePlanDetailBean.setBudgetPrice(new BigDecimal(200));//预算价格
            //purchasePlanDetailBean.setReferenceAmount(new BigDecimal(200));//参考金额(没有)
            purchasePlanDetailBean.setArriveDate(new Date("2018/8/27"));
            //purchasePlanDetailBean.setRequireDept("需求部门" + i);//需求部门(没有)
            purchasePlanDetailBean.setRequirePlanItemId("i");//需求计划明细id
           // purchaseMap.put("requirePlanid", i);
            purchasePlanDetailBean.setDescription("sb");
            purchasePlanDetailList.add(purchasePlanDetailBean);
        }

        Map<String, Object> returnData = new HashMap<String, Object>();
        returnData.put("editList", purchasePlanDetailList);

        return returnData;
    }

    @RequestMapping("/editPurchasePlanGroup")
    @RequiresPermissions("material:purchasePlan:edit")
    @ResponseBody
    Map<String, Object> editPurchasePlanGroup(@RequestParam("id") String id){

        //调用接口
        ResultMsg rsm = purchasePlanService.detail(id);
        List<PurchasePlanItemBean> editPurchasePlanGroup = new ArrayList<>();//(正式的)
        //做测试数据 调用接口前使用 begin
        Double type1=0.00;
        int type1cnt=0;
        int budgetAmount=0;
        for (int i = 0; i < 6; i++) {
            //做测试数据 begin
            PurchasePlanItemBean purchasePlanGroupBean = new PurchasePlanItemBean();//正式
            ///正式
            if(i<=3) {
                type1 = type1+ 5;
                type1cnt = type1cnt  + 500;
                budgetAmount = budgetAmount+1400;
            }
            if(i==3){
                //purchasePlanGroupBean.setMaterialType("物料类别1");//物料类别（没有）
                purchasePlanGroupBean.setMaterialUnitName("单位1");//单位
                purchasePlanGroupBean.setPurchaseQty((double)type1);
                //purchasePlanGroupBean.setReferenceAmount(new BigDecimal("type1cnt"));//参考金额(没有)
                //purchasePlanGroupBean.setBudgetAmount(new BigDecimal("budgetAmount"));//预算金额(没有)
                editPurchasePlanGroup.add(purchasePlanGroupBean);
                type1 = 0.00;
                type1cnt = 0;
                budgetAmount = 0;
            }
            if(i>3) {
                type1 = type1+ 5;
                type1cnt = type1cnt  + 500;
                budgetAmount = budgetAmount+1400;
                //purchasePlanGroupBean.setMaterialType("物料类别2");//物料类别（没有）
                purchasePlanGroupBean.setMaterialUnitName("单位2");//单位
                purchasePlanGroupBean.setPurchaseQty((double)type1);
                //purchasePlanGroupBean.setReferenceAmount(new BigDecimal("type1cnt"));//参考金额(没有)
                //purchasePlanGroupBean.setBudgetAmount(new BigDecimal("budgetAmount"));//预算金额(没有)
                editPurchasePlanGroup.add(purchasePlanGroupBean);
            }
        }

        Map<String, Object> returnData = new HashMap<String, Object>();
        returnData.put("editPurchasePlanGroup", editPurchasePlanGroup);
        return returnData;
    }
    //修改end
    /*@ResponseBody
    @GetMapping("//{code}")
    @RequiresPermissions("material:purchasePlan:add")
    Map<String, Object> getMaterialDetailByCode(@PathVariable("code") String code){
        List<Map<String, Object>> getPurchasePlanDetailList = new ArrayList<>();//调用接口
        String codeArray[] = code.split(",");
        //做测试数据 调用接口前使用 begin
        for (int i = 0; i < codeArray.length; i++) {
            Map<String, Object> purchaseMap = new HashMap<>();
            //purchaseMap.put("requirePlanid", codeArray[i]);
            purchaseMap.put("materialType", "物料类别" + i);
            purchaseMap.put("materialName", "物资A" + i);
            purchaseMap.put("materilaCode", "物资编码" + i);
            purchaseMap.put("specification", "规格" + i);
            purchaseMap.put("materialUnitName", "单位" + i);
            purchaseMap.put("materialSubArray", "包装物料" + i);
            purchaseMap.put("requireQty","25345");
            purchaseMap.put("purchaseQty","5");
            purchaseMap.put("stockQty", "47");
            purchaseMap.put("reserveQty", "57657");
            purchaseMap.put("onwayQty", "878");
            purchaseMap.put("budgetQty", "7");
            purchaseMap.put("referencePrice", "100");
            purchaseMap.put("budgetPrice", "200");
            purchaseMap.put("referenceAmount", "500");
            //purchaseMap.put("requireDate","2018/8/27");
            purchaseMap.put("arriveDate", "2018/8/27");
            //purchaseMap.put("purchaserName", "张三");
            purchaseMap.put("requireDept", "需求部门" + i);
            purchaseMap.put("requirePlanid", codeArray[i]);
            purchaseMap.put("description", "sb");
            getPurchasePlanDetailList.add(purchaseMap);
        }
        Map<String, Object> returnData = new HashMap<String, Object>();
        returnData.put("getPurchasePlanDetailList", getPurchasePlanDetailList);

        return returnData;

    }*/

    //点击下一步后的第二页明细列表begin
    @ResponseBody
    @GetMapping("/purchasePlanGroup")
    @RequiresPermissions("material:purchasePlan:add")
    Map<String, Object> purchasePlanGroup(@RequestParam("code") String code){
        //调用接口
        List ml = new ArrayList();
        ml.add(code);
        ResultMsg rsm = purchasePlanService.createItems(ml);
        String codeArray[] = code.split(",");
        //做测试数据 调用接口前使用 begin
        List<PurchasePlanItemBean> purchasePlanGroup = new ArrayList<>();//调用接口
        PurchasePlanItemBean purchasePlanGroupBean = new PurchasePlanItemBean();//正式
        int type1=0;
        //int type1cnt=0;
        //int budgetAmount=0;
        for (int i = 0; i < codeArray.length; i++) {
            //做测试数据 begin
            if(i<=3) {
                type1 = type1+ 5;
                //type1cnt = type1cnt  + 500;
               // budgetAmount = budgetAmount+1400;
            }
            if(i==3){
                //purchasePlanGroupBean.setMaterialType("物料类别1");//物料类别（没有）
                purchasePlanGroupBean.setMaterialUnitName("单位1");//单位
                purchasePlanGroupBean.setPurchaseQty((double)type1);
                //purchasePlanGroupBean.setReferenceAmount(new BigDecimal("type1cnt"));//参考金额(没有)
                //purchasePlanGroupBean.setBudgetAmount(new BigDecimal("budgetAmount"));//预算金额(没有)
                purchasePlanGroup.add(purchasePlanGroupBean);
                type1 = 0;
                //type1cnt = 0;
               // budgetAmount = 0;
            }
            if(i>3) {
                type1 = type1+ 5;
                //type1cnt = type1cnt  + 500;
                //budgetAmount = budgetAmount+1400;
                //purchasePlanGroupBean.setMaterialType("物料类别2");//物料类别（没有）
                purchasePlanGroupBean.setMaterialUnitName("单位2");//单位
                purchasePlanGroupBean.setPurchaseQty((double)type1);
                //purchasePlanGroupBean.setReferenceAmount(new BigDecimal("type1cnt"));//参考金额(没有)
                //purchasePlanGroupBean.setBudgetAmount(new BigDecimal("budgetAmount"));//预算金额(没有)
                purchasePlanGroup.add(purchasePlanGroupBean);
            }

        }

        Map<String, Object> returnData = new HashMap<String, Object>();
        rsm = new ResultMsg();
        rsm.setData(purchasePlanGroup);
        //做测试数据 end
        returnData.put("purchasePlanGroup", rsm.getData());
        return returnData;
    }

    /*@ResponseBody
    @GetMapping("/getPurchasePlanDetail")
    @RequiresPermissions("material:purchasePlan:add")
    Map<String, Object> getPurchasePlanDetail(@RequestParam("code") String code){
        List<Map<String, Object>> getPurchasePlanDetailList = new ArrayList<>();//调用接口
        String codeArray[] = code.split(",");
        //做测试数据 调用接口前使用 begin
        for (int i = 0; i < codeArray.length; i++) {
            Map<String, Object> purchaseMap = new HashMap<>();
            //purchaseMap.put("requirePlanid", codeArray[i]);
            if(i<=3){
                purchaseMap.put("materialType", "物料类别1");

                purchaseMap.put("materialUnitName", "单位1");
            }else{
                purchaseMap.put("materialType", "物料类别2");
                purchaseMap.put("materialUnitName", "单位2");
            }

            purchaseMap.put("materialName", "物资A" + i);
            purchaseMap.put("materilaCode", "物资编码" + i);
            purchaseMap.put("specification", "规格" + i);
            purchaseMap.put("materialSubArray", "包装物料" + i);
            purchaseMap.put("requireQty","100");
            purchaseMap.put("purchaseQty","5");
            purchaseMap.put("stockQty", "47");
            purchaseMap.put("reserveQty", "57657");
            purchaseMap.put("onwayQty", "878");
            purchaseMap.put("budgetQty", "7");
            purchaseMap.put("referencePrice", "100");
            purchaseMap.put("budgetPrice", "200");
            purchaseMap.put("referenceAmount", "500");
            //purchaseMap.put("requireDate","2018/8/27");
            purchaseMap.put("arriveDate", "2018/8/27");
            //purchaseMap.put("purchaserName", "张三");
            purchaseMap.put("requireDept", "需求部门" + i);
            purchaseMap.put("requirePlanid", codeArray[i]);
            purchaseMap.put("description", "sb");
            getPurchasePlanDetailList.add(purchaseMap);
        }

        Map<String, Object> returnData = new HashMap<String, Object>();
        returnData.put("getPurchasePlanDetailList", getPurchasePlanDetailList);

        return returnData;

    }
    */


    @GetMapping("/getPurchasePlanDetail")
    @RequiresPermissions("material:purchasePlan:add")
    @ResponseBody
    Map<String, Object> getPurchasePlanDetail(@RequestParam("code") String code){
        //调用接口
        List ml = new ArrayList();
        ml.add(code);
        ResultMsg rsm = purchasePlanService.createItems(ml);

        //做测试数据 begin
        String codeArray[] = code.split(",");
        List<PurchasePlanItemBean> purchasePlanDetailList = new ArrayList<>();//调用接口
        PurchasePlanItemBean purchasePlanDetailBean = new PurchasePlanItemBean();
        for (int i = 0; i < codeArray.length; i++) {
            //做测试数据 调用接口前使用 begin
            if(i<=3){
                //purchasePlanDetailBean.setMaterialType("物料类别1");//物料类别（没有）
                purchasePlanDetailBean.setMaterialUnitName("单位1");//单位
            }else{
                //purchasePlanDetailBean.setMaterialType("物料类别2");//物料类别（没有）
                purchasePlanDetailBean.setMaterialUnitName("单位2");//单位
            }

            purchasePlanDetailBean.setMaterialName("物资A" + i);//物资名称
            purchasePlanDetailBean.setMaterilaCode("物资编码" + i);//物资编码
            purchasePlanDetailBean.setSpecification("规格" + i);//规格
            purchasePlanDetailBean.setMaterialSubArray("包装物料" + i);//包装物料
            purchasePlanDetailBean.setRequireQty(Double.valueOf("100"));
            purchasePlanDetailBean.setPurchaseQty(Double.valueOf("5"));
            //purchasePlanDetailBean.setStockQty(Double.valueOf("47"));//库存数量（没有）
            purchasePlanDetailBean.setPurchaseQty(Double.valueOf("5"));
            purchasePlanDetailBean.setBudgetQty(Double.valueOf("7"));
            purchasePlanDetailBean.setReferencePrice(new BigDecimal(100));//参考单价
            purchasePlanDetailBean.setBudgetPrice(new BigDecimal(200));//预算价格
            //purchasePlanDetailBean.setReferenceAmount(new BigDecimal(200));//参考金额(没有)
            purchasePlanDetailBean.setArriveDate(new Date("2018/8/27"));
            //purchasePlanDetailBean.setRequireDept("需求部门" + i);//需求部门(没有)
            purchasePlanDetailBean.setRequirePlanItemId("codeArray[i]");//需求计划明细id
            // purchaseMap.put("requirePlanid", i);
            purchasePlanDetailBean.setDescription("sb");
            purchasePlanDetailList.add(purchasePlanDetailBean);
        }
        Map<String, Object> returnData = new HashMap<String, Object>();
        rsm = new ResultMsg();
        rsm.setData(purchasePlanDetailList);
        //做测试数据 end
        returnData.put("getPurchasePlanDetailList", rsm.getData());
        return returnData;

    }
    //点击下一步后的第二页明细列表end



    /* @ResponseBody
    @GetMapping("/purchasePlanDetailList")
    @RequiresPermissions("material:purchasePlan:purchasePlan")
    public PageUtils purchasePlanDetailList(@RequestParam Map<String, Object> params){
        //查询列表数据
//		Query query = new Query(params);
        List<Map<String, Object>> purchasePlanDetailList = new ArrayList<>();//调用接口
        for(int i=1;i<21;i++){
            //做测试数据 调用接口前使用 begin
            Map<String, Object> purchaseMap = new HashMap<>();
            purchaseMap.put("requirePlanid","需求计划编号"+i);
            purchaseMap.put("materialName","物资A"+i);
            purchaseMap.put("materilaCode","物资编码"+i);
            purchaseMap.put("specification","型号"+i);
            purchaseMap.put("materialUnitName","单位"+i);
            purchaseMap.put("referencePrice","10"+i);
            purchaseMap.put("requireQty","1000");
            purchaseMap.put("requireDept","需求部门"+i);
            purchaseMap.put("arriveDate","2018/8/27");
            purchaseMap.put("createDate","2018/8/20");
            purchaseMap.put("description","sb");
            purchasePlanDetailList.add(purchaseMap);
        }
        int total = 20;//调用接口
        PageUtils pageUtils = new PageUtils(purchasePlanDetailList, total);
        return pageUtils;
    }
*/

    //采购计划编制明细列表（第一页）（已完）
    @GetMapping("/purchasePlanDetailList")
    @ResponseBody
    PageInfo purchasePlanDetailList(@RequestParam Map<String, Object> params) {

        // 查询列表数据
        ResultMsg rsm = requirePlanService.search(params.get("pageNumber").toString(),
                params.get("pageSize").toString(),"",
                params);
        //做测试数据 begin
        List<Map<String, Object>> purchasePlanDetailList = new ArrayList();
        for(int i=1;i<11;i++){
            //做测试数据 调用接口前使用 begin
            Map<String, Object> purchasePlanMap = new HashMap<>();
            purchasePlanMap.put("requirePlanid","需求计划编号"+i);
            purchasePlanMap.put("materialName","物资A"+i);
            purchasePlanMap.put("materilaCode","物资编码"+i);
            purchasePlanMap.put("specification","型号"+i);
            purchasePlanMap.put("materialUnitName","单位"+i);
            purchasePlanMap.put("referencePrice","10"+i);
            purchasePlanMap.put("requireQty","1000");
            purchasePlanMap.put("requireDept","需求部门"+i);
            purchasePlanMap.put("arriveDate","2018/8/27");
            purchasePlanMap.put("createDate","2018/8/20");
            purchasePlanMap.put("description","sb");
            purchasePlanDetailList.add(purchasePlanMap);
        }
        int total = 20;
        PageInfo pageInfo = new PageInfo(purchasePlanDetailList,
                Integer.parseInt(params.get("pageNumber").toString()));
        pageInfo.setTotal(total);
        //做测试数据 end
        //PageInfo pageInfo = (PageInfo)rsm.getData();//调用接口
        return pageInfo;
    }

    /*@ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("material:purchasePlan:purchasePlan")
    public PageUtils list(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        List<Map<String, Object>> purchasePlanDetailList = new ArrayList<>();//调用接口
        for(int i=1;i<11;i++){
            //做测试数据 调用接口前使用 begin
            Map<String, Object> purchaseMap = new HashMap<>();
            purchaseMap.put("statusName","状态"+i);
            purchaseMap.put("planNo","编号"+i);
            purchaseMap.put("name","名称"+i);
            purchaseMap.put("authorDeptName","编制部门"+i);
            //purchaseMap.put("purchaseDept","采购部门"+i);
            purchaseMap.put("budgetMoney","1000");
            purchaseMap.put("totalMoney","2000");
            purchaseMap.put("authorUserName","编制人"+i);
            purchaseMap.put("createDate","2018/8/27");
            purchasePlanDetailList.add(purchaseMap);
        }
        int total = 20;//调用接口
        PageUtils pageUtils = new PageUtils(purchasePlanDetailList, total);
        return pageUtils;
    }*/

    //采购管理列表
    @GetMapping("/list")  //（已完）
    @ResponseBody
    PageInfo list(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        ResultMsg rsm = purchasePlanService.search(params.get("pageNumber").toString(),
                params.get("pageSize").toString(),"",
                params);
        //做测试数据 begin
        List<Map<String, Object>> purchasePlanList = new ArrayList();
        for(int i=1;i<11;i++){
            Map<String, Object> purchaseMap = new HashMap<>();
            purchaseMap.put("id",i);
            purchaseMap.put("statusName","状态"+i);
            purchaseMap.put("planNo","编号"+i);
            purchaseMap.put("name","名称"+i);
            purchaseMap.put("authorDeptName","编制部门"+i);
            //purchaseMap.put("purchaseDept","采购部门"+i);
            purchaseMap.put("budgetMoney","1000");
            purchaseMap.put("totalMoney","2000");
            purchaseMap.put("authorUserName","编制人"+i);
            purchaseMap.put("createDate","2018-08-"+String.valueOf(10+i));
            purchasePlanList.add(purchaseMap);
        }
        int total = 20;
        PageInfo pageInfo = new PageInfo(purchasePlanList,
                Integer.parseInt(params.get("pageNumber").toString()));
        pageInfo.setTotal(total);
        //做测试数据 end
        //PageInfo pageInfo = (PageInfo)rsm.getData();//调用接口

        return pageInfo;
    }


    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("material:purchasePlan:add")
    public R save(@RequestParam Map<String, Object> params) {
    	PurchasePlanBean purchasePlanModel = new PurchasePlanBean();
        purchasePlanModel.setName((String)params.get("name"));
        purchasePlanModel.setCode((String)params.get("code"));
        purchasePlanModel.setAuthorUserName((String)params.get("authorUserName"));
        purchasePlanModel.setRemark((String)params.get("remark"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date createTime = sdf.parse((String)params.get("createDate"));
            Date createDate = new java.sql.Date(createTime.getTime());
            purchasePlanModel.setCreateDate(createDate);
            Date businessTime = sdf.parse((String)params.get("businessDate"));
            Date businessDate = new java.sql.Date(businessTime.getTime());
            purchasePlanModel.setBusinessDate(businessDate);
        }catch (Exception e){
            return R.error();
        }
        List itemList = new ArrayList<PurchasePlanItemBean>();

        JSONArray array = JSONArray.fromObject(params.get("applyEntryJson"));
        for(int i=0;i<array.size();i++){
            System.out.println(array.get(i));
            PurchasePlanItemBean purchasePlanItemBean = (PurchasePlanItemBean) JSONObject.toBean((JSONObject)array.get(i), PurchasePlanItemBean.class);
            itemList.add(purchasePlanItemBean);
        }
        purchasePlanModel.setPurchasePlanItemBeans(itemList);
        ResultMsg rms = purchasePlanService.save(purchasePlanModel);
        if ("1".equals(rms.getCode())) {
            return R.ok();
        }
        return R.error();
    }
    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("material:purchasePlan:edit")
    public R update(@RequestParam Map<String, Object> params){

        PurchasePlanBean purchasePlanModel = new PurchasePlanBean();
        purchasePlanModel.setName((String)params.get("name"));
        purchasePlanModel.setCode((String)params.get("code"));
        purchasePlanModel.setAuthorUserName((String)params.get("authorUserName"));
        purchasePlanModel.setRemark((String)params.get("remark"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
        	 Date createTime = sdf.parse((String)params.get("createDate"));
             Date createDate = new java.sql.Date(createTime.getTime());
             purchasePlanModel.setCreateDate(createDate);
             Date businessTime = sdf.parse((String)params.get("businessDate"));
             Date businessDate = new java.sql.Date(businessTime.getTime());
             purchasePlanModel.setBusinessDate(businessDate);
        }catch (Exception e){
            return R.error();
        }
        List itemList = new ArrayList<PurchasePlanItemBean>();

        JSONArray array = JSONArray.fromObject(params.get("applyEntryJson"));
        for(int i=0;i<array.size();i++){
            System.out.println(array.get(i));
            PurchasePlanItemBean purchasePlanItemBean = (PurchasePlanItemBean) JSONObject.toBean((JSONObject)array.get(i), PurchasePlanItemBean.class);
            itemList.add(purchasePlanItemBean);
        }
        purchasePlanModel.setPurchasePlanItemBeans(itemList);
        ResultMsg rms = purchasePlanService.save(purchasePlanModel);
        if ("1".equals(rms.getCode())) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping( "/remove")
    @ResponseBody
    @RequiresPermissions("material:purchasePlan:remove")
    public R remove( String id){
        //调用接口
        ResultMsg rms = purchasePlanService.remove(id);
        if ("1".equals(rms.getCode())) {
            return R.ok();
        }
        return R.error(rms.getCode(), rms.getMsg());
    }

    /**
     * 提交审批
     */
    @PostMapping( "/submitApproval")
    @ResponseBody
    @RequiresPermissions("material:purchasePlan:submitApproval")
    public R submitApproval(@RequestParam Map<String, Object> params){
        System.out.println(params);
        //int contactIds = service.save(customerContact);
        R r = new R();
        r.put("id",1);
        return r.ok();
    }
    /**
     * 撤回审批
     */
    @PostMapping( "/withdrawApproval")
    @ResponseBody
    @RequiresPermissions("material:purchasePlan:withdrawApproval")
    public R withdrawApproval(@RequestParam Map<String, Object> params){
        System.out.println(params);
        //int contactIds = service.save(customerContact);

        return R.ok();
    }
}
