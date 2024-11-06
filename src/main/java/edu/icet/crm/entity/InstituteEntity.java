package edu.icet.crm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="institute_id",referencedColumnName = "institute_id")
    private List<RegisteredStudentsEntity> registeredStudents;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="institute_id",referencedColumnName = "institute_id")
    private List<RegisteredTeachersEntity> registeredTeachers;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="institute_id",referencedColumnName = "institute_id")
    private List<CourseEntity> courseList;
}
