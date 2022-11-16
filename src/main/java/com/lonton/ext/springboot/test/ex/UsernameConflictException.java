package com.lonton.ext.springboot.test.ex;

/**
 * 用户名冲突的异常
 * <p/>
 * @author 张利红
 */
public class UsernameConflictException extends ServiceException {

    public UsernameConflictException(String message) {
        super(message);
    }
}
