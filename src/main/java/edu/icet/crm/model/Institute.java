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
    @NotNull @NotBlank(message = "Institute id is missing !!")
    private String instituteId;
    @NotNull @NotBlank(message = "Institute name is missing !!")
    private String name;
    @NotNull @NotBlank(message = "Institute email is missing !!")
    private String email;
    @NotNull @NotBlank(message = "Institute contact is missing !!")
    @Size(max = 10,message = "Contact Must Contain 10 numbers!!")
    private String contact;
    @NotNull @NotBlank(message = "Institute address is missing !!")
    private String address;
    @NotNull(message = "Institute password is missing !! ")
    @NotBlank @Size(min = 8, message = "Institute password need to have at least 8 characters !!")
    private String password;
    private List<RegisteredStudents> registeredStudents;
    private List<RegisteredTeachers> registeredTeachers;
    private List<Course> courseList;

    public Institute(String instituteId, String name, String email, String contact, String address,String password) {
        this.instituteId = instituteId;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.address = address;
        this.password=password;
    }
}
