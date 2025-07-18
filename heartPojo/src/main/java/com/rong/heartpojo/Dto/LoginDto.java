package com.rong.heartpojo.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用于登录和注册的参数实体类 --- 用于接收前端传来的参数 --- 作为一个数据传输对象DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    private String username;
    private String password;
    private String avatarUrl;
}
