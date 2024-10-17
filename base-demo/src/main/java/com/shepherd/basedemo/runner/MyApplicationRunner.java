package com.shepherd.basedemo.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/10/9 16:34
 */
@Component
public class MyApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("ApplicationRunner running!");
        for (String sourceArg : args.getSourceArgs()) {
            System.out.println("ApplicationRunner Arg: " + sourceArg);
        }
        for (String nonOptionArg : args.getNonOptionArgs()) {
            System.out.println("ApplicationRunner nonOptionArg: " + nonOptionArg);
        }
        for (String optionName : args.getOptionNames()) {
            System.out.println("ApplicationRunner optionArg: " + args.getOptionValues(optionName));
        }


    }
}
