package com.shepherd.basedemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/4/9 15:59
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_user")
public class User {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String userNo;

    private String nickname;

    private String email;

    private String phone;

    private Integer gender;

    private Date birthday;

    private String address;

    private Integer isDelete;

}
