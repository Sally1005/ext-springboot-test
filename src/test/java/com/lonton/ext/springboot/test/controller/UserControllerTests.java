package com.lonton.ext.springboot.test.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@Slf4j
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class UserControllerTests {
    private static final String BASE_URL = "/users";
    private static final String CREATE_URL = BASE_URL + "/create";

    @Autowired
    MockMvc mockMvc;

     // 1. 成功注册
    @Test
    @Sql(value ={ "classpath:/sql/truncate_user.sql","classpath:/sql/insert_user_1.sql "})
    @Sql(value = "classpath:/sql/truncate_user.sql ",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testCreateSuccessfully() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(CREATE_URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .param("username","user004")
        .param("password","1234567")
        .param("confirmPassword","1234567")
        .param("phone","13645678904")
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("status").value(HttpStatus.OK.value()))
        .andDo(MockMvcResultHandlers.print());
    }
    // 2. 测试检验参数格式失败
    @Test
    @Sql(value ={ "classpath:/sql/truncate_user.sql","classpath:/sql/insert_user_1.sql "})
    void testCreateFailBecauseValid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(CREATE_URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username","user004")
                .param("password","1234567")
                .param("confirmPassword","1234567")
                .param("phone","12345678901")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("status").value(HttpStatus.BAD_REQUEST.value()))
                .andDo(MockMvcResultHandlers.print());
    }
    // 3. 测试密码与确认密码不一致
    @Test
    @Sql(value = "classpath:/sql/truncate_user.sql")
    @Sql(value = "classpath:/sql/truncate_user.sql ",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testCreateFailBecausePasswordConflict() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(CREATE_URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username","user004")
                .param("password","1234567")
                .param("confirmPassword","12345678")
                .param("phone","12345678902")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("status").value(HttpStatus.BAD_REQUEST.value()))
                .andDo(MockMvcResultHandlers.print());
    }
    // 4. 用户名被占用
    @Test
    @Sql(value = {"classpath:/sql/truncate_user.sql","classpath:/sql/insert_user_1.sql"})
    @Sql(value = "classpath:/sql/truncate_user.sql ",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testCreateFailBecauseUsernameConflict() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(CREATE_URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username","user001")
                .param("password","1234567")
                .param("confirmPassword","1234567")
                .param("phone","13645678955")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("status").value(HttpStatus.CONFLICT.value()))
                .andDo(MockMvcResultHandlers.print());
    }
    // 5. 手机号码被占用
    @Test
    @Sql(value = {"classpath:/sql/truncate_user.sql","classpath:/sql/insert_user_1.sql"})
    @Sql(value = "classpath:/sql/truncate_user.sql ",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testCreateFailBecausePhoneConflict() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(CREATE_URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username","user005")
                .param("password","1234567")
                .param("confirmPassword","1234567")
                .param("phone","13646789001")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("status").value(HttpStatus.CONFLICT.value()))
                .andDo(MockMvcResultHandlers.print());
    }

}
