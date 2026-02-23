package com.example.library_management_system.service;


import com.example.library_management_system.Model.Author;
import com.example.library_management_system.Model.Book;
import com.example.library_management_system.Model.Genre;
import com.example.library_management_system.dtos.CreateBookRequest;
import com.example.library_management_system.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Book create(Book book) {
        Author author = book.getAuthor();
        if(author != null && author.getId()==null){
            author = this.authorService.GetOrCreate(author);
            book.setAuthor(author);

        }
        return this.bookRepository.save(book);
    }

    public List<Book> findByName(String name){
        return bookRepository.findByName(name);

    }

    public List<Book> findByGenre(String genre){
        return bookRepository.findByGenre(Genre.valueOf(genre));
    }

    public Book findById(Integer bookId) {
       return bookRepository.findById(bookId).orElse(null);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }
    public List<Book> findByStudentId(Integer studentId) {
        return bookRepository.findByStudentId(studentId);
    }

}
