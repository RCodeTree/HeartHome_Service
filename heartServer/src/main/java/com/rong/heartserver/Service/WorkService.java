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

    /*
     * 获取用户文章
     *
     * @param username 用户名
     *
     * @return WorksVo 用户文章列表
     */
    List<WorksVo> getWorksForEssay(String username);

    /*
     * 获取用户图片
     *
     * @param username 用户名
     *
     * @return List<WorksVo> 用户图片列表
     */
    List<WorksVo> getWorksForImage(String username);
}
