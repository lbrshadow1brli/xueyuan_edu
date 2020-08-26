package com.online.edu.eduservice.service;

import com.online.edu.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.online.edu.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dxq
 * @since 2020-08-13
 */
public interface IEduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    void deleteChapter(String chapterId);

    void removeChapterByCourseId(String courseId);
}
