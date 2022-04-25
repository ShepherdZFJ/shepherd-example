package com.shepherd.example.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/4/25 22:09
 */

/**
 * 简单分析，主线程初始值是"xiexiexie"，子线程1的inheritableThreadLocal打出来的也是"xiexiexie"；子线程1改成了"qiqiqi";子线程2的
 * inheritableThreadLocal打出来的也是"qiqiqi"，这个主要是inheritableThreadLocal赋值是浅复制，子线程1变化了，其实也是变化了主线程
 * ThreadLocalMap。这时主线程把值换成了“setsetset”，但是子线程2的打出来的依旧是上一次的结果"qiqiqi"。
 * 造成这个结果的原因：只有创建子线程的时候才会设置子线程的inheritableTreadLocals值，假如第一次提交的任务是A，第二次是B，B任务提交任
 * 务时使用的是A的任务的缓存线程，A任务执行时已经重新set了InheritableThreadLocals，值已经变为qiqiqi，B任务再次获取时候直接从
 * t.inheritableThreadLocals中获取，所以获得的是A任务提交的值，而不是父线程的值(父线程值没有改变的原因是子线程set的值，只会set到子
 * 线程对应的t.inheritableThreadLocals中，不会影响父线程的inheritableThreadLocals)
 */
public class InheritableThreadLocalTest {

    public static void main(String[] args) throws InterruptedException {

        final InheritableThreadLocal<Span> inheritableThreadLocal=new InheritableThreadLocal<>();
        inheritableThreadLocal.set(new Span("xiexiexie"));
        //输出xiexiexie
        Span span1 = inheritableThreadLocal.get();
        System.out.println(span1.name);
        // 注意执行结果和这里的线程池核心数有关系，记住线程池会重复利用线程，而不是每次都重新创建，这是导致InheritableThreadLocal变量传递出问题的原因
        ExecutorService es= Executors.newFixedThreadPool(1);
        es.execute(()->{
            System.out.println("====子线程1====");
            //输出 xiexiexie
            Span span2 = inheritableThreadLocal.get();
            System.out.println(span2.name);
            inheritableThreadLocal.set(new Span("qiqiqi"));
            //输出 qiqiqi
            span2 = inheritableThreadLocal.get();
            System.out.println(span2.name);
        });
        TimeUnit.SECONDS.sleep(1);

        inheritableThreadLocal.set(new Span("setsetset"));
        es.execute(()->{
            System.out.println("====子线程2====");
            //输出qiqiqi
            Span span2 = inheritableThreadLocal.get();
            System.out.println(span2.name);
            inheritableThreadLocal.set(new Span("xxx"));
            //输出qiqiqi
            span2 = inheritableThreadLocal.get();
            System.out.println(span2.name);
        });

        TimeUnit.SECONDS.sleep(1);
        System.out.println("====main线程====");
        //输出xiexiexie
        Span span = inheritableThreadLocal.get();
        System.out.println(span.name);

    }

    static class Span{
        public String name;
        public Span(String name){
            this.name=name;
        }
    }
}

