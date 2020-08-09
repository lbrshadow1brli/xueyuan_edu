package com.online.edu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.eduservice.entity.EduTeacher;
import com.online.edu.eduservice.entity.query.QueryTeacher;
import com.online.edu.eduservice.handler.EduException;
import com.online.edu.eduservice.mapper.EduTeacherMapper;
import com.online.edu.eduservice.service.EDUTEACHERervice;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dxq
 * @since 2020-07-15
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EDUTEACHERervice {


    //多条件的分页查询
    @Override
    public void pageListCondition(Page<EduTeacher> pageTeacher, QueryTeacher queryTeacher) {

        /*此段为测试统一异常处理，写一个肯定报错的代码*/
        //int i = 9 / 0;
        /*此段测试自定义异常*/
        // try { int j = 9 / 0; } catch (Exception e) {throw new EduException(20001, "执行自定义异常"); }

        //关键：queryTeacher有传递过来的条件值，判断，如果有条件值，拼接条件
        if (queryTeacher == null) {
            //无条件传来 直接分页查询
            baseMapper.selectPage(pageTeacher, null);
            return;
        }

        //如果queryTeacher不为空
        //从queryTeacher对象里获取出条件值
        String name = queryTeacher.getName();
        String begin = queryTeacher.getBegin();
        String end = queryTeacher.getEnd();

        //判断条件值
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        //name != null && !"".equals(name
        if (!StringUtils.isEmpty(name)) {
            //拼接条件
            wrapper.like("name", name);
        }

        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin);
        }

        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create", end);
        }

        //之后带条件查询
        baseMapper.selectPage(pageTeacher, wrapper);
    }
}
