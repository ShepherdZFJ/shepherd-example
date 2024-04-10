package com.shepherd.basedemo.Config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.ErrorHandler;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/4/9 17:50
 */
@Slf4j
@Configuration
public class InitConfig {


    /**
     * 自定义事件广播器，异步处理事件，这样监听器就不需要使用@Async注解了
     * @param executor
     * @return
     */
    @Bean(AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME)
    public SimpleApplicationEventMulticaster simpleApplicationEventMulticaster(
            @Qualifier("asyncExecutor") Executor executor,
            ErrorHandler errorHandler) {
        SimpleApplicationEventMulticaster simpleApplicationEventMulticaster = new SimpleApplicationEventMulticaster();
        simpleApplicationEventMulticaster.setTaskExecutor(executor);
        simpleApplicationEventMulticaster.setErrorHandler(errorHandler);
        return simpleApplicationEventMulticaster;
    }

    /**
     * 注入一个事件异常处理器
     * @return
     */
    @Bean
    public ErrorHandler errorHandler() {
        return (t) -> {
            log.error("listener handle error: ", t);
        };
    }

    /**
     * 初始化一个线程池，放入spring beanFactory
     * @return
     */
    @Bean(name = "asyncExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(200);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("asyncExecutor-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }
}