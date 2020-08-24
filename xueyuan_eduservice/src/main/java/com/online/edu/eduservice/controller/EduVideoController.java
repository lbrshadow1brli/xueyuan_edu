package com.online.edu.eduservice.controller;


import com.online.edu.common.R;
import com.online.edu.eduservice.entity.EduVideo;
import com.online.edu.eduservice.service.IEduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author dxq
 * @since 2020-08-13
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/video")
public class EduVideoController {

    @Autowired
    private IEduVideoService iEduVideoService;

    //添加小节
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo) {
        boolean save = iEduVideoService.save(eduVideo);

        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //删除小节
    // TODO 后面这个方法需完善 删除小节时 视频也要删除
    @DeleteMapping("deleteVideo/{id}")
    public R deleteVideo(@PathVariable String id) {

        boolean remove = iEduVideoService.removeById(id);

        if (remove) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //修改小节
}

