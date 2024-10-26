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
    private String assignmentId;
    private String assignmentName;
    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name = "teacherId")
    private TeacherEntity teacher;

    @ManyToOne
    @JoinColumn(name = "courseId")
    private CourseEntity course;

    @OneToMany(mappedBy = "assignment",cascade = CascadeType.ALL)
    private List<StudentAssignmentSubmissionEntity> submittedList;
}
