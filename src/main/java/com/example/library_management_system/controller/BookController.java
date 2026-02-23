package com.example.library_management_system.controller;

import com.example.library_management_system.Model.Book;
import com.example.library_management_system.dtos.CreateBookRequest;
import com.example.library_management_system.service.BookService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/find-by-name")
    public List<Book> findBookByName(@RequestParam("name") String name) {
        logger.info("find book by name request received- {}", name);
        return bookService.findByName(name);
    }

    @GetMapping("/find-by-genre")
    public List<Book> findBookByGenre(@RequestParam("genre") String genre) {
        logger.info("find book by genre request received- {}", genre);
        return bookService.findByGenre(genre);
    }
}
