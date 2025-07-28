package com.rong.heartpojo.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 用户实体类 --- 用于数据库user表的映射
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户头像路径
     */
    private String avatarUrl;

    /**
     * 个人简介
     */
    private String personalDescription;

    /**
     * 用户地址
     */
    private String address;

    /**
     * 注册时间/加入时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 用户状态 0为正常 1为禁用
     */
    private Integer status;

    /**
     * 是否为管理员 0为普通用户 1为管理员
     */
    private Integer manager;

}