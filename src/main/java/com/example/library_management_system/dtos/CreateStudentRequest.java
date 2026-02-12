package com.example.library_management_system.dtos;

import com.example.library_management_system.Model.Department;
import com.example.library_management_system.Model.Student;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudentRequest {
    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;

    @NotNull
    private Department department;
    @NotNull
    private Integer rolNo;


    public Student toStudent(){
        return Student.builder()
                .name(this.name)
                .email(this.email)
                .department(this.department)
                .rollNo(this.rolNo)

                .build();
    }

}
