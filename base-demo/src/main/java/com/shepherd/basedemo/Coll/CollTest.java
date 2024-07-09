package com.shepherd.basedemo.Coll;

import cn.hutool.core.collection.ListUtil;
import com.alibaba.excel.util.ListUtils;
import com.shepherd.basedemo.pojo.User;
import org.apache.commons.compress.utils.Lists;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2023/12/27 16:14
 * 集合测试类
 */
public class CollTest {

    public static void main(String[] args) {
//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        List<Integer> subList = list.subList(1, 2);
//        subList.add(4);
//        System.out.println(list);
        testListRemove();
    }

    static void testToMap1() {
        List<User> list = new ArrayList<>();
        User u1 = User.builder().id(1L).userNo("001").name("张三").build();
        User u2 = User.builder().id(2L).userNo("002").name("李四").build();
        User u3 = User.builder().id(3L).name("张三").build();   // userNo为空
        list.add(u1);
        list.add(u2);
        list.add(u3);
        Map<String, Long> map = list.stream().collect(Collectors.toMap(User::getName, User::getId));
        System.out.println(map);
    }

    static void testToMap() {
        List<User> list = new ArrayList<>();
        User u1 = User.builder().id(1L).userNo("001").name("张三").build();
        User u2 = User.builder().id(2L).userNo("002").name("李四").build();
        User u3 = User.builder().id(3L).name("张三").build();   // userNo为空
        list.add(u1);
        list.add(u2);
        list.add(u3);
        //解决方案1 用filter过滤value为null的
        Map<Long, String> userNameMap = new HashMap<>();
        userNameMap = list.stream().filter(user -> user.getName() != null).collect(Collectors.toMap(User::getId, User::getName));
        System.out.println(userNameMap);

        //解决方案2 手动实现重载方法
        userNameMap = list.stream().collect(HashMap::new, (map, user) -> map.put(user.getId(), user.getName()), HashMap::putAll);
        System.out.println(userNameMap);

        //解决方案3 使用原来的for循环或者foreach循环
        Map<Long, String> finalUserNameMap = userNameMap;
        list.forEach(user -> finalUserNameMap.put(user.getId(), user.getName()));
        System.out.println(finalUserNameMap);

        //解决方案4 使用Optional包装value
        Map<Long, Optional<String>> coll = list.stream().collect(Collectors.toMap(User::getId, user -> Optional.ofNullable(user.getName())));
        System.out.println(coll);
    }


    static void testMap() {
        Map<String ,Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        map.forEach((k, v) -> {
            if (v == 2) {
                map.put("d", 4);
            }
        });
        System.out.println(map);
    }

    static void testList() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer v = iterator.next();
            if (v == 1) {
                iterator.remove();
            }
        }
        System.out.println(list);
    }

    static void testSubList() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        List<Integer> subList = list.subList(1, 2);
//        subList.add(4);
//        System.out.println(list);
        ArrayList l = (ArrayList)subList;
        System.out.println(l);
    }

    static void testArrayAsList() {
        Integer[] nums = new Integer[]{1,2,3};
        List<Integer> list = Arrays.asList(nums);
        list.add(4);
        System.out.println(list);
    }

    static void testListRemove() {
        AllotCase allotCase = new AllotCase();
        allotCase.setUserIds(ListUtils.newArrayList(1,2,3,4,5));
        List<Integer> userIds = allotCase.getUserIds();
        System.out.println(userIds);
        userIds.removeAll(ListUtils.newArrayList(3,4));
        System.out.println(allotCase.getUserIds());

    }


}
