package com.shepherd.basedemo.function;

import cn.hutool.core.thread.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/9/4 22:57
 */
public class AsyncUtils {

    public static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNamePrefix("async-pool-").build();
    public static ExecutorService threadPoolExecutor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors()*2,
            Runtime.getRuntime().availableProcessors() * 40,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(Runtime.getRuntime().availableProcessors() * 20),
            namedThreadFactory);

    /**
     * 借助 CompletableFuture 来实现异步行为。
     * 不会抛出异常，在 onFailure 中处理异常
     *
     * @param executeTask
     * @param <R>
     * @return
     */
    private static <T,R> CompletableFuture<R> doInvoker(CallbackTask<T,R> executeTask, T t) {
        CompletableFuture<R> invoke = CompletableFuture
                .supplyAsync(() -> {
                    try {
                        return executeTask.execute(t);
                    } catch (Exception exception) {
                        throw new RuntimeException(exception.getMessage());
                    }
                }, threadPoolExecutor)
                .whenComplete((result, throwable) -> {
                    // 不管成功与失败，whenComplete 都会执行，
                    // 通过 throwable == null 跳过执行
                    if (throwable == null) {
                        executeTask.onSuccess(result);
                    }
                })
                .exceptionally(throwable -> {
                    executeTask.onFailure(throwable);
                    // todo 给一个默认值，或者使用 Optional包装一下，否者异常会出现NPE
                    return null;
                });
        return invoke;
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> result =
                AsyncUtils.doInvoker(new CallbackTask<Integer, String>() {

                    @Override
                    public void onSuccess(String result) {
                        System.out.println("on success result: " + result);
                    }

                    @Override
                    public String execute(Integer integer) {
                        return String.valueOf(integer * 10);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        System.out.println("error " + t.getMessage());
                    }
                }, 10);
        String res = result.get();
        System.out.println(res);
    }
}
