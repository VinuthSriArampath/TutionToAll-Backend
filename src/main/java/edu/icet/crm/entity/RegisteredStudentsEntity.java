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
@Table(name = "registered_students")
public class RegisteredStudentsEntity {
    @Id
    @Column(name = "Institute_Student_Id")
    private  String instituteStudentId;
    @Column(name = "registered_date")
    private LocalDate date;
}
