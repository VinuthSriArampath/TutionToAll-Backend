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
@Table(name = "teacher")
public class TeacherEntity {
    @Id
    @Column(name = "teacher_id")
    private String id;

    @Column(name = "teacher_first_name")
    private String firstName;

    @Column(name = "teacher_last_name")
    private String lastName;

    @Column(name = "teacher_date_of_birth")
    private LocalDate dob;

    @Column(name = "teacher_contact")
    private String contact;

    @Column(name = "teacher_email")
    private String email;

    @Column(name = "teacher_address")
    private String address;

    @Column(name = "teacher_password")
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id",referencedColumnName = "teacher_id")
    private List<RegisteredTeachersEntity> registeredInstitutes;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id",referencedColumnName = "teacher_id")
    private List<CourseEntity> registeredCourses;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id",referencedColumnName = "teacher_id")
    private List<NoteEntity> managedNoteList;
}
