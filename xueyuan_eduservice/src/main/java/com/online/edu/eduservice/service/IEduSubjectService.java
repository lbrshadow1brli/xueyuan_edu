package com.online.edu.eduservice.service;

import com.online.edu.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.online.edu.eduservice.entity.subject.OneSubject;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dxq
 * @since 2020-08-10
 */
public interface IEduSubjectService extends IService<EduSubject> {

    List<OneSubject> getAllOneTwoSubject();
}
