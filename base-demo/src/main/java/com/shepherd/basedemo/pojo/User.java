package com.shepherd.basedemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2023/12/27 15:01
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String userNo;
    private String name;
    private Integer age;
}
