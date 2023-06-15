package com.wyhcode.filter;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Sets;
import com.wyhcode.config.CustomConfig;
import com.wyhcode.config.SecurityConfig;
import com.wyhcode.enums.StatusEnum;
import com.wyhcode.service.CustomUserDetailsService;
import com.wyhcode.util.JwtUtil;
import com.wyhcode.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author weiyuhui
 * @date 2023/6/14 14:17
 * @description 认证过滤器
 */

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private CustomConfig customConfig;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (checkIgnores(request) || request.getRequestURI().contains("doc.html")){
            filterChain.doFilter(request,response);
            return;
        }

        String token = jwtUtil.getJwtFromRequest(request);

        // 判断token是否为空
        if (StrUtil.isNotBlank(token)){
            //获取 用户名
            String username = jwtUtil.getUserNameFromJWT(token);
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request,response);
        }else{
            ResponseUtil.renderJson(response, StatusEnum.UNAUTHORIZED,null);
        }
    }

    /**
     *  请求是否需要进行拦截
     * @param request 当前请求
     * @return true 忽略  false -不忽略
     */
    private boolean checkIgnores(HttpServletRequest request){
        String method = request.getMethod();

        HttpMethod httpMethod = HttpMethod.resolve(method);

        if (ObjectUtil.isNull(httpMethod)){
            httpMethod = HttpMethod.GET;
        }

        Set<String> ignores = Sets.newHashSet();

        switch (httpMethod){
            case GET:
                ignores.addAll(customConfig.getIgnores().getGet());
                break;
            case PUT:
                ignores.addAll(customConfig.getIgnores().getPut());
                break;
            case HEAD:
                ignores.addAll(customConfig.getIgnores().getHead());
                break;
            case POST:
                ignores.addAll(customConfig.getIgnores().getPost());
                break;
            case PATCH:
                ignores.addAll(customConfig.getIgnores().getPatch());
                break;
            case TRACE:
                ignores.addAll(customConfig.getIgnores().getTrace());
                break;
            case DELETE:
                ignores.addAll(customConfig.getIgnores().getDelete());
                break;
            case OPTIONS:
                ignores.addAll(customConfig.getIgnores().getOptions());
                break;
            default:
                break;
        }
        //无需考虑方法的url
        ignores.addAll(customConfig.getIgnores().getPattern());

        if (CollUtil.isNotEmpty(ignores)){
            for (String ignore : ignores) {
                AntPathRequestMatcher matcher = new AntPathRequestMatcher(ignore, method);
                if (matcher.matches(request)){
                    return true;
                }
            }
        }
        return false;
    }
}
