package com.bootdo.material.controller;

import com.bootdo.common.utils.R;
import com.dx.client.model.purchase.AllotPlanBean;
import com.dx.client.model.purchase.AllotPlanItemBean;
import com.dx.service.purchase.service.api.IAllotPlanService;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.wxcl.amy.utils.common.ResultMsg;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Mingyuan Li on 2018/9/4.
 * Package name: com.bootdo.material.controller.
 * Project name: dxcms.
 */
@Controller
@RequestMapping("/allocationPlan/allocationPlan")
public class AllocationPlanController {

    @Autowired
    private IAllotPlanService allotPlanService;

    @GetMapping()
    @RequiresPermissions("allocationPlan:allocationPlan")
    String allocationPlan(){
        return "material/allocationPlan/allocationPlan";
    }

    @GetMapping("/add")
    @RequiresPermissions("allocationPlan:add")
    String addAllocationPlan(){
        return "material/allocationPlan/add";
    }

    @GetMapping("/edit/{planNo}")
    @RequiresPermissions("allocationPlan:edit")
    String edit(@PathVariable("planNo") String planNo, Model model){
        String stringDate = "2018-08-27";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormat.parse(stringDate);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        ResultMsg rm = allotPlanService.primary(planNo);
        AllotPlanBean allotPlanBean = new AllotPlanBean();
        allotPlanBean.setCode(planNo);
        allotPlanBean.setName("2018年8月货源计划");
        allotPlanBean.setAuthorUserId("111");
        allotPlanBean.setAuthorUserName("张三");
        allotPlanBean.setCreateDate(date);
        allotPlanBean.setRemark("sb");

        rm = new ResultMsg();
        rm.setData(allotPlanBean);
        model.addAttribute("allocationPlanBean",rm.getData());

        return "material/allocationPlan/edit";
    }

    @RequestMapping("/edit_ajax/{planNo}")
    @ResponseBody
    Map<String, Object> edit_ajax(@PathVariable("planNo") String planNo) {
        ResultMsg rsm = allotPlanService.detail(planNo);
        List<AllotPlanItemBean> dataList = new ArrayList<>();//调用接口
        List<String> categoryList = new ArrayList<>();//调用接口
        //做测试数据 调用接口前使用 begin
        for (int i = 0; i < 10; i++) {
            AllotPlanItemBean allotPlanItemBean = new AllotPlanItemBean();
            if (i < 7){
                if (i < 4){
                    allotPlanItemBean.setMaterialClassName("办公用品类");

                }
                else {
                    allotPlanItemBean.setMaterialClassName("机物料类");

                }
                if (i % 2 == 0){
                    allotPlanItemBean.setCompanyId("供应商Id" + 1);
                    allotPlanItemBean.setCompanyName("供应商" + 1);
//                    allotPlanItemBean.setRequireCorpId("11");
                    allotPlanItemBean.setRequireCorpName("需求机构" + i);
                    allotPlanItemBean.setMaterialName("物料名称" + i);
                    allotPlanItemBean.setMaterilaCode("物料编码" + i);
                    allotPlanItemBean.setSpecification("物料特性" + i);
                    allotPlanItemBean.setMaterialUnitName("单位");
//                    allotPlanItemBean.setPurchasePlanItemId();
//                    allocationMap.put("purchaseQty","采购数量");
                    allotPlanItemBean.setUnitPrice(BigDecimal.valueOf(4000));
                    allotPlanItemBean.setAllotQty((double)200);
                    allotPlanItemBean.setAllotRatio((double)50);
                }
                if (i % 2 == 1){
                    allotPlanItemBean.setCompanyId("供应商Id" + 2);
                    allotPlanItemBean.setCompanyName("供应商" + 2);
//                    allotPlanItemBean.setRequireCorpId("11");
                    allotPlanItemBean.setRequireCorpName("需求机构" + i);
                    allotPlanItemBean.setMaterialName("物料名称" + i);
                    allotPlanItemBean.setMaterilaCode("物料编码" + i);
                    allotPlanItemBean.setSpecification("物料特性" + i);
                    allotPlanItemBean.setMaterialUnitName("单位");
//                    allotPlanItemBean.setPurchasePlanItemId();
//                    allocationMap.put("purchaseQty","采购数量");
                    allotPlanItemBean.setUnitPrice(BigDecimal.valueOf(60));
                    allotPlanItemBean.setAllotQty((double)99);
                    allotPlanItemBean.setAllotRatio((double)15);
                }
            }else {
                allotPlanItemBean.setCompanyId("供应商Id" + i);
                allotPlanItemBean.setCompanyName("供应商" + i);
//                    allotPlanItemBean.setRequireCorpId("11");
                allotPlanItemBean.setRequireCorpName("需求机构" + i);
                allotPlanItemBean.setMaterialClassName("单独采购");
                allotPlanItemBean.setMaterialName("物料名称" + i);
                allotPlanItemBean.setMaterilaCode("物料编码" + i);
                allotPlanItemBean.setSpecification("物料特性" + i);
                allotPlanItemBean.setMaterialUnitName("单位");
//                allotPlanItemBean.setPurchasePlanItemId();
//                allocationMap.put("purchaseQty","采购数量");
                allotPlanItemBean.setUnitPrice(BigDecimal.valueOf(124));
                allotPlanItemBean.setAllotQty((double)78);
                allotPlanItemBean.setAllotRatio((double)6);

            }

            dataList.add(allotPlanItemBean);
        }

        List<Map<String, Object>> getAllocationPlanDetailList = new ArrayList<>();
        for (int i=0; i< dataList.size(); i++){
            Map<String, Object> allocationMap = new HashMap<>();
            if (i < 7){
                if (i < 4){
                    allocationMap.put("materialClassName", dataList.get(i).getMaterialClassName());

                }
                else {
                    allocationMap.put("materialClassName", dataList.get(i).getMaterialClassName());

                }
                if (i % 2 == 0){
                    allocationMap.put("companyId", dataList.get(i).getCompanyId());
                    allocationMap.put("companyName", dataList.get(i).getCompanyName());
                    allocationMap.put("authorDeptName", dataList.get(i).getRequireCorpName());
                    allocationMap.put("materialName", dataList.get(i).getMaterialName());
                    allocationMap.put("materilaCode", dataList.get(i).getMaterilaCode());
                    allocationMap.put("specification", dataList.get(i).getSpecification());
                    allocationMap.put("materialUnitName",dataList.get(i).getMaterialUnitName());
                    allocationMap.put("purchaseQty","1000");
                    allocationMap.put("unitPrice",dataList.get(i).getUnitPrice());
                    allocationMap.put("allotQty",dataList.get(i).getAllotQty());
                    allocationMap.put("allotRatio",dataList.get(i).getAllotRatio());
                }
                if (i % 2 == 1){
                    allocationMap.put("companyId", dataList.get(i).getCompanyId());
                    allocationMap.put("companyName", dataList.get(i).getCompanyName());
                    allocationMap.put("authorDeptName", dataList.get(i).getRequireCorpName());
                    allocationMap.put("materialName", dataList.get(i).getMaterialName());
                    allocationMap.put("materilaCode", dataList.get(i).getMaterilaCode());
                    allocationMap.put("specification", dataList.get(i).getSpecification());
                    allocationMap.put("materialUnitName",dataList.get(i).getMaterialUnitName());
                    allocationMap.put("purchaseQty","500");
                    allocationMap.put("unitPrice",dataList.get(i).getUnitPrice());
                    allocationMap.put("allotQty",dataList.get(i).getAllotQty());
                    allocationMap.put("allotRatio",dataList.get(i).getAllotRatio());
                }
            }else {
                allocationMap.put("companyId", dataList.get(i).getCompanyId());
                allocationMap.put("companyName", dataList.get(i).getCompanyName());
                allocationMap.put("authorDeptName", dataList.get(i).getRequireCorpName());
                allocationMap.put("materialClassName", dataList.get(i).getMaterialClassName());
                allocationMap.put("materialName", dataList.get(i).getMaterialName());
                allocationMap.put("materilaCode", dataList.get(i).getMaterilaCode());
                allocationMap.put("specification", dataList.get(i).getSpecification());
                allocationMap.put("materialUnitName",dataList.get(i).getMaterialUnitName());
                allocationMap.put("purchaseQty","600");
                allocationMap.put("unitPrice",dataList.get(i).getUnitPrice());
                allocationMap.put("allotQty",dataList.get(i).getAllotQty());
                allocationMap.put("allotRatio",dataList.get(i).getAllotRatio());
            }

            getAllocationPlanDetailList.add(allocationMap);

        }
        List<String> tempList = new ArrayList<>();
        for (int i = 0; i < getAllocationPlanDetailList.size(); i++){
            tempList.add((String) getAllocationPlanDetailList.get(i).get("materialClassName"));

        }
        for(String str : tempList) {
            if(!categoryList.contains(str)) {
                categoryList.add(str);
            }
        }

        List<List> supplierList = new ArrayList<>();//调用接口
        for(int i = 0; i < categoryList.size()-1; i++){
            List<Map<String, Object>> temp = new ArrayList<>();

            for (int j = 0; j < getAllocationPlanDetailList.size(); j++){
                Map<String, Object> supplierMap = new HashMap<>();
                if (categoryList.get(i) == getAllocationPlanDetailList.get(j).get("materialClassName")){
                    supplierMap.put("companyId",getAllocationPlanDetailList.get(j).get("companyId"));
                    supplierMap.put("companyName",getAllocationPlanDetailList.get(j).get("companyName"));
                    temp.add(supplierMap);
                }
            }
            clearList(temp);
            supplierList.add(temp);
        }


        List<List> returnList = new ArrayList<>();

        for (int i = 0; i < categoryList.size(); i++){
            int companyIndex = 0;
            List<String> companyList = new ArrayList<>();
            List<Map<String, Object>> temp = new ArrayList<>();
            for (int j = 0; j < getAllocationPlanDetailList.size(); j++){
                Map<String, Object> tempMap = getAllocationPlanDetailList.get(j);
                if (categoryList.get(i) == tempMap.get("materialClassName")){
                    if(companyList.size() == 0 ||!companyList.contains(tempMap.get("companyId"))){

                        companyList.add((String) tempMap.get("companyId"));
                        tempMap.put("unitPrice" + companyIndex, tempMap.get("unitPrice"));
                        tempMap.put("allotQty" + companyIndex, tempMap.get("allotQty"));
                        tempMap.put("allotRatio" + companyIndex, tempMap.get("allotRatio"));
                        companyIndex++;
                    }else {
                        int tempIndex = companyList.indexOf(tempMap.get("companyId"));
                        tempMap.put("unitPrice" + tempIndex, tempMap.get("unitPrice"));
                        tempMap.put("allotQty" + tempIndex, tempMap.get("allotQty"));
                        tempMap.put("allotRatio" + tempIndex, tempMap.get("allotRatio"));
                    }

                    temp.add(tempMap);
                }
            }
            returnList.add(temp);
        }

        Map<String, Object> returnData = new HashMap<String, Object>();
        returnData.put("returnList", returnList);
        returnData.put("supplierList",supplierList);
        returnData.put("categoryList",categoryList);
        return returnData;

    }

    @GetMapping("/check/{planNo}")
    @RequiresPermissions("allocationPlan:check")
    String check(@PathVariable("planNo") String planNo, Model model){
        String stringDate = "2018-08-27";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormat.parse(stringDate);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        ResultMsg rm = allotPlanService.primary(planNo);
        AllotPlanBean allotPlanBean = new AllotPlanBean();
        allotPlanBean.setCode(planNo);
        allotPlanBean.setName("2018年8月货源计划");
        allotPlanBean.setAuthorUserId("111");
        allotPlanBean.setAuthorUserName("张三");
        allotPlanBean.setCreateDate(date);
        allotPlanBean.setRemark("sb");

        rm = new ResultMsg();
        rm.setData(allotPlanBean);
        model.addAttribute("allocationPlanBean",rm.getData());

        return "material/allocationPlan/check";
    }

    @RequestMapping("/check_ajax/{planNo}")
    @ResponseBody
    Map<String, Object> check_ajax(@PathVariable("planNo") String planNo) {
        ResultMsg rsm = allotPlanService.detail(planNo);
        List<AllotPlanItemBean> dataList = new ArrayList<>();//调用接口
        List<String> categoryList = new ArrayList<>();//调用接口
        //做测试数据 调用接口前使用 begin
        for (int i = 0; i < 10; i++) {
            AllotPlanItemBean allotPlanItemBean = new AllotPlanItemBean();
            if (i < 7){
                if (i < 4){
                    allotPlanItemBean.setMaterialClassName("办公用品类");

                }
                else {
                    allotPlanItemBean.setMaterialClassName("机物料类");

                }
                if (i % 2 == 0){
                    allotPlanItemBean.setCompanyId("供应商Id" + 1);
                    allotPlanItemBean.setCompanyName("供应商" + 1);
//                    allotPlanItemBean.setRequireCorpId("11");
                    allotPlanItemBean.setRequireCorpName("需求机构" + i);
                    allotPlanItemBean.setMaterialName("物料名称" + i);
                    allotPlanItemBean.setMaterilaCode("物料编码" + i);
                    allotPlanItemBean.setSpecification("物料特性" + i);
                    allotPlanItemBean.setMaterialUnitName("单位");
//                    allotPlanItemBean.setPurchasePlanItemId();
//                    allocationMap.put("purchaseQty","采购数量");
                    allotPlanItemBean.setUnitPrice(BigDecimal.valueOf(4000));
                    allotPlanItemBean.setAllotQty((double)200);
                    allotPlanItemBean.setAllotRatio((double)50);
                }
                if (i % 2 == 1){
                    allotPlanItemBean.setCompanyId("供应商Id" + 2);
                    allotPlanItemBean.setCompanyName("供应商" + 2);
//                    allotPlanItemBean.setRequireCorpId("11");
                    allotPlanItemBean.setRequireCorpName("需求机构" + i);
                    allotPlanItemBean.setMaterialName("物料名称" + i);
                    allotPlanItemBean.setMaterilaCode("物料编码" + i);
                    allotPlanItemBean.setSpecification("物料特性" + i);
                    allotPlanItemBean.setMaterialUnitName("单位");
//                    allotPlanItemBean.setPurchasePlanItemId();
//                    allocationMap.put("purchaseQty","采购数量");
                    allotPlanItemBean.setUnitPrice(BigDecimal.valueOf(60));
                    allotPlanItemBean.setAllotQty((double)99);
                    allotPlanItemBean.setAllotRatio((double)15);
                }
            }else {
                allotPlanItemBean.setCompanyId("供应商Id" + i);
                allotPlanItemBean.setCompanyName("供应商" + i);
//                    allotPlanItemBean.setRequireCorpId("11");
                allotPlanItemBean.setRequireCorpName("需求机构" + i);
                allotPlanItemBean.setMaterialClassName("单独采购");
                allotPlanItemBean.setMaterialName("物料名称" + i);
                allotPlanItemBean.setMaterilaCode("物料编码" + i);
                allotPlanItemBean.setSpecification("物料特性" + i);
                allotPlanItemBean.setMaterialUnitName("单位");
//                allotPlanItemBean.setPurchasePlanItemId();
//                allocationMap.put("purchaseQty","采购数量");
                allotPlanItemBean.setUnitPrice(BigDecimal.valueOf(124));
                allotPlanItemBean.setAllotQty((double)78);
                allotPlanItemBean.setAllotRatio((double)6);

            }

            dataList.add(allotPlanItemBean);
        }

        List<Map<String, Object>> getAllocationPlanDetailList = new ArrayList<>();
        for (int i=0; i< dataList.size(); i++){
            Map<String, Object> allocationMap = new HashMap<>();
            if (i < 7){
                if (i < 4){
                    allocationMap.put("materialClassName", dataList.get(i).getMaterialClassName());

                }
                else {
                    allocationMap.put("materialClassName", dataList.get(i).getMaterialClassName());

                }
                if (i % 2 == 0){
                    allocationMap.put("companyId", dataList.get(i).getCompanyId());
                    allocationMap.put("companyName", dataList.get(i).getCompanyName());
                    allocationMap.put("authorDeptName", dataList.get(i).getRequireCorpName());
                    allocationMap.put("materialName", dataList.get(i).getMaterialName());
                    allocationMap.put("materilaCode", dataList.get(i).getMaterilaCode());
                    allocationMap.put("specification", dataList.get(i).getSpecification());
                    allocationMap.put("materialUnitName",dataList.get(i).getMaterialUnitName());
                    allocationMap.put("purchaseQty","1000");
                    allocationMap.put("unitPrice",dataList.get(i).getUnitPrice());
                    allocationMap.put("allotQty",dataList.get(i).getAllotQty());
                    allocationMap.put("allotRatio",dataList.get(i).getAllotRatio());
                }
                if (i % 2 == 1){
                    allocationMap.put("companyId", dataList.get(i).getCompanyId());
                    allocationMap.put("companyName", dataList.get(i).getCompanyName());
                    allocationMap.put("authorDeptName", dataList.get(i).getRequireCorpName());
                    allocationMap.put("materialName", dataList.get(i).getMaterialName());
                    allocationMap.put("materilaCode", dataList.get(i).getMaterilaCode());
                    allocationMap.put("specification", dataList.get(i).getSpecification());
                    allocationMap.put("materialUnitName",dataList.get(i).getMaterialUnitName());
                    allocationMap.put("purchaseQty","500");
                    allocationMap.put("unitPrice",dataList.get(i).getUnitPrice());
                    allocationMap.put("allotQty",dataList.get(i).getAllotQty());
                    allocationMap.put("allotRatio",dataList.get(i).getAllotRatio());
                }
            }else {
                allocationMap.put("companyId", dataList.get(i).getCompanyId());
                allocationMap.put("companyName", dataList.get(i).getCompanyName());
                allocationMap.put("authorDeptName", dataList.get(i).getRequireCorpName());
                allocationMap.put("materialClassName", dataList.get(i).getMaterialClassName());
                allocationMap.put("materialName", dataList.get(i).getMaterialName());
                allocationMap.put("materilaCode", dataList.get(i).getMaterilaCode());
                allocationMap.put("specification", dataList.get(i).getSpecification());
                allocationMap.put("materialUnitName",dataList.get(i).getMaterialUnitName());
                allocationMap.put("purchaseQty","600");
                allocationMap.put("unitPrice",dataList.get(i).getUnitPrice());
                allocationMap.put("allotQty",dataList.get(i).getAllotQty());
                allocationMap.put("allotRatio",dataList.get(i).getAllotRatio());
            }

            getAllocationPlanDetailList.add(allocationMap);

        }
        List<String> tempList = new ArrayList<>();
        for (int i = 0; i < getAllocationPlanDetailList.size(); i++){
            tempList.add((String) getAllocationPlanDetailList.get(i).get("materialClassName"));

        }
        for(String str : tempList) {
            if(!categoryList.contains(str)) {
                categoryList.add(str);
            }
        }

        List<List> supplierList = new ArrayList<>();//调用接口
        for(int i = 0; i < categoryList.size()-1; i++){
            List<Map<String, Object>> temp = new ArrayList<>();

            for (int j = 0; j < getAllocationPlanDetailList.size(); j++){
                Map<String, Object> supplierMap = new HashMap<>();
                if (categoryList.get(i) == getAllocationPlanDetailList.get(j).get("materialClassName")){
                    supplierMap.put("companyId",getAllocationPlanDetailList.get(j).get("companyId"));
                    supplierMap.put("companyName",getAllocationPlanDetailList.get(j).get("companyName"));
                    temp.add(supplierMap);
                }
            }
            clearList(temp);
            supplierList.add(temp);
        }


        List<List> returnList = new ArrayList<>();

        for (int i = 0; i < categoryList.size(); i++){
            int companyIndex = 0;
            List<String> companyList = new ArrayList<>();
            List<Map<String, Object>> temp = new ArrayList<>();
            for (int j = 0; j < getAllocationPlanDetailList.size(); j++){
                Map<String, Object> tempMap = getAllocationPlanDetailList.get(j);
                if (categoryList.get(i) == tempMap.get("materialClassName")){
                    if(companyList.size() == 0 ||!companyList.contains(tempMap.get("companyId"))){

                        companyList.add((String) tempMap.get("companyId"));
                        tempMap.put("unitPrice" + companyIndex, tempMap.get("unitPrice"));
                        tempMap.put("allotQty" + companyIndex, tempMap.get("allotQty"));
                        tempMap.put("allotRatio" + companyIndex, tempMap.get("allotRatio"));
                        companyIndex++;
                    }else {
                        int tempIndex = companyList.indexOf(tempMap.get("companyId"));
                        tempMap.put("unitPrice" + tempIndex, tempMap.get("unitPrice"));
                        tempMap.put("allotQty" + tempIndex, tempMap.get("allotQty"));
                        tempMap.put("allotRatio" + tempIndex, tempMap.get("allotRatio"));
                    }

                    temp.add(tempMap);
                }
            }
            returnList.add(temp);
        }

        Map<String, Object> returnData = new HashMap<String, Object>();
        returnData.put("returnList", returnList);
        returnData.put("supplierList",supplierList);
        returnData.put("categoryList",categoryList);
        return returnData;

    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("allocationPlan:allocationPlan")
    public PageInfo list(@RequestParam Map<String, Object> params){
        //查询列表数据
        ResultMsg rsm = allotPlanService.search(params.get("pageNumber").toString(),
                params.get("pageSize").toString(),"",
                params);
        List<Map<String, Object>> allocationPlanList = new ArrayList<>();//调用接口
        for(int i=1;i<11;i++){
            //做测试数据 调用接口前使用 begin
            Map<String, Object> requirePlanMap = new HashMap<>();
            requirePlanMap.put("status","状态"+i);
            requirePlanMap.put("planNo","编号"+i);
            requirePlanMap.put("name","名称"+i);
            requirePlanMap.put("authorDept","编制部门"+i);
            requirePlanMap.put("budgetMoney","1000");
            requirePlanMap.put("totalMoney","2000");
            requirePlanMap.put("authorUser","编制人"+i);
            requirePlanMap.put("createDate","2018-8-27");
            allocationPlanList.add(requirePlanMap);
        }
        int total = 10;//调用接口
        PageInfo pageInfo = new PageInfo(allocationPlanList,
                Integer.parseInt(params.get("pageNumber").toString()));
        pageInfo.setTotal(total);
        return pageInfo;
    }

    @ResponseBody
    @GetMapping("/allocationPlanAddList")
    @RequiresPermissions("allocationPlan:allocationPlan")
    public PageInfo allocationPlanAddList(@RequestParam Map<String, Object> params){
        //查询列表数据
//		Query query = new Query(params);
        List<Map<String, Object>> allocationPlanAddList = new ArrayList<>();//调用接口
        for(int i=1;i<21;i++){
            //做测试数据 调用接口前使用 begin
            Map<String, Object> requireMap = new HashMap<>();
            requireMap.put("purchasePlanItemId",i);
            requireMap.put("materialName","物资A"+i);
            requireMap.put("materilaCode","物资编码"+i);
            requireMap.put("brand","型号"+i);
            requireMap.put("materialUnitName","单位"+i);
            requireMap.put("requireQty","1000");
            requireMap.put("requireDept","需求部门"+i);
            requireMap.put("requireDate","2018-8-27");
            requireMap.put("createDate","2018-8-20");
            requireMap.put("remark","sb");
            allocationPlanAddList.add(requireMap);
        }
        int total = 20;//调用接口
        PageInfo pageInfo = new PageInfo(allocationPlanAddList, total);
        return pageInfo;
    }

    @GetMapping("/nextStep/{purchasePlanId}")
    @RequiresPermissions("allocationPlan:add")
    String nextStep(@PathVariable("purchasePlanId") String purchasePlanId, Model model){
//        String tabNames = "办公用品类,机物料类,单独采购";
//        model.addAttribute("tabNames",tabNames);
        model.addAttribute("purchasePlanId",purchasePlanId);

        return "material/allocationPlan/nextStep";
    }

    @ResponseBody
    @GetMapping("/getAllocationPlanDetail")
    @RequiresPermissions("allocationPlan:add")
    Map<String, Object> getSourcePlanDetail(@RequestParam("code") String code){
        List<Map<String, Object>> getAllocationPlanDetailList = new ArrayList<>();//调用接口
        List<String> categoryList = new ArrayList<>();//调用接口
        String codeArray[] = code.split(",");
        //做测试数据 调用接口前使用 begin
        for (int i = 0; i < 10; i++) {
            Map<String, Object> allocationMap = new HashMap<>();
            if (i < 7){
                if (i < 4){
                    allocationMap.put("materialClassName", "办公用品类");

                }
                else {
                    allocationMap.put("materialClassName", "机物料类");

                }
                if (i % 2 == 0){
                    allocationMap.put("companyId", "供应商Id" + 1);
                    allocationMap.put("companyName", "供应商" + 1);
                    allocationMap.put("authorDeptName", "需求机构" + i);
                    allocationMap.put("materialName", "物料名称" + i);
                    allocationMap.put("materilaCode", "物料编码" + i);
                    allocationMap.put("specification", "物料特性" + i);
                    allocationMap.put("materialUnitName","单位");
                    allocationMap.put("purchaseQty","1000");
                    allocationMap.put("unitPrice","4000");
                    allocationMap.put("allotQty","200");
                    allocationMap.put("allotRatio","50");
                }
                if (i % 2 == 1){
                    allocationMap.put("companyId", "供应商Id" + 2);
                    allocationMap.put("companyName", "供应商" + 2);
                    allocationMap.put("authorDeptName", "需求机构" + i);
                    allocationMap.put("materialName", "物料名称" + i);
                    allocationMap.put("materilaCode", "物料编码" + i);
                    allocationMap.put("specification", "物料特性" + i);
                    allocationMap.put("materialUnitName","单位");
                    allocationMap.put("purchaseQty","500");
                    allocationMap.put("unitPrice","60");
                    allocationMap.put("allotQty","99");
                    allocationMap.put("allotRatio","15");
                }
            }else {
                allocationMap.put("companyId", "供应商Id" + i);
                allocationMap.put("companyName", "供应商" + i);
                allocationMap.put("authorDeptName", "需求机构" + i);
                allocationMap.put("materialClassName", "单独采购");
                allocationMap.put("materialName", "物料名称" + i);
                allocationMap.put("materilaCode", "物料编码" + i);
                allocationMap.put("specification", "物料特性" + i);
                allocationMap.put("materialUnitName","单位");
                allocationMap.put("purchaseQty","600");
                allocationMap.put("unitPrice","124");
                allocationMap.put("allotQty","78");
                allocationMap.put("allotRatio","6");
            }

            getAllocationPlanDetailList.add(allocationMap);
        }
        List<String> tempList = new ArrayList<>();
        for (int i = 0; i < getAllocationPlanDetailList.size(); i++){
            tempList.add((String) getAllocationPlanDetailList.get(i).get("materialClassName"));

        }
        for(String str : tempList) {
            if(!categoryList.contains(str)) {
                categoryList.add(str);
            }
        }

        List<List> supplierList = new ArrayList<>();//调用接口
        for(int i = 0; i < categoryList.size()-1; i++){
            List<Map<String, Object>> temp = new ArrayList<>();

            for (int j = 0; j < getAllocationPlanDetailList.size(); j++){
                Map<String, Object> supplierMap = new HashMap<>();
                if (categoryList.get(i) == getAllocationPlanDetailList.get(j).get("materialClassName")){
                    supplierMap.put("companyId",getAllocationPlanDetailList.get(j).get("companyId"));
                    supplierMap.put("companyName",getAllocationPlanDetailList.get(j).get("companyName"));
                    temp.add(supplierMap);
                }
            }
            clearList(temp);
            supplierList.add(temp);
        }


        List<List> returnList = new ArrayList<>();

        for (int i = 0; i < categoryList.size(); i++){
            int companyIndex = 0;
            List<String> companyList = new ArrayList<>();
            List<Map<String, Object>> temp = new ArrayList<>();
            for (int j = 0; j < getAllocationPlanDetailList.size(); j++){
                Map<String, Object> tempMap = getAllocationPlanDetailList.get(j);
                if (categoryList.get(i) == tempMap.get("materialClassName")){
                    if(companyList.size() == 0 ||!companyList.contains(tempMap.get("companyId"))){

                        companyList.add((String) tempMap.get("companyId"));
                        tempMap.put("unitPrice" + companyIndex, tempMap.get("unitPrice"));
                        tempMap.put("allotQty" + companyIndex, tempMap.get("allotQty"));
                        tempMap.put("allotRatio" + companyIndex, tempMap.get("allotRatio"));
                        companyIndex++;
                    }else {
                        int tempIndex = companyList.indexOf(tempMap.get("companyId"));
                        tempMap.put("unitPrice" + tempIndex, tempMap.get("unitPrice"));
                        tempMap.put("allotQty" + tempIndex, tempMap.get("allotQty"));
                        tempMap.put("allotRatio" + tempIndex, tempMap.get("allotRatio"));
                    }

                    temp.add(tempMap);
                }
            }
            returnList.add(temp);
        }

        Map<String, Object> returnData = new HashMap<String, Object>();
        returnData.put("returnList", returnList);
        returnData.put("supplierList",supplierList);
        returnData.put("categoryList",categoryList);
        return returnData;

    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("allocationPlan:add")
    public R save(@RequestParam Map<String, Object> params){
        AllotPlanBean allotPlanBean = new AllotPlanBean();
        allotPlanBean.setName((String) params.get("title"));
        allotPlanBean.setCode((String)params.get("planNo"));
//        allotPlanBean.setRequireTypeName((String)params.get("type"));
        allotPlanBean.setAuthorUserName((String)params.get("authorUser"));
        allotPlanBean.setRemark((String)params.get("remark"));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date createDate = dateFormat.parse((String)params.get("createDate"));
            allotPlanBean.setCreateDate(createDate);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        ArrayList<AllotPlanItemBean> itemList = new ArrayList<>();
        JSONArray jsonArray = JSONArray.fromObject(params.get("applyEntryJsonArray"));
        for (int i = 0; i < jsonArray.size(); i++){
            AllotPlanItemBean allotPlanItemBean = (AllotPlanItemBean) JSONObject.toBean((JSONObject)jsonArray.get(i), AllotPlanItemBean.class);
            itemList.add(allotPlanItemBean);
        }
        allotPlanBean.setAllotPlanItemBeans(itemList);
        ResultMsg rms =allotPlanService.save(allotPlanBean);
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
    @RequiresPermissions("allocationPlan:edit")
    public R update(@RequestParam Map<String, Object> params){
        AllotPlanBean allotPlanBean = new AllotPlanBean();
        allotPlanBean.setName((String) params.get("title"));
        allotPlanBean.setCode((String)params.get("planNo"));
//        allotPlanBean.setRequireTypeName((String)params.get("type"));
        allotPlanBean.setAuthorUserName((String)params.get("authorUser"));
        allotPlanBean.setRemark((String)params.get("remark"));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date createTime = dateFormat.parse((String)params.get("createDate"));
            Date createDate=new java.sql.Date(createTime.getTime()); 
            allotPlanBean.setCreateDate(createDate);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        ArrayList<AllotPlanItemBean> itemList = new ArrayList<>();
        JSONArray jsonArray = JSONArray.fromObject(params.get("applyEntryJsonArray"));
        for (int i = 0; i < jsonArray.size(); i++){
            AllotPlanItemBean allotPlanItemBean = (AllotPlanItemBean) JSONObject.toBean((JSONObject)jsonArray.get(i), AllotPlanItemBean.class);
            itemList.add(allotPlanItemBean);
        }
        allotPlanBean.setAllotPlanItemBeans(itemList);
        ResultMsg rms =allotPlanService.save(allotPlanBean);
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
    @RequiresPermissions("requirementPlan:remove")
    public R remove( String id){
        //调用接口
        ResultMsg rms = allotPlanService.remove(id);
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
    @RequiresPermissions("allocationPlan:submitApproval")
    public R submitApproval( String planNo){
        System.out.println(planNo);
        //int contactIds = service.save(customerContact);

        return R.ok();
    }
    /**
     * 撤回审批
     */
    @PostMapping( "/withdrawApproval")
    @ResponseBody
    @RequiresPermissions("allocationPlan:withdrawApproval")
    public R withdrawApproval( String planNo){
        System.out.println(planNo);
        //int contactIds = service.save(customerContact);

        return R.ok();
    }

    /**
     * 导出
     */
    @RequestMapping(value = "/export")
//    @RequiresPermissions("allocationPlan:add")
    public @ResponseBody void export(@RequestParam Map<String, Object> params,HttpServletResponse response,
                                     HttpServletRequest request){
        ArrayList<AllotPlanItemBean> itemList = new ArrayList<>();
        JSONArray jsonArray = JSONArray.fromObject(params.get("applyEntryJsonArray"));
        for (int i = 0; i < jsonArray.size(); i++){
            AllotPlanItemBean allotPlanItemBean = (AllotPlanItemBean) JSONObject.toBean((JSONObject)jsonArray.get(i), AllotPlanItemBean.class);
            itemList.add(allotPlanItemBean);
        }
        if (itemList.size() > 0) {
            System.out.println("---------------------list.size------------------->" + itemList.size());
            response.setContentType("application/binary;charset=UTF-8");
            try {
                ServletOutputStream out = response.getOutputStream();
                String fileName = new String((new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date()).getBytes(),
                        "UTF-8");
                response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
                String[] titles = { "物资类别名称", "物资编码", "物资名称", "单位名称", "规格",
                        "供应商id", "分配比例", "分配数量", "合同单价"};
                exportExcel(titles, out, itemList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    /**
     * 导出Excel写入文件方法
     */
    private static void exportExcel(String[] titles, ServletOutputStream out, ArrayList<AllotPlanItemBean> list) {
        try {
            // 第一步，创建一个workbook，对应一个Excel文件
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet hssfSheet = workbook.createSheet("sheet1");
            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
            HSSFRow hssfRow = hssfSheet.createRow(0);
            // 第四步，创建单元格，并设置值表头 设置表头居中
            HSSFCellStyle hssfCellStyle = workbook.createCellStyle();
            // 居中样式
            hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            HSSFCell hssfCell = null;
            for (int i = 0; i < titles.length; i++) {
                hssfCell = hssfRow.createCell(i);// 列索引从0开始
                hssfCell.setCellValue(titles[i]);// 列名1
                hssfCell.setCellStyle(hssfCellStyle);// 列居中显示
            }
            // 第五步，写入实体数据
            if (list != null && !list.isEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                for (int i = 0; i < list.size(); i++) {
                    hssfRow = hssfSheet.createRow(i + 1);
                    AllotPlanItemBean report = list.get(i);
                    /** 第六步，创建单元格，并设置值 **/
                    // 序号
                    hssfRow.createCell(0).setCellValue(i + 1);
                    // 物资类别名称
                    String materialClassName = "";
                    if (report.getMaterialClassName() != null) {
                        materialClassName = report.getMaterialClassName();
                    }
                    hssfRow.createCell(0).setCellValue(materialClassName);
                    // 物资编码
                    String materilaCode = "";
                    if (report.getMaterilaCode() != null) {
                        materilaCode = report.getMaterilaCode();
                    }
                    hssfRow.createCell(1).setCellValue(materilaCode);
                    // 物资名称
                    String materialName = "";
                    if (report.getMaterialName() != null) {
                        materialName = report.getMaterialName();
                    }
                    hssfRow.createCell(2).setCellValue(materialName);
                    // 单位名称
                    String materialUnitName = "";
                    if (report.getMaterialUnitName() != null) {
                        materialUnitName = report.getMaterialUnitName();
                    }
                    hssfRow.createCell(3).setCellValue(materialUnitName);
                    // 规格
                    String specification = "";
                    if (report.getSpecification() != null) {
                        specification = report.getSpecification();
                    }
                    hssfRow.createCell(4).setCellValue(specification);
                    // 供应商id
                    String companyId = "";
                    if (report.getCompanyId() != null) {
                        companyId = report.getCompanyId();
                    }
                    hssfRow.createCell(5).setCellValue(companyId);
                    // 分配比例
                    double allotRatio = 0;
                    if (report.getAllotRatio() != null) {
                        allotRatio = report.getAllotRatio();
                    }
                    hssfRow.createCell(6).setCellValue(allotRatio);
                    // 分配数量
                    double allotQty = 0;
                    if (report.getAllotQty() != null) {
                        allotQty = report.getAllotQty();
                    }
                    hssfRow.createCell(7).setCellValue(allotQty);
                    // 合同单价
                    BigDecimal unitPrice = null;
                    if (report.getUnitPrice() != null) {
                        unitPrice = report.getUnitPrice();
                    }
                    hssfRow.createCell(8).setCellValue(String.valueOf(unitPrice));
                }
            }
            // 第七步，将文件输出到客户端浏览器
            try {
                workbook.write(out);
                out.flush();
                out.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                throw new Exception("企业信息导出失败！");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }


    private static void clearList(List<Map<String, Object>> list)
    {
        if (list == null) return;
        Set<Object> set = new HashSet<Object>();
        for (Iterator<Map<String, Object>> it = list.iterator(); it.hasNext();)
        {
            //里面的map至少有一个元素，不然报错
            Object value = it.next().entrySet().iterator().next().getValue();
            if (set.contains(value))
            {
                it.remove();
            }
            else
            {
                set.add(value);
            }
        }
    }
}
