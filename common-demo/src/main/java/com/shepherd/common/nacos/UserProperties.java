package com.shepherd.common.nacos;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.Data;
import org.springframework.context.annotation.Configuration;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/4/21 14:45
 */
@Configuration
@Data
public class UserProperties {
    @NacosValue(value = "${user1.name}", autoRefreshed = true)
    private String name;
}
