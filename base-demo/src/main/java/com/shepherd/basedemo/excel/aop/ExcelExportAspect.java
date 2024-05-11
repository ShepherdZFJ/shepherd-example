package com.shepherd.basedemo.excel.aop;

import com.alibaba.excel.EasyExcel;
import com.shepherd.basedemo.excel.anno.ExcelExport;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/5/8 15:25
 */
@Aspect
@Slf4j
@Component
public class ExcelExportAspect {

    /**
     * 注解切面，打了注解才会进入
     */
    @Pointcut("@annotation(com.shepherd.basedemo.excel.anno.ExcelExport)")
    public void excelExportPointcut(){}

    @Around("excelExportPointcut()")
    public void aroundExcelExport(ProceedingJoinPoint joinPoint) throws Throwable {
        // 1.获取注解信息
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        ExcelExport excelExport = method.getAnnotation(ExcelExport.class);// 文件名
        String name = excelExport.name();
        String sheetName = excelExport.sheetName();
        Object obj = joinPoint.proceed();
        if (!(obj instanceof List)) {
            return;
        }
        List dataList = (List) obj;
        ParameterizedType returnType = (ParameterizedType) method.getGenericReturnType();
        Class<? extends Type> dataClass = returnType.getActualTypeArguments()[0].getClass();
        String fileName = URLEncoder.encode(name, "UTF-8");
        // 根据实际的文件类型找到对应的 contentType
        String contentType = MediaTypeFactory.getMediaType(fileName).map(MediaType::toString)
                .orElse("application/vnd.ms-excel");
        HttpServletResponse response = getResponse();
        response.setContentType(contentType);
        response.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
        response.setCharacterEncoding("utf-8");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename*=utf-8''" + fileName);
        EasyExcel.write(response.getOutputStream(), dataClass).sheet(sheetName).doWrite(dataList);
    }

    private ExcelExport getExcelExport(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        ExcelExport excelExport = method.getAnnotation(ExcelExport.class);
        return excelExport;
    }

    private HttpServletResponse getResponse() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = requestAttributes.getResponse();
        return response;
    }

    public static void main(String[] args) throws NoSuchMethodException {
        // 获取方法对象
        Method method = ExcelExportAspect.class.getMethod("getList");

        // 获取方法返回类型
        Type returnType = method.getGenericReturnType();

        // 判断是否为 ParameterizedType（参数化类型）
        if (returnType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) returnType;

            // 获取泛型类型参数列表
            Type[] typeArguments = parameterizedType.getActualTypeArguments();

            // 遍历泛型类型参数列表
            for (Type typeArgument : typeArguments) {
                // 输出泛型类型参数
                System.out.println("泛型类型参数: " + typeArgument);
            }
        }
    }

    // 定义一个返回类型为 List<String> 的方法
    public List<String> getList() {
        return null;
    }
}
