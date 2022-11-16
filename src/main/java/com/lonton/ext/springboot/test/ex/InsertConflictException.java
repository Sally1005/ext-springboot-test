package com.lonton.ext.springboot.test.ex;

/**
 * 插入数据的异常
 * 一般不会出现，当插入的受影响的行数为不为1时，还是建议抛出
 * <p/>
 * @author 张利红
 */
public class InsertConflictException extends ServiceException {
    public InsertConflictException(String message) {
        super(message);
    }

}
