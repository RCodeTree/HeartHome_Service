package com.rong.heartpojo.Entity;

import com.rong.heartcommon.Enum.WorkTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 作品实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkEntity {
    
    /**
     * 作品ID
     */
    private String workId;
    
    /**
     * 用户ID
     */
    private String userId;
    
    /**
     * 作品标题
     */
    private String title;
    
    /**
     * 作品图片路径
     */
    private String imageUrl;
    
    /**
     * 作品简述
     */
    private String shortDesc;
    
    /**
     * 作品类型
     */
    private WorkTypeEnum workType;
    
    /**
     * 状态 1发布 0草稿 -1删除
     */
    private Integer status;
    
    /**
     * 点赞数
     */
    private Integer likeCount;
    
    /**
     * 评论数
     */
    private Integer commentCount;
    
    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
    
    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;
}
