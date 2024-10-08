package com.shepherd.basedemo.function;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/4/26 11:10
 */
public class Test {
    public static void main(String[] args) {
        Function<String,String> function = (str)-> str.toUpperCase();
        System.out.println(function.apply("abc"));

        Predicate<String> predicate = (str)-> str.isEmpty();
        System.out.println(predicate.test(""));

        Consumer<String> consumer = (str)->{System.out.println(str.substring(0,2));};
        consumer.accept("hello");

        Supplier supplier = ()-> 1024;
        System.out.println(supplier.get());

        Test test = new Test();
        test.testProducerConsumerInner();
    }

    public <T> void  producerConsumerInner(Executor executor,
                                           Consumer<Consumer<T>> producer,
                                           Consumer<Supplier<T>> consumer) {
        BlockingQueue<T> blockingQueue = new LinkedBlockingQueue<>();
        executor.execute(() -> producer.accept(blockingQueue::add));
        executor.execute(() -> consumer.accept(() -> {
            try {
                return blockingQueue.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }));
    }

    private void testProducerConsumerInner() {
        producerConsumerInner(Runnable::run,
                (Consumer<Consumer<Integer>>) producer -> {
                    producer.accept(1);
                    producer.accept(2);
                },
                consumer -> {
                    assert consumer.get() == 1;
                    System.out.println(consumer.get());
                    assert consumer.get() == 2;
                });
    }

    public static void testClose() throws IOException {
        // 创建 10 个任务对象，并且每个任务对象给一个任务编号
        List<Runnable> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int k = i + 1;
            Runnable task
                    = () -> System.out.println(Thread.currentThread()+":执行任务" + k);
            list.add(task);
        }

        ExecutorService service = Executors.newFixedThreadPool(10);
        for (Runnable task : list) {
            service.submit(task);
        }
        System.in.read();
    }


}
