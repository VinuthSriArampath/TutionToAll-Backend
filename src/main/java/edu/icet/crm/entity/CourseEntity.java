// CourseEntity.java
package edu.icet.crm.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "course")
public class CourseEntity {

    @Id
    @Column(name = "course_id")
    private String id;

    @Column(name = "course_name")
    private String name;

    @Column(name = "course_type")
    private String type;

    @OneToMany(cascade = CascadeType.ALL)
    private List<StudentRegisteredCoursesEntity> studentCoursesList;

    @OneToMany(cascade = CascadeType.ALL)
    private List<AssignmentEntity> assignmentList;
}
