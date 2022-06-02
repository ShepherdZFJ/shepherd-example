package com.shepherd.third.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/6/1 18:04
 */
@Data
public class Brand {
    private Long id;
    private Long categoryId;
    private String name;
    private String image;
    private String description;
    private String letter;
    private Integer isDelete;
    private Date createTime;
    private Date updateTime;

}
