package com.dx.service.purchase.fallback;

import com.dx.client.model.purchase.AllotPlanBean;
import com.dx.client.model.purchase.AllotPlanItemBean;
import com.dx.client.model.purchase.PurchasePlanItemBean;
import com.dx.service.purchase.service.api.IAllotPlanService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.wxcl.amy.utils.common.ResultMsg;

import java.util.List;
import java.util.Map;

/**
 * @Auther: DX01
 * @Date: 2018/9/3 14:27
 * @Description:
 */
@Component("allotPlanServiceFallbackFactory")
public class AllotPlanServiceFallbackFactory implements FallbackFactory<IAllotPlanService> {
    @Override
    public IAllotPlanService create(Throwable throwable) {
        return new IAllotPlanService() {
            @Override
            public ResultMsg save(AllotPlanBean allotPlanBean) {
                return null;
            }

            @Override
            public ResultMsg cancel(String allotPlanId) {
                return null;
            }

            @Override
            public ResultMsg remove(String allotPlanId) {
                return null;
            }

            @Override
            public ResultMsg submit(AllotPlanBean allotPlanBean) {
                return null;
            }

            @Override
            public ResultMsg primary(String allotPlanId) {
                return null;
            }

            @Override
            public ResultMsg detail(String allotPlanId) {
                return null;
            }

            @Override
            public ResultMsg createItems(List<PurchasePlanItemBean> purchasePlanItemBeans) {
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
