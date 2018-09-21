package com.dx.service.tender.fallback;

import com.dx.client.model.tender.ExpeSchemeBean;
import com.dx.client.model.tender.TenderElementBean;
import com.dx.client.model.tender.TenderHtmlBean;
import com.dx.client.model.tender.TenderSchemeBean;
import com.dx.service.tender.service.api.IExpeSchemeService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.wxcl.amy.utils.common.ResultMsg;

import java.util.List;
import java.util.Map;

/**
 * @Auther: DX01
 * @Date: 2018/9/5 14:25
 * @Description:
 */
@Component("expeSchemeServiceFallbackFactory")
public class ExpeSchemeServiceFallbackFactory implements FallbackFactory<IExpeSchemeService> {
    @Override
    public IExpeSchemeService create(Throwable throwable) {
        return new IExpeSchemeService() {
            @Override
            public ResultMsg save(ExpeSchemeBean expeSchemeBean, List<TenderSchemeBean> tenderSchemeBeans, List<TenderElementBean> tenderElementBeans, TenderHtmlBean tenderHtmlBean, boolean isSubmit) {
                return null;
            }

            @Override
            public ResultMsg cancel(String expeSchemeId) {
                return null;
            }

            @Override
            public ResultMsg remove(String expeSchemeId) {
                return null;
            }

            @Override
            public ResultMsg primary(String expeSchemeId) {
                return null;
            }

            @Override
            public ResultMsg getElements(String expeSchemeId) {
                return null;
            }

            @Override
            public ResultMsg search(String pageNum, String pageSize, String orderBy, Map<String, Object> params) {
                return null;
            }
        };
    }
}
