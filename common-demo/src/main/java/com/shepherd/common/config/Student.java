package com.shepherd.common.config;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2023/3/27 13:41
 */
@Data
//@Component
public class Student {
    private String stuNo;
    private String name;
    private Integer age;
}
