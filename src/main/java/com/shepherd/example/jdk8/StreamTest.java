package com.shepherd.example.jdk8;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/3/11 11:49
 */
public class StreamTest {
    public static void main(String[] args) {
        StreamTest test = new StreamTest();
        test.distinctObjByField();
    }


    /**
     * 通过对象属性去重
     */
    public void distinctObjByField() {
        ArrayList<User> users = Lists.newArrayList(User.builder().id(1l).age(20).name("xiao").gender(1).build(),
                User.builder().id(2l).age(10).name("xiao1111").gender(0).build(),
                User.builder().id(3l).age(30).name("xiao2222").gender(1).build(),
                User.builder().id(2l).age(25).name("xiao3333").gender(1).build(),
                User.builder().id(3l).age(35).name("xiao444").gender(0).build());
        ArrayList<User> distinctUsers = users.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(User::getId))),
                        ArrayList::new));
        distinctUsers.forEach(System.out::println);


    }
}
