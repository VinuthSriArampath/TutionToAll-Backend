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
public class Assignment {
    private String id;
    private String courseId;
    private String assignmentName;
    private LocalDate dueDate;
    private String path;
    private List<StudentAssignmentSubmission> submittedList;
}
