package com.example.demoauth.controllers;

import com.example.demoauth.service.FileUploadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity uploadFile(@RequestParam("file")MultipartFile multipartFile) throws IOException {
        System.out.println("This File: ");
        System.out.println("This File: " + multipartFile);
        fileUploadService.uploadFile(multipartFile);
        return new ResponseEntity(HttpStatus.OK);
    }
}
