package com.example.library_management_system.service;

import com.example.library_management_system.Model.Student;
import com.example.library_management_system.dtos.CreateStudentRequest;
import com.example.library_management_system.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public String deleteStudent(Integer id) {
        Student student = findById(id);
        if(student != null){
            studentRepository.delete(student);
            return "student with id " + id + " deleted";
        }
        else {
            return "student with id " + id + " not found";
        }


    }

    public List<Student> findAllStudents(){
        return studentRepository.findAll();
    }
}
