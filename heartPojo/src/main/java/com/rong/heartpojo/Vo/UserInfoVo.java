package com.rong.heartpojo.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用于渲染用户资料卡
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVo {
    private String username; // 用户名
    private String avatarUrl; // 头像
    private String personalDescription; // 个人描述
    private String address; // 地址
    private String createTime; // 注册时间
    private Integer status; // 状态
    private Integer manager; // 管理员
    private Integer worksCount; // 作品数量
    private Integer fansCount; // 粉丝数量
    private Integer followsCount; // 关注数量
}
