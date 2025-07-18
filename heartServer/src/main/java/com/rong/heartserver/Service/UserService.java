package com.rong.heartserver.Service;

import com.rong.heartpojo.Dto.LoginDto;

public interface UserService {
    /*
     * 用户登录
     * 
     * @param username 用户名
     * 
     * @param password 密码
     */
    LoginDto signin(LoginDto loginDto);

    /*
     * 用户注册
     * 
     * @param username 用户名
     * 
     * @param password 密码
     */
    void signup(LoginDto loginDto);
}
