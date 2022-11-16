package com.lonton.ext.springboot.test.ex;

/**
 * 手机号码冲突的异常
 * <p/>
 * @author 张利红
 */
public class PhoneConflictException extends ServiceException {
    public PhoneConflictException(String message) {
        super(message);
    }

    }

