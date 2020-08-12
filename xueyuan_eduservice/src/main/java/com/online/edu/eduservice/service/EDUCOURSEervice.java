package com.online.edu.eduservice.service;

import com.online.edu.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.online.edu.eduservice.entity.form.CourseInfoForm;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dxq
 * @since 2020-08-04
 */
public interface EDUCOURSEervice extends IService<EduCourse> {

    String savaCourseInfo(CourseInfoForm courseInfoForm);
}
