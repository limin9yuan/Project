package com.dx.service.purchase.fallback;

import com.dx.client.model.datacenter.MaterialBean;
import com.dx.client.model.purchase.RequireApplyBean;
import com.dx.client.model.purchase.RequireApplyItemBean;
import com.dx.service.purchase.service.api.IRequireApplyService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.wxcl.amy.utils.common.ResultMsg;

import java.util.List;
import java.util.Map;

/**
 * @Auther: DX01
 * @Date: 2018/9/3 14:24
 * @Description:
 */
@Component("requireApplyServiceFallbackFactory")
public class RequireApplyServiceFallbackFactory implements FallbackFactory<IRequireApplyService> {
    @Override
    public IRequireApplyService create(Throwable throwable) {
        return new IRequireApplyService() {
            @Override
            public ResultMsg save(RequireApplyBean requireApplyBean, List<RequireApplyItemBean> requireApplyItemBeans, boolean isSubmit) {
                return null;
            }

            @Override
            public ResultMsg cancel(String requireApplyId) {
                return null;
            }

            @Override
            public ResultMsg remove(String requireApplyId) {
                return null;
            }

            @Override
            public ResultMsg submit(RequireApplyBean requireApplyBean) {
                return null;
            }

            @Override
            public ResultMsg primary(String requireApplyId) {
                return null;
            }

            @Override
            public ResultMsg detail(String requireApplyId) {
                return null;
            }

            @Override
            public ResultMsg createItems(List<MaterialBean> materialBeans) {
                return null;
            }

            @Override
            public ResultMsg search(String pageNum, String pageSize, String orderBy, Map<String, Object> params) {
                return null;
            }
        };
    }
}
