package com.dx.service.purchase.service;

import com.dx.client.model.purchase.AllotPlanBean;
import com.dx.client.model.purchase.PurchaseOrderBean;
import com.dx.service.purchase.service.api.IPurchaseOrderService;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wxcl.amy.utils.common.ResultMsg;
import org.wxcl.amy.utils.common.ResultUtil;

import java.util.List;
import java.util.Map;

/**
 * @Auther: DX01
 * @Date: 2018/9/21 12:52
 * @Description:
 */
@RefreshScope
@RestController
@RequestMapping("/purchase")
public class PurchaseOrderService implements IPurchaseOrderService {
    @Override
    @RequestMapping("/purchaseOrderService/save")
    public ResultMsg save(PurchaseOrderBean purchaseOrderBean) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/purchaseOrderService/cancel")
    public ResultMsg cancel(String purchaseOrderId) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/purchaseOrderService/remove")
    public ResultMsg remove(String purchaseOrderId) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/purchaseOrderService/submit")
    public ResultMsg submit(List<PurchaseOrderBean> purchaseOrderBeans) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/purchaseOrderService/primary")
    public ResultMsg primary(String purchaseOrderId) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/purchaseOrderService/detail")
    public ResultMsg detail(String purchaseOrderId) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/purchaseOrderService/getWaitAcceptedItems")
    public ResultMsg getWaitAcceptedItems(String acceptUserId) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/purchaseOrderService/createOrders")
    public ResultMsg createOrders(List<AllotPlanBean> allotPlanBeans) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/purchaseOrderService/pushedOrders")
    public ResultMsg pushedOrders(List<PurchaseOrderBean> purchaseOrderBeans) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/purchaseOrderService/stopOrder")
    public ResultMsg stopOrder(PurchaseOrderBean purchaseOrderBean) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/purchaseOrderService/closeOrder")
    public ResultMsg closeOrder(PurchaseOrderBean purchaseOrderBean) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/purchaseOrderService/changeOrder")
    public ResultMsg changeOrder(PurchaseOrderBean purchaseOrderBean) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/purchaseOrderService/printCountOrder")
    public ResultMsg printCountOrder(String purchaseOrderId, String printUserId) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/purchaseOrderService/modifyExecuter")
    public ResultMsg modifyExecuter(List<PurchaseOrderBean> purchaseOrderBeans, String executerId) {
        return ResultUtil.todo("待实现");
    }

    @Override
    @RequestMapping("/purchaseOrderService/search")
    public ResultMsg search(String pageNum, String pageSize, String orderBy, Map<String, Object> params) {
        return ResultUtil.todo("待实现");
    }
}
