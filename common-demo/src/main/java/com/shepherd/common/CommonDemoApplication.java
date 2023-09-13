package com.shepherd.common;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.shepherd"})
//@NacosPropertySource(dataId = "example.properties", autoRefreshed = true)
public class CommonDemoApplication {

    public static void main(String[] args) {
//        SpringApplication.run(CommonDemoApplication.class, args);
        ConfigurableApplicationContext applicationContext = SpringApplication.run(CommonDemoApplication.class, args);
//        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
//        for (String beanDefinitionName : beanDefinitionNames) {
//            System.out.println(beanDefinitionName);
//        }
    }

}
