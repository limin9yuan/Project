package com.dx.service.purchase.fallback;

import com.dx.client.model.purchase.RequireApplyItemBean;
import com.dx.client.model.purchase.RequirePlanBean;
import com.dx.client.model.purchase.RequirePlanItemBean;
import com.dx.service.purchase.service.api.IRequirePlanService;
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
@Component("requirePlanServiceFallbackFactory")
public class RequirePlanServiceFallbackFactory implements FallbackFactory<IRequirePlanService> {
    @Override
    public IRequirePlanService create(Throwable throwable) {
        return new IRequirePlanService() {
            @Override
            public ResultMsg save(RequirePlanBean requirePlanBean, List<RequirePlanItemBean> requirePlanItemBeans, boolean isSubmit) {
                return null;
            }

            @Override
            public ResultMsg cancel(String requirePlanId) {
                return null;
            }

            @Override
            public ResultMsg remove(String requirePlanId) {
                return null;
            }

            @Override
            public ResultMsg submit(RequirePlanBean requirePlanBean) {
                return null;
            }

            @Override
            public ResultMsg primary(String requirePlanId) {
                return null;
            }

            @Override
            public ResultMsg detail(String requirePlanId) {
                return null;
            }

            @Override
            public ResultMsg createItems(List<RequireApplyItemBean> requireApplyItemBeans) {
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
