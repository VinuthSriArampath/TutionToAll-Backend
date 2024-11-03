package edu.icet.crm.entity;

import jakarta.persistence.*;
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
@Table(name = "registered_teachers")
public class RegisteredTeachersEntity {


    @Id
    @ManyToOne
    @JoinColumn(name = "teacherId")
    private TeacherEntity teacher;

    @Column(name = "registered_date")
    private LocalDate date;


}
