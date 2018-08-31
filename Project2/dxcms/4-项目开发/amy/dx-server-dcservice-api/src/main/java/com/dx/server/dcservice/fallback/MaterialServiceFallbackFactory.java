package com.dx.server.dcservice.fallback;

import com.dx.client.model.datacenter.MaterialBean;
import feign.hystrix.FallbackFactory;
import com.dx.server.dcservice.service.api.IMaterialService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: DX01
 * @Date: 2018/8/29 11:51
 * @Description:
 */
@Component("materialServiceFallbackFactory")
public class MaterialServiceFallbackFactory implements FallbackFactory<IMaterialService>  {
    @Override
    public IMaterialService create(Throwable cause){
        return new IMaterialService() {
            @Override
            public Object save(MaterialBean materialBean) {
                return null;
            }
        };
    }
}
