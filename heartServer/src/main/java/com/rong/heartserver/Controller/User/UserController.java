package com.rong.heartserver.Controller.User;

import com.rong.heartpojo.Vo.UserInfoVo;
import com.rong.heartpojo.Vo.WorksVo;
import com.rong.heartserver.Service.FriendService;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.rong.heartserver.Config.JwtUtilsYml;
import com.rong.heartserver.Service.UserService;
import com.rong.heartcommon.Result.Result;
import com.rong.heartcommon.Utils.JwtUtils;
import com.rong.heartpojo.Dto.LoginDto;

@RestController
@RequestMapping("/user")
@CrossOrigin("*") // 允许跨域请求
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private FriendService friendService;

    @Autowired
    JwtUtilsYml jwtUtilsYml;

    // 用户登录
    @PostMapping("/login/signin")
    public Result<Map<String, Object>> signin(@RequestBody LoginDto loginDto) {
        log.info("Controller---用户登录：{}, {}", loginDto.getUsername(), loginDto.getPassword());
        LoginDto user = userService.signin(loginDto);

        /*
         * 生成JWT token
         */
        // 创建一个Map来存储自定义声明
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        claims.put("password", user.getPassword());

        // 生成密钥---使用配置文件中的密钥和算法
        SecretKey secretKey = JwtUtils.generateKey(jwtUtilsYml.getSecret(), jwtUtilsYml.getAlgorithm());

        // 设置过期时间 ---使用配置文件中的过期时间
        long expirationTime = jwtUtilsYml.getExpire();

        // 设置token头部信息
        String header = jwtUtilsYml.getHeader();

        // 生成JWT token
        String jwtToken = JwtUtils.generateToken(claims, secretKey, expirationTime, header);
        System.out.println("jwtToken是：" + jwtToken); // 打印token，用于调试

        // 修改返回结果，同时包含user和jwtToken
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("user", user);
        responseData.put("jwtToken", jwtToken);

        return Result.success(200, "登录成功", responseData); // 返回包含user和jwtToken的结果
    }

    // 用户注册
    @PostMapping("/login/signup")
    public Result signup(@RequestBody LoginDto loginDto) {
        log.info("Controller---用户注册：{}, {}", loginDto.getUsername(), loginDto.getPassword());
        userService.signup(loginDto);
        return Result.success(200, "注册成功", null);
    }

    // 获取用户信息
    @GetMapping("/userInfo/{username}")
    public Result<UserInfoVo> getUserInfo(@PathVariable String username) {
        log.info("Controller---获取用户信息：{}", username);
        if (username == null) {
            return Result.error("用户名不能为空");
        }
        UserInfoVo userInfo = userService.getUserInfo(username);
        return Result.success(200, "获取用户信息成功", userInfo);
    }

    // 获取用户好友列表
    @GetMapping("/friends/{username}")
    public Result<List<UserInfoVo>> getFriends(@PathVariable String username) {
        log.info("Controller---获取用户好友列表：{}", username);
        if (username == null) {
            return Result.error("用户名不能为空");
        }
        List<UserInfoVo> friends = friendService.getFriends(username);

        return Result.success(200, "获取用户好友列表成功", friends);
    }

}
