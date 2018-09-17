package com.dx.service.purchase.service.api;

import com.dx.client.model.purchase.PurchasePlanBean;
import com.dx.client.model.purchase.PurchasePlanItemBean;
import com.dx.client.model.purchase.RequirePlanItemBean;
import com.dx.service.purchase.config.PurchaseFeignConfig;
import com.dx.service.purchase.fallback.PurchasePlanServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wxcl.amy.utils.common.ResultMsg;

import java.util.List;
import java.util.Map;

/**
 * @Auther: DX01
 * @Date: 2018/9/3 14:31
 * @Description:采购计划API
 */
@FeignClient(name="PURCHASESERVICE.DX.COM",
        configuration = PurchaseFeignConfig.class,
        fallbackFactory = PurchasePlanServiceFallbackFactory.class
)
public interface IPurchasePlanService {
    //保存
    @RequestMapping("/purchase/purchasePlanService/save")
    @ResponseBody
    public ResultMsg save(@RequestBody PurchasePlanBean purchasePlanBean);

    //注销
    @RequestMapping("/purchase/purchasePlanService/cancel")
    @ResponseBody
    public ResultMsg cancel(@RequestParam("purchasePlanId") String purchasePlanId);

    //删除
    @RequestMapping("/purchase/purchasePlanService/remove")
    @ResponseBody
    public ResultMsg remove(@RequestParam("purchasePlanId") String purchasePlanId);

    //提交审批
    @RequestMapping("/purchase/purchasePlanService/submit")
    @ResponseBody
    public ResultMsg submit(@RequestBody PurchasePlanBean purchasePlanBean);

    //主要
    //返回数据类型PurchasePlanBean
    @RequestMapping("/purchase/purchasePlanService/primary")
    @ResponseBody
    public ResultMsg primary(@RequestParam("purchasePlanId") String purchasePlanId);

    //详细
    //返回数据类型List<PurchasePlanItemBean>
    @RequestMapping("/purchase/purchasePlanService/detail")
    @ResponseBody
    public ResultMsg detail(@RequestParam("purchasePlanId") String purchasePlanId);

    //依据需求计划明细生成采购计划明细
    //返回数据：List<PurchasePlanItemBean>
    @RequestMapping("/purchase/purchasePlanService/createItems")
    @ResponseBody
    public ResultMsg createItems(@RequestBody List<RequirePlanItemBean> requirePlanItemBeans);

    //获取待受理明细-需求计划明细
    //返回数据类型Map
    @RequestMapping("/purchase/purchasePlanService/getWaitAcceptedItems")
    @ResponseBody
    public ResultMsg getWaitAcceptedItems(@RequestParam("acceptUserId") String acceptUserId);

    //查询
    //orderBy参数如：blog_ID desc，示例代码：PageHelper.startPage(pageNum , pageSize); PageHelper.orderBy("blog_ID desc");
    @RequestMapping("/purchase/purchasePlanService/search")
    @ResponseBody
    public ResultMsg search(@RequestParam("pageNum") String pageNum,
                            @RequestParam("pageSize") String pageSize,
                            @RequestParam("orderBy") String orderBy,
                            @RequestParam("params") Map<String, Object> params);
}
