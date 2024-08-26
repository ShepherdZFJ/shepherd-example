package com.shepherd.basedemo.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/8/21 11:02
 */
@Slf4j
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {


    /**
     * 入参筛选
     *
     * @param methodParameter 参数集合
     * @return 格式化后的参数
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(LoginUser.class) && methodParameter.getParameterType().equals(UserSession.class);
    }

    /**
     * @param methodParameter       入参集合
     * @param modelAndViewContainer model 和 view
     * @param nativeWebRequest      web相关
     * @param webDataBinderFactory  入参解析
     * @return 包装对象
     */
    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) {
        return getCurrentUser(nativeWebRequest);
    }

    private UserSession getCurrentUser(NativeWebRequest webRequest) {
        // 这里是获取当前用户的逻辑
        // 1.你可以从请求信息中获取
        // HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        // ...

        // 2.也可以从登陆认证之后的上下文中获取
        // UserSession currentUser = RequestUserHolder.getCurrentUser();

        // 这里为了示例，就直接返回一个userSession进行模拟了
        UserSession session = new UserSession();
        session.setId(8L);
        session.setName("张三");
        session.setOrgId(6L);
        return session;
    }


}
