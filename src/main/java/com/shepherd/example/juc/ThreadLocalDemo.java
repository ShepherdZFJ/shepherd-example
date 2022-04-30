package com.shepherd.example.juc;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/4/24 17:52
 */
public class ThreadLocalDemo {

    private static ThreadLocal<Integer> countValue = new ThreadLocal<Integer>(){
        // 实现initialValue()
        public Integer initialValue() {
            return 0;
        }
    };

    public int nextSeq(){
        countValue.set(countValue.get()  + 1);
        return countValue.get();
    }

    public static void main(String[] args){
         ThreadLocalDemo demo = new ThreadLocalDemo();
        MyThread thread1 = new MyThread(demo);
        MyThread thread2 = new MyThread(demo);
        MyThread thread3 = new MyThread(demo);
        MyThread thread4 = new MyThread(demo);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }

    private static class MyThread extends Thread{
        private ThreadLocalDemo demo;

        MyThread(ThreadLocalDemo demo){
            this.demo = demo;
        }

        @Override
        public void run() {
            for(int i = 0 ; i < 3 ; i++){
                System.out.println(Thread.currentThread().getName() + " countValue :" + demo.nextSeq());
            }
        }
    }
}
