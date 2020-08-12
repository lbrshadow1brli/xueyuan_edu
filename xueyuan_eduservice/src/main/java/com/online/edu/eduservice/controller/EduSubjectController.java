package com.online.edu.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.online.edu.common.R;
import com.online.edu.eduservice.entity.EduSubject;
import com.online.edu.eduservice.entity.subject.OneSubject;
import com.online.edu.eduservice.service.IEduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author dxq
 * @since 2020-08-10
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private IEduSubjectService iEduSubjectService;

    //添加课程分类
    @PostMapping("addSubject")
    public R addSubject(@RequestBody EduSubject eduSubject) {

        if (eduSubject.getParentId() == null) {
            eduSubject.setParentId("0");

            QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
            wrapper.eq("title", eduSubject.getTitle());
            wrapper.eq("parent_id", eduSubject.getParentId());

            EduSubject one = iEduSubjectService.getOne(wrapper);

            if (one != null) {
                return R.ok().messsage("科目已存在");
            }
        } else {
            QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
            wrapper.eq("title", eduSubject.getTitle());
            wrapper.ne("parent_id", "0");

            EduSubject one = iEduSubjectService.getOne(wrapper);

            if (one != null) {
                return R.ok().messsage("科目已存在");
            }
        }

        boolean save = iEduSubjectService.save(eduSubject);

        if (save) {
            return R.ok();
        } else {
            return R.error();
        }

    }

    //获取课程列表（树形）
    @GetMapping("getAllSubject")
    public R getAllSubject() {

        //一级分类有二级分类的列表，所以返回一级分类
        List<OneSubject> list = iEduSubjectService.getAllOneTwoSubject();


        return R.ok().data("list", list);
    }

    //获取二级分类列表
    @PostMapping("getTwoLevelList")
    public R getTwoLevelList(@RequestParam(value = "parentId") String parentId) {

        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", parentId);


        List<EduSubject> subjectList = iEduSubjectService.list(wrapper);

        return R.ok().data("list",subjectList);
    }
}

