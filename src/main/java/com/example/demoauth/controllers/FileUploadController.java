package com.example.demoauth.controllers;

import com.example.demoauth.service.FileUploadService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/downloadFile")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 180)
public class FileUploadController {
    private final FileUploadService fileUploadService;

    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @PostMapping
    public void uploadFile(@RequestParam("file")MultipartFile multipartFile) throws IOException {
        fileUploadService.uploadFile(multipartFile);
    }
}
