package com.example.HATEOAS.web;

import com.example.HATEOAS.model.dto.OrderDTO;
import com.example.HATEOAS.model.dto.StudentDTO;
import com.example.HATEOAS.service.StudentService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequestMapping("/students")
@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<StudentDTO>>> getStudents() {
        List<EntityModel<StudentDTO>> studentEntityModels  = studentService
                .getAllStudents()
                .stream()
                .map(s -> EntityModel.of(s, getStudentLinks(s)))
                .toList();


        return ResponseEntity
                .ok(CollectionModel.of(studentEntityModels));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<StudentDTO>> getStudentById(@PathVariable("id") Long id) {
        var studentOpt = studentService.getStudentById(id);
        if (studentOpt.isEmpty()) {
            return ResponseEntity
                    .notFound()
                    .build();
        }

        var student = studentOpt.get();
        return ResponseEntity
                .ok(EntityModel.of(student, getStudentLinks(student)));
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<CollectionModel<EntityModel<OrderDTO>>>getStudentOrders
            (@PathVariable("id") Long id) {

        var orders = studentService
                .getStudentOrders(id)
                .stream()
                .map(o -> EntityModel.of(o, getOrderLinks(o))).toList();

        return ResponseEntity
                .ok(CollectionModel.of(orders));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<StudentDTO>> updateStudent(
            @PathVariable("id") String id,
            StudentDTO studentDTO) {
        throw new UnsupportedOperationException("Not important right now!");
    }

    private Link[] getStudentLinks(StudentDTO studentDTO) {
        List<Link> studentLinks = new ArrayList<>();

        Link selfRef = linkTo(methodOn(StudentController.class)
                .getStudentById(studentDTO.getId()))
                .withSelfRel();
        studentLinks.add(selfRef);

        studentLinks.add(selfRef);

        if (!studentDTO.isDeleted()) {
            Link orderLink = linkTo(methodOn(StudentController.class)
                    .getStudentOrders(studentDTO.getId())).withRel("orders");

            studentLinks.add(orderLink);

            Link updateLink = linkTo(methodOn(StudentController.class)
                    .updateStudent(String.valueOf(studentDTO.getId()), studentDTO)).withRel("update");

            studentLinks.add(updateLink);
        }

        return studentLinks.toArray(new Link[0]);
    }

    private Link getOrderLinks(OrderDTO orderDTO) {
        return linkTo(methodOn(StudentController.class)
                .getStudentById(orderDTO.getStudentId()))
                .withRel("student");
    }
}
