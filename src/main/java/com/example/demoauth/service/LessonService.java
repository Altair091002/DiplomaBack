package com.example.demoauth.service;

import com.example.demoauth.dto.LessonDTO;
import com.example.demoauth.exception.PostNotFoundException;
import com.example.demoauth.mappers.LessonsMapper;
import com.example.demoauth.models.Lesson;
import com.example.demoauth.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;
    private final LessonsMapper lessonsMapper;

    public List<LessonDTO> showAllPosts() {
        List<Lesson> lessons = lessonRepository.findAll();
        return lessonsMapper.LessonListToLessonDtoList(lessons);
    }

    @Transactional
    public void createPost(LessonDTO lessonDTO) {
        Lesson lesson = lessonsMapper.LessonDtoToLesson(lessonDTO);
        lessonRepository.save(lesson);
    }

    @Transactional
    public void updatedPost(Long id, LessonDTO lessonDTO) {
        Lesson lesson = lessonRepository.findById(id).get();
        lesson.setContent(lessonDTO.getContent());
        lesson.setTitle(lessonDTO.getTitle());
        lesson.setCreatedOn(lessonDTO.getCreatedOn());
        lessonRepository.save(lesson);
    }

    @Transactional
    public void deletePost(Long id) {
        lessonRepository.deleteById(id);
    }

    @Transactional
    public LessonDTO readSinglePost(Long id) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow(() -> new PostNotFoundException("For id " + id));
        return lessonsMapper.LessonToLessonDto(lesson);
    }

}
