package com.bootdo.common.config;

import com.bootdo.common.utils.DateUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * String转Date
 */
@Configuration
public class WebDateConvert {
    @Bean
    public Converter<String, Date> addNewConvert() {
        return new Converter<String, Date>() {
            @Override
            public Date convert(String source) {
                Date date = DateUtils.parse(source,"yyyy-MM-dd HH:mm:ss");
                return date;
            }
        };
    }
}

