package com.wyhcode.filter;

import cn.hutool.core.util.ObjectUtil;
import com.wyhcode.service.CustomUserDetailsService;
import com.wyhcode.service.user.UserService;
import com.wyhcode.util.JwtManager;
import com.wyhcode.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author weiyuhui
 * @date 2023/6/19 10:45
 * @description
 */

@Component
public class LoginFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Resource
    private JwtManager jwtManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 登录  注册放行
        if (request.getRequestURI().contains("login") || request.getRequestURI().contains("register")){
            filterChain.doFilter(request,response);
            return;
        }
        // 从head中拿到token
        String token = request.getHeader("Authorization");
        Claims claims = jwtUtil.parseJWT(token);
        if (claims != null){
            //获取用户名
            String userName = claims.getSubject();
            // 查询出用户对象
            UserDetails user = customUserDetailsService.loadUserByUsername(userName);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, user.getPassword(),user.getAuthorities());
            //将认证的对象放入Spring上下文中
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        }
        filterChain.doFilter(request,response);
    }
}
