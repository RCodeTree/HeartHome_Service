package com.rong.server.Service.User;

import com.rong.pojo.Entity.User;

public interface UserService {
    User getUser(String name, Integer age);
}
