package com.example.library_management_system.service;

import com.example.library_management_system.Model.*;
import com.example.library_management_system.repository.TransactionRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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

    @Value("${books.issue.duration}")
    private Integer duration;

    @Value("${books.fine.per-day}")
    private Long finePerDay;




    public String issueTxn(Integer studentId, Integer bookId) {
        Book book = bookService.findById(bookId);
        Long originalIssueCount= book.getIssueCount()==null?0:book.getIssueCount();
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
             book.setIssueCount(originalIssueCount+1);
             bookService.create(book);
             transaction.setStatus(TransactionsStatus.COMPLETED);

         } catch (Exception e) {
             book.setIsAvailable(true);
             book.setStudent(null);
             book.setIssueCount(originalIssueCount);
             bookService.create(book);
             transaction.setStatus(TransactionsStatus.FAILED);

         }
         finally {
             transactionRepository.save(transaction);
         }
         return transaction.getExternalTxnId();

    }

    public String returnTxn(Integer studentId, Integer bookId) {
        Book book = bookService.findById(bookId);
        if(book==null || book.getStudent()==null || book.getStudent().getId()!=studentId){
            throw new ValidationException("Book is not assigned to relevant student");
        }
        Student student = studentService.findById(studentId);
        if(student==null){
            throw new ValidationException("student is no valid to return");
        }

        Transaction transaction = Transaction.builder()
                .externalTxnId(UUID.randomUUID().toString())
                .status(TransactionsStatus.IN_PROCESS)
                .type(TransactionType.RETURN)
                .book(book)
                .student(student)
                .fine(calculateFine(book,student))
                .build();
        transaction = transactionRepository.save(transaction);

        try {
            book.setIsAvailable(true);
            book.setStudent(null);
            bookService.create(book);
            transaction.setStatus(TransactionsStatus.COMPLETED);
        }catch (Exception e){
            book.setIsAvailable(false);
            book.setStudent(student);
            bookService.create(book);
            transaction.setStatus(TransactionsStatus.FAILED);
        }
        finally {
            transactionRepository.save(transaction);
        }


        return transaction.getExternalTxnId();
    }

    public Long calculateFine(Book book, Student student){
        Transaction transaction =transactionRepository.findTopByStudentAndBookAndTypeOrderByIdDesc(student,book,TransactionType.ISSUE);


        long daysPassed = ChronoUnit.DAYS.between(transaction.getCreatedAt().toInstant(), new Date().toInstant());

        if((daysPassed - duration) <= 0){
            return 0L;
        }

        return (daysPassed - duration) * finePerDay;
//        Date issueDate= transaction.getCreatedAt();
//
//        long issueEpoach=issueDate.getTime();
//        long dueDateEpoch=issueEpoach+duration*1296000000;
//        long currentTime=System.currentTimeMillis();
//
//        long timeDifEpoach=currentTime-dueDateEpoch;
//        if(timeDifEpoach<0){
//            return 0L;
//        }
//        Long daysPassed =TimeUnit.DAYS.convert(timeDifEpoach,TimeUnit.MILLISECONDS);
//        if(daysPassed>0){
//            return daysPassed*finePerDay;
//        }
//
//        return 0L;
    }
}
