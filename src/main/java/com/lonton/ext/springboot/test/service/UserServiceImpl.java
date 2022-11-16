package com.lonton.ext.springboot.test.service;

import com.lonton.ext.springboot.test.entity.User;
import com.lonton.ext.springboot.test.ex.InsertConflictException;
import com.lonton.ext.springboot.test.ex.PasswordNotMatchException;
import com.lonton.ext.springboot.test.ex.PhoneConflictException;
import com.lonton.ext.springboot.test.ex.UsernameConflictException;
import com.lonton.ext.springboot.test.mapper.UserMapper;
import com.lonton.ext.springboot.test.pojo.dto.CreateUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 用户注册业务
 *
 * @author 张利红
 */

@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    private UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void createNewUser(CreateUserDTO createUserDTO) {
        log.debug("UserServiceImpl. createNewUser()");
        log.debug("参数：createUserDTO:{}",createUserDTO);
        log.debug("检查参数createUserDTO中\"密码\"与\"确认密码\"是否一致");
        String password = createUserDTO.getPassword();
        String confirmPassword = createUserDTO.getConfirmPassword();
        log.debug("\"密码\"与\"确认密码\"不一致将抛出 PasswordNotMatchException");
        if(password == null || !password.equals(confirmPassword)){
            throw new PasswordNotMatchException("用户注册失败，\"密码\"与\"确认密码\"不一致。");
        }
        log.debug("\"密码\"与\"确认密码\"一致!");

        log.debug("检查用户名是否被占用");
        String username = createUserDTO.getUsername();
        User result = userMapper.findByUsername(username);
        if (result != null) {
            log.debug("根据：{}查询存在匹配结果，若存在则抛出UsernameConflictException，用户名已被占用。",username);
            throw new UsernameConflictException("用户注册失败，\"用户名\"已被占用。");
        }
        log.debug("根据：{}查询不存在匹配结果，用户名未被占用。",username);

        log.debug("检查手机号码是否被占用");
        String phone = createUserDTO.getPhone();
        result = userMapper.findByPhone(phone);
        if (result != null) {
            log.debug("根据：{}查询存在匹配结果，若存在则抛出PhoneConflictException，手机号码已被占用。",phone);
            throw new PhoneConflictException("用户注册失败，\"手机号码\"已被占用。");
        }
        log.debug("根据：{}查询不存在匹配结果，手机号码未被占用。",phone);

        log.debug("所有检查均通过，开始插入数据");
        User user = new User();
        BeanUtils.copyProperties(createUserDTO,user);
        log.debug("准备插入的数据为：{}",user);
        int rows = userMapper.insert(user);
        if (rows != 1) {
            log.debug("插入的数据受影响行数达不到预期值为1，则抛出InsertConflictException");
            throw new InsertConflictException("用户注册失败,插入数据时出现错误，请稍后再试，或联系管理员进行处理。");
        }
        log.debug("插入数据成功。UserServiceImpl. createNewUser()执行结束！");
    }
}
