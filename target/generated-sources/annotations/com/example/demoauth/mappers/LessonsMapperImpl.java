package com.example.demoauth.mappers;

import com.example.demoauth.dto.LessonDTO;
import com.example.demoauth.models.Lesson;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-24T14:09:43+0600",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.4.1 (Oracle Corporation)"
)
@Component
public class LessonsMapperImpl implements LessonsMapper {

    @Override
    public List<LessonDTO> LessonListToLessonDtoList(List<Lesson> lessons) {
        if ( lessons == null ) {
            return null;
        }

        List<LessonDTO> list = new ArrayList<LessonDTO>( lessons.size() );
        for ( Lesson lesson : lessons ) {
            list.add( LessonToLessonDto( lesson ) );
        }

        return list;
    }

    @Override
    public LessonDTO LessonToLessonDto(Lesson lesson) {
        if ( lesson == null ) {
            return null;
        }

        LessonDTO lessonDTO = new LessonDTO();

        lessonDTO.setId( lesson.getId() );
        lessonDTO.setTitle( lesson.getTitle() );
        lessonDTO.setContent( lesson.getContent() );
        lessonDTO.setCreatedOn( lesson.getCreatedOn() );
        lessonDTO.setUpdatedOn( lesson.getUpdatedOn() );

        return lessonDTO;
    }

    @Override
    public Lesson LessonDtoToLesson(LessonDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Lesson lesson = new Lesson();

        lesson.setId( dto.getId() );
        lesson.setTitle( dto.getTitle() );
        lesson.setContent( dto.getContent() );
        lesson.setCreatedOn( dto.getCreatedOn() );
        lesson.setUpdatedOn( dto.getUpdatedOn() );

        return lesson;
    }
}
