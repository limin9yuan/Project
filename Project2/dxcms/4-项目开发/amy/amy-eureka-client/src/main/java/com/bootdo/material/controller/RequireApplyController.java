package com.bootdo.material.controller;


import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.*;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.dx.client.model.datacenter.MaterialBean;
import com.dx.client.model.purchase.RequireApplyItemBean;
import com.dx.client.model.purchase.RequireApplyBean;
import org.wxcl.amy.utils.common.ResultMsg;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@RefreshScope
@Controller
@RequestMapping("/material/requireApply")
public class RequireApplyController extends BaseController {
    @Autowired
    private com.dx.service.purchase.service.api.IRequireApplyService requireApplyService;
    @Autowired
    private com.dx.service.datacenter.service.api.IMaterialService  materialService;



    private String prefix="material/requireApply"  ;

    /**
     * 采购申请管理页
     */
    @RequiresPermissions("material:requireApply:requireApply")
    @GetMapping("")
    String requireApply(Model model) {
        return prefix + "/requireApply";
    }

    /**
     * 采购申请编制页
     */
    @GetMapping("/add")
    @RequiresPermissions("material:requireApply:add")
    String addRequireApply(Model model){
        Long createUserId =getUser().getUserId();
        String createUserName =getUser().getUsername();
        String deptName =getUser().getDeptName();
        Long deptId =getUser().getDeptId();
        String code = "";
        String businessDate = DateUtils.format(new Date(),DateUtils.DATE_PATTERN);
        String name = businessDate.substring(0,4)+"年"+businessDate.substring(5,7)+"月采购申请";

        model.addAttribute("name", name);//名称
        model.addAttribute("code", code);//编号
        model.addAttribute("authorCorpName", deptName); //编制机构名称
        model.addAttribute("businessDate", businessDate); //编制机构名称
        model.addAttribute("authorCorpName", deptName); //编制部门名称
        model.addAttribute("authorCorpId", deptId); //编制部门Id

        model.addAttribute("createUserId", createUserId); //编制人Id
        model.addAttribute("createUserName", createUserName); //编制人姓名
        model.addAttribute("createDate", businessDate);//编制日期

        return prefix + "/add";
    }

    /**
     * 物资明细列表页
     */
    @GetMapping("/materialList")
    @RequiresPermissions("material:requireApply:add")
    String materialList(Model model){
        return prefix + "/materialList";
    }

    /**
     * 修改页
     */
    @GetMapping("/edit/{id}")
    @RequiresPermissions("material:requireApply:edit")
    String edit(@PathVariable("id") String id, Model model) {
        //调用接口
        ResultMsg rm = requireApplyService.primary(id);
        //做测试数据 begin
        RequireApplyBean requireApplyModel = new RequireApplyBean();
        requireApplyModel.setId(id);
        requireApplyModel.setName("2018年8月采购申请");
        requireApplyModel.setCode(id);
        requireApplyModel.setAuthorCorpId("8");
        requireApplyModel.setAuthorCorpName("研发二部");
        //requireApplyModel.setBusinessDate(new Date("YYYY-MM-DD"));
        //requireApplyModel.setAuthorCorpId("编制部门Id");
        //requireApplyModel.setCreateUserId("编制人Id");
        requireApplyModel.setCreateUserName("编制人姓名");
        requireApplyModel.setRemark("备注");
        rm = new ResultMsg();
        rm.setData(requireApplyModel);
        //做测试数据 end
        model.addAttribute("requireApplyModel", rm.getData());//编制日期
        return prefix + "/edit";
    }
    /**
     * 查看页
     */
    @GetMapping("/view/{id}")
    @RequiresPermissions("material:requireApply:requireApply")
    String view(@PathVariable("id") String id, Model model) {
        //调用接口
        ResultMsg rm = requireApplyService.primary(id);
        //做测试数据 begin
        RequireApplyBean requireApplyModel = new RequireApplyBean();
        requireApplyModel.setId(id);
        requireApplyModel.setName("2018年8月采购申请");
        requireApplyModel.setCode(id);
        requireApplyModel.setAuthorCorpId("8");
        requireApplyModel.setAuthorCorpName("研发二部");
        //requireApplyModel.setBusinessDate(new Date("YYYY-MM-DD"));
        //requireApplyModel.setAuthorCorpId("编制部门Id");
        //requireApplyModel.setCreateUserId("编制人Id");
        requireApplyModel.setCreateUserName("编制人姓名");
        requireApplyModel.setRemark("备注");
        rm = new ResultMsg();
        rm.setData(requireApplyModel);
        //做测试数据 end
        model.addAttribute("requireApplyModel", rm.getData());
        return prefix + "/view";
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("material:requireApply:edit")
    public R update(@RequestParam Map<String, Object> params) {

        RequireApplyBean requireApplyModel = new RequireApplyBean();
        requireApplyModel.setName((String)params.get("name"));
        requireApplyModel.setCode((String)params.get("code"));
        requireApplyModel.setAuthorCorpId((String)params.get("authorCorpId"));
        requireApplyModel.setCreateUserId((String)params.get("createUserId"));
        requireApplyModel.setRemark((String)params.get("remark"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date createDate = sdf.parse((String)params.get("createDate"));
            requireApplyModel.setCreateDate(createDate);
            Date businessDate = sdf.parse((String)params.get("businessDate"));
            requireApplyModel.setBusinessDate(businessDate);
        }catch (Exception e){
            return R.error();
        }
        List itemList = new ArrayList<RequireApplyItemBean>();

        JSONArray array = JSONArray.fromObject(params.get("applyEntryJson"));
        for(int i=0;i<array.size();i++){
            System.out.println(array.get(i));
            RequireApplyItemBean requireApplyItemBean = (RequireApplyItemBean) JSONObject.toBean((JSONObject)array.get(i), RequireApplyItemBean.class);
            itemList.add(requireApplyItemBean);
        }
        requireApplyModel.setRequireApplyItemBeans(itemList);
        ResultMsg rms = requireApplyService.save(requireApplyModel);
        if ("1".equals(rms.getCode())) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("material:requireApply:remove")
    public R remove(String id) {

        //调用接口
        ResultMsg rms = requireApplyService.remove(id);
        if ("1".equals(rms.getCode())) {
            return R.ok();
        }
        return R.error(rms.getCode(), rms.getMsg());
    }

    /**
     * 取得选择物资列表
     */
    @GetMapping("/getMaterialList")
    @RequiresPermissions("material:requireApply:add")
    @ResponseBody
    PageInfo getMaterialList(@RequestParam Map<String, Object> params){
        // 查询列表数据
        ResultMsg rsm = materialService.search(params.get("pageNumber").toString(),
                params.get("pageSize").toString(),"",
                params);
        //做测试数据 begin
        List<Map<String, Object>> materialList = new ArrayList();
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
        int total = 20;
        PageInfo pageInfo = new PageInfo(materialList,
                Integer.parseInt(params.get("pageNumber").toString()));
        pageInfo.setTotal(total);
        //做测试数据 end
        //PageInfo pageInfo = (PageInfo)rsm.getData();//调用接口
        return pageInfo;
    }

    /**
     * 采购申请列表
     */
    @GetMapping("/list")
    @ResponseBody
    PageInfo list(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        ResultMsg rsm = requireApplyService.search(params.get("pageNumber").toString(),
                params.get("pageSize").toString(),"",
                params);
        //做测试数据 begin
        List<Map<String, Object>> requireApplyList = new ArrayList();
        for(int i=1;i<11;i++){
            Map<String, Object> materialMap = new HashMap<>();
            materialMap.put("id",i);
            materialMap.put("status","状态"+i);
            materialMap.put("planNo",i);
            materialMap.put("name","名称"+i);
            materialMap.put("authorDeptName","编制部门"+i);
            materialMap.put("purchaseDept","统管部门"+i);
            materialMap.put("budgetMoney","100"+i);
            materialMap.put("totalMoney","1000"+i);
            materialMap.put("authorUserName","编制人"+i);
            materialMap.put("createDate","2018-08-"+String.valueOf(10+i));
            requireApplyList.add(materialMap);
        }
        int total = 20;
        PageInfo pageInfo = new PageInfo(requireApplyList,
                Integer.parseInt(params.get("pageNumber").toString()));
        pageInfo.setTotal(total);
        //做测试数据 end
        //PageInfo pageInfo = (PageInfo)rsm.getData();//调用接口

        return pageInfo;
    }

    /**
     * 采购申请明细
     */
    @GetMapping("/requireMaterialDetailList")
    @ResponseBody
    PageInfo requireMaterialDetailList(@RequestParam Map<String, Object> params) {

        ResultMsg rsm = materialService.search(params.get("pageNumber").toString(),
                params.get("pageSize").toString(),
                "",params);
        // 查询列表数据
        List<RequireApplyItemBean> requireMaterialDetailList = new ArrayList();//调用接口
        //做测试数据 调用接口前使用 begin
        /*RequireApplyDetailModel requireApplyDetailModel = new  RequireApplyDetailModel();
        MaterialModel ma = new MaterialModel();
        ma.setName("测试物资");
        requireApplyDetailModel.setMaterial(ma);
        requireMaterialDetailList.add(requireApplyDetailModel);*/
        //做测试数据 end
        int total = 1;//调用接口
        PageInfo pageInfo = new PageInfo(requireMaterialDetailList, total);
        return pageInfo;
    }

    /**
     * 取得一条采购物资记录
     */
    @ResponseBody
    @GetMapping("/getMaterialDetailByCode/{code}")
    @RequiresPermissions("material:requireApply:add")
    Map<String, Object> getMaterialDetailByCode(@PathVariable("code") String code){
        //调用接口
        List ml = new ArrayList();
        ml.add(code);
        ResultMsg rsm = requireApplyService.createItems(ml);
        //做测试数据 begin
        RequireApplyItemBean materialItemBean = new RequireApplyItemBean();
        materialItemBean.setMaterialName("物资A"+code);
        materialItemBean.setMaterialSubArray("物资M包装"+code);
        materialItemBean.setMaterialCode("物资编码"+code);
        materialItemBean.setMaterialUnitName("单位"+code);
        materialItemBean.setSpecification("规格型号"+code);
        materialItemBean.setTexture("材质"+code);
        //materialItemBean.setOutsideBarCode("包装物资"+code);
        materialItemBean.setBudgetQty(1000.23);//预算数量
        materialItemBean.setBudgetPrice(new BigDecimal("900.24"));//预算单价
        materialItemBean.setReferencePrice(new BigDecimal("900.24"));//参考单价
        materialItemBean.setStockQty(200.34);//库存数量
        materialItemBean.setAcceptUserName("张三");
        materialItemBean.setAcceptUserId("zhangsan");
        Map<String, Object> returnData = new HashMap<String, Object>();
        rsm = new ResultMsg();
        rsm.setData(materialItemBean);
        //做测试数据 end
        returnData.put("materialDetail", rsm.getData());
        return returnData;

    }
    /**
     * 取得采购申请物资明细列表
     */
    @GetMapping("/getRequireApplyDetailByCode")
    @RequiresPermissions("material:requireApply:edit")
    @ResponseBody
    Map<String, Object> getRequireApplyDetailByCode(@RequestParam("id") String id){
        //调用接口
        ResultMsg rsm = requireApplyService.detail(id);

        //做测试数据 调用接口前使用 begin
        List<RequireApplyItemBean> requireApplyDetailList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            //做测试数据 begin
            RequireApplyItemBean materialItemBean = new RequireApplyItemBean();
            materialItemBean.setMaterialName("物资A"+id);
            //materialItemBean.setMaterialClassName("物资类别"+code);
            materialItemBean.setMaterialCode("物资编码"+id);
            materialItemBean.setMaterialUnitName("单位"+id);
            materialItemBean.setSpecification("规格型号"+id);
            materialItemBean.setTexture("材质"+id);
            //materialItemBean.setOutsideBarCode("包装物资"+code);
            materialItemBean.setBudgetQty(1000.23);//预算数量
            materialItemBean.setRequireQty(500.90);//需求数量
            materialItemBean.setBudgetPrice(new BigDecimal("900.24"));//预算单价
            materialItemBean.setReferencePrice(new BigDecimal("900.24"));//参考单价
            materialItemBean.setStockQty(200.34);//库存数量
            materialItemBean.setAcceptUserName("张三");
            materialItemBean.setAcceptUserId("zhangsan");
            requireApplyDetailList.add(materialItemBean);
        }
        Map<String, Object> returnData = new HashMap<String, Object>();
        returnData.put("requireApplyDetailList", requireApplyDetailList);

        return returnData;

    }
    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("material:requireApply:add")
    public R save(@RequestParam Map<String, Object> params) {
        System.out.println(params);
        //int contactIds = service.save(customerContact);

        return R.ok();
    }

    /**
     * 提交审批
     */
    @ResponseBody
    @PostMapping("/commitApply")
    @RequiresPermissions("material:requireApply:approve")
    public R approve(@RequestParam Map<String, Object> params) {
        System.out.println(params);
        //int contactIds = service.save(customerContact);
        R r = new R();
        r.put("id",1);
        return r.ok();
    }

    /**
     * 取消审批
     */
    @ResponseBody
    @PostMapping("/cancelApprove")
    @RequiresPermissions("material:requireApply:cancelApprove")
    public R cancelApply(@RequestParam Map<String, Object> params) {
        System.out.println(params);
        //int contactIds = service.save(customerContact);

        return R.ok();
    }
}
