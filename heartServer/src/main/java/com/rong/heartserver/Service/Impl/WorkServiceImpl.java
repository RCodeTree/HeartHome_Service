package com.rong.heartserver.Service.Impl;

import com.rong.heartpojo.Vo.WorksVo;
import com.rong.heartserver.Mapper.WorkMapper;
import com.rong.heartserver.Service.WorkService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkServiceImpl implements WorkService {
    @Autowired
    private WorkMapper workMapper;

    /*
     * 获取用户作品
     *
     * @param username 用户名
     *
     * @return WorksVo 用户作品列表
     */
    @Override
    public List<WorksVo> getWorksForAll(String username) {
        // 获取用户作品
        List<WorksVo> works = workMapper.getWorksForAll(username);
        return works;
    }

    /*
     * 获取用户文章
     *
     * @param username 用户名
     *
     * @return List<WorksVo> 用户文章列表
     */
    @Override
    public List<WorksVo> getWorksForEssay(String username) {
        List<WorksVo> essays = workMapper.getWorksForEssay(username);
        return essays;
    }

    /*
     * 获取用户图片
     *
     * @param username 用户名
     *
     * @return List<WorksVo> 用户图片列表
     */
    @Override
    public List<WorksVo> getWorksForImage(String username) {
        List<WorksVo> images = workMapper.getWorksForImage(username);
        return images;
    }


}
