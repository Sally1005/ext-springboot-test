package com.lonton.ext.springboot.test.validation.handler;

import com.lonton.ext.springboot.test.controller.response.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/**
 * 处理绑定异常
 * <p/>
 * @author 张利红
 */
@RestControllerAdvice
@Slf4j
public class BindExceptionHandler {
    @ExceptionHandler(BindException.class)
    public JsonResult handlerBindException(BindException e){
        log.debug("BindExceptionHandler.handlerBindException()");
        log.debug("程序运行过程中出现异常：{}",e.getClass().getName());
         // 从绑定错误信息中随机抽取一条
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        JsonResult result = JsonResult.error(HttpStatus.BAD_REQUEST.value(), message);
        log.debug("即将响应：{}",result);
        return result;
    }

}
