package com.shepherd.common.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/3/18 10:31
 */
public class RegexDemo {
    public static void main(String[] args) {
        test1();
        test2();
    }

    public static void test1() {
        // 文本内容
        String content = "I am shepherdZFJ";
        // 正则表达式 .表示匹配除换行符（\n、\r）之外的任何单个字符 * 表示前面的字符可以有0个或者多个
        String pattern = ".*shepherd.*";
        // 是否匹配子串
        boolean isMatch = Pattern.matches(pattern, content);
        System.out.println(isMatch);
    }

    public static void test2() {
        String text = "春天来了我们将在#{meetDate}相约，如果不下雨#{shepherd}一定准时到达，并带上#{gift}";
        Pattern pattern = Pattern.compile("#\\{.+?\\}");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String group = matcher.group();
            System.out.println(group.substring(2, group.length()-1));
        }

    }
}
