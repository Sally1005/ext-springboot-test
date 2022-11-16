package com.lonton.ext.springboot.test.controller.response;

import lombok.Data;
import org.springframework.http.HttpStatus;
import java.io.Serializable;
/**
 * 响应结果类
 * <p/>
 * @author 张利红
 */

@Data
public class JsonResult implements Serializable {
    /**
     * 状态码
     */
    private Integer status;
    /**
     * 错误提示信息
     */
    private String message;
    /**
     * 返回数据
     */
    private Object data;

    public JsonResult() {
    }

    public JsonResult(Integer status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
     // 若成功时则调用下面的ok()方法，不返回数据和错误信息
    public static JsonResult ok(){
        return  ok(null);
    }
    public static  JsonResult ok(Object data){
        return new JsonResult(HttpStatus.OK.value(),null,null);
    }

     // 若失败时则调用下面的error()方法，不返回数据,错误信息由ex.getMessage()
    public static JsonResult error(Integer status ,Throwable ex){
        return error(status,ex.getMessage());
    }
    public static JsonResult error(Integer status , String message){
        return new JsonResult(status,message,null);
    }

}
