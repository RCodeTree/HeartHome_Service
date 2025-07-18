package com.rong.heartcommon.Exception.Login;

/**
 * @ClassName: UserLoginException
 * @Description: 用户登录异常
 * @Author: rong
 * @Date: 2025/7/17
 */
public class UserLoginException extends RuntimeException {
    private String detailMessage; // 详细信息

    public UserLoginException() { // 构造方法
    }

    public UserLoginException(String message) { // 构造方法
        this.detailMessage = message;
    }

    public String getDetailMessage() { // 获取详细信息
        return detailMessage;
    }

}