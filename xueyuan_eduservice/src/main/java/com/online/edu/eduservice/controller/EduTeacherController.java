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
@CrossOrigin
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
    //使用RequestBody必须使用post提交
    @PostMapping("moreConditionPageList/{page}/{limit}")
    public R getMoreConditionPageList(@PathVariable Long page, @PathVariable Long limit,
                                      @RequestBody(required = false) QueryTeacher queryTeacher) {
        //接收前台传的json数据    required = false使传值可以为空

        Page<EduTeacher> pageTeacher = new Page<>(page, limit);
        //调用service的方法实现多条件的分页查询
        eduTeacherService.pageListCondition(pageTeacher, queryTeacher);

        long total = pageTeacher.getTotal();
        List<EduTeacher> record = pageTeacher.getRecords();

        return R.ok().data("total", total).data("list", record);
    }

    //5 添加讲师
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean save = eduTeacherService.save(eduTeacher);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //6 根据id查询讲师
    @GetMapping("getTeacherInfo/{id}")
    public R getTeacherInfo(@PathVariable String id) {
        EduTeacher eduTeacher = eduTeacherService.getById(id);

        return R.ok().data("eduTeacher", eduTeacher);
    }

    //7 根据id修改的方法
    @PostMapping("updateTeacher/{id}")
    public R updateTeacher(@PathVariable String id, @RequestBody EduTeacher eduTeacher) {
        boolean b = eduTeacherService.updateById(eduTeacher);
        if (b) {
            return R.ok();
        } else {
            return R.error();
        }
    }
}

