package com.shepherd.common.juc;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/4/25 16:26
 */

/**
 * 使用ThreadLocal的时候，在异步场景下是无法给子线程共享父线程中创建的线程副本数据的
 */
public class InheritableThreadLocalDemo {
    public static void main(String[] args) {
        ThreadLocal<String> ThreadLocal = new ThreadLocal<>();
        ThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();
        ThreadLocal.set("父线程数据:threadLocal");
        inheritableThreadLocal.set("父线程数据:inheritableThreadLocal");

        new Thread(()->{
            System.out.println("子线程获取父类ThreadLocal数据：" + ThreadLocal.get());
            System.out.println("子线程获取父类inheritableThreadLocal数据：" + inheritableThreadLocal.get());
        }).start();
    }
}
