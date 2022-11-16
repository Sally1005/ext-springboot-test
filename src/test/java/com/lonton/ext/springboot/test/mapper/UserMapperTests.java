package com.lonton.ext.springboot.test.mapper;

import com.lonton.ext.springboot.test.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
// @Transactional 测试能够回滚，不会将测试的数据储存在数据库中
public class UserMapperTests {
    @Autowired
    UserMapper userMapper;

    /**
     *  1. 正常插入数据时，应该成功，成功后受影响的行数应该为1，第1次插入数据时id应该为1；
     */
    @Test
    @Sql(value = "classpath:/sql/truncate_user.sql ")
    @Sql(value = "classpath:/sql/truncate_user.sql ",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
   // @Commit
   // @Sql(value = "classpath:/sql/truncate_user.sql ",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testInsertSuccessfully(){
        User user = new User();
        user.setUsername("user001");
        user.setPassword("1234567");
        user.setPhone("12346789001");
        Assertions.assertDoesNotThrow(()->{
            int rows = userMapper.insert(user);
            Assertions.assertEquals(1,rows);
            Assertions.assertEquals(1,user.getId());
        });
    }

    /**
     * 2. 插入用户名冲突时，应该抛出org.springframework.dao.DuplicateKeyException异常
     */
    @Test
    @Sql(value = "classpath:/sql/insert_user_1.sql")
    @Sql(value = "classpath:/sql/truncate_user.sql ",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testInsertFailBecauseUsernameDuplicate(){
        User user = new User();
        user.setUsername("user001");
        user.setPassword("1234567");
        user.setPhone("12346789001");
        Assertions.assertThrows(DuplicateKeyException.class,()->{
            userMapper.insert(user);
        });
    }

    /**
     * 3. 插入手机号码冲突时，应该抛出org.springframework.dao.DuplicateKeyException异常
     */
    @Test
    @Sql(value = "classpath:/sql/insert_user_1.sql")
    @Sql(value = "classpath:/sql/truncate_user.sql ",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testInsertFailBecausePhoneDuplicate(){
        User user = new User();
        user.setUsername("user002");
        user.setPassword("1234567");
        user.setPhone("13646789001");
        Assertions.assertThrows(DuplicateKeyException.class,()->{
            userMapper.insert(user);
        });
    }
    /**
     * 4. 插入用户名为null时，应该抛出org.springframework.dao.DataIntegrityViolationException异常
     */
    @Test
    @Sql(value = "classpath:/sql/truncate_user.sql ",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testInsertFailBecauseUsernameIsNull(){
        User user = new User();
        user.setUsername(null);
        user.setPassword("1234567");
        user.setPhone("12346789001");
        Assertions.assertThrows(DataIntegrityViolationException.class,()->{
            userMapper.insert(user);
        });
    }
    /**
     * 5. 插入密码为null时，应该抛出org.springframework.dao.DataIntegrityViolationException异常
     */
    @Test
    @Sql(value = "classpath:/sql/truncate_user.sql ",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testInsertFailBecausePasswordIsNull(){
        User user = new User();
        user.setUsername("admin");
        user.setPassword(null);
        user.setPhone("12346789001");
        Assertions.assertThrows(DataIntegrityViolationException.class,()->{
            userMapper.insert(user);
        });
    }
    /**
     * 6. 插入手机号码为null时，应该抛出org.springframework.dao.DataIntegrityViolationException异常
     */
    @Test
    @Sql(value = "classpath:/sql/truncate_user.sql ",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testInsertFailBecausePhoneIsNull(){
        User user = new User();
        user.setUsername("admin01");
        user.setPassword("1234567");
        user.setPhone(null);
        Assertions.assertThrows(DataIntegrityViolationException.class,()->{
            userMapper.insert(user);
        });
    }
    /**
     * 7. 根据用户名查询数据库中存在的有效的数据，能够成功查询出来
     */
    @Test
    @Sql(value = "classpath:/sql/insert_user_1.sql")
    @Sql(value = "classpath:/sql/truncate_user.sql ",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testFailByUsernameSuccessfully(){
        String username = "user001";
        User result = userMapper.findByUsername(username);
        Assertions.assertNotNull(result);
        Assertions.assertEquals("user001",result.getUsername());
        Assertions.assertEquals("1234567",result.getPassword());
        Assertions.assertEquals("13646789001",result.getPhone());
        Assertions.assertEquals(1,result.getId());
    }
    /**
     * 8. 根据用户名查询数据库中没有的数据，查询出来的结果为null
     */
    @Test
    void testFailByUsernameFail(){
        String username = "userHero";
        User result = userMapper.findByUsername(username);
        Assertions.assertNull(result);
    }

    /**
     * 9. 根据手机号码查询数据库中存在的有效的数据，能够成功查询出来
     */
    @Test
    @Sql(value = "classpath:/sql/insert_user_1.sql")
    @Sql(value = "classpath:/sql/truncate_user.sql ",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testFailByPhoneSuccessfully(){
        String phone = "13646789001";
        User result = userMapper.findByPhone(phone);
        Assertions.assertNotNull(result);
        Assertions.assertEquals("user001",result.getUsername());
        Assertions.assertEquals("1234567",result.getPassword());
        Assertions.assertEquals("13646789001",result.getPhone());
        Assertions.assertEquals(1,result.getId());
    }
    /**
     * 10. 根据手机号码查询数据库中没有的数据，查询出来的结果为null
     */
    @Test
    void testFailByPhoneFail(){
        String phone = "1221222221";
        User result = userMapper.findByPhone(phone);
        Assertions.assertNull(result);
    }

}
