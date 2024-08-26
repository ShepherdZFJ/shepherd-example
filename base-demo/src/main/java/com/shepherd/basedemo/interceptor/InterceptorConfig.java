package com.shepherd.basedemo.interceptor;

import com.shepherd.basedemo.handler.LoginUserArgumentResolver;
import com.shepherd.basedemo.handler.ResponseReturnValueHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/5/10 21:02
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Resource
    private AInterceptor aInterceptor;
    @Resource
    private BInterceptor bInterceptor;

    @Resource
    private IpAccessInterceptor ipAccessInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // registry.addInterceptor(new AInterceptor()); new 拦截器会导致拦截器不能成功依赖注入
//        registry.addInterceptor(bInterceptor).addPathPatterns("/**").excludePathPatterns("/user/**").order(2);
//        registry.addInterceptor(aInterceptor).addPathPatterns("/**").excludePathPatterns("/user/**").order(1);
//        registry.addInterceptor(ipAccessInterceptor).addPathPatterns("/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginUserArgumentResolver());
    }

//    @Override
//    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
//        handlers.add(new ResponseReturnValueHandler());
//    }
}
