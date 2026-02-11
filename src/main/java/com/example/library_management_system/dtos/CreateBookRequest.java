package com.example.library_management_system.dtos;

import com.example.library_management_system.Model.Author;
import com.example.library_management_system.Model.Book;
import com.example.library_management_system.Model.Genre;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookRequest {

    @NotBlank
    private String name;

    @NotNull
    private Genre genre;

    @NotBlank
    private String authorName;

    @NotBlank
    @Email
    private String authorEmail;
    private String authorCountry;

    public Book toBook() {
       return Book.builder()
               .name(this.name)
               .genre(this.genre)
               .isAvailable(true)
               .issueCount(0L)
               .author(
                       Author.builder()
                               .name(this.authorName)
                               .email(this.authorEmail)
                               .country(this.authorCountry)
                               .build()
               )
               .build();
    }
}
