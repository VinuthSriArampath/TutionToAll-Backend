package edu.icet.crm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "course")
public class CourseEntity {
    @Id
    @ManyToOne
    @JoinColumn(name = "instituteId")
    private InstituteEntity institute;
    @Id
    private String courseId;
    private String name;
    private String type;
}
