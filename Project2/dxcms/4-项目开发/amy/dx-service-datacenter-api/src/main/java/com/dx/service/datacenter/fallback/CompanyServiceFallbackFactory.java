package com.dx.service.datacenter.fallback;

import com.dx.client.model.datacenter.CompanyBean;
import com.dx.service.datacenter.service.api.ICompanyService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.wxcl.amy.utils.common.ResultMsg;

import java.util.Map;

/**
 * @Auther: DX01
 * @Date: 2018/8/31 10:17
 * @Description:
 */
@Component("companyServiceFallbackFactory")
public class CompanyServiceFallbackFactory implements FallbackFactory<ICompanyService> {
    @Override
    public ICompanyService create(Throwable throwable) {
        return new ICompanyService() {
            @Override
            public ResultMsg save(CompanyBean companyBean) {
                return null;
            }

            @Override
            public ResultMsg cancel(String companyId) {
                return null;
            }

            @Override
            public ResultMsg remove(String companyId) {
                return null;
            }

            @Override
            public ResultMsg detail(String companyId) {
                return null;
            }

            @Override
            public ResultMsg search(String pageNum, String pageSize, String orderBy, Map<String, Object> params) {
                return null;
            }
        };
    }
}
