package edu.icet.crm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Course {
    private String id;
    private String name;
    private String type;
    private String teacherId;
    private String teacherName;
    private List<StudentRegisteredCourses> studentCoursesList;
    private List<Assignment> assignmentList;
    private List<Note> noteList;
}
