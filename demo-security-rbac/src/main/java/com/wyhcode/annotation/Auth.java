package com.wyhcode.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author weiyuhui
 * @date 2023/6/20 11:30
 * @description 权限注解，用于标识需要权限处理的接口
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface Auth {

    /**
     * 权限id
     * @return long
     */
    long id();

    /**
     * 权限名称
     * @return name
     */
    String name();
}
