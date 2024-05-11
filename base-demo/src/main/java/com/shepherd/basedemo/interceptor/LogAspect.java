package com.shepherd.basedemo.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/5/11 10:55
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Around("execution(* com.shepherd..controller..*(..))")
    public Object timeAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("执行@Aspect切面方法了");
         return joinPoint.proceed();
    }

}
