package com.example.library_management_system.controller;

import com.example.library_management_system.Model.Book;
import com.example.library_management_system.dtos.CreateBookRequest;
import com.example.library_management_system.service.BookService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

    private static Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @PostMapping("")
    public Book createBook(@Valid @RequestBody CreateBookRequest createBookRequest) {
        logger.info("create book request received- {}", createBookRequest);
        bookService.create(createBookRequest);
        return createBookRequest.toBook();
    }

}
