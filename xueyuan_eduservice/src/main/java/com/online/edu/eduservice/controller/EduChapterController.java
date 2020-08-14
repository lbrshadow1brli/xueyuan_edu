package com.online.edu.eduservice.controller;


import com.online.edu.common.R;
import com.online.edu.eduservice.entity.EduChapter;
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

    //2.添加章节
    @PostMapping("addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter) {

        boolean save = iEduChapterService.save(eduChapter);

        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //3.查询章节详情
    @GetMapping("getChapterInfo/{chapterId}")
    public R getChapterInfo(@PathVariable String chapterId) {
        EduChapter chapter = iEduChapterService.getById(chapterId);

        return R.ok().data("ChapterInfo", chapter);
    }

    //4.修改章节
    @PostMapping("updateChapter")
    public R updateChapter(@RequestBody EduChapter eduChapter) {

        boolean save = iEduChapterService.updateById(eduChapter);

        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //5.删除章节
    @DeleteMapping("deleteChapter/{chapterId}")
    public R deleteChapter(@PathVariable String chapterId) {
        iEduChapterService.deleteChapter(chapterId);

        return R.ok();
    }
}

