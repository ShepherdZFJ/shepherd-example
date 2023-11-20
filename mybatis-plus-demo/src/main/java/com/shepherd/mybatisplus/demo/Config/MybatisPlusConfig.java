package com.shepherd.mybatisplus.demo.Config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.shepherd.mybatisplus.demo.handlers.DefaultDBFieldHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2023/11/16 18:12
 */
@Configuration
public class MybatisPlusConfig {

    @Bean
    public MetaObjectHandler defaultMetaObjectHandler(){
        return new DefaultDBFieldHandler();
    }
}
