package edu.icet.crm.model;

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
public class Teacher {
    private String teacherId;
    private String fName;
    private String lName;
    private LocalDate dob;
    private String contact;
    private String email;
    private String address;
    private List<RegisteredTeachers> registeredInstitutes;
    private List<Assignment> managedAssignmentList;
    private List<Note> managedNoteList;
}
