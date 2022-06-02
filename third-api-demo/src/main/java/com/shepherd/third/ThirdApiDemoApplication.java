package com.shepherd.third;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.shepherd.third.feign")
public class ThirdApiDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThirdApiDemoApplication.class, args);
    }

}
