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
@Table(name = "student_assignment_submission")
public class StudentAssignmentSubmissionEntity {
    @Id
    @ManyToOne
    @JoinColumn(name = "studentId")
    private StudentEntity student;
    @Id
    @ManyToOne
    @JoinColumn(name = "assignmentId")
    private AssignmentEntity assignment;
    @Column(name = "submitted_date")
    private LocalDate date;
}
