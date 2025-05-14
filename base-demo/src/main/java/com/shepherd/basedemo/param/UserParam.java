package com.shepherd.basedemo.param;

import com.shepherd.basedemo.entity.Address;
import com.shepherd.basedemo.validator.CombineNotNull;
import com.shepherd.basedemo.validator.EnumValue;
import com.shepherd.basedemo.validator.GenderEnum;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.validation.groups.Default;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/7/3 14:17
 */
@Data
public class UserParam {
    @NotNull(message = "{user.id.notNull}", groups = {Update.class})
    private Long id;
//    @NotBlank(message = "用户名不能为空", groups = {Insert.class})
//    @Size(min = 8, max = 16, message = "长度必须在8~16个字符之间")
    private String userNo;
//    @NotBlank(message = "姓名不能为空")
//    @Size(max = 32, message = "姓名不能超过32个字符")
    private String name;
//    @NotNull(message = "性别不能为空")
//    @Past(message = "出身日期必须在当前日期之前")
    @CombineNotNull(message = "出生日期不能为空", condition = "#this.gender == 1")
    private Date birthday;
//    @Email(message = "邮箱格式不对")
    private String email;
//    @Pattern(regexp = "^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$", message = "手机号格式不对")
    private String phone;

//    @NotEmpty(message = "地址不能为空")
//    @Valid
    private List<Address> addressList;

    /** 性别  0：男生  1：女生 */
    @EnumValue(message = "性别枚举值不对", linkEnum = GenderEnum.class)
    @NotNull(message = "性别不能为空")
    private Integer gender;


    public interface Insert extends Default {};

    public interface  Update extends Default {};

}
