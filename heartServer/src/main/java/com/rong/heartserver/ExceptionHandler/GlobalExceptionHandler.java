package com.rong.heartserver.ExceptionHandler;

import com.rong.heartcommon.Exception.User.UserLoginException;
import com.rong.heartcommon.Exception.User.UserSinUpException;
import com.rong.heartcommon.Result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
 * 全局异常处理类
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 用户登录异常
    @ExceptionHandler(UserLoginException.class)
    public Result handleUserAuthenticationException(UserLoginException e) {
        return Result.error(e.getDetailMessage());
    }

    // 用户注册异常
    @ExceptionHandler(UserSinUpException.class)
    public Result handleUserAuthenticationException(UserSinUpException e) {
        return Result.error(e.getDetailMessage());
    }
}