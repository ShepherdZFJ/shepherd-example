package com.shepherd.basedemo.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2025/1/10 14:32
 */
@Slf4j
@Aspect
@Component
public class ScheduledTaskAspect {

    @Pointcut("@annotation(org.springframework.scheduling.annotation.Scheduled)")
    public void trace() {}

    @Around(value = "trace()")
    public void doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            String traceId = UUID.randomUUID().toString().replace("-", "");
            MDC.put("traceId", traceId);
            joinPoint.proceed();
        } catch (Exception e) {
            log.error("task error: ", e);
        } finally {
            MDC.remove("traceId");
        }
    }

}
