package com.example.demohello.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author weiyuhui
 * @Date: 2025/5/27 11:52
 **/
public class WindowsCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String osName = context.getEnvironment().getProperty("os.name");
        System.out.println("WindowsCondition osName: " + osName);
        return "win".equals(osName);
    }
}
