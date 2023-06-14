package com.wyhcode.config;

import com.wyhcode.enums.StatusEnum;
import com.wyhcode.util.ResponseUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @author weiyuhui
 * @date 2023/6/14 11:43
 * @description 结果处理配置
 */

@Configuration
public class SecurityHandlerConfig {

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return ((request, response, accessDeniedException) -> ResponseUtil.renderJson(response, StatusEnum.ACCESS_DENIED, null));
    }
}
