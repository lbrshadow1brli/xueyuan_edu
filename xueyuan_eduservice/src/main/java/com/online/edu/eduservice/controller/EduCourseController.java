package com.online.edu.eduservice.controller;


import com.online.edu.common.R;
import com.online.edu.eduservice.entity.EduCourse;
import com.online.edu.eduservice.entity.form.CourseInfoForm;
import com.online.edu.eduservice.service.EDUCOURSEervice;
import com.online.edu.eduservice.service.impl.EduCourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author dxq
 * @since 2020-08-04
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/course")
public class EduCourseController {

    @Autowired
    private EDUCOURSEervice eduCourseService;

    //1 添加课程信息
    @PostMapping("addCourse")
    public R addCourse(@RequestBody CourseInfoForm courseInfoForm) {

        String id = eduCourseService.savaCourseInfo(courseInfoForm);

        //返回添加之后课程id
        return R.ok().data("courseId", id);
    }

    //2 根据课程id查询课程信息
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId) {
        CourseInfoForm courseInfoForm = eduCourseService.getCourseInfo(courseId);

        return R.ok().data("courseInfo", courseInfoForm);
    }

    //3 修改课程信息
    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoForm courseInfoForm) {

        eduCourseService.updateCourseInfo(courseInfoForm);

        return R.ok();
    }

    //4 获取所有课程
    @GetMapping("getCourseList")
    public R getCourseList() {

        List<EduCourse> list = eduCourseService.list(null);

        return R.ok().data("list", list);
    }

    //5 删除课程
        @DeleteMapping("deleteCourse/{id}")
    public R deleteCourse(@PathVariable String id) {

        boolean remove = eduCourseService.removeById(id);

        if (remove) {
            return R.ok();
        } else {
            return R.error();
        }
    }
}

