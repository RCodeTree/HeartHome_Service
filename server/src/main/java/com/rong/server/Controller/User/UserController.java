package com.rong.server.Controller.User;

import com.rong.pojo.Entity.User;
import com.rong.server.Service.User.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
@Slf4j
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getUser")
    @ResponseBody
    public User getUser(String name) {
        log.info("正在获取用户信息，用户姓名为{}", name);
        User user = userService.getUser(name);
        if (user == null) return null;
        else return user;
    }
}
