package com.online.edu.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.common.R;
import com.online.edu.eduservice.entity.EduTeacher;
import com.online.edu.eduservice.entity.query.QueryTeacher;
import com.online.edu.eduservice.service.EDUTEACHERervice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author dxq
 * @since 2020-07-15
 */
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    //注入service
    @Autowired
    private EDUTEACHERervice eduTeacherService;


    //1 查询所有讲师
    @GetMapping
    public R getAllTeacherList() {

        //调用service里的方法
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("teacher", list);
    }

    //2 逻辑删除讲师
    @DeleteMapping("{id}")
    public boolean deleteTeacherById(@PathVariable String id) {

        boolean b = eduTeacherService.removeById(id);
        return b;
    }

    //3 分页查询讲师列表
    @GetMapping("pageList/{page}/{limit}")
    public R getPageTeacherList(@PathVariable Long page, @PathVariable Long limit) {

        //创建page对象，传递两个参数
        Page<EduTeacher> pageTeacher = new Page<>(page, limit);
        //调用方法分页查询
        eduTeacherService.page(pageTeacher, null);
        //从pageTeacher对象里面获取分页数据
        long total = pageTeacher.getTotal();
        List<EduTeacher> record = pageTeacher.getRecords();

        return R.ok().data("total", total).data("list", record);
    }

    //4 多条件的分页查询
    @GetMapping("moreConditionPageList/{page}/{limit}")
    public R getMoreConditionPageList(@PathVariable Long page, @PathVariable Long limit,
                                      QueryTeacher queryTeacher) {

        Page<EduTeacher> pageTeacher = new Page<>(page, limit);
        //调用service的方法实现多条件的分页查询
        eduTeacherService.pageListCondition(pageTeacher, queryTeacher);

        return
    }
}

