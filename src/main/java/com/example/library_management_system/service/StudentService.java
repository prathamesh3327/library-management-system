package com.example.library_management_system.service;

import com.example.library_management_system.Model.Student;
import com.example.library_management_system.dtos.CreateStudentRequest;
import com.example.library_management_system.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    public Student createStudent(CreateStudentRequest createStudentRequest) {
        Student student = createStudentRequest.toStudent();
        return studentRepository.save(student);
    }

    public Student findById(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }
}
