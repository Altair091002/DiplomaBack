package com.example.demoauth.repository;

import com.example.demoauth.models.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
//    CRUD
}
