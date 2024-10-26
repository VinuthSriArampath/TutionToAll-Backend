package edu.icet.crm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "note")
public class NoteEntity {
    @Id
    private String noteId;
    private String title;
    @Id
    @ManyToOne
    @JoinColumn(name = "teacherId")
    private TeacherEntity teacher;
}
