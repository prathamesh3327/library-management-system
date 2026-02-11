package com.example.library_management_system.service;

import com.example.library_management_system.Model.Author;
import com.example.library_management_system.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    public Author GetOrCreate(Author author) {
        Author authorFromDb =authorRepository.findByEmail(author.getEmail());
        if(authorFromDb !=null){
            return authorFromDb;
        }
        return this.authorRepository.save(author);
    }

}
