package com.wyhcode.config;

import com.wyhcode.filter.JwtAuthenticationFilter;
import com.wyhcode.service.CustomUserDetailsService;
import com.wyhcode.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(CustomConfig.class)
public class SecurityConfig {

    @Autowired
    private CustomConfig customConfig;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors()
                //关闭csrf
                .and().csrf().disable()
                //登录
                .formLogin()
                .loginProcessingUrl("/api/auth/login")
                //认证请求
                .and()
                .authorizeRequests()
                //所有请求都需要登录
                .anyRequest()
                .authenticated()
                //RBAC 动态url认证    5.2版本后不能同时出现一个以上anyRequest   TODO  考虑使用拦截器做这一步
//                .anyRequest()
//                .access("@rbacAuthorityService.hasPermission(request,authentication)")

                //登出行为
                .and().logout().disable()
                //Session 管理
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                //异常处理
                .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);
        //添加自定义JWT过滤器
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // configure Web security...
        return (web -> {
            // 忽略 GET
            customConfig.getIgnores().getGet().forEach(url -> web.ignoring().antMatchers(HttpMethod.GET, url));

            // 忽略 POST
            customConfig.getIgnores().getPost().forEach(url -> web.ignoring().antMatchers(HttpMethod.POST, url));

            // 忽略 DELETE
            customConfig.getIgnores().getDelete().forEach(url -> web.ignoring().antMatchers(HttpMethod.DELETE, url));

            // 忽略 PUT
            customConfig.getIgnores().getPut().forEach(url -> web.ignoring().antMatchers(HttpMethod.PUT, url));

            // 忽略 HEAD
            customConfig.getIgnores().getHead().forEach(url -> web.ignoring().antMatchers(HttpMethod.HEAD, url));

            // 忽略 PATCH
            customConfig.getIgnores().getPatch().forEach(url -> web.ignoring().antMatchers(HttpMethod.PATCH, url));

            // 忽略 OPTIONS
            customConfig.getIgnores().getOptions().forEach(url -> web.ignoring().antMatchers(HttpMethod.OPTIONS, url));

            // 忽略 TRACE
            customConfig.getIgnores().getTrace().forEach(url -> web.ignoring().antMatchers(HttpMethod.TRACE, url));

            // 按照请求格式忽略
            customConfig.getIgnores().getPattern().forEach(url -> web.ignoring().antMatchers(url));
        });
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(customUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }



}
