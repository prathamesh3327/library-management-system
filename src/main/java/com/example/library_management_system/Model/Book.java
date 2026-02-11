package com.example.library_management_system.Model;

import jakarta.persistence.*;
import lombok.*;
import org.apache.tomcat.jni.Library;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    private Boolean isAvailable;
    private Long issueCount;

    @CreationTimestamp
    private Date creationDate;
    @UpdateTimestamp
    private Date updateDate;

    @JoinColumn
    @ManyToOne
    private Student student;

    @JoinColumn
    @ManyToOne
    private Author author;
}
