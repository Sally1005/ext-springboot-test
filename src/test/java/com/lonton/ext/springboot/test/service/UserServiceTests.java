package com.lonton.ext.springboot.test.service;

import com.lonton.ext.springboot.test.ex.PasswordNotMatchException;
import com.lonton.ext.springboot.test.ex.PhoneConflictException;
import com.lonton.ext.springboot.test.ex.UsernameConflictException;
import com.lonton.ext.springboot.test.pojo.dto.CreateUserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

/**
 * 测试用户注册业务
 *
 * @author 张利红
 */
@SpringBootTest
public class UserServiceTests {
    @Autowired
    IUserService userService;

    // 1. 插入数据成功
    @Test
    @Sql(value = "classpath:/sql/truncate_user.sql ")
    @Sql(value = "classpath:/sql/truncate_user.sql ",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testCreateNewUserSuccessfully(){
        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setUsername("user001");
        createUserDTO.setPassword("1234567");
        createUserDTO.setConfirmPassword("1234567");
        createUserDTO.setPhone("12346789001");
        Assertions.assertDoesNotThrow(()->{
            userService.createNewUser(createUserDTO);
        });
    }
    // 2. 插入数据失败。1)密码与确认密码不一致；2）用户名被占用；3）手机号码被占用
    @Test
    @Sql(value = "classpath:/sql/truncate_user.sql ")
    @Sql(value = "classpath:/sql/truncate_user.sql ",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testCreateNewUserFailBecausePasswordNotMatch(){
        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setUsername("user001");
        createUserDTO.setPassword("1234567");
        createUserDTO.setConfirmPassword("123456");
        createUserDTO.setPhone("12346789002");
        Assertions.assertThrows(PasswordNotMatchException.class,()->{
            userService.createNewUser(createUserDTO);
        });
    }
    @Test
    @Sql(value ={ "classpath:/sql/truncate_user.sql","classpath:/sql/insert_user_1.sql "})
    @Sql(value = "classpath:/sql/truncate_user.sql ",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testCreateNewUserFailBecauseUsernameConflict(){
        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setUsername("user001");
        createUserDTO.setPassword("1234567");
        createUserDTO.setConfirmPassword("1234567");
        createUserDTO.setPhone("12346789088");
        Assertions.assertThrows(UsernameConflictException.class,()->{
            userService.createNewUser(createUserDTO);
        });
    }
    @Test
    @Sql(value ={ "classpath:/sql/truncate_user.sql","classpath:/sql/insert_user_1.sql "})
    @Sql(value = "classpath:/sql/truncate_user.sql ",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testCreateNewUserFailBecausePhoneConflict(){
        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setUsername("user002");
        createUserDTO.setPassword("1234567");
        createUserDTO.setConfirmPassword("1234567");
        createUserDTO.setPhone("13646789001");
        Assertions.assertThrows(PhoneConflictException.class,()->{
            userService.createNewUser(createUserDTO);
        });
    }



}
