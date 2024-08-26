package com.shepherd.basedemo.handler;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/8/21 11:37
 */
public class RequestUserHolder {
    private static final ThreadLocal<UserSession> userHolder = new ThreadLocal<>();

    /**
     * 存储用户信息
     *
     * @param userSession
     */
    public static void add(UserSession userSession) {
        userHolder.set(userSession);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public static UserSession getCurrentUser() {
        return userHolder.get();
    }

    /**
     * 清除
     */
    public static void remove() {
        userHolder.remove();
    }
}
