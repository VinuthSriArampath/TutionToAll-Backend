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
    @Column(name = "course_id")
    private String id;
    @Column(name = "course_name")
    private String name;
    @Column(name = "course_type")
    private String type;

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private List<StudentRegisteredCoursesEntity> studentCoursesList;

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private List<AssignmentEntity> assignmentList;
}
