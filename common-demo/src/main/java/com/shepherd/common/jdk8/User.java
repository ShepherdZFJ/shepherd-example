package com.shepherd.common.jdk8;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/3/11 11:40
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String no;
    private String name;
    private Date birthday;
    private Integer age;
    private Integer gender;
    private String email;
    private String phone;
}
