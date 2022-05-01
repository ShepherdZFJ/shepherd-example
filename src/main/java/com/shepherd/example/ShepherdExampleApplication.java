package com.shepherd.example;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.shepherd.example.config.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.shepherd"})
@SpringBootApplication
@NacosPropertySource(dataId = "example.properties", autoRefreshed = true)
public class ShepherdExampleApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ShepherdExampleApplication.class, args);
        Person p1 =(Person)run.getBean("person01");
        Person p2 =(Person)run.getBean("person01");
        System.out.println(p1 == p2);
    }

}
