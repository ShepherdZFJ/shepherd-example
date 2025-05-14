package com.shepherd.basedemo.entity;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2025/1/7 14:18
 */


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 序号实体类。
 *
 * @author
 */
@TableName("sequence")
@Data
public class Sequence {
    /**
     * 序号类型
     */
    @TableId
    private String sequenceType;

    /**
     * 启始数值
     */
    private long startValue;

    /**
     * 当前数值
     */
    private long currValue;

    /**
     * 建立时间
     */
    private LocalDateTime crtTime;
}
