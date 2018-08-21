package org.wxcl.amy.service2.config;

import feign.Logger;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

/**
 *
 *
 * @author wangxin
 * @create 2018/7/26
 * @since 1.0.0
 */

@Configuration
public class FeignConfig {

    //@Bean
    //public Contract feignContract(){
    //    return new feign.Contract.Default();
    //}

    //@Bean
    //public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
    //    return new BasicAuthRequestInterceptor("admin", "123123");
    //}

    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }

    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;

    @Bean
    @Primary
    @Scope("prototype")
    public Encoder feignEncoder() {
        return new SpringFormEncoder(new SpringEncoder(messageConverters));
    }


}
