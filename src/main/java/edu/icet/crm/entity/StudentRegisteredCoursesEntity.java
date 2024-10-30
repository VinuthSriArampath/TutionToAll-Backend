package edu.icet.crm.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "student_registered_courses")
public class StudentRegisteredCoursesEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "studentId")
    private StudentEntity student;

    @Id
    @ManyToOne
    @JoinColumn(name = "courseId")
    private CourseEntity course;

    @Column(name = "registered_date")
    private LocalDate date;
}
