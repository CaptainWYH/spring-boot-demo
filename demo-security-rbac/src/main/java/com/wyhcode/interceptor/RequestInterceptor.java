package com.wyhcode.interceptor;

import com.wyhcode.config.CustomConfig;
import com.wyhcode.service.RbacAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author weiyuhui
 * @date 2023/6/14 17:55
 * @description 所有请求拦截器
 */

@Component
public class RequestInterceptor implements HandlerInterceptor {

    @Autowired
    private RbacAuthorityService rbacAuthorityService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return rbacAuthorityService.hasPermission(request, authentication);
    }
}
