package com.shepherd.example.jdk8;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/5/5 14:06
 */
public class StringTest {
    public static void main(String[] args) {

        String str1 = "str";
        String str2 = "ing";
        String str3 = "str" + "ing";
        String str4 = str1 + str2;
        String str5 = "string";
        System.out.println(str3 == str4);//false
        System.out.println(str3 == str5);//true
        System.out.println(str4 == str5);//false

//        final String str1 = "str";
//        final String str2 = "ing";
//        // 下面两个表达式其实是等价的
//        String c = "str" + "ing";// 常量池中的对象
//        String d = str1 + str2; // 常量池中的对象
//        System.out.println(c == d);// true
    }
}
