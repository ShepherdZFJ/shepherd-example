package com.shepherd.basedemo.function;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/9/4 22:55
 */
public interface CallbackTask<T,R> {
    R execute(T t);
    default void onSuccess(R r) {
    }
    default void onFailure(Throwable t) {
    }
}
