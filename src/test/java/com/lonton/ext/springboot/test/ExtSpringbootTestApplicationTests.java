package com.lonton.ext.springboot.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class ExtSpringbootTestApplicationTests {
    @Autowired
    DataSource dataSource;

    @Test
    void contextLoads() {

    }
    @Test
    void testGetConnectionSuccessfully() throws Exception {
         // 使用Assert进行断言，jdk1.8 中lambda表达式（）->{},中括号中可以使用语句来传参
         // 静态导入Assertions可以避免断言代码过长
        Assertions.assertDoesNotThrow(()->{ dataSource.getConnection();});
    }

}
