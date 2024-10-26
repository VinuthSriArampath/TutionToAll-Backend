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
    private String studentId;
    private String fName;
    private String lName;
    private LocalDate dob;
    private String contact;
    private String email;
    private String address;
    private List<RegisteredStudents> registeredInstitutes;
    private List<StudentRegisteredCourses> registeredCourses;
    private List<StudentAssignmentSubmission> submissionList;
}
