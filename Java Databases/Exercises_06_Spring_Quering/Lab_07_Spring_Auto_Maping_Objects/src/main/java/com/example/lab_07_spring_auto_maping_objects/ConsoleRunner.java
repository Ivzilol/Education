package com.example.lab_07_spring_auto_maping_objects;

import com.example.lab_07_spring_auto_maping_objects.entities.Employee;
import com.example.lab_07_spring_auto_maping_objects.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private EmployeeService employeeService;

    @Autowired
    public ConsoleRunner(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void run(String... args) throws Exception {

        Employee manager = new Employee(
                "Mrs.",
                "Manager",
                BigDecimal.ONE,
                LocalDate.now(),
                null
                );


        Employee first = new Employee(
                "first",
                "last",
                BigDecimal.TEN,
                LocalDate.now(),
                manager);

        Employee second = new Employee(
                "second",
                "last",
                BigDecimal.TEN,
                LocalDate.now(),
                manager);


    }
}
