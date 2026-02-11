package com.example.library_management_system.service;


import com.example.library_management_system.Model.Author;
import com.example.library_management_system.Model.Book;
import com.example.library_management_system.dtos.CreateBookRequest;
import com.example.library_management_system.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorService authorService;

    public Book create(CreateBookRequest createBookRequest ) {
        Book book = createBookRequest.toBook();
        Author author = createBookRequest.toBook().getAuthor();
        author = this.authorService.GetOrCreate(author);

        book.setAuthor(author);
        return this.bookRepository.save(book);

    }
}
