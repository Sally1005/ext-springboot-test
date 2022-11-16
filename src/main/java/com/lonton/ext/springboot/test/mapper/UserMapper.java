package com.lonton.ext.springboot.test.mapper;

import com.lonton.ext.springboot.test.entity.User;
import org.springframework.stereotype.Repository;

/**
 * 存储层插入和查询用户详情
 * <p/>
 * @author 张利红
 */
@Repository
public interface UserMapper {
    /**
     * 插入用户数据
     * <br/>
     * @param user 将插入的用户数据
     * @return 受影响的行数
     */
    int insert (User user);

    /**
     * 根据用户名查询用户详情
     * <br/>
     * @param username 用户名
     * @return 匹配的用户详情,如果没有匹配的数据返回null
     */
    User findByUsername(String username);

    /**
     * 根据手机号码查询用户详情
     * <br/>
     * @param phone 用户名
     * @return 匹配的用户详情，如果没有匹配的数据返回null
     */
    User findByPhone(String phone);
}
