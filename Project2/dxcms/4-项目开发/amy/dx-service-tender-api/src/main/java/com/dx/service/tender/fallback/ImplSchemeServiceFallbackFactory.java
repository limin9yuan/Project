package com.dx.service.tender.fallback;

import com.dx.client.model.tender.ImplSchemeBean;
import com.dx.client.model.tender.TenderElementBean;
import com.dx.client.model.tender.TenderHtmlBean;
import com.dx.client.model.tender.TenderSchemeBean;
import com.dx.service.tender.service.api.IImplSchemeService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.wxcl.amy.utils.common.ResultMsg;

import java.util.List;
import java.util.Map;

/**
 * @Auther: DX01
 * @Date: 2018/9/5 14:26
 * @Description:
 */
@Component("implSchemeServiceFallbackFactory")
public class ImplSchemeServiceFallbackFactory implements FallbackFactory<IImplSchemeService> {
    @Override
    public IImplSchemeService create(Throwable throwable) {
        return new IImplSchemeService() {
            @Override
            public ResultMsg save(ImplSchemeBean implSchemeBean, List<TenderSchemeBean> tenderSchemeBeans, List<TenderElementBean> tenderElementBeans, TenderHtmlBean tenderHtmlBean, boolean isSubmit) {
                return null;
            }

            @Override
            public ResultMsg cancel(String implSchemeId) {
                return null;
            }

            @Override
            public ResultMsg remove(String implSchemeId) {
                return null;
            }

            @Override
            public ResultMsg primary(String implSchemeId) {
                return null;
            }

            @Override
            public ResultMsg getElements(String implSchemeId) {
                return null;
            }

            @Override
            public ResultMsg search(String pageNum, String pageSize, String orderBy, Map<String, Object> params) {
                return null;
            }
        };
    }
}
