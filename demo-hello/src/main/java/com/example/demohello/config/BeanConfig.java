package com.example.demohello.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author weiyuhui
 * @Date: 2025/5/27 11:50
 **/
@Configuration
public class BeanConfig {

    @Bean
    @Conditional(WindowsCondition.class)
    public WindowsBean windowsBean() {
        return new WindowsBean();
    }

    @Bean LinuxBean linuxBean() {
        return new LinuxBean();
    }
}
