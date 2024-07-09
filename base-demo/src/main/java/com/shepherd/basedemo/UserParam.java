package com.shepherd.basedemo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/7/3 14:17
 */
@Data
public class UserParam {
    private Long id;
    private String name;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDate birthday;

    private LocalDateTime createTime;
}
