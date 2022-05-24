package com.shepherd.common.nacos;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/4/21 18:40
 */
@Configuration
public class ABCConfig {
    @Conditional(ABCCondition.class)
    @Bean
    public ABC abc() {
        return new ABC();
    }
}
