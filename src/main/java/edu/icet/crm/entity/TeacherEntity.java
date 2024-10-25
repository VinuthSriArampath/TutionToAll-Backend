package edu.icet.crm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

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
}
