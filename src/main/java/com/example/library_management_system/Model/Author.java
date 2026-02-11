package com.example.library_management_system.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @Column(unique=true,nullable=false)
    private String email;

    private String country;
    @OneToMany(mappedBy = "author")
    private List<Book> books;
}
