package com.shepherd.basedemo.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/6/13 14:41
 */
@ControllerAdvice
@Slf4j
public class GlobalAdviceHandler {


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // 自定义数据绑定逻辑
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), false));
    }

    /**
     * 全局异常处理
     * @param e
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public ResponseVO exceptionHandler(Exception e){
        // 处理业务异常
        if (e instanceof BizException) {
            BizException bizException = (BizException) e;
            if (bizException.getCode() == null) {
                bizException.setCode(ResponseStatusEnum.BAD_REQUEST.getCode());
            }
            return ResponseVO.failure(bizException.getCode(), bizException.getMessage());
        } else if (e instanceof MethodArgumentNotValidException) {
            // 参数检验异常
            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) e;
            Map<String, String> map = new HashMap<>();
            BindingResult result = methodArgumentNotValidException.getBindingResult();
            result.getFieldErrors().forEach((item)->{
                String message = item.getDefaultMessage();
                String field = item.getField();
                map.put(field, message);
            });
            log.error("数据校验出现错误：", e);
            return ResponseVO.failure(ResponseStatusEnum.BAD_REQUEST, map);
        } else if (e instanceof ConstraintViolationException) {
            log.error("数据校验出现错误：", e);
            return ResponseVO.failure(ResponseStatusEnum.BAD_REQUEST, e.getMessage());
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            log.error("请求方法错误：", e);
            return ResponseVO.failure(ResponseStatusEnum.BAD_REQUEST.getCode(), "请求方法不正确");
        } else if (e instanceof MissingServletRequestParameterException) {
            log.error("请求参数缺失：", e);
            MissingServletRequestParameterException ex = (MissingServletRequestParameterException) e;
            return ResponseVO.failure(ResponseStatusEnum.BAD_REQUEST.getCode(), "请求参数缺少: " + ex.getParameterName());
        } else if (e instanceof MethodArgumentTypeMismatchException) {
            log.error("请求参数类型错误：", e);
            MethodArgumentTypeMismatchException ex = (MethodArgumentTypeMismatchException) e;
            return ResponseVO.failure(ResponseStatusEnum.BAD_REQUEST.getCode(), "请求参数类型不正确：" + ex.getName());
        } else if (e instanceof NoHandlerFoundException) {
            NoHandlerFoundException ex = (NoHandlerFoundException) e;
            log.error("请求地址不存在：", e);
            return ResponseVO.failure(ResponseStatusEnum.NOT_EXIST, ex.getRequestURL());
        } else {
            //如果是系统的异常，比如空指针这些异常
            log.error("【系统异常】", e);
            return ResponseVO.failure(ResponseStatusEnum.SYSTEM_ERROR.getCode(), ResponseStatusEnum.SYSTEM_ERROR.getMsg());
        }
    }

}