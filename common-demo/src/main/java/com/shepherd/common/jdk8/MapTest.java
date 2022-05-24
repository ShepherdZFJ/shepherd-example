package com.shepherd.common.jdk8;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/5/6 18:40
 */
public class MapTest {
    public static void main(String[] args) {
       // testTraverseMap();
//        testListToMap();
        testLinkHashMap();
    }

    /**
     * 性能 stream并行流遍历>entrySet>stream单线程>keySet
     * keySet慢的原因是我们还需要根据key去查找vaue
     */
    static void testTraverseMap() {
        // 创建并赋值 HashMap
        Map<Integer, String> map = new HashMap();
        map.put(1, "Java");
        map.put(2, "JDK");
        map.put(3, "Spring Framework");
        map.put(4, "MyBatis framework");
        map.put(5, "Java中文社群");

        // 1.迭代器 EntrySet 遍历
        System.out.println("=============迭代器EntrySet遍历");
        Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        // 2.迭代器keySet遍历
        System.out.println("=============迭代器KeySet遍历");
        Iterator<Integer> keyIterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Integer key = keyIterator.next();
            System.out.println(key + " " + map.get(key));
        }

        // 3.foreach entrySet遍历
        System.out.println("=============foreach entrySet遍历");
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        // 4.foreach keySet遍历
        System.out.println("=============foreach keySet遍历");
        for (Integer key : map.keySet()) {
            System.out.println(key + " " + map.get(key));
        }

        // 5.lambda遍历
        System.out.println("=============lambda遍历");
        map.forEach((key, value) -> {
            System.out.println(key + " " + value);
        });

        // 6.stream单线程遍历
        System.out.println("=============stream单线程遍历");
        map.entrySet().stream().forEach((entry) -> {
            System.out.println(entry.getKey() + " " + entry.getValue());
        });

        // stream多线程并行遍历
        System.out.println("=============stream多线程并行遍历");
        map.entrySet().parallelStream().forEach((entry) -> {
            System.out.println(entry.getKey() + " " + entry.getValue());
        });
    }

    // 在使用 java.util.stream.Collectors 类的 toMap() 方法转为 Map 集合时，
    // 一定要注意当 value 为 null 时会抛 NPE 异常
    static void testListToMap() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("张三",25));
        personList.add(new Person("李四",35));
        personList.add(new Person("王五"));
        Map<String, Integer> map = personList.stream().collect(Collectors.toMap(Person::getName, Person::getAge));
    }

    static void testLinkHashMap() {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("key1", 1);
        map.put("key2", 2);
        map.put("key3", 3);
        map.put("key4", 4);
        map.put("key5", 5);
        map.entrySet().stream().forEach((entry)->{
            System.out.println(entry.getKey() + " " + entry.getValue());
        });

    }


}
