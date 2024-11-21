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

    // ? GET parameters

    @GetMapping("/all")// -! Get All NOTES
    public List<Note> getAllAssignments(){
        return notesService.getAllNotes();
    }
    @GetMapping("/all/byCourseId/{courseId}")// -! Get All NOTES by Course id
    public List<Note> getAllAssignmentsByCourseId(@PathVariable String courseId){
        return  notesService.getAllNotesByCourseId(courseId);
    }
    @GetMapping("/getDocumentByNoteId/{noteId}")// -! Get the Document by NOTE id
    public ResponseEntity<byte[]> getDocumentByNoteId(@PathVariable String noteId){
        return  notesService.getDocumentByNoteId(noteId);
    }

    // ? POST parameters

    @PostMapping("/add")// -! Add an NOTE to the course
    public void addNoteToCourse(@RequestPart("note") Note note, @RequestPart("document") MultipartFile file) throws IOException {
        notesService.addNoteToCourse(note,file);
    }
}
