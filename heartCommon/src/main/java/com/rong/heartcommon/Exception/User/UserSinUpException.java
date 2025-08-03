package com.rong.heartcommon.Exception.User;

/**
 * @ClassName: UserSinUpException
 * @Description: 用户注册异常
 * @Author: rong
 * @Date: 2025/7/17
 */
public class UserSinUpException extends RuntimeException {

    private String detailMessage;

    public UserSinUpException() {
    }

    public UserSinUpException(String message) {
        this.detailMessage = message;
    }

    public String getDetailMessage() {
        return detailMessage;
    }
}
