package com.shepherd.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/4/18 22:40
 */
@Data
public class Brand {
    @TableId(type = IdType.AUTO)
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
