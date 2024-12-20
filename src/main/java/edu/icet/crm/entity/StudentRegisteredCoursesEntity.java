package edu.icet.crm.entity;

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
    @Column(name = "student_id")
    private String studentId;

    @Id
    @Column(name = "course_id")
    private String courseId;

    @Column(name = "registered_date")
    private LocalDate date;
}
