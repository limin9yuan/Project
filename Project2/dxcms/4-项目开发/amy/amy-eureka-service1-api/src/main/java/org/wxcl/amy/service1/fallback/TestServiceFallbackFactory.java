/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: TestServiceFallbackFactory
 * Author:   wangxin
 * Date:     2018/7/31 16:11
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package org.wxcl.amy.service1.fallback;

import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.wxcl.amy.model.TestModel;
import org.wxcl.amy.model.User;
import org.wxcl.amy.service1.service.api.ITestService;

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
@Component("testServiceFallbackFactory1")
public class TestServiceFallbackFactory implements FallbackFactory<ITestService> {

    private static final Logger logger= LoggerFactory.getLogger(TestServiceFallbackFactory.class);

    @Override
    public ITestService create(Throwable cause) {
        return new ITestService() {
            @Override
            public Object test1() {
                logger.warn("fallback reson:{}",cause.getMessage());
                return "无法调用";
            }

            @Override
            public User add(User user) {
                logger.warn("fallback reson:{}",cause.getMessage());
                user = new User();
                user.setUsername("-1");
                user.setAddress("-1");
                user.setPhone("-1");
                return user;
            }

            @Override
            public Object fileUpload2(String filename, MultipartFile file) throws IOException {
                logger.warn("fallback reson:{}",cause.getMessage());
                Map<String, String> rv = new HashMap<>();
                rv.put("code", "-1");
                rv.put("msg", "服务停止");
                return rv;
            }

            @Override
            public Object save(TestModel testModel) {
                logger.warn("fallback reson:{}",cause.getMessage());
                Map<String, String> rv = new HashMap<>();
                rv.put("code", "-1");
                rv.put("msg", "服务停止");
                return rv;
            }

            @Override
            public Object detail(TestModel testModel) {
                logger.warn("fallback reson:{}",cause.getMessage());
                Map<String, String> rv = new HashMap<>();
                rv.put("code", "-1");
                rv.put("msg", "服务停止");
                return rv;
            }

            @Override
            public Object remove(TestModel testModel) {
                logger.warn("fallback reson:{}",cause.getMessage());
                Map<String, String> rv = new HashMap<>();
                rv.put("code", "-1");
                rv.put("msg", "服务停止");
                return rv;
            }

            @Override
            public Object list(String pageNum, String pageSize, TestModel testModel) {
                logger.warn("fallback reson:{}",cause.getMessage());
                Map<String, String> rv = new HashMap<>();
                rv.put("code", "-1");
                rv.put("msg", "服务停止");
                return rv;
            }
        };
    }
}
