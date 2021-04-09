package com.springsecurity.auth.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice//控制器增强
public class ExceptionHandle {

    //捕获Exception此类异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handle(Exception e){
        return e.getMessage();
    }
}
