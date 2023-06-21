package com.wyhcode.security;

import com.wyhcode.entity.Permission;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author weiyuhui
 * @date 2023/6/20 10:42
 * @description
 */

@Component
public class MySecurityMetadataSource implements SecurityMetadataSource {


    /**
     * 缓存所有接口资源对象
     */
    private static final Set<Permission> RESOURCES = new HashSet<>();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
