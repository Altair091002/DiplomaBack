package com.example.demoauth.controllers;

import com.example.demoauth.dto.PostDto;
import com.example.demoauth.service.PostService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts/")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 180)
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity createPost(@RequestBody PostDto postDto) {
        postService.createPost(postDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostDto>> showAllPosts() {
        return new ResponseEntity<>(postService.showAllPosts(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PostDto> getSinglePost(@PathVariable @RequestBody Long id) {
        return new ResponseEntity<>(postService.readSinglePost(id), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updatePost(@PathVariable Long id, @RequestBody PostDto postDto) {
        postService.updatedPost(id, postDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping ("/{id}")
    public ResponseEntity<HttpStatus> deletePost(@PathVariable Long id){
        postService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
