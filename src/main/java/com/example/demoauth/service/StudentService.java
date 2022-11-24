package com.example.demoauth.service;

import com.example.demoauth.jwt.PersonDetailsImpl;
import com.example.demoauth.models.Student;
import com.example.demoauth.models.User;
import com.example.demoauth.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    @Transactional
    public void updateStatus(Long id, String status) {
        Student student = studentRepository.findById(id).get();
        student.setStatus(status);
        studentRepository.save(student);
    }

    public User userDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetailsImpl principal = (PersonDetailsImpl) authentication.getPrincipal();
        return principal.getUser();
    }
}
