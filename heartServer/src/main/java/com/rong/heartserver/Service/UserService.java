package com.rong.heartserver.Service;

import com.rong.heartpojo.Dto.LoginDto;
import com.rong.heartpojo.Entity.UserEntity;
import com.rong.heartpojo.Vo.UserInfoVo;

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

    /*
     * 获取用户信息
     *
     * @param username 用户名
     *
     * @return 用户信息
     */
    UserInfoVo getUserInfo(String username);
}
