package org.wxcl.amy.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.wxcl.amy.common.log.LogUtils;

/**
 *
 *
 * @author wangxin
 * @create 2018/7/26
 * @since 1.0.0
 */
@EnableConfigServer
@EnableEurekaClient
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        LogUtils.initLogParams(args);
        SpringApplication.run(Application.class, args);
    }

}