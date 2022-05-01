package com.shepherd.example.config;

import org.springframework.context.annotation.Configuration;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/5/1 23:21
 */
@Configuration
public class MyConfig {

    public Person person01() {
        Person person = new Person();
        person.setName("shepherd");
        person.setAge(25);
        return person;
    }

}
