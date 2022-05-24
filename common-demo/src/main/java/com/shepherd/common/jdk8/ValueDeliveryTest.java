package com.shepherd.common.jdk8;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/5/5 19:06
 */
public class ValueDeliveryTest {

    public static void main(String[] args) {
//        int num1 = 10;
//        int num2 = 20;
//        swap(num1, num2);
//        System.out.println("num1 = " + num1);
//        System.out.println("num2 = " + num2);

//        int[] arr = { 1, 2, 3, 4, 5 };
//        System.out.println(arr[0]);
//        change(arr);
//        System.out.println(arr[0]);

        Person xiaoZhang = new Person("小张");
        Person xiaoLi = new Person("小李");
        swap(xiaoZhang, xiaoLi);
        System.out.println("xiaoZhang:" + xiaoZhang.getName());
        System.out.println("xiaoLi:" + xiaoLi.getName());
    }

    public static void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }

    public static void change(int[] array) {
        // 将数组的第一个元素变为0
        array[0] = 0;
    }

    public static void swap(Person person1, Person person2) {
        Person temp = person1;
        person1 = person2;
        person2 = temp;
        System.out.println("person1:" + person1.getName());
        System.out.println("person2:" + person2.getName());
        person2.setName("hhh");
    }
}
