package com.shepherd.basedemo.runner;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.stereotype.Component;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/10/30 14:44
 */
@Component
public class MySmartInitializingSingleton implements SmartInitializingSingleton {
    @Override
    public void afterSingletonsInstantiated() {
        System.out.println("所有单例 Bean 初始化完成，执行 afterSingletonsInstantiated() 方法！");
    }
}
