package com.online.edu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.online.edu.eduservice.entity.EduChapter;
import com.online.edu.eduservice.entity.EduVideo;
import com.online.edu.eduservice.entity.chapter.ChapterVo;
import com.online.edu.eduservice.entity.chapter.VideoVo;
import com.online.edu.eduservice.mapper.EduChapterMapper;
import com.online.edu.eduservice.service.IEduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.online.edu.eduservice.service.IEduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dxq
 * @since 2020-08-13
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements IEduChapterService {

    @Autowired
    private IEduVideoService iEduVideoService;

    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {

        //1 根据courseId查出所有章节
        QueryWrapper<EduChapter> wrapperChapter = new QueryWrapper();

        wrapperChapter.eq("courese_id", courseId);

        List<EduChapter> eduChapterList = baseMapper.selectList(wrapperChapter);

        //2 根据courseId查出所有视频

        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper();

        wrapperVideo.eq("courese_id", courseId);

        List<EduVideo> eduVideoList = iEduVideoService.list(wrapperVideo);

        List<ChapterVo> finalChapterList = new ArrayList<>();

        for (int i = 0; i < eduChapterList.size(); i++) {

            EduChapter thatChapter = eduChapterList.get(i);

            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(thatChapter, chapterVo);

            List<VideoVo> finalVideoList = new ArrayList<>();

            for (int m = 0; m < eduVideoList.size(); m++) {

                EduVideo thatVideo = eduVideoList.get(m);

                if (thatVideo.getChapterId().equals(thatChapter.getId())) {
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(thatVideo, videoVo);

                    finalVideoList.add(videoVo);
                }
            }

            chapterVo.setChildren(finalVideoList);

            finalChapterList.add(chapterVo);
        }

        return finalChapterList;
    }
}
