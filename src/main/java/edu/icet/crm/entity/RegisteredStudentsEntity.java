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
@Table(name = "registered_students")
public class RegisteredStudentsEntity {
    @Id
    @Column(name = "student_id")
    private String studentId;
    @Id
    @Column(name="institute_id")
    private String instituteId;
    @Column(name = "registered_date")
    private LocalDate date;
}
