package org.wxcl.amy.eureka.client2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.wxcl.amy.common.log.LogUtils;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 测试服务
 *
 * @author wangxin
 * @create 2018/6/29
 * @since 1.0.0
 */
@SpringBootApplication(scanBasePackages = "org.wxcl.amy")
@EnableEurekaClient
@EnableFeignClients(basePackages = {"org.wxcl.amy"})
@EnableHystrixDashboard
@EnableCircuitBreaker
@EnableSwagger2
public class App {

    public static void main(String[] args) {
        LogUtils.initLogParams(args);
        SpringApplication.run(App.class, args);
    }

}
