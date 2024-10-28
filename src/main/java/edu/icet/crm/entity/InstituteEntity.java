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
@Table(name = "institute")
public class InstituteEntity {
    @Id
    @Column(name = "institute_id")
    private String id;
    @Column(name = "institute_name")
    private String name;
    @Column(name = "institute_email")
    private String email;
    @Column(name = "institute_contact")
    private String contact;
    @Column(name = "institute_address")
    private String address;
    @Column(name = "institute_login_password")
    private String password;

    @OneToMany(mappedBy = "institute",cascade = CascadeType.ALL)
    private List<RegisteredStudentsEntity> registeredStudents;

    @OneToMany(mappedBy = "institute",cascade = CascadeType.ALL)
    private List<RegisteredTeachersEntity> registeredTeachers;

    @OneToMany(mappedBy = "institute",cascade = CascadeType.ALL)
    private List<CourseEntity> courseList;
}
