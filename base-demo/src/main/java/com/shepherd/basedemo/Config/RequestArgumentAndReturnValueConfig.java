package com.shepherd.basedemo.Config;

import com.shepherd.basedemo.handler.ResponseReturnValueHandler;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/8/19 20:38
 */

//@Configuration
public class RequestArgumentAndReturnValueConfig implements InitializingBean {
    @Resource
    RequestMappingHandlerAdapter requestMappingHandlerAdapter;
    @Override
    public void afterPropertiesSet() throws Exception {
        // 获取处理器list
        List<HandlerMethodReturnValueHandler> originHandlers = requestMappingHandlerAdapter.getReturnValueHandlers();
        List<HandlerMethodReturnValueHandler> newHandlers = new ArrayList<>(originHandlers.size());
        // 遍历处理器list，替换掉RequestResponseBodyMethodProcessor
        for (HandlerMethodReturnValueHandler handler : originHandlers) {
            if (handler instanceof RequestResponseBodyMethodProcessor) {
                newHandlers.add(new ResponseReturnValueHandler(handler));
            }else{
                newHandlers.add(handler);
            }
        }
        // 把新的处理器list放入Spring MVC
        requestMappingHandlerAdapter.setReturnValueHandlers(newHandlers);
    }
}
