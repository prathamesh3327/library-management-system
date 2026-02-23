package com.example.library_management_system.controller;

import com.example.library_management_system.Model.Student;
import com.example.library_management_system.dtos.CreateStudentRequest;
import com.example.library_management_system.service.StudentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private static Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    StudentService studentService;

    @PostMapping("/add-studnet")
    public Student addStudent(@Valid @RequestBody CreateStudentRequest createStudentRequest){
        logger.info("add student request received - {}", createStudentRequest);

        return studentService.createStudent(createStudentRequest);


    }
    @DeleteMapping("/delete-student-by-id")
    public String deleteStudent(@RequestParam(name = "id") Integer id){
        logger.info("delete student request received - {}", id);
        return studentService.deleteStudent(id);
    }

    @GetMapping("/all-students")
    public List<Student> getAllStudents(){
        logger.info("get all students request received");
        return studentService.findAllStudents();
    }
}
