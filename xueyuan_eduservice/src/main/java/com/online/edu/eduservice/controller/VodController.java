package com.online.edu.eduservice.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.online.edu.common.R;
import com.online.edu.eduservice.handler.ConstantPropertiesUtil;
import com.online.edu.eduservice.service.IEduVodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduservice/vod")
@CrossOrigin
public class VodController {

    @Autowired
    private IEduVodService iEduVodService;

    //创建阿里媒资管理封装的对象
    String regionId = "cn-shanghai";  // 点播服务接入区域
    DefaultProfile profile = DefaultProfile.getProfile(regionId, ConstantPropertiesUtil.KEYID, ConstantPropertiesUtil.KEYSECRET);
    //阿里媒资管理封装的对象
    DefaultAcsClient client = new DefaultAcsClient(profile);

    // 上传视频到阿里云
    @PostMapping("uploadVideo")
    public R uploadVideo(MultipartFile file) {

        //返回上传视频id
        String videoId = iEduVodService.uploadVod(file);

        return R.ok().data("videoId", videoId);
    }

    // 根据视频id删除阿里云视频
    @DeleteMapping("deleteVod/{id}")
    public R deleteVod(@PathVariable String id) {

        try {
            //构建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(id);
            //调用阿里媒资管理封装的对象里的删除方法传送删除请求
            client.getAcsResponse(request);
            return R.ok();

        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }
}
