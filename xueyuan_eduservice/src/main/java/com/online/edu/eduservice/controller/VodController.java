package com.online.edu.eduservice.controller;

import com.online.edu.common.R;
import com.online.edu.eduservice.service.IEduVodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduservice/vod")
@CrossOrigin
public class VodController {

    @Autowired
    private IEduVodService iEduVodService;

    // 上传视频到阿里云
    @PostMapping("uploadVideo")
    public R uploadVideo(MultipartFile file) {

        return R.ok();
    }
}
