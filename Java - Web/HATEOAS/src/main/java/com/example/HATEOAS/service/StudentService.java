package com.example.HATEOAS.service;

import com.example.HATEOAS.model.dto.OrderDTO;
import com.example.HATEOAS.model.dto.StudentDTO;
import com.example.HATEOAS.model.entity.OrderEntity;
import com.example.HATEOAS.model.entity.StudentEntity;
import com.example.HATEOAS.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream().map(this::map).toList();
    }

    public List<OrderDTO> getStudentOrders (Long studentId) {
        return getStudentById(studentId)
                .map(StudentDTO::getOrders)
                .orElseGet(ArrayList::new);

    }

    public Optional<StudentDTO> getStudentById(Long studentId) {
        return studentRepository.findById(studentId)
                .map(this::map);
    }

    private StudentDTO map(StudentEntity entity) {

        var orders =  entity.getOrders()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());

        return new StudentDTO()
                .setAge(entity.getAge())
                .setDeleted(entity.isDeleted())
                .setId(entity.getId())
                .setName(entity.getName())
                .setOrders(orders);
    }

    private OrderDTO map(OrderEntity entity) {
        return new OrderDTO()
                .setStudentId(entity.getStudent().getId())
                .setCourseId(entity.getCourse().getId());
    }

}
