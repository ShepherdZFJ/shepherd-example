package com.shepherd.common.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Objects;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2023/4/9 23:41
 */
public class ChineseCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        String property = environment.getProperty("lang");
        if (Objects.equals(property, "zh_CN")) {
            return true;
        }
        return false;
    }
}
