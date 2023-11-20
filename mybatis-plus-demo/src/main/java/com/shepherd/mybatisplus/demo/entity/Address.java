package com.shepherd.mybatisplus.demo.entity;

import lombok.Data;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2023/11/16 18:32
 */
@Data
public class Address {
    private Long id;
    private String province;
    private String city;
    private String region;
    private String address;
}
