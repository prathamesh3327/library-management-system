package com.example.library_management_system.service;

import com.example.library_management_system.Model.*;
import com.example.library_management_system.repository.TransactionRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private  BookService bookService;

    @Autowired
    private  StudentService studentService;

    @Value("${books.issue.max-allowed}")
    private Integer maxAllowed;
    public String issueTxn(Integer studentId, Integer bookId) {
        Book book = bookService.findById(bookId);
        if(book==null && book.getStudent()!=null){
            throw new ValidationException("Book is not available");

        }

        Student student = studentService.findById(studentId);
        if(student==null){
            throw new ValidationException("Student is not available");
        }

        List<Book> books = bookService.findByStudentId(studentId);
        if(books==null || books.size()>maxAllowed){
            throw new ValidationException("student has issued more books than allowed");
        }
        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.ISSUE);
        transaction.setStatus(TransactionsStatus.IN_PROCESS);
        transaction.setExternalTxnId(UUID.randomUUID().toString());
        transaction.setBook(book);
        transaction.setStudent(student);

         transaction = transactionRepository.save(transaction);

         try {

             book.setIsAvailable(false);
             book.setStudent(student);

             bookService.create(book);
             transaction.setStatus(TransactionsStatus.COMPLETED);

         } catch (Exception e) {
             book.setIsAvailable(true);
             book.setStudent(null);
             bookService.create(book);
             transaction.setStatus(TransactionsStatus.FAILED);

         }
         finally {
             transactionRepository.save(transaction);
         }
         return transaction.getExternalTxnId();

    }
}
