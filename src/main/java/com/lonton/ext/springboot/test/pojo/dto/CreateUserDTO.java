package com.lonton.ext.springboot.test.pojo.dto;

import static com.lonton.ext.springboot.test.validation.RegExpressions.*;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
/**
 * createNewUser的方法参数
 * <p/>
 * @author 张利红
 */
@Data
public class CreateUserDTO implements Serializable {
    private  static final String MESSAGE_PREFIX = "创建新用户失败！";

    @NotNull(message = MESSAGE_PREFIX+"请提交用户名!")
    @Pattern(regexp = REGEXP_USERNAME,message = MESSAGE_PREFIX+MESSAGE_USERNAME)
    private String username;

    @NotNull(message = MESSAGE_PREFIX+"请提交密码!")
    @Pattern(regexp = REGEXP_PASSWORD,message = MESSAGE_PREFIX+MESSAGE_PASSWORD)
    private String password;

    @NotNull(message = MESSAGE_PREFIX+"请确认密码!")
    @Pattern(regexp = REGEXP_PASSWORD,message = MESSAGE_PREFIX+MESSAGE_PASSWORD)
    private String confirmPassword;

    @NotNull(message = MESSAGE_PREFIX+"请提交手机号码!")
    @Pattern(regexp = REGEXP_PHONE,message = MESSAGE_PREFIX+MESSAGE_PHONE)
    private String phone;
}
