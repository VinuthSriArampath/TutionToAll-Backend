package edu.icet.crm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String contact;
    private String email;
    private String address;
    private String password;
    private List<RegisteredStudents> registeredInstitutes;
    private List<StudentRegisteredCourses> registeredCourses;
    private List<StudentAssignmentSubmission> submissionList;
}
