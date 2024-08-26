package com.shepherd.basedemo.handler;

import java.lang.annotation.*;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/8/21 11:31
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginUser {
}
