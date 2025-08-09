package com.rong.heartserver.Service.Impl;

import com.rong.heartcommon.Exception.CommonException.GetDataException;
import com.rong.heartpojo.Entity.UserEntity;
import com.rong.heartserver.Mapper.UserMapper;
import com.rong.heartserver.Service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserEntity> getFriends(String username) {

        try {
            List<UserEntity> friends = userMapper.getFriends(username);
            return friends; // 返回用户好友列表
        } catch (Exception e) {
            System.out.println(e);
            throw new GetDataException("获取用户好友列表失败,请刷新重试");
        }

    }
}
