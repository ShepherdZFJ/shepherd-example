package com.shepherd.basedemo.function;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
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

        Supplier supplier = ()->{ return 1024; };
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


}
