package com.rong.heartserver.Controller.User;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
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
@RequestMapping("/user/login")
@CrossOrigin("*") // 允许跨域请求
@Slf4j
public class UserController {
    @Autowired
    UserService loginService;

    @Autowired
    JwtUtilsYml jwtUtilsYml;

    @PostMapping("/signin")
    public Result<Map<String, Object>> signin(@RequestBody LoginDto loginDto) {
        log.info("Controller---用户登录：{}, {}", loginDto.getUsername(), loginDto.getPassword());
        LoginDto user = loginService.signin(loginDto);

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

    @PostMapping("/signup")
    public Result signup(@RequestBody LoginDto loginDto) {
        log.info("Controller---用户注册：{}, {}", loginDto.getUsername(), loginDto.getPassword());
        loginService.signup(loginDto);
        return Result.success(200, "注册成功", null);
    }
}
