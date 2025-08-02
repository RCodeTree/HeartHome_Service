package com.rong.heartserver.Mapper;

import com.rong.heartpojo.Vo.WorksVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WorkMapper {

    /*
     * 获取用户作品
     *
     * @param username 用户名
     *
     * @return WorksVo 用户作品列表
     */
    @Select("SELECT w.title, w.short_desc, w.image_url, w.created_at as createTime, w.work_type, COUNT(DISTINCT l.user_id) as likesCount, COUNT(DISTINCT c.comment_id) as commentsCount FROM works w LEFT JOIN likes l ON w.work_id = l.work_id LEFT JOIN comments c ON w.work_id = c.work_id WHERE w.user_id = (SELECT userid FROM user WHERE username = #{username}) GROUP BY w.work_id, w.title, w.short_desc, w.image_url, w.created_at, w.work_type;")
    List<WorksVo> getWorksForAll(String username);
}
