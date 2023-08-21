package com.wyhcode.core;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author weiyuhui
 * @date 2023/8/21 13:44
 * @description 模拟IOC容器
 */

@Data
@Slf4j
public class ApplicationContext {

    /**
     * IOC容器
     */
    private final Map<Class<?>,Definition> definitionMap= new ConcurrentHashMap<>();

    /**
     * 已经初识化的bean
     */
    private final Map<Class<?>,Object> targetMap = new ConcurrentHashMap<>();

    /**
     * 扫描需要注册的bean
     */
    public ApplicationContext(Set<Class<?>> classes,boolean isLazy){
        for (Class<?> target :classes){
            if (target.isAnnotationPresent(Component.class)){
                Definition definition = new Definition();
                definition.setTargetClazz(target);
                definition.setTargetName(target.getSimpleName());
                // TODO 注册bean
                this.register(target,definition,isLazy);
            }
        }
    }

    /**
     * 将bean添加到容器中
     * @param target
     * @param definition
     * @param isLasy
     */
    public void register(Class<?> target,Definition definition,boolean isLasy){
        if (definitionMap.containsKey(target)){
            log.warn("class already exist");
            return;
        }
        // 是否懒汉模式
        if (!isLasy){
            getBean(target);
        }
    }

    /**
     * 获取bean
     * @param tClass
     * @return
     * @param <T>
     */
    public <T> T getBean(Class<T> tClass){
        Object obj = targetMap.get(tClass);
        if (Objects.nonNull(obj)){
            return (T) obj;
        }
        return doCreateBean(tClass);
    }

    /**
     * 初始化Bean
     * @param tClass
     * @return
     * @param <T>
     */
    public <T> T doCreateBean(Class<T> tClass){
        // 加锁
        synchronized (ApplicationContext.class){
            Object obj = targetMap.get(tClass);
            if (Objects.nonNull(obj)){
                return (T) obj;
            }
            try {
                obj = tClass.newInstance();
                targetMap.put(tClass,obj);
                return (T) obj;
            } catch (Exception e) {
                throw new RuntimeException("error newInstance");
            }
        }

    }
}
