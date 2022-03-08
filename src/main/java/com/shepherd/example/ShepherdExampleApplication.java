package com.shepherd.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.shepherd"})
@SpringBootApplication
public class ShepherdExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShepherdExampleApplication.class, args);
    }

}
