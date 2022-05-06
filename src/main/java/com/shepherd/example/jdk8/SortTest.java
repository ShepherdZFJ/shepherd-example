package com.shepherd.example.jdk8;

import java.util.*;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/5/6 11:11
 */
public class SortTest {
    public static void main(String[] args) {
        // testComparator();
        testComparable();
    }

    static void testComparator(){
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(-1);
        arrayList.add(3);
        arrayList.add(3);
        arrayList.add(-5);
        arrayList.add(7);
        arrayList.add(4);
        arrayList.add(-9);
        arrayList.add(-7);
        System.out.println("原始数组:");
        System.out.println(arrayList);
        // void reverse(List list)：反转
        Collections.reverse(arrayList);
        System.out.println("Collections.reverse(arrayList):");
        System.out.println(arrayList);

        // void sort(List list),按自然排序的升序排序
        Collections.sort(arrayList);
        System.out.println("Collections.sort(arrayList):");
        System.out.println(arrayList);
        // 定制排序的用法
        Collections.sort(arrayList, new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        System.out.println("定制排序后：");
        System.out.println(arrayList);
    }

    static void testComparable() {
        // person对象没有实现Comparable接口，所以必须实现，这样才不会出错，才可以使treemap中的数据按顺序排列
        // 前面一个例子的String类已经默认实现了Comparable接口，详细可以查看String类的API文档，另外其他
        // 像Integer类等都已经实现了Comparable接口，所以不需要另外实现了
        TreeMap<Person, String> pdata = new TreeMap<Person, String>();
        pdata.put(new Person("王五", 10), "wangwu");
        pdata.put(new Person("张三", 30), "zhangsan");
        pdata.put(new Person("李四", 20), "lisi");
        pdata.put(new Person("小红", 5), "xiaohong");
        // 得到key的值的同时得到key所对应的值
        Set<Person> keys = pdata.keySet();
        for (Person key : keys) {
            System.out.println(key.getAge() + "-" + key.getName());

        }
    }
}
