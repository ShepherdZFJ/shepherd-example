package com.shepherd.basedemo.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/5/10 14:50
 */
@Configuration
public class MyConfig {

//    @Resource
//    private AFilter aFilter;
//    @Resource
//    private BFilter bFilter;

//    @Bean
//    public FilterRegistrationBean buildBFilter() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        // 设置过滤器B, 这里使用注入的filter bean，如果自己new一个那么filter就不能成功依赖注入bean
//        filterRegistrationBean.setFilter(bFilter);
//        // filterRegistrationBean.setFilter(new BFilter());
//
//        // 设置过滤器拦截的url通配符
//        filterRegistrationBean.addUrlPatterns("/*");
//        filterRegistrationBean.setName("BFilter");
//        filterRegistrationBean.setOrder(2);
//        return filterRegistrationBean;
//    }
//
//
//    @Bean
//    public FilterRegistrationBean buildAFilter() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        // 设置过滤器A
//        filterRegistrationBean.setFilter(aFilter);
//        // 设置过滤器拦截的url通配符
//        filterRegistrationBean.addUrlPatterns("/*");
//        filterRegistrationBean.setName("AFilter");
//        filterRegistrationBean.setOrder(1);
//        return filterRegistrationBean;
//    }

}
