package com.example.demoauth.mappers;

import com.example.demoauth.dto.LessonDTO;
import com.example.demoauth.models.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LessonsMapper {
    @Mappings({})
    List<LessonDTO> LessonListToLessonDtoList(List<Lesson> lessons);
    @Mappings({})
    LessonDTO LessonToLessonDto(Lesson lesson);
    @Mappings({})
    Lesson LessonDtoToLesson(LessonDTO dto);
}
