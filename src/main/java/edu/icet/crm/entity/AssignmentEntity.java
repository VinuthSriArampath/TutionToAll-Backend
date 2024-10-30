package edu.icet.crm.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column(name = "assignment_name")
    private String assignmentName;

    @Column(name = "assignment_due_date")
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
