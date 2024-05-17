package com.shepherd.basedemo.interceptor;

import com.shepherd.basedemo.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/5/14 15:52
 */
@Component
@Slf4j
public class IpAccessInterceptor implements HandlerInterceptor {


    @Value("${ip.black}")
    private Set<String> blackIpList;

    @Value("${ip.white}")
    private Set<String> whiteIpList;


    private final static String LOCAL = "127.0.0.1";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = IpUtil.getIpAddress();
        if (Objects.equals(ip, LOCAL)) {
            // 本机直接放过
            return true;
        }
        // 获取黑白名单：从配置文件、或者缓存、数据库都可以，这里还可以定制化判断，比如结合登录人角色一起判断都可以
        // 这里为了方便演示，我从配置文件读取
        if (blackIpList.contains(ip)) {
            // 在黑名单中直接拒绝访问
            log.info("ip:{} 在黑名单中拒绝访问.....", ip);
            return false;
        }
        if (!whiteIpList.contains(ip)) {
            // 不在白名单中，也拒绝访问
            log.info("ip:{} 不在白名单中拒绝访问.....", ip);
            return false;
        }
        // 验证通过
        return true;
    }


}
