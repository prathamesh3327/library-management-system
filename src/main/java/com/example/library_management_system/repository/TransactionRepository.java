package com.example.library_management_system.repository;

import com.example.library_management_system.Model.Book;
import com.example.library_management_system.Model.Student;
import com.example.library_management_system.Model.Transaction;
import com.example.library_management_system.Model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    Transaction findTopByStudentAndBookAndTypeOrderByIdDesc(
            Student student,
            Book book,
            TransactionType type
    );
}
