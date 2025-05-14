package com.shepherd.basedemo.pojo;

import lombok.Data;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/11/15 14:35
 */
@Data
public class Org {
    private Long id;
    private String name;
    private Integer isAdmin;
    private Integer isMask;
}
