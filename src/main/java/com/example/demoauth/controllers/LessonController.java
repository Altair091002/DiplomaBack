package com.example.demoauth.controllers;

import com.example.demoauth.dto.LessonDTO;
import com.example.demoauth.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts/")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 180)
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @PostMapping
    public ResponseEntity createPost(@RequestBody LessonDTO lessonDTO) {
        lessonService.createPost(lessonDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<LessonDTO>> showAllPosts() {
        return new ResponseEntity<>(lessonService.showAllPosts(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<LessonDTO> getSinglePost(@PathVariable @RequestBody Long id) {
        return new ResponseEntity<>(lessonService.readSinglePost(id), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updatePost(@PathVariable Long id, @RequestBody LessonDTO lessonDTO) {
        lessonService.updatedPost(id, lessonDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping ("/{id}")
    public ResponseEntity<HttpStatus> deletePost(@PathVariable Long id){
        lessonService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
