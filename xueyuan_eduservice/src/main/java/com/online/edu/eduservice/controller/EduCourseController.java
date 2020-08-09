package com.online.edu.eduservice.controller;


import com.online.edu.common.R;
import com.online.edu.eduservice.entity.form.CourseInfoForm;
import com.online.edu.eduservice.service.EDUCOURSEervice;
import com.online.edu.eduservice.service.impl.EduCourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dxq
 * @since 2020-08-04
 */
@RestController
@RequestMapping("/eduservice/course")
public class EduCourseController {

    @Autowired
    private EDUCOURSEervice eduCourseService;

    //1 添加课程信息
    @PostMapping
    public R addCourse(@RequestBody CourseInfoForm courseInfoForm) {

        Boolean flag = eduCourseService.insertCouseInfo(courseInfoForm);

        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }
}

