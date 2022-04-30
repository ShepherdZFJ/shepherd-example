package com.shepherd.example.juc;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.Data;

import java.util.concurrent.*;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/4/25 19:04
 */
public class TransmittableThreadLocalTest {
    private static ExecutorService executorService =
            TtlExecutors.getTtlExecutorService(Executors.newFixedThreadPool(1));

    // TTL
    private static TransmittableThreadLocal<LocalBean> ttl = new TransmittableThreadLocal();

    public static void main(String[] args) throws InterruptedException {
        LocalBean localBean = new LocalBean("初始值1");
        ttl.set(localBean); // 在主线程设置初始值
        new Thread(() -> {
            System.out.println("对普通线程的传递性：" + ttl.get());
            localBean.setProp("单一线程更新值2");
        }).start();
        // 1、线程池中取值，设置对象内的属性值，测试是否影响外部线程
        executorService.execute(() -> {
            LocalBean localBean1 = ttl.get();
            System.out.println(String.format("线程名称(%s): %s", Thread.currentThread().getName(), localBean1.getProp()));
            localBean1.setProp("线程池更新值3");
            System.out.println(String.format("After set， 线程名称(%s): %s", Thread.currentThread().getName(), ttl.get().getProp()));
        });

        TimeUnit.SECONDS.sleep(1);
        System.out.println(String.format("Main 线程名称(%s): %s", Thread.currentThread().getName(), ttl.get().getProp()));
        // 2、修改对象引用，测试是否影响外部线程
        executorService.execute(() -> {
            System.out.println(String.format("线程名称(%s): %s", Thread.currentThread().getName(), ttl.get().getProp()));
            ttl.set(new LocalBean("线程池替换localBean"));
        });

        TimeUnit.SECONDS.sleep(1);
        System.out.println(String.format("main 线程名称(%s): %s", Thread.currentThread().getName(), ttl.get()));
    }


    @Data
    static class LocalBean {
        private String prop;

        LocalBean(String p) {
            this.prop = p;
        }
    }
}
