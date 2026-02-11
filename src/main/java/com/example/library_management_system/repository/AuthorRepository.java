package com.example.library_management_system.repository;

import com.example.library_management_system.Model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


public interface AuthorRepository extends JpaRepository<Author,Integer> {
    @Query
    Author findByEmail(String email);

}
