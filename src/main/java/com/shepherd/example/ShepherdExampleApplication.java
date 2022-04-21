package com.shepherd.example;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.shepherd"})
@SpringBootApplication
@NacosPropertySource(dataId = "example.properties", autoRefreshed = true)
public class ShepherdExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShepherdExampleApplication.class, args);
    }

}
