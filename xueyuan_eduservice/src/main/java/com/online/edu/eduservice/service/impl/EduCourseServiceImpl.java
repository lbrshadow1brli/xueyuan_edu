package com.online.edu.eduservice.service.impl;

import com.online.edu.eduservice.entity.EduCourse;
import com.online.edu.eduservice.entity.EduCourseDescription;
import com.online.edu.eduservice.entity.form.CourseInfoForm;
import com.online.edu.eduservice.handler.EduException;
import com.online.edu.eduservice.mapper.EduCourseMapper;
import com.online.edu.eduservice.service.EDUCOURSEDESCRIPTIONervice;
import com.online.edu.eduservice.service.EDUCOURSEervice;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dxq
 * @since 2020-08-04
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EDUCOURSEervice {

    //在EduCourseService中调用EduCourseDescriptionService来操纵EduCourseDescription的方法
    @Autowired
    private EDUCOURSEDESCRIPTIONervice eduCourseDescriptionService;

    //添加课程信息
    @Override
    public Boolean insertCouseInfo(CourseInfoForm courseInfoForm) {

        //1 课程基本信息到课程表

        //把courseInfoForm数据复制到EduCourse对象里去，再进行添加
        EduCourse eduCourse = new EduCourse();
        //BeanUtils.copyProperties 按属性名相对应进行赋值
        BeanUtils.copyProperties(courseInfoForm,eduCourse);

        int result = baseMapper.insert(eduCourse);

        //判断如何添加课程信息成功，添加描述
        if (result == 0) {
            throw new EduException(20001, "添加课程信息失败");
        }
        //2 课程描述到课程描述表
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        //获取描述内容
        String description = courseInfoForm.getDescription();
        eduCourseDescription.setDescription(description);
        //获取课程id
        String courseId = eduCourse.getId();
        eduCourseDescription.setId(courseId);
        boolean save = eduCourseDescriptionService.save(eduCourseDescription);

        return save;
    }
}
