package com.example.demoauth.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileUploadService {
    public void uploadFile(MultipartFile multipartFile) throws IOException {
        multipartFile.transferTo(new File("/Users/macbook/Documents/Java/Spring/spring-jwt-realization/src/main/resources/static/files" + multipartFile.getOriginalFilename()));
    }
}
