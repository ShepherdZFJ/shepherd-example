package com.shepherd.basedemo.Coll;

import cn.hutool.core.collection.ListUtil;
import com.alibaba.excel.util.ListUtils;
import com.shepherd.basedemo.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
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

    static void testStream() {
        List<Integer> nums = ListUtil.of(0, 2, 5, 6, 9, 3, 7, 8);
    }

    static void testStream2() {
        List<Employee> employees = Arrays.asList(
                new Employee("John", "HR", 50000),
                new Employee("Jane", "IT", 60000),
                new Employee("Jack", "HR", 55000),
                new Employee("Jill", "IT", 70000),
                new Employee("Joe", "Finance", 75000)
        );

        // 使用 Stream API 分组并收集姓名
        Map<String, List<String>> employeesByDepartment = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,                       // 分组条件：部门
                        Collectors.mapping(Employee::getName,          // 收集每个部门中员工的姓名
                                Collectors.toList())       // 将姓名收集到一个 List 中
                ));

        // 输出分组结果
        employeesByDepartment.forEach((department, names) -> {
            System.out.println("Department: " + department);
            names.forEach(name -> System.out.println(" - " + name));
        });
    }

    static void testStream3() {
        List<String> list1 = Arrays.asList("apple", "banana", "cherry");
        List<String> list2 = Arrays.asList("cherry", "date", "elderberry");

        // 判断 list1 是否有任意一个元素在 list2 中存在
        boolean anyMatch = list1.stream()
                .anyMatch(list2::contains);

        if (anyMatch) {
            System.out.println("list1 中有至少一个元素在 list2 中存在");
        } else {
            System.out.println("list1 中没有元素在 list2 中存在");
        }
    }

    static void testStream4() {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 30, ListUtil.of(new Skill("Java"), new Skill("Python"))));
        people.add(new Person("Bob", 25, ListUtil.of(new Skill("Python"), new Skill("JavaScript"))));
        people.add(new Person("Charlie", 35, ListUtil.of(new Skill("Java"), new Skill("C++"))));
        people.add(new Person("David", 28, ListUtil.of(new Skill("JavaScript"), new Skill("C#"))));
        people.add(new Person("Eve", 40, ListUtil.of(new Skill("C++"), new Skill("Python"))));

        // 根据技能名称进行分组
//        Map<String, List<Person>> groupedBySkill = people.stream()
//                .flatMap(person -> person.getSkills().stream().map(skill -> Map.entry(skill.getName(), person)))
//                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
//
//        groupedBySkill.forEach((skill, peopleWithSkill) -> {
//            System.out.println("Skill: " + skill);
//            peopleWithSkill.forEach(person -> System.out.println("  " + person));
//        });
    }

    static void testSort() {
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(3);
        list.add(8);
        list.add(1);

        // 使用 Collections.sort() 方法进行升序排序
        Collections.sort(list);

        // 使用 List.sort() 方法进行升序排序
        list.sort(Integer::compareTo);

        // 使用 Stream.sorted() 方法进行升序排序
        List<Integer> sortedList = list.stream()
                .sorted()
                .collect(Collectors.toList());
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

    @Data
    @AllArgsConstructor
     static  class Employee {
         private String name;
         private String department;
         private double salary;
     }

     @Data
     @AllArgsConstructor
     static class Skill {
         private String name;
     }

     @Data
     @AllArgsConstructor
     static class Person {
         private String name;
         private int age;
         private List<Skill> skills;
     }


}
