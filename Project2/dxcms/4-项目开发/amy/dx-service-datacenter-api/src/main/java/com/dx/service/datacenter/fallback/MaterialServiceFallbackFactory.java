package com.dx.service.datacenter.fallback;

import com.dx.client.model.datacenter.MaterialBean;
import com.dx.service.datacenter.service.api.IMaterialService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.wxcl.amy.utils.common.ResultMsg;

import java.util.Map;

/**
 * @Auther: DX01
 * @Date: 2018/8/31 09:21
 * @Description:
 */
@Component("materialServiceFallbackFactory")
public class MaterialServiceFallbackFactory implements FallbackFactory<IMaterialService> {
    @Override
    public IMaterialService create(Throwable cause){
        return new IMaterialService() {

            @Override
            public ResultMsg save(MaterialBean materialBean) {
                return null;
            }

            @Override
            public ResultMsg cancel(String materialId) {
                return null;
            }

            @Override
            public ResultMsg remove(String materialId) {
                return null;
            }

            @Override
            public ResultMsg detail(String materialId) {
                return null;
            }

            @Override
            public ResultMsg search(String pageNum, String pageSize, String orderBy, Map<String, Object> params) {
                return null;
            }
        };
    }
}
