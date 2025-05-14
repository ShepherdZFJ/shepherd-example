package com.shepherd.common.juc;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/12/20 10:37
 */
public class TtlTest {
//    private static ExecutorService executorService =
//            TtlExecutors.getTtlExecutorService(Executors.newFixedThreadPool(2));
    private static ExecutorService executorService = Executors.newFixedThreadPool(2);
    // TTL
    private static TransmittableThreadLocal<String> ttl = new TransmittableThreadLocal();

    public static void main(String[] args) {
        ttl.set("12345");
        System.out.println("主线程1：" + ttl.get());
//        executorService.submit(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(3);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("子线程：" + ttl.get());
//        });
//        ttl.remove();
//        System.out.println("主线程删除：" + ttl.get());
//        executorService.execute(() -> {
//            System.out.println("子线程1：" + ttl.get());
//        });
//        ttl.set("54321");
//        System.out.println("主线程2：" + ttl.get());
//        executorService.execute(() -> {
//            System.out.println("子线程2：" + ttl.get());
//        });
        Runnable task = () -> System.out.println("子线程：" + ttl.get());
        TtlRunnable ttlRunnable = TtlRunnable.get(task);
        executorService.submit(task);
        ttl.set("54321");
        executorService.submit(task);
    }
}
