package com.shepherd.basedemo.function;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/9/5 22:54
 */
public class DataProcessor<T, R> {
    public R processData(T data, DataTransformer<T, R> transformer) {
        return transformer.transform(data);
    }

    public static void main(String[] args) {
        // 使用场景
        DataProcessor<String, Integer> processor = new DataProcessor<>();
        Integer length = processor.processData("Hello", String::length);
        System.out.println(length); // 输出 5
    }
}
