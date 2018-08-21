package org.wxcl.amy.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.wxcl.amy.common.log.LogUtils;

/**
 * eureka server
 *
 * @author wangxin
 * @create 2018/6/29
 * @since 1.0.0
 */
@SpringBootApplication
@EnableEurekaServer
public class App {

    public static void main(String[] args) {
        LogUtils.initLogParams(args);
        SpringApplication.run(App.class, args);
    }

}
