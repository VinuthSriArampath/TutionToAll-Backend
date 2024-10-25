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
    private String instituteId;
    private String name;
    private String email;
    private String contact;
    private String address;

    @OneToMany(mappedBy = "institute")
    private List<RegisteredStudentsEntity> studentList;
}
