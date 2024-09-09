package com.shepherd.basedemo.function;

import java.util.concurrent.*;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/9/4 16:33
 */

public abstract class ProducerConsumer<T> {

    private final Executor executor;

    private final BlockingQueue<T> blockingQueue;

    public ProducerConsumer(Executor executor) {
        this.executor = executor;
        this.blockingQueue = new LinkedBlockingQueue<>();
    }

    public void start() {
        executor.execute(this::produce);
        executor.execute(this::consume);
    }

    abstract void produce();

    abstract void consume();

    protected void produceInner(T item) {
        blockingQueue.add(item);
    }

    protected T consumeInner() {
        try {
            return blockingQueue.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void testProducerConsumerAbCls() {
        new ProducerConsumer<Integer>(Runnable::run) {
            @Override
            void produce() {
                produceInner(1);
                produceInner(2);
            }

            @Override
            void consume() {
                assert consumeInner() == 1;
                System.out.println(consumeInner() == 1);
                assert consumeInner() == 2;
                System.out.println(consumeInner() == 2);
            }
        }.start();
    }



    public void producerConsumer() {
        new ProducerConsumer<Integer>(Executors.newFixedThreadPool(2)) {
            @Override
            void produce() {
                for (int i = 0; i < 10; i++) {
                    produceInner(i + ThreadLocalRandom.current().nextInt(100));
                }
            }

            @Override
            void consume() {
                while (true) {
                    Integer result = consumeInner();
                    System.out.println(result);
                }
            }
        }.start();
    }

    public static void main(String[] args) {
        testProducerConsumerAbCls();
    }
}
