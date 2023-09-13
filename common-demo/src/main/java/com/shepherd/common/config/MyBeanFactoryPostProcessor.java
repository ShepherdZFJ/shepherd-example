package com.shepherd.common.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2023/4/13 16:01
 */
//@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactoryPostProcessor execute...");
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("boo");
        if (Objects.nonNull(beanDefinition)) {
            beanDefinition.setDescription("芽儿哟，可以的");
        }
    }
}
