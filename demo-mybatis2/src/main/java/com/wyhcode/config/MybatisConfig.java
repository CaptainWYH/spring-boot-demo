package com.wyhcode.config;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisConfig {

    @Bean("pageInterceptor")
    public Interceptor pageInterceptor(){
        return new PageInterceptor();
    }
}
