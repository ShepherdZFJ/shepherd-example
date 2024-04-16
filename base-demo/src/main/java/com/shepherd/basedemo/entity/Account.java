package com.shepherd.basedemo.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/4/12 10:43
 */
@Data
public class Account {
    private String name;
    private String idCard;
    private String cardNo;
    private BigDecimal amount;
    private Integer status;
    private Date lastUsedTime;
}
