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
@Table(name = "Assignments")
public class AssignmentEntity {
    @Id
    private String assignmentId;
    private String assignmentName;
    private LocalDate dueDate;

    @Id
    @ManyToOne
    @JoinColumn(name = "teacherId")
    private TeacherEntity teacher;
}
