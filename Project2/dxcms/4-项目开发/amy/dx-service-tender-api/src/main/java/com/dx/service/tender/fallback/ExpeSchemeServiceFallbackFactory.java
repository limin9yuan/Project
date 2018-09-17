package com.dx.service.tender.fallback;

import com.dx.service.tender.service.api.IExpeSchemeService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

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
        };
    }
}
