package com.example.cache.service;

import com.example.cache.model.StudentDTO;
import com.example.cache.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(StudentService.class);
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Cacheable("students")
    public List<StudentDTO> getAllStudents() {
        LOGGER.info("Getting all students.");
        return studentRepository.getAllStudents();
    }

    @Cacheable("students")
    public StudentDTO getAllStudentsByName(String name) {
        LOGGER.info("Getting student by name {}.", name);
        return studentRepository.findAllStudentsByName(name);
    }

    @CacheEvict(cacheNames = "students", allEntries = true)
    public void refresh() {

    }

}
