package com.online.edu.eduservice.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.online.edu.eduservice.handler.ConstantPropertiesUtil;
import com.online.edu.eduservice.service.IEduVodService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class EduVodServiceImpl implements IEduVodService {
    @Override
    public String uploadVod(MultipartFile file) {

        try {

            String fileName = file.getOriginalFilename();

            //title,不需要文件名称的后缀.xxx

            String title = fileName.substring(0,fileName.lastIndexOf("."));

            //文件流用文件对象的getInputStream得到
            InputStream inputStream = file.getInputStream();

            UploadStreamRequest request = new UploadStreamRequest(ConstantPropertiesUtil.KEYID, ConstantPropertiesUtil.KEYSECRET, title, fileName, inputStream);

            UploadVideoImpl uploader = new UploadVideoImpl();

            UploadStreamResponse response = uploader.uploadStream(request);

            String videoId = null;

            if (response.isSuccess()) {
                videoId = response.getRequestId();
            } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                videoId = response.getRequestId();
            }


            return videoId;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
