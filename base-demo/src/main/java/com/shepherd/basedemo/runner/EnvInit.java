package com.shepherd.basedemo.runner;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/10/9 15:42
 */
@Data
public class EnvInit {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Integer isInit;
}
