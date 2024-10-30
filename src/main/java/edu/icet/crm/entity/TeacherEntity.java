package edu.icet.crm.entity;

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
@Table(name = "teacher")
public class TeacherEntity {
    @Id
    @Column(name = "teacher_id")
    private String id;
    @Column(name = "teacher_first_name")
    private String fName;
    @Column(name = "teacher_last_name")
    private String lName;
    @Column(name = "teacher_date_of_birth")
    private LocalDate dob;
    @Column(name = "teacher_contact")
    private String contact;
    @Column(name = "teacher_email")
    private String email;
    @Column(name = "teacher_address")
    private String address;

    @OneToMany(mappedBy = "teacher",cascade = CascadeType.ALL)
    private List<RegisteredTeachersEntity> registeredInstitutes;

    @OneToMany(mappedBy = "teacher",cascade = CascadeType.ALL)
    private List<AssignmentEntity> managedAssignmentList;

    @OneToMany(mappedBy = "teacher",cascade = CascadeType.ALL)
    private List<NoteEntity> managedNoteList;
}
