package com.rong.heartserver.Mapper;

import com.rong.heartpojo.Entity.WorkEntity;
import com.rong.heartpojo.Vo.WorksVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface WorkMapper {

    /*
     * 获取用户作品
     *
     * @param username 用户名
     *
     * @return WorksVo 用户作品列表
     */
    @Select("")
    WorkEntity getWorksForAll(String username);
}
