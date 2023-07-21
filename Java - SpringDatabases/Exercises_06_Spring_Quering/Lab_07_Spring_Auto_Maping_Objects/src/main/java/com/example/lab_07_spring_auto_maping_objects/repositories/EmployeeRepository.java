package com.example.lab_07_spring_auto_maping_objects.repositories;

import com.example.lab_07_spring_auto_maping_objects.demo.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
