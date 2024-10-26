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
    private String teacherId;
    private String fName;
    private String lName;
    private LocalDate dob;
    private String contact;
    private String email;
    private String address;

    @OneToMany(mappedBy = "teacher",cascade = CascadeType.ALL)
    private List<RegisteredTeachersEntity> registeredInstitutes;

    @OneToMany(mappedBy = "teacher",cascade = CascadeType.ALL)
    private List<AssignmentEntity> managedAssignmentList;

    @OneToMany(mappedBy = "teacher",cascade = CascadeType.ALL)
    private List<NoteEntity> managedNoteList;
}
