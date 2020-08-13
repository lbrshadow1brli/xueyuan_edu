package com.online.edu.eduservice.controller;


import com.online.edu.common.R;
import com.online.edu.eduservice.entity.chapter.ChapterVo;
import com.online.edu.eduservice.service.IEduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author dxq
 * @since 2020-08-13
 */
@RestController
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class EduChapterController {

    @Autowired
    private IEduChapterService iEduChapterService;

    //1.查询课程大纲
    @GetMapping("getChapterVideo/{courseId}")
    public R getChapterVideo(@PathVariable String courseId) {

        List<ChapterVo> list = iEduChapterService.getChapterVideoByCourseId(courseId);


        return R.ok().data("list", list);
    }
}

