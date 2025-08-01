package com.rong.heartserver.Service.Impl;

import com.rong.heartpojo.Entity.WorkEntity;
import com.rong.heartpojo.Vo.WorksVo;
import com.rong.heartserver.Mapper.WorkMapper;
import com.rong.heartserver.Service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public WorksVo getWorksForAll(String username) {
        // 获取用户作品
        WorkEntity workEntity = workMapper.getWorksForAll(username);

        // 转换为 WorksVo
        WorksVo worksVo = new WorksVo();


        return null;
    }
}
