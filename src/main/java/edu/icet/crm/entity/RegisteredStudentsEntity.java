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
@Table(name = "RegisteredStudents")
public class RegisteredStudentsEntity {
    @Id
    @ManyToOne
    @JoinColumn(name = "instituteId")
    private InstituteEntity institute;
    @OneToMany
    private StudentEntity student;
    private LocalDate date;
}
