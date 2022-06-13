package com.shepherd.eureka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
// @EnableDiscoveryClient 注解，开启 Spring Cloud 的注册发现功能。不过从 Spring Cloud Edgware 版本开始，
// 实际上已经不需要添加 @EnableDiscoveryClient 注解，只需要引入 Spring Cloud 注册发现组件，就会自动开启注册发现的功能
@EnableDiscoveryClient
public class EurekaProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaProviderApplication.class, args);
    }

    @RestController
    @Slf4j
    static class TestController {

        @Value("${server.port}")
        private String serverPort;

        @GetMapping("/provider")
        public String test(String name) {
            log.info("我被调用了, {}", serverPort);
            return "provider:" + name;
        }

    }

}
