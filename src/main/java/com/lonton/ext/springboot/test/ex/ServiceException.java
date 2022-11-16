package com.lonton.ext.springboot.test.ex;
/**
 * 自定义异常的父类，继承自 RuntimeException
 * <p/>
 * @author 张利红
 */
public class ServiceException extends RuntimeException{

    public ServiceException(String message) {
        super(message);
    }

}
