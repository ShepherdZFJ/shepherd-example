package com.shepherd.basedemo.function;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/9/5 22:52
 */
@FunctionalInterface
public interface DataTransformer<T, R> {
    R transform(T input);
}
