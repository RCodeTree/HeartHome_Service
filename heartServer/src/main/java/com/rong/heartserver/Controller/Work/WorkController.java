package com.rong.heartserver.Controller.Work;

import com.rong.heartcommon.Result.Result;
import com.rong.heartpojo.Vo.WorksVo;
import com.rong.heartserver.Service.WorkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/works")
@Slf4j
public class WorkController {
    @Autowired
    private WorkService workService;


    @GetMapping("/all/{username}")
    public Result<WorksVo> getWorks(@PathVariable String username) {
        log.info("Controller---获取用户作品：{}", username);
        if (username == null) {
            return Result.error("用户名不能为空");
        }

        WorksVo works = workService.getWorksForAll(username);
        return Result.success();
    }
}
