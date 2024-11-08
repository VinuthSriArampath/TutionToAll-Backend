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
public class RegisteredStudents {
    private String studentId;
    private String instituteId;
    private String instituteName;
    private LocalDate date;
    private List<StudentRegisteredCourses> courses;
}
