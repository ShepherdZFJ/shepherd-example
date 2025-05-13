package com.shepherd.basedemo.guava;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Multimap;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/9/11 16:44
 */
public class Test {
    public static void main(String[] args) {
        test2();
    }

    private static void test2() {
        ArrayListMultimap<String, Integer> multimap = ArrayListMultimap.create();
        multimap.put("day",1);
        multimap.put("day",2);
        multimap.put("day",8);
        multimap.put("month",3);

        System.out.println(multimap);

        List<Integer> day = multimap.get("day");
        System.out.println(day);

        Map<String, Collection<Integer>> map = multimap.asMap();
        System.out.println(map);

    }

    private static void test1() {
        HashBiMap<String, String> biMap = HashBiMap.create();
        biMap.put("Hydra","Programmer");
        biMap.put("Tony","IronMan");
        biMap.put("Thanos","Titan");
        //使用key获取value
        System.out.println(biMap.get("Tony"));

        BiMap<String, String> inverse = biMap.inverse();
        //使用value获取key, value重复的话，不支持此操作
        System.out.println(inverse.get("Titan"));
    }

}
