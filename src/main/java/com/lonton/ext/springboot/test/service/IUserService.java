package com.lonton.ext.springboot.test.service;

import com.lonton.ext.springboot.test.pojo.dto.CreateUserDTO;
/**
 * 创建用户接口
 * <p/>
 * @author 张利红
 */
public interface IUserService {
    /**
     * 创建新用户
     * <br/>
     * @param createUserDTO 封装了创建用户的相关数据
     */
    void createNewUser(CreateUserDTO createUserDTO);
}
