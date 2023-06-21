package com.wyhcode.config;

import com.wyhcode.annotation.Auth;
import com.wyhcode.entity.Permission;
import com.wyhcode.enums.ResourceTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author weiyuhui
 * @date 2023/6/20 10:39
 * @description
 */
@Component
public class ApplicationStartup implements ApplicationRunner {

    @Autowired
    private RequestMappingInfoHandlerMapping requestMappingInfoHandlerMapping;

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }

    /**
     * 扫描并返回所有需要权限处理的接口资源
     * @return
     */
    private List<Permission> getAuthResources(){
        //要添加到数据库的资源
        LinkedList<Permission> list = new LinkedList<>();
        // 拿到所有接口的信息
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingInfoHandlerMapping.getHandlerMethods();
        handlerMethods.forEach((info,handlerMethod)->{
            // 拿到类（模块）上的权限注解
            Auth moduleAuth = handlerMethod.getBeanType().getAnnotation(Auth.class);
            // 拿到接口方法上的权限注解
            Auth methodAuth = handlerMethod.getMethod().getAnnotation(Auth.class);
            // 模块注解和方法注解缺一个都代表不进行任何处理
            if (moduleAuth == null || methodAuth == null){
                return;
            }

            //拿到改接口方法的请求方式（GET、POST等）
            Set<RequestMethod> methods = info.getMethodsCondition().getMethods();
            // 判断接口方法是否有多个方式    多方式   不处理
            if (methods.size() != 1){
                return;
            }
            // 将请求方式和路径使用: 拼接起来  如：GET:/user/{id}
            assert info.getPatternsCondition() != null;
            String path =  methods.toArray()[0] + ":" + info.getPatternsCondition().getPatterns().toArray()[0];
            // 将权限名 、 资源路径 、 资源类型组装成资源对象，并添加到集合中
            Permission permission = new Permission();
            permission.setType(ResourceTypeEnum.URI.getType());
            // TODO 待做完
        });
        return list;
    }
}
