package edu.icet.crm.service;

import edu.icet.crm.model.Note;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface NotesService {
    void addNoteToCourse(Note note, MultipartFile file) throws IOException;
    String generateNoteId();
    List<Note> getAllNotes();

    List<Note> getAllNotesByCourseId(String courseId);
}
