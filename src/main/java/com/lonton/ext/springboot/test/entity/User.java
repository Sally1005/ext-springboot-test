package com.lonton.ext.springboot.test.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 数据库中用户属性
 * <p/>
 * @author 张利红
 */
@Data
public class User implements Serializable {
    /**
     * id
     */
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 手机号码
     */
    private String phone;
}
