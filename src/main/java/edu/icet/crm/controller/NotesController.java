package edu.icet.crm.controller;
import edu.icet.crm.model.Note;
import edu.icet.crm.service.NotesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/notes")
public class NotesController {
    private final NotesService notesService;

    @GetMapping("/all")
    public List<Note> getAllAssignments(){
        return notesService.getAllNotes();
    }
    @GetMapping("/all/byCourseId/{courseId}")
    public List<Note> getAllAssignmentsByCourseId(@PathVariable String courseId){
        return  notesService.getAllNotesByCourseId(courseId);
    }
    @GetMapping("/getDocumentByNoteId/{noteId}")
    public ResponseEntity<byte[]> getDocumentByNoteId(@PathVariable String noteId){
        return  notesService.getDocumentByNoteId(noteId);
    }

    @PostMapping("/add")
    public void addNoteToCourse(@RequestPart("note") Note note, @RequestPart("document") MultipartFile file) throws IOException {
        notesService.addNoteToCourse(note,file);
    }
}
