package com.lonton.ext.springboot.test.ex.handler;

import com.lonton.ext.springboot.test.controller.response.JsonResult;
import com.lonton.ext.springboot.test.ex.InsertConflictException;
import com.lonton.ext.springboot.test.ex.PasswordNotMatchException;
import com.lonton.ext.springboot.test.ex.PhoneConflictException;
import com.lonton.ext.springboot.test.ex.UsernameConflictException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 处理业务异常
 * <p/>
 * @author 张利红
 */
@RestControllerAdvice
@Slf4j
public class ServiceExceptionHandler {
    // 状态码：409  UsernameConflictException,PhoneConflictException
    @ExceptionHandler({UsernameConflictException.class, PhoneConflictException.class})
    public JsonResult handler409Exception(Throwable e){
        log.debug("serviceExceptionHandler.handler409Exception()");
        log.debug("程序运行过程中出现异常：{}",e.getClass().getName());
        JsonResult result = JsonResult.error(HttpStatus.CONFLICT.value(), e);
        log.debug("即将响应：{}",result);
        return result;
    }
    // 状态码：400 PasswordNotNatchException
    @ExceptionHandler(PasswordNotMatchException.class)
    public JsonResult handler400Exception(Throwable e){
        log.debug("serviceExceptionHandler.handler400Exception()");
        log.debug("程序运行过程中出现异常：{}",e.getClass().getName());
        JsonResult result = JsonResult.error(HttpStatus.BAD_REQUEST.value(), e);
        log.debug("即将响应：{}",result);
        return result;
    }
    // 状态码：500 InsertConflictException
    @ExceptionHandler(InsertConflictException.class)
    public JsonResult handler500Exception(Throwable e){
        log.debug("serviceExceptionHandler.handler500Exception()");
        log.debug("程序运行过程中出现异常：{}",e.getClass().getName());
        JsonResult result = JsonResult.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
        log.debug("即将响应：{}",result);
        return result;
    }
}
