package com.online.edu.eduservice.service.impl;

import com.online.edu.eduservice.entity.EduCourse;
import com.online.edu.eduservice.entity.EduCourseDescription;
import com.online.edu.eduservice.entity.form.CourseInfoForm;
import com.online.edu.eduservice.entity.vo.CoursePublishVo;
import com.online.edu.eduservice.handler.EduException;
import com.online.edu.eduservice.mapper.EduCourseMapper;
import com.online.edu.eduservice.service.EDUCOURSEDESCRIPTIONervice;
import com.online.edu.eduservice.service.EDUCOURSEervice;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.online.edu.eduservice.service.IEduChapterService;
import com.online.edu.eduservice.service.IEduVideoService;
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

    @Autowired
    private IEduVideoService iEduVideoService;

    @Autowired
    private IEduChapterService iEduChapterService;

    //1 添加课程信息
    @Override
    public String savaCourseInfo(CourseInfoForm courseInfoForm) {

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

        return courseId;
    }

    //2 根据课程id查询课程基本信息
    @Override
    public CourseInfoForm getCourseInfo(String courseId) {

        //1.先查课程信息
        EduCourse eduCourse = baseMapper.selectById(courseId);

        //2.再通过descroptionService查课程描述
        EduCourseDescription courseDescription = eduCourseDescriptionService.getById(courseId);

        CourseInfoForm courseInfoForm = new CourseInfoForm();
        BeanUtils.copyProperties(eduCourse, courseInfoForm);
        courseInfoForm.setDescription(courseDescription.getDescription());

        return courseInfoForm;
    }

    //3 根据id修改课程基本信息
    @Override
    public void updateCourseInfo(CourseInfoForm courseInfoForm) {

        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm, eduCourse);
        int update = baseMapper.updateById(eduCourse);
        if (update == 0) {
            throw new EduException(20001, "修改课程信息失败");
        }
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(courseInfoForm.getId());
        eduCourseDescription.setDescription(courseInfoForm.getDescription());
        eduCourseDescriptionService.updateById(eduCourseDescription);
    }

    // 查询课程所有信息
    @Override
    public CoursePublishVo publishCourseInfo(String id) {

        CoursePublishVo publishCourseInfo = baseMapper.getPublishCourseInfo(id);

        return publishCourseInfo;
    }

    //删除课程
    @Override
    public void removeCourse(String courseId) {
        //1 删除小节
        iEduVideoService.removeVideoByCourseId(courseId);

        //2 删除章节
        iEduChapterService.removeChapterByCourseId(courseId);

        //3 删除描述
        eduCourseDescriptionService.removeById(courseId);

        //4 删除课程
        baseMapper.deleteById(courseId);
    }
}
