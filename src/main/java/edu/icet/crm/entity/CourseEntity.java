package edu.icet.crm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "course")
public class CourseEntity {
    @ManyToOne
    @JoinColumn(name = "instituteId")
    private InstituteEntity institute;
    @Id
    private String courseId;
    private String name;
    private String type;

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private List<StudentRegisteredCoursesEntity> studentCoursesList;

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private List<AssignmentEntity> assignmentList;
}
