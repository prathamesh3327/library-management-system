package com.example.library_management_system.repository;

import com.example.library_management_system.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends  JpaRepository<Book,Integer> {
    @Query
    List<Book> findByStudentId(Integer studentId);

}
