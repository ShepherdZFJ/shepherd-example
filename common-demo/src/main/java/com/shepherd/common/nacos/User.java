package com.shepherd.common.nacos;

import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/4/21 14:42
 */

/**
 * 注意   注意    注意
 * 使用@ConfigurationProperties注解如果不走配置中心，或者走配置中心但没有配置当前配置属性信息，这时候会加载本地配置问题
 * 使用@ConfigurationProperties不会动态更新
 * 使用@NacosConfigurationProperties注解如果不走配置中心，或者走配置中心但没有配置当前配置属性信息，这时候即时本地文件配置了也不会加载注入属性
 */
@Data
@Component
@NacosConfigurationProperties(prefix = "user1", autoRefreshed = true, dataId = "example.properties")
//@ConfigurationProperties(prefix = "user1")
public class User {
    private String name;
    private Integer age;
    private Long id;
}
