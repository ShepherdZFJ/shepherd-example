package com.shepherd.common.config;

import lombok.Builder;
import lombok.Data;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2023/4/9 23:39
 */
@Data
@Builder
public class Language {
    private Long id;
    private String content;
}
