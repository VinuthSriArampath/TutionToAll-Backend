package edu.icet.crm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "student")
public class StudentEntity {
    @Id
    private String studentId;
    private String fName;
    private String lName;
    private LocalDate dob;
    private String contact;
    private String email;
    private String address;

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private List<RegisteredStudentsEntity> registeredInstitutes;
}
