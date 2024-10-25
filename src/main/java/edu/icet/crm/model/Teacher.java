package edu.icet.crm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Teacher {
    private String teacherId;
    private String fName;
    private String lName;
    private LocalDate dob;
    private String contact;
    private String email;
    private String address;
}
