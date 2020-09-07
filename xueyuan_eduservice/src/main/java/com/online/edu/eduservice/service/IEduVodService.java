package com.online.edu.eduservice.service;

import org.springframework.web.multipart.MultipartFile;

public interface IEduVodService {
    String uploadVod(MultipartFile file);
}
