package com.shepherd.basedemo.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/5/10 14:58
 */
@Component
@Slf4j
//@WebFilter(urlPatterns = "/*")
@Order(1)
public class BFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("过滤器B: 执行init方法了");
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("过滤器B: 执行doFilter方法了");
        // 放行
        filterChain.doFilter(servletRequest, servletResponse);

    }


    @Override
    public void destroy() {
        log.info("过滤器B: 执行destroy方法了");
    }
}
