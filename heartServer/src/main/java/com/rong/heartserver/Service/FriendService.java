package com.rong.heartserver.Service;

import com.rong.heartpojo.Entity.UserEntity;

import java.util.List;

public interface FriendService {

    List<UserEntity> getFriends(String username);
}
