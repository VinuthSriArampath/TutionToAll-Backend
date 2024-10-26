package edu.icet.crm.entity;

import edu.icet.crm.model.Assignment;
import edu.icet.crm.model.Student;
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
    private LocalDate date;
}
