package com.example.library_management_system.Model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;


    @Enumerated(value=EnumType.STRING)
    private Department department;
    @Column(unique=true,nullable=false)
    private String rollNo;
    @CreationTimestamp
    private Date createdDate;

    @UpdateTimestamp
    private Date lastModifiedDate;

    @Column(unique=true,nullable=false)
    private String email;
    //books
    @OneToMany(mappedBy = "student")
    private List<Book> books;

}
