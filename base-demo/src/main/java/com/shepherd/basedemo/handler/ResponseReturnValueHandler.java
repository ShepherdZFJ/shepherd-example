package com.shepherd.basedemo.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shepherd.basedemo.advice.ResponseVO;
import com.shepherd.basedemo.entity.User;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/8/19 19:45
 */
public class ResponseReturnValueHandler implements HandlerMethodReturnValueHandler {

    private HandlerMethodReturnValueHandler handler;

    public ResponseReturnValueHandler(HandlerMethodReturnValueHandler handler) {
        this.handler = handler;
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        // 和RequestResponseBodyMethodProcessor支持的一样，方便后续替换
        return handler.supportsReturnType(returnType);
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        if (!(returnValue instanceof ResponseVO)) {
            returnValue = ResponseVO.success(returnValue);
        }
        handler.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
    }
}
