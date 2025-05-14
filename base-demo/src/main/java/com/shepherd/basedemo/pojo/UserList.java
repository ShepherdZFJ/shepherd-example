package com.shepherd.basedemo.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2025/2/21 19:39
 */
@Data
public class UserList {
    private Long id;
    private List<UserInfo> users;
}
