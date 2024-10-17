package com.shepherd.basedemo.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/10/9 14:02
 */
@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("CommandLineRunner running!");
        for (String arg : args) {
            System.out.println("CommandLineRunner Arg: " + arg);
        }
    }
}
