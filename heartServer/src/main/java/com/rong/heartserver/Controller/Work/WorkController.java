package com.rong.heartserver.Controller.Work;

import com.rong.heartcommon.Result.Result;
import com.rong.heartpojo.Vo.WorksVo;
import com.rong.heartserver.Service.WorkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/works")
@CrossOrigin("*")
@Slf4j
public class WorkController {
    @Autowired
    private WorkService workService;

    @GetMapping("/all/{username}")
    public Result<List<WorksVo>> getWorks(@PathVariable String username) {
        log.info("Controller---获取用户作品：{}", username);
        if (username == null) {
            return Result.error("用户名不能为空");
        }

        List<WorksVo> works = workService.getWorksForAll(username);
        log.info("Controller---获取用户作品：{}", works);
        return Result.success(200, "获取用户作品成功", works);
    }
}
