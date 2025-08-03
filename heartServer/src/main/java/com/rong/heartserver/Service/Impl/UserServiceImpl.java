package com.rong.heartserver.Service.Impl;

import com.rong.heartcommon.Exception.User.UserSinUpException;
import com.rong.heartcommon.Exception.User.UserLoginException;
import com.rong.heartpojo.Dto.LoginDto;
import com.rong.heartpojo.Vo.UserInfoVo;
import com.rong.heartserver.Mapper.UserMapper;
import com.rong.heartserver.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    /*
     * 登录
     *
     * @param username 用户名
     *
     * @param password 密码
     */
    @Override
    public LoginDto signin(LoginDto loginDto) {
        // 获取前端传输的用户名
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();
        LoginDto user = userMapper.signin(username, password); // 调用mapper层的方法, 返回用户实体类, 使用UserDto接收(根据用户名查询用户信息)

        // 判断用户是否存在
        if (user == null) {
            throw new UserLoginException("登录失败，用户不存在或密码错误");
        }
        return user;
    }

    /*
     * 注册
     *
     * @param username 用户名
     *
     * @param password 密码
     */
    @Override
    @Transactional(rollbackFor = UserSinUpException.class)
    public void signup(LoginDto loginDto) {
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();

        // 先查询是否已存在该用户，已存在则使用全局异常处理 --- 使用用户名查询用户信息
        LoginDto user = userMapper.getUserByUsername(username);

        // 如果用户不存在则注册用户
        if (user == null) {
            try {
                userMapper.signup(username, password);
            } catch (Exception e) {
                throw new UserSinUpException("注册失败，请重试"); // 当插入用户信息时出现异常，则抛出全局异常，并回滚数据
            }
            return;
        }

        throw new UserSinUpException("注册失败，用户已存在");

    }

    /*
     * 获取用户信息
     *
     * @param username 用户名
     *
     * @return 用户信息
     */
    @Override
    public UserInfoVo getUserInfo(String username) {
        UserInfoVo userInfo = userMapper.getUserInfo(username); // 返回基本用户信息

        // 获取用户作品数量
        userInfo.setWorksCount(userMapper.getWorksCount(username));

        // 获取用户粉丝数量
        userInfo.setFansCount(userMapper.getFansCount(username));

        // 获取用户关注数量
        userInfo.setFollowsCount(userMapper.getFollowsCount(username));

        return userInfo;
    }
}
