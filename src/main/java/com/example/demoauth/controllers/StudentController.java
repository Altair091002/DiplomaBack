package com.example.demoauth.controllers;

import com.example.demoauth.service.StudentService;
import lombok.Data;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@Data
@RequestMapping("/api/student")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 180)
public class StudentController {
    private final StudentService studentService;

//    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}/status/{status}")
    public void status(@PathVariable Long id, @PathVariable String status) {
        System.out.println(id);
        System.out.println(status);
        System.out.println(studentService.userDetails());
//        studentService.updateStatus(id1, status);
    }
}
