package edu.icet.crm.entity;

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
@Table(name = "Assignments")
public class AssignmentEntity {
    @Id
    @Column(name = "assignment_id")
    private String id;

    @Column(name = "course_id")
    private String courseId;

    @Column(name = "assignment_name")
    private String assignmentName;

    @Column(name = "assignment_due_date")
    private LocalDate dueDate;

    @Column(name = "assignment_path")
    private String path;

    @OneToMany(mappedBy = "assignment",cascade = CascadeType.ALL)
    private List<StudentAssignmentSubmissionEntity> submittedList;
}
