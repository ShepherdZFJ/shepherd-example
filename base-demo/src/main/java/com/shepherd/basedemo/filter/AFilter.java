package com.shepherd.basedemo.filter;

import com.shepherd.basedemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.FilterConfig;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/5/10 14:43
 */

@Component
@Slf4j
//@WebFilter(urlPatterns = "/*")
@Order(2)
public class AFilter implements Filter {

    @Resource
    private UserService userService;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("过滤器A: 执行init方法了");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("过滤器A: 执行doFilter方法了");
        userService.test();
        // 放行
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("过滤器A: 执行destroy方法了");
    }
}
