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
    @Column(name = "note_id")
    private String id;
    @Column(name = "note_title")
    private String title;
    @Id
    @ManyToOne
    @JoinColumn(name = "teacherId")
    private TeacherEntity teacher;
}
