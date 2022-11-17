package com.example.lab_07_spring_auto_maping_objects.services;

import com.example.lab_07_spring_auto_maping_objects.demo.entities.Employee;

import java.util.Optional;

public interface EmployeeService {
    Optional<Employee> findOneById(int id);

    void save (Employee employee);

}
