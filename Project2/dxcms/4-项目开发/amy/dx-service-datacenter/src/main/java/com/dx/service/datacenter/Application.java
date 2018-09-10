package com.dx.service.datacenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.wxcl.amy.common.log.LogUtils;

/**
 * @Auther: DX01
 * @Date: 2018/8/31 09:31
 * @Description:
 */
@SpringBootApplication
@EnableEurekaClient
@EnableHystrixDashboard
@EnableCircuitBreaker
public class Application {

    public static void main(String[] args) {
        LogUtils.initLogParams(args);
        SpringApplication.run(Application.class, args);
    }
}
