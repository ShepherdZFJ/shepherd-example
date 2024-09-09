package com.shepherd.basedemo.function;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/9/4 16:40
 */
public class NumberProducerConsumer extends ProducerConsumer<Integer> {

    private final Supplier<Integer> numberGenerator;

    private final Consumer<Integer> numberConsumer;

    public NumberProducerConsumer(Executor executor,
                                  Supplier<Integer> numberGenerator,
                                  Consumer<Integer> numberConsumer) {
        super(executor);
        this.numberGenerator = numberGenerator;
        this.numberConsumer = numberConsumer;
    }

    @Override
    void produce() {
        for (int i = 0; i < 10; i++) {
            produceInner(i + numberGenerator.get());
        }
    }

    @Override
    void consume() {
        while (true) {
            Integer result = consumeInner();
            numberConsumer.accept(result);
        }
    }

    public static void producerConsumer2() {
        new NumberProducerConsumer(Executors.newFixedThreadPool(2),
                () -> ThreadLocalRandom.current().nextInt(100),
                System.out::println).start();
    }

    private static void testProducerConsumerInner2() {
        new NumberProducerConsumer(Runnable::run, () -> 0, i -> System.out.println(i)).start();
    }

    public static void main(String[] args) {
//        testProducerConsumerInner2();
        producerConsumer2();
    }
}

