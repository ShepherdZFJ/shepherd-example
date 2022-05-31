package com.shepherd.common.vo;

import lombok.Data;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/5/27 09:46
 */
@Data
public class BrandVO {
    private Long categoryId;
    private String name;
    private String image;
    private String description;
    @Size(max = 2, message = "名称最大支持2条搜索")
    private List<String> nameList;
}
