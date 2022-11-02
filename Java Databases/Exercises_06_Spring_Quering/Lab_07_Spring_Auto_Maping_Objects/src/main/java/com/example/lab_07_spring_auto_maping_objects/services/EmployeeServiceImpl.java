package com.example.lab_07_spring_auto_maping_objects.services;

import com.example.lab_07_spring_auto_maping_objects.demo.entities.Employee;
import com.example.lab_07_spring_auto_maping_objects.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Optional<Employee> findOneById(int id) {
        return this.employeeRepository.findById(id);
    }

    @Override
    public void save(Employee employee) {
        this.employeeRepository.save(employee);
    }
}
