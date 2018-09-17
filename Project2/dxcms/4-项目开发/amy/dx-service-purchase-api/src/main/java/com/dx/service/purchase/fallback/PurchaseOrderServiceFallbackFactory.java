package com.dx.service.purchase.fallback;

import com.dx.client.model.purchase.AllotPlanBean;
import com.dx.client.model.purchase.PurchaseOrderBean;
import com.dx.client.model.purchase.PurchaseOrderItemBean;
import com.dx.service.purchase.service.api.IPurchaseOrderService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.wxcl.amy.utils.common.ResultMsg;

import java.util.List;
import java.util.Map;

/**
 * @Auther: DX01
 * @Date: 2018/9/3 14:30
 * @Description:
 */
@Component("purchaseOrderServiceFallbackFactory")
public class PurchaseOrderServiceFallbackFactory implements FallbackFactory<IPurchaseOrderService> {
    @Override
    public IPurchaseOrderService create(Throwable throwable) {
        return new IPurchaseOrderService() {
            @Override
            public ResultMsg save(PurchaseOrderBean purchaseOrderBean) {
                return null;
            }

            @Override
            public ResultMsg cancel(String purchaseOrderId) {
                return null;
            }

            @Override
            public ResultMsg remove(String purchaseOrderId) {
                return null;
            }

            @Override
            public ResultMsg submit(List<PurchaseOrderBean> purchaseOrderBeans) {
                return null;
            }

            @Override
            public ResultMsg primary(String purchaseOrderId) {
                return null;
            }

            @Override
            public ResultMsg detail(String purchaseOrderId) {
                return null;
            }

            @Override
            public ResultMsg getWaitAcceptedItems(String acceptUserId) {
                return null;
            }

            @Override
            public ResultMsg createOrders(List<AllotPlanBean> allotPlanBeans) {
                return null;
            }

            @Override
            public ResultMsg pushedOrders(List<PurchaseOrderBean> purchaseOrderBeans) {
                return null;
            }

            @Override
            public ResultMsg stopOrder(PurchaseOrderBean purchaseOrderBean) {
                return null;
            }

            @Override
            public ResultMsg closeOrder(PurchaseOrderBean purchaseOrderBean) {
                return null;
            }

            @Override
            public ResultMsg changeOrder(PurchaseOrderBean purchaseOrderBean) {
                return null;
            }

            @Override
            public ResultMsg printCountOrder(String purchaseOrderId, String printUserId) {
                return null;
            }

            @Override
            public ResultMsg modifyExecuter(List<PurchaseOrderBean> purchaseOrderBeans, String executerId) {
                return null;
            }

            @Override
            public ResultMsg search(String pageNum, String pageSize, String orderBy, Map<String, Object> params) {
                return null;
            }
        };
    }
}
