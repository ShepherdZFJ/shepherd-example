package com.shepherd.basedemo.jvm.classload;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/3/14 09:59
 */
public class Test {
    private static Integer num;

    private int[] arr;

    void method() {
        System.out.println(num);
        System.out.println(arr);
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.method();
    }
}
