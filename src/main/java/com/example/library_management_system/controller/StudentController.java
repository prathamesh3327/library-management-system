package com.example.library_management_system.controller;

import com.example.library_management_system.Model.Student;
import com.example.library_management_system.dtos.CreateStudentRequest;
import com.example.library_management_system.service.StudentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {

    private static Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    StudentService studentService;

    @PostMapping("")
    public Student addStudent(@Valid @RequestBody CreateStudentRequest createStudentRequest){
        logger.info("add student request received - {}", createStudentRequest);

        return studentService.createStudent(createStudentRequest);


    }
}
