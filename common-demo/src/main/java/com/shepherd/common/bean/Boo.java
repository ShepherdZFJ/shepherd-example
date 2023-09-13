package com.shepherd.common.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2023/4/14 14:41
 */
@Data
@AllArgsConstructor
public class Boo implements InitializingBean, DisposableBean, BeanNameAware {
    private Long id;
    private String name;

    public Boo() {
        System.out.println("boo实例化构造方法执行了...");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("boo执行初始化@postConstruct注解标注的方法了...");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("boo执行初始化@preDestroy标注的方法了...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("boo执行InitializingBean的afterPropertiesSet()方法了...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("boo执行DisposableBean的destroy()方法了...");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("boo执行BeanNameAware的setBeanName()方法了...");
    }

    private void initMethod() {
        System.out.println("boo执行init-method()方法了...");
    }

    public void destroyMethod() {
        System.out.println("boo执行destroy-method()方法了...");
    }


}
