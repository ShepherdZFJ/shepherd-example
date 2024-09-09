package com.shepherd.basedemo.function;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/9/5 22:54
 */
public class DataProcessor<T, R> {
    public static <T, R> R processData(T data, DataTransformer<T, R> transformer) {
        return transformer.transform(data);
    }

    public static void main(String[] args) {
        // 使用场景
        Integer length = DataProcessor.processData("Hello", String::length);
        System.out.println(length); // 输出 5
        Person person = new Person();
        person.setName("zhangsan");
        person.setAge(18);
        Student student = DataProcessor.processData(person, DataProcessor::convertToStudent);
        System.out.println(student);
    }

    private static Student convertToStudent(Person person) {
        Student student = new Student();
        student.setName(person.getName());
        student.setAge(person.getAge());
        return student;
    }

}
