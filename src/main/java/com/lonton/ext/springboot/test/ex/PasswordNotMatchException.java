package com.lonton.ext.springboot.test.ex;

/**
 * 密码不匹配的异常
 * <p/>
 * @author 张利红
 */
public class PasswordNotMatchException extends ServiceException {

    public PasswordNotMatchException(String message) {
        super(message);
    }

}
