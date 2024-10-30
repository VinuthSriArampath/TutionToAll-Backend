package edu.icet.crm.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
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
@Entity
@Table(name = "student")
public class StudentEntity {

    @Id
    @Column(name = "student_id")
    private String id;

    @Column(name = "student_first_name")
    private String fName;

    @Column(name = "student_last_name")
    private String lName;

    @Column(name = "student_date_of_birth")
    private LocalDate dob;

    @Column(name = "student_contact")
    private String contact;

    @Column(name = "student_email")
    private String email;

    @Column(name = "student_address")
    private String address;

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private List<RegisteredStudentsEntity> registeredInstitutes;

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private List<StudentRegisteredCoursesEntity> registeredCourses;

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private List<StudentAssignmentSubmissionEntity> submissionList;
}
