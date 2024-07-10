package com.shepherd.basedemo.advice;

import com.shepherd.basedemo.entity.Address;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/6/6 18:12
 */

@ControllerAdvice
public class LogRequestBodyAdvice implements RequestBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 支持所有请求
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        // 读取请求体之前的逻辑，可以用来记录日志
        System.out.println("Before Body Read: " + inputMessage.getBody());
        return inputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 读取请求体之后的逻辑，可以用来记录日志
        Address address = (Address) body;
        address.setCity("123sdf");
        address.setAddress("谁带饭");
        System.out.println("After Body Read: " + body);
        return address;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 处理空请求体
        System.out.println("Handle Empty Body");
        return body;
    }
}
