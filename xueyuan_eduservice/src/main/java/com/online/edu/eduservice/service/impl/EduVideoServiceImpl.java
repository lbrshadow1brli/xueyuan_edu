package com.online.edu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.online.edu.eduservice.entity.EduVideo;
import com.online.edu.eduservice.mapper.EduVideoMapper;
import com.online.edu.eduservice.service.IEduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dxq
 * @since 2020-08-13
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements IEduVideoService {

    //根据课程id删除小节
    // TODO 删除小节，删除对应视频
    @Override
    public void removeVideoByCourseId(String courseId) {

        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("courese_id", courseId);

        baseMapper.delete(queryWrapper);
    }
}
