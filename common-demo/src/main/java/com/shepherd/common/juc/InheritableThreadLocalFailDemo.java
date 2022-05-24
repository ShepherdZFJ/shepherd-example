package com.shepherd.common.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/4/25 18:44
 */

public class InheritableThreadLocalFailDemo {

    // 创建线程池
    private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(1);


    public static void main(String[] args) {
        //TransmittableThreadLocal<String> context = new TransmittableThreadLocal<String>();
        InheritableThreadLocal<String> context = new InheritableThreadLocal<>();
        context.set("value1-set-in-parent");
        fixedThreadPool.submit(()->{
            // 第一个子线程打印value1-set-in-parent，毋庸置疑
            System.out.println(context.get());
        });
        // 父线程修改InheritableThreadLocal变量值
        context.set("value2-set-in-parent");
        // 再次新建一个子线程，这时候就要看这个子线程是新建的，还是复用线程池里面的之前的线程
        // 如上面线程池核心数和最大线程数都为1，说明线程池只能创建一个线程，此时这个子线程会复用前面那个子线程，这时候InheritableThreadLocal
        // 的变量值就等于前面那个线程的，所以这里和第一个子线程一样打印value1-set-in-parent
        // 如果上面线程池核心数和最大线程数都为2，这时候会新建一个子线程，此时会获取父线程的最新值，打印value2-set-in-parent
        fixedThreadPool.submit(()->{
            System.out.println(context.get());
        });
    }
}
