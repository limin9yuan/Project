package com.dx.service.purchase.fallback;

import com.dx.client.model.purchase.PurchasePlanBean;
import com.dx.client.model.purchase.PurchasePlanItemBean;
import com.dx.client.model.purchase.RequirePlanItemBean;
import com.dx.service.purchase.service.api.IPurchasePlanService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.wxcl.amy.utils.common.ResultMsg;

import java.util.List;
import java.util.Map;

/**
 * @Auther: DX01
 * @Date: 2018/9/3 14:26
 * @Description:
 */
@Component("purchasePlanServiceFallbackFactory")
public class PurchasePlanServiceFallbackFactory implements FallbackFactory<IPurchasePlanService> {
    @Override
    public IPurchasePlanService create(Throwable throwable) {
        return new IPurchasePlanService() {
            @Override
            public ResultMsg save(PurchasePlanBean purchasePlanBean, List<PurchasePlanItemBean> purchasePlanItemBeans, boolean isSubmit) {
                return null;
            }

            @Override
            public ResultMsg cancel(String purchasePlanId) {
                return null;
            }

            @Override
            public ResultMsg remove(String purchasePlanId) {
                return null;
            }

            @Override
            public ResultMsg submit(PurchasePlanBean purchasePlanBean) {
                return null;
            }

            @Override
            public ResultMsg primary(String purchasePlanId) {
                return null;
            }

            @Override
            public ResultMsg detail(String purchasePlanId) {
                return null;
            }

            @Override
            public ResultMsg createItems(List<RequirePlanItemBean> requirePlanItemBeans) {
                return null;
            }

            @Override
            public ResultMsg getWaitAcceptedItems(String acceptUserId) {
                return null;
            }

            @Override
            public ResultMsg search(String pageNum, String pageSize, String orderBy, Map<String, Object> params) {
                return null;
            }
        };
    }
}
