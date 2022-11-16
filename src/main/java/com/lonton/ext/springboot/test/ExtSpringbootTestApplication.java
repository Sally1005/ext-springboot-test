package com.lonton.ext.springboot.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * 启动类
 * <p/>
 * @author 张利红
 */
@MapperScan("com.lonton.ext.springboot.test.mapper")
@SpringBootApplication
public class ExtSpringbootTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExtSpringbootTestApplication.class, args);
    }

}
