package com.online.test;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;

import java.util.List;

public class testVod {

    public static void main(String[] args) throws ClientException {
        //2 根据视频id 获取视频播放凭证
        //创建初始化对象
        DefaultAcsClient client = initObject.initVodClient("LTAI4GD68W9FjZRN8AEEFfVC", "gGCTbzi854i4jKz1Patp68fBNhjlMM");

        //创建获取视频凭证request和response
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();

        request.setVideoId("82907f65d95b44a5a024ea43e65d8a35");

        response = client.getAcsResponse(request);

        //播放凭证
        System.out.print("PlayAuth = " + response.getPlayAuth() + "\n");
        //VideoMeta信息
        System.out.print("VideoMeta.Title = " + response.getVideoMeta().getTitle() + "\n");
    }

    public static void getPlayUrl() throws ClientException {
        //1 根据视频id 获取视频播放地址
        //创建初始化对象
        DefaultAcsClient client = initObject.initVodClient("LTAI4GD68W9FjZRN8AEEFfVC", "gGCTbzi854i4jKz1Patp68fBNhjlMM");

        //创建获取视频地址request和response
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();

        //向request对象设置视频id
        request.setVideoId("54904865aac942f1a359abe31fea3206");

        //调用初始化对象里面的方法，传递request，获取数据
        response = client.getAcsResponse(request);

        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        //播放地址
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
        }
        //Base信息
        System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");

    }
}
