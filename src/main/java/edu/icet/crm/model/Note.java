package edu.icet.crm.model;

import edu.icet.crm.entity.TeacherEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Note {
    private String noteId;
    private String title;
    private Teacher teacher;
}
