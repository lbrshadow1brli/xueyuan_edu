package com.online.edu.eduservice.controller;


import com.online.edu.common.R;
import com.online.edu.eduservice.entity.form.CourseInfoForm;
import com.online.edu.eduservice.service.EDUCOURSEervice;
import com.online.edu.eduservice.service.impl.EduCourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping
    public R addCourse(@RequestBody CourseInfoForm courseInfoForm) {

        String id = eduCourseService.savaCourseInfo(courseInfoForm);

        //返回添加之后课程id
        return R.ok().data("courseId", id);
    }
}

