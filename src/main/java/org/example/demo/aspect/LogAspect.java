package org.example.demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author
 * @date 2022/11/4
 */
@Slf4j
@Component
@Aspect
public class LogAspect {
    @Pointcut("execution(public * main.controller.*.*(..))")
    public void controller() {
    }

    @Before("controller()")
    public void before(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    @Around("controller() ")
    public Object SetControllerLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info(joinPoint.getStaticPart().toString() + " is starting...");
        long startTime = System.currentTimeMillis();
        Object[] args = joinPoint.getArgs();
        Object result = joinPoint.proceed(args);
        long endTime = System.currentTimeMillis();
        log.info(joinPoint.getStaticPart().toString() + " is end...,totalcost " + (endTime - startTime) + "ms");
        return result;
    }



}
