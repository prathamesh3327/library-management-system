package com.example.library_management_system.controller;

import com.example.library_management_system.Model.Transaction;
import com.example.library_management_system.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private Logger logger = LoggerFactory.getLogger(TransactionController.class);
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/issue")
    public String issueTransaction(@RequestParam("student-id") Integer studentId,
                                   @RequestParam("book-id") Integer bookId) {
        return transactionService.issueTxn(studentId,bookId);


    }

}
