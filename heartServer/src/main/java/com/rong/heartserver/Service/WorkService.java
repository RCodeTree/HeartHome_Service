package com.rong.heartserver.Service;

import com.rong.heartpojo.Vo.WorksVo;

import java.util.List;

public interface WorkService {
    /*
     * 获取用户作品
     *
     * @param username 用户名
     *
     * @return WorksVo 用户作品列表
     */
    List<WorksVo> getWorksForAll(String username);
}
