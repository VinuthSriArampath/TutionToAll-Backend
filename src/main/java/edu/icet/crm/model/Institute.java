package edu.icet.crm.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Institute {
    private String id;
    private String name;
    private String email;
    private String contact;
    private String address;
    private String password;
    private List<RegisteredStudents> registeredStudents;
    private List<RegisteredTeachers> registeredTeachers;
    private List<Course> courseList;
}
