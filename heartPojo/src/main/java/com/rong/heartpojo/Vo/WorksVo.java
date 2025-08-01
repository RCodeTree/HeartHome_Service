package com.rong.heartpojo.Vo;

import com.rong.heartcommon.Enum.WorkTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 用于渲染用户作品列表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorksVo {
    private String title; // 作品标题
    private String shortDesc; // 作品描述
    private String imageUrl; // 作品图片
    private LocalDateTime createTime; // 作品创建时间
    private Integer likesCount; // 作品点赞数
    private Integer commentsCount; // 作品评论数
    private WorkTypeEnum workType; // 作品类型
}
