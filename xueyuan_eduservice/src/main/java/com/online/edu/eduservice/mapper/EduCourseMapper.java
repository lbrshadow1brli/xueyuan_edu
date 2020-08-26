package com.online.edu.eduservice.mapper;

import com.online.edu.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.online.edu.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dxq
 * @since 2020-08-04
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    public CoursePublishVo getPublishCourseInfo(String courseId);
}
