package com.shepherd.example.jdk8;

import lombok.Data;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/5/5 19:09
 */
@Data
public class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }
}
