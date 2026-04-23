package com.rong.server.Service.User.Impl;

import com.rong.pojo.Entity.User;
import com.rong.server.Mapper.User.UserMapper;
import com.rong.server.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUser(String name) {
        User user = userMapper.getUser(name);
        return user;
    }
}
