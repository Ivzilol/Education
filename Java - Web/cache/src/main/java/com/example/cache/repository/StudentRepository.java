package com.example.cache.repository;

import com.example.cache.model.StudentDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepository {

    private static List<StudentDTO> allStudents = List.of(
            new StudentDTO("Pesho", 10, 20),
            new StudentDTO("Anna", 11, 21),
            new StudentDTO("Gosho", 9, 22)

    );

    public List<StudentDTO> getAllStudents() {
        DummyWait();
        return allStudents;
    }

    public StudentDTO findAllStudentsByName(String name) {
        DummyWait();
        return allStudents
                .stream()
                .filter(s -> s.getName().equals(name))
                .findAny()
                .orElse(null);
    }

    private void DummyWait() {
        try {
            Thread.sleep(5000);
            //Dammy - imagine we need to perform complex operation to fetch student data
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
