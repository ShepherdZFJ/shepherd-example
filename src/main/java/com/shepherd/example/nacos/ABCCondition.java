package com.shepherd.example.nacos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/4/21 18:39
 */
@Slf4j
public class ABCCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String property = context.getEnvironment().getProperty("enable.scheduled");
        log.info("读取配置中的属性enable.scheduled = [{}]", property);
        return Boolean.valueOf(property);
    }
}
