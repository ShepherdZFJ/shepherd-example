package com.shepherd.example.jdk8;

import lombok.Data;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/5/5 19:09
 */
@Data
public class Person implements Comparable<Person> {
    private String name;
    private Integer age;

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    /**
     * T重写compareTo方法实现按年龄来排序
     */
    @Override
    public int compareTo(Person o) {
        if (this.age > o.getAge()) {
            return 1;
        }
        if (this.age < o.getAge()) {
            return -1;
        }
        return 0;
    }
}
