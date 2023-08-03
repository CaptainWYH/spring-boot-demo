package com.example.demohello.annocation;

import com.example.demohello.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class LogPointAspectPoint {

    @Pointcut("@annotation(com.example.demohello.annocation.LogPoint)")
    public void logPointCut(){

    }

    @AfterReturning(value = "logPointCut()",returning = "ret")
    public void doAfter(JoinPoint joinPoint,Object ret){
        log.info("=============END============");
        log.info("ART   {}",ret);
    }

    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        // 执行切入点

        Person person = null;
        try {
            person = (Person) proceedingJoinPoint.proceed();
            // 出参
            log.info("方法返回参数为 ↓");
            log.info("姓名：{}",person.getName());
            log.info("性别：{}",person.getSex());
            // 执行耗时
            log.info("Time-Consuming:{} ms",System.currentTimeMillis() - startTime);
        } catch (Exception e) {
            log.info("程序执行异常，进行异常处理逻辑");
        }
        return person;
    }

    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint) throws Exception{
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert servletRequestAttributes != null;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        // 请求相关信息
        log.info("========================== START ==========================");
        log.info("URL               : {}", request.getRequestURL().toString());
        log.info("HTTP Method       : {}", request.getMethod());
        log.info("Class Method      : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        log.info("IP                : {}", request.getRemoteAddr());
    }



}
