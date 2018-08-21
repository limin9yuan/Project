package org.wxcl.amy.service2.fallback;

import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.wxcl.amy.service2.model.User;
import org.wxcl.amy.service2.service.api.ITestService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * @author wangxin
 * @create 2018/7/31
 * @since 1.0.0
 */
@Component("testServiceFallbackFactory2")
public class TestServiceFallbackFactory implements FallbackFactory<ITestService> {

    private static final Logger logger= LoggerFactory.getLogger(TestServiceFallbackFactory.class);

    @Override
    public ITestService create(Throwable cause) {
        return new ITestService() {
            @Override
            public Object test1() {
                logger.warn("fallback reason:{}",cause);
                return "无法调用";
            }

            @Override
            public User add(User user) {
                logger.warn("fallback reason:{}",cause);
                user = new User();
                user.setUsername("-1");
                user.setAddress("-1");
                user.setPhone("-1");
                return user;
            }

            @Override
            public Object fileUpload2(String filename, MultipartFile file) throws IOException {
                logger.warn("fallback reason:{}",cause);
                Map<String, String> rv = new HashMap<>();
                rv.put("code", "-1");
                rv.put("msg", "服务停止");
                return rv;
            }
        };
    }
}
