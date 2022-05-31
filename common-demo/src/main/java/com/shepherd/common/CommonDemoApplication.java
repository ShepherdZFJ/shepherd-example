package com.shepherd.common;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.shepherd"})
//@NacosPropertySource(dataId = "example.properties", autoRefreshed = true)
public class CommonDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonDemoApplication.class, args);
    }

}
