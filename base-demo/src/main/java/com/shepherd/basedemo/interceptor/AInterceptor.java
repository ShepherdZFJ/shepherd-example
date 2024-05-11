package com.shepherd.basedemo.interceptor;

import com.shepherd.basedemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/5/10 20:58
 */
@Slf4j
@Component
@Order(2)
public class AInterceptor implements HandlerInterceptor {
    @Resource
    private UserService userService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("A拦截器preHandle()方法执行了");
        userService.test();
        // 为ture放行执行后续逻辑
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("A拦截器postHandle()方法执行了");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("A拦截器afterCompletion()方法执行了");
    }
}
