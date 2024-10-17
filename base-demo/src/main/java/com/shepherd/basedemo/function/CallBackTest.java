package com.shepherd.basedemo.function;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/9/10 15:43
 */
// 1. 定义回调接口
interface Callback {
    void onComplete(String result);
}

// 2. 定义业务逻辑类，接受回调
class Task {
    public void execute(Callback callback) {
        // 模拟一些业务逻辑处理
        String result = "Task Completed!";
        // 回调通知调用方
        callback.onComplete(result);
    }
}

// 3. 使用回调
public class CallBackTest {
    public static void main(String[] args) {
        Task task = new Task();

        // 使用Lambda表达式实现回调
        task.execute(result -> System.out.println("Callback received: " + result));
    }

}
