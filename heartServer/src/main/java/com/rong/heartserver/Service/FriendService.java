package com.rong.heartserver.Service;

import com.rong.heartpojo.Vo.UserInfoVo;

import java.util.List;

public interface FriendService {

    List<UserInfoVo> getFriends(String username);
}
