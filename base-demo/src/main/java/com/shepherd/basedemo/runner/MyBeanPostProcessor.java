//package com.shepherd.basedemo.runner;
//
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.BeanPostProcessor;
//import org.springframework.stereotype.Component;
//
///**
// * @author fjzheng
// * @version 1.0
// * @date 2024/10/30 14:41
// */
//@Component
//public class MyBeanPostProcessor implements BeanPostProcessor {
//
//    @Override
//    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("初始化前处理 Bean：" + beanName);
//        return bean;
//    }
//
//    @Override
//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("初始化后处理 Bean：" + beanName);
//        return bean;
//    }
//}
