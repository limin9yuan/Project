package com.dx.service.tender.fallback;

import com.dx.client.model.tender.ProjectBean;
import com.dx.client.model.tender.TenderElementBean;
import com.dx.client.model.tender.TenderHtmlBean;
import com.dx.client.model.tender.TenderSchemeBean;
import com.dx.service.tender.service.api.IProjectService;
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
@Component("projectServiceFallbackFactory")
public class ProjectServiceFallbackFactory implements FallbackFactory<IProjectService> {
    @Override
    public IProjectService create(Throwable throwable) {
        return new IProjectService() {
            @Override
            public ResultMsg save(ProjectBean projectApplyBean, List<TenderSchemeBean> tenderSchemeBeans, List<TenderElementBean> tenderElementBeans, TenderHtmlBean tenderHtmlBean, boolean isSubmit) {
                return null;
            }

            @Override
            public ResultMsg cancel(String projectId) {
                return null;
            }

            @Override
            public ResultMsg remove(String projectId) {
                return null;
            }

            @Override
            public ResultMsg primary(String projectId) {
                return null;
            }

            @Override
            public ResultMsg getElements(String projectId) {
                return null;
            }

            @Override
            public ResultMsg search(String pageNum, String pageSize, String orderBy, Map<String, Object> params) {
                return null;
            }
        };
    }
}
