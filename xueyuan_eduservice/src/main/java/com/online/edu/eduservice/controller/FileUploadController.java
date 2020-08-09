package com.online.edu.eduservice.controller;

/*上传文件到阿里云oss*/

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.online.edu.common.R;
import com.online.edu.eduservice.handler.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/eduservice/oss")
public class FileUploadController {

    //上传教师头像
    @PostMapping("upload")
    public R uploadTeacherImg(@RequestParam("file") MultipartFile file,@RequestParam(value="host",required = false) String host) {

        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = ConstantPropertiesUtil.ENDPOINT;
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = ConstantPropertiesUtil.KEYID;
        String accessKeySecret = ConstantPropertiesUtil.KEYSECRET;

        String yourBucketName = ConstantPropertiesUtil.BUCKETNAME;

        try {

            //1.获取上传文件 MultipartFile file
            //2. 获取上传文件名称，获取上传文件输入流
            String filename = file.getOriginalFilename();
            InputStream in = file.getInputStream();

            //在文件名称之前添加uuid值，报纸未见
            String uuid = UUID.randomUUID().toString();
            filename = uuid + filename;

            //获取当前日期 2020/07/30
            String filePath = new DateTime().toString("yyyy/MM/dd");
            filename = host + "/" + filePath + "/" + filename;


            //3.把上传文件存储到oss

            //创建OSSClient实例
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            //上传文件流
            ossClient.putObject(yourBucketName, filename, in);

            ossClient.shutdown();


            String path = "http://" + yourBucketName + "." + endpoint + "/" + filename;

            return R.ok().data("avatar", path);

        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }
}
