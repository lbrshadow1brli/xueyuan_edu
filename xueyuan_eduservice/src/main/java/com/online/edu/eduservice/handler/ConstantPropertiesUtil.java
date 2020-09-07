package com.online.edu.eduservice.handler;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//此类的作用：读取配置文件，达到在配置文件中定义全局变量
//！要加上这个注解
@Component
//！要继承这个类 implements InitializingBean
public class ConstantPropertiesUtil implements InitializingBean {

    //服务器启动时候，ConstantPropertiesUtil初始化，调用afterPropertiesSet读取配置文件内容

    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.file.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.oss.file.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.oss.file.yourBucketName}")
    private String yourBucketName;

    //定义常量，为了能够使用

    public static String ENDPOINT;
    public static String KEYID;
    public static String KEYSECRET;
    public static String BUCKETNAME;


    @Override
    public void afterPropertiesSet() throws Exception {
        ENDPOINT = endpoint;
        KEYID = accessKeyId;
        KEYSECRET = accessKeySecret;
        BUCKETNAME = yourBucketName;
    }
}
