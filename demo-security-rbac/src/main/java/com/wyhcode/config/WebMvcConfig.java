package com.wyhcode.config;

import com.wyhcode.interceptor.RequestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author weiyuhui
 * @date 2023/6/14 17:53
 * @description
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new RequestInterceptor())
//                .excludePathPatterns("/login","logout","/doc.html/**");
    }
}
