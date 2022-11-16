package com.lonton.ext.springboot.test.validation;

/**
 * 验证参数格式是否正确的正则表达式
 * <p/>
 * @author 张利红
 */
public interface RegExpressions {
    String REGEXP_USERNAME ="^[a-zA-Z]{1}[0-9a-zA-Z]{3,15}$";
    String MESSAGE_USERNAME = "用户名必须是字母和数字组合的4~16字符，并且第一个字符必须是字母！";

    String REGEXP_PASSWORD ="^\\S{4,16}$";
    String MESSAGE_PASSWORD = "密码的长度必须是4~16位！";

    String REGEXP_PHONE ="^1(3[0-9]|4[5,7]|5[0-3,5-9]|7[2-3,6-8]|8[0-9]|9[1,8-9])\\d{8}$";
    String MESSAGE_PHONE = "请填写中国大陆正确的手机号码！";
}
