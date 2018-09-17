package com.dx.service.purchase.service.api;

import com.dx.client.model.purchase.*;
import com.dx.service.purchase.config.PurchaseFeignConfig;
import com.dx.service.purchase.fallback.PurchaseOrderServiceFallbackFactory;
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
 * @Date: 2018/9/3 14:32
 * @Description:采购订单API
 */
@FeignClient(name="PURCHASESERVICE.DX.COM",
        configuration = PurchaseFeignConfig.class,
        fallbackFactory = PurchaseOrderServiceFallbackFactory.class
)
public interface IPurchaseOrderService {
    //保存
    @RequestMapping("/purchase/purchaseOrderService/save")
    @ResponseBody
    public ResultMsg save(@RequestBody PurchaseOrderBean purchaseOrderBean);

    //注销
    @RequestMapping("/purchase/purchaseOrderService/cancel")
    @ResponseBody
    public ResultMsg cancel(@RequestParam("purchaseOrderId") String purchaseOrderId);

    //删除
    @RequestMapping("/purchase/purchaseOrderService/remove")
    @ResponseBody
    public ResultMsg remove(@RequestParam("purchaseOrderId") String purchaseOrderId);

    //提交审批
    @RequestMapping("/purchase/purchaseOrderService/submit")
    @ResponseBody
    public ResultMsg submit(@RequestBody List<PurchaseOrderBean> purchaseOrderBeans);

    //主要
    //返回数据类型PurchaseOrderBean
    @RequestMapping("/purchase/purchaseOrderService/primary")
    @ResponseBody
    public ResultMsg primary(@RequestParam("purchaseOrderId") String purchaseOrderId);

    //详细
    //返回数据类型List<PurchaseOrderItemBean>
    @RequestMapping("/purchase/purchaseOrderService/detail")
    @ResponseBody
    public ResultMsg detail(@RequestParam("purchaseOrderId") String purchaseOrderId);

    //获取待受理明细-货源计划主表
    //返回数据类型List<AllotPlanBean>
    @RequestMapping("/purchase/purchaseOrderService/getWaitAcceptedItems")
    @ResponseBody
    public ResultMsg getWaitAcceptedItems(@RequestParam("acceptUserId") String acceptUserId);

    //依据货源计划生成采购订单列表
    @RequestMapping("/purchase/purchaseOrderService/createOrders")
    @ResponseBody
    public ResultMsg createOrders(@RequestBody List<AllotPlanBean> allotPlanBeans);

    //上传订单
    @RequestMapping("/purchase/purchaseOrderService/pushedOrders")
    @ResponseBody
    public ResultMsg pushedOrders(@RequestBody List<PurchaseOrderBean> purchaseOrderBeans);

    //终止订单
    @RequestMapping("/purchase/purchaseOrderService/stopOrder")
    @ResponseBody
    public ResultMsg stopOrder(@RequestBody PurchaseOrderBean purchaseOrderBean);

    //关闭订单(订单执行完毕，关闭订单，关闭后的订单不允许任何修改）
    @RequestMapping("/purchase/purchaseOrderService/closeOrder")
    @ResponseBody
    public ResultMsg closeOrder(@RequestBody PurchaseOrderBean purchaseOrderBean);

    //变更订单
    //purchaseOrderId：变更订单id，afterPurchaseOrderBean：变更后订单
    //返回数据：List<purchaseOrderChangeBean>：变更记录
    @RequestMapping("/purchase/purchaseOrderService/changeOrder")
    @ResponseBody
    public ResultMsg changeOrder(@RequestBody PurchaseOrderBean purchaseOrderBean);

    //打印计数
    @RequestMapping("/purchase/purchaseOrderService/printCountOrder")
    @ResponseBody
    public ResultMsg printCountOrder(@RequestParam("purchaseOrderId") String purchaseOrderId, @RequestParam("printUserId") String printUserId);

    //修改执行人
    @RequestMapping("/purchase/purchaseOrderService/modifyExecuter")
    @ResponseBody
    public ResultMsg modifyExecuter(@RequestBody List<PurchaseOrderBean> purchaseOrderBeans, @RequestParam("executerId") String executerId);

    //查询
    //orderBy参数如：blog_ID desc，示例代码：PageHelper.startPage(pageNum , pageSize); PageHelper.orderBy("blog_ID desc");
    @RequestMapping("/purchase/purchaseOrderService/search")
    @ResponseBody
    public ResultMsg search(@RequestParam("pageNum") String pageNum,
                            @RequestParam("pageSize") String pageSize,
                            @RequestParam("orderBy") String orderBy,
                            @RequestParam("params") Map<String, Object> params);
}
