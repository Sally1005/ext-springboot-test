package com.lonton.ext.springboot.test.controller;

import com.lonton.ext.springboot.test.controller.response.JsonResult;
import com.lonton.ext.springboot.test.pojo.dto.CreateUserDTO;
import com.lonton.ext.springboot.test.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

/**
 * 用户注册的控制器层
 * 主要逻辑都写在业务层，只需调用 <br/>
 * <p/>
 * @author 张利红
 */
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    private IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public JsonResult create(@Valid CreateUserDTO createUserDTO){
        log.debug(" UserController.create()");
        log.debug("开始创建用户,请求参数为：{}",createUserDTO);

        userService.createNewUser(createUserDTO);
        JsonResult result = JsonResult.ok();
        log.debug("创建用户成功，返回数据为：{}",result);
        return result;
    }

}
