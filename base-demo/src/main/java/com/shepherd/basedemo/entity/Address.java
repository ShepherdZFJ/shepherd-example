package com.shepherd.basedemo.entity;

import com.shepherd.basedemo.validator.CombineNotNull;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/4/12 10:39
 */
@Data
public class Address {
    @NotBlank(message = "省份不能为空")
    private String province;
    @NotBlank(message = "城市不能为空")
    private String city;
    private String region;
    @CombineNotNull(message = "地址不能为空", condition = "#this.region != null")
    private String address;
    private Long id;
}
