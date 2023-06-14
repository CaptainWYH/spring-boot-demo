package com.wyhcode.service;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.wyhcode.entity.Permission;
import com.wyhcode.entity.Role;
import com.wyhcode.entity.vo.UserPrincipal;
import com.wyhcode.enums.Consts;
import com.wyhcode.enums.StatusEnum;
import com.wyhcode.exception.SecurityException;
import com.wyhcode.mapper.PermissionMapper;
import com.wyhcode.mapper.RoleMapper;
import org.apache.tomcat.util.bcel.Const;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author weiyuhui
 * @date 2023/6/14 16:04
 * @description
 */

@Component
public class RbacAuthorityService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RequestMappingHandlerMapping mappingHandlerMapping;

    public boolean hasPermission(HttpServletRequest request, Authentication authentication){
        checkRequest(request);

        Object userInfo = authentication.getPrincipal();
        boolean hasPermission = false;

        if (userInfo instanceof UserDetails){
            UserPrincipal principal = (UserPrincipal) userInfo;
            Long userId = principal.getId();

            //获取用户相关角色
            List<Role> roles = roleMapper.selectByUserId(userId);
            //提取角色id
            List<Long> roleIds = roles.stream().map(Role::getId).collect(Collectors.toList());
            //获取相关权限信息
            List<Permission> permissionList = permissionMapper.selectByRoleId(roleIds);

            //获取资源，前后端分离，过滤页面权限，保留按钮权限
            List<Permission> btnPerms = permissionList.stream()
                    //过滤页面权限
                    .filter(permission -> Objects.equals(permission.getType(), Consts.BUTTON))
                    //过滤URL为空
                    .filter(permission -> StrUtil.isNotBlank(permission.getUrl()))
                    //过滤 METHOD 为空
                    .filter(permission -> StrUtil.isNotBlank(permission.getMethod()))
                    .collect(Collectors.toList());

            for (Permission btnPerm : btnPerms) {
                AntPathRequestMatcher antPathRequestMatcher = new AntPathRequestMatcher(btnPerm.getUrl(), btnPerm.getMethod());
                if (antPathRequestMatcher.matches(request)){
                    hasPermission = true;
                    break;
                }
            }
            return hasPermission;
        }else {
            return false;
        }
    }

    /**
     * 校验请求是否存在
     * @param request 请求
     */
    public void checkRequest(HttpServletRequest request){
        //获取当前请求的方法
        String method = request.getMethod();
        Multimap<String, String> urlMapping = allUrlMapping();
        for (String uri : urlMapping.keySet()) {
            // 通过 AntPathRequestMatcher 匹配 url
            // 可以通过 2 种方式创建 AntPathRequestMatcher
            // 1：new AntPathRequestMatcher(uri,method) 这种方式可以直接判断方法是否匹配，因为这里我们把 方法不匹配 自定义抛出，所以，我们使用第2种方式创建
            // 2：new AntPathRequestMatcher(uri) 这种方式不校验请求方法，只校验请求路径
            AntPathRequestMatcher antPathMatcher = new AntPathRequestMatcher(uri);
            if (antPathMatcher.matches(request)) {
                if (!urlMapping.get(uri).contains(method)) {
                    throw new SecurityException(StatusEnum.HTTP_BAD_METHOD);
                } else {
                    return;
                }
            }
        }
    }

    public Multimap<String,String> allUrlMapping(){
        ArrayListMultimap<String,String> urlMapping = ArrayListMultimap.create();

        //获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = mappingHandlerMapping.getHandlerMethods();
        handlerMethods.forEach((k,v)->{
            Set<String> url = k.getPatternsCondition().getPatterns();
            //获取当前key下的所有URL
            RequestMethodsRequestCondition method = k.getMethodsCondition();

            //为每个URL添加所有的请求方法
            url.forEach(s -> urlMapping.putAll(s,method.getMethods().stream().map(Enum::toString).collect(Collectors.toList())));
        });
        return urlMapping;
    }
}
