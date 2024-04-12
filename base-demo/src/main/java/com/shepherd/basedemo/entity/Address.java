package com.shepherd.basedemo.entity;

import lombok.Data;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/4/12 10:39
 */
@Data
public class Address {
    private String province;
    private String city;
    private String region;
    private String address;
}
