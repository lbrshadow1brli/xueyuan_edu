package com.online.edu.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.online.edu.eduservice.entity.query.QueryTeacher;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dxq
 * @since 2020-07-15
 */
public interface EDUTEACHERervice extends IService<EduTeacher> {

    //多条件的分页查询
    void pageListCondition(Page<EduTeacher> pageTeacher, QueryTeacher queryTeacher);
}
