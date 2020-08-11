package com.online.edu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.online.edu.eduservice.entity.EduSubject;
import com.online.edu.eduservice.entity.subject.OneSubject;
import com.online.edu.eduservice.entity.subject.TwoSubject;
import com.online.edu.eduservice.mapper.EduSubjectMapper;
import com.online.edu.eduservice.service.IEduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dxq
 * @since 2020-08-10
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements IEduSubjectService {

    @Override
    public List<OneSubject> getAllOneTwoSubject() {

        //1 查询出所有的一级分类
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();

        wrapperOne.eq("parent_id", "0");

        List<EduSubject> oneSubjects = baseMapper.selectList(wrapperOne);

        //2 查询出所有的二级分类
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();

        wrapperTwo.ne("parent_id", "0");

        List<EduSubject> twoSubjects = baseMapper.selectList(wrapperTwo);



        //3创建list集合，存储最终封装数据
        List<OneSubject> finalSubjectList = new ArrayList<>();

        //4 封装一级分类
        for (int i = 0; i < oneSubjects.size(); i++) {
            EduSubject eduSubject = oneSubjects.get(i);

            OneSubject oneSubject = new OneSubject();

            /*oneSubject.setId(eduSubject.getId());
            oneSubject.setTitle(eduSubject.getTitle());*/
            BeanUtils.copyProperties(eduSubject, oneSubject);


            List<TwoSubject> twoFinalSubjectList = new ArrayList<>();

            for (int m = 0; m < twoSubjects.size(); m++) {

                EduSubject tSubject = twoSubjects.get(m);

                if (tSubject.getParentId().equals(eduSubject.getId())) {
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(tSubject, twoSubject);
                    twoFinalSubjectList.add(twoSubject);
                }
            }

            oneSubject.setChildren(twoFinalSubjectList);

            finalSubjectList.add(oneSubject);

        }

        return finalSubjectList;
    }
}
