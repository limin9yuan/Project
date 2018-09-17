package com.dx.service.tender.fallback;

import com.dx.client.model.tender.TenderElementBean;
import com.dx.client.model.tender.TenderHtmlBean;
import com.dx.client.model.tender.TenderSchemeBean;
import com.dx.service.tender.service.api.ITenderSchemeService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.wxcl.amy.utils.common.ResultMsg;

import java.util.List;
import java.util.Map;

/**
 * @Auther: DX01
 * @Date: 2018/9/5 14:53
 * @Description:
 */
@Component("tenderSchemeServiceFallbackFactory")
public class TenderSchemeServiceFallbackFactory implements FallbackFactory<ITenderSchemeService> {
    @Override
    public ITenderSchemeService create(Throwable throwable) {
        return new ITenderSchemeService() {
            @Override
            public ResultMsg save(TenderSchemeBean tenderSchemeBean, List<TenderSchemeBean> tenderSchemeBeans, List<TenderElementBean> tenderElementBeans, TenderHtmlBean tenderHtmlBean, boolean isSubmit) {
                return null;
            }

            @Override
            public ResultMsg cancel(String tenderSchemeId) {
                return null;
            }

            @Override
            public ResultMsg remove(String tenderSchemeId) {
                return null;
            }

            @Override
            public ResultMsg primary(String tenderSchemeId) {
                return null;
            }

            @Override
            public ResultMsg getElements(String tenderSchemeId) {
                return null;
            }

            @Override
            public ResultMsg search(String pageNum, String pageSize, String orderBy, Map<String, Object> params) {
                return null;
            }
        };
    }
}
