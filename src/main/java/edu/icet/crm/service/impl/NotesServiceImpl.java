package edu.icet.crm.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.crm.entity.NoteEntity;
import edu.icet.crm.model.Note;
import edu.icet.crm.repository.NotesRepository;
import edu.icet.crm.service.NotesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotesServiceImpl implements NotesService {
    private final NotesRepository notesRepository;
    private final ObjectMapper mapper;
    @Override
    public void addNoteToCourse(Note note, MultipartFile file) throws IOException {
        NoteEntity noteEntity = mapper.convertValue(note, NoteEntity.class);
        String uploadDir = System.getProperty("user.dir") + "/notes";

        Path uploadPath= Paths.get(uploadDir);
        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }
        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));

        long currentTimeMillis = System.currentTimeMillis();
        String id = generateNoteId();
        String filename = currentTimeMillis + "_" + id + fileExtension;

        Path filePath = uploadPath.resolve(filename);
        Files.copy(file.getInputStream(),filePath, StandardCopyOption.REPLACE_EXISTING);

        noteEntity.setId(id);
        noteEntity.setPath(filePath.toString());
        notesRepository.save(noteEntity);
    }

    @Override
    public String generateNoteId() {
        List<Note> allNotes = getAllNotes();
        int idNum=allNotes.isEmpty() ? 1 : Integer.parseInt(allNotes.get(allNotes.size()-1).getId().split("Not")[1])+1;
        return "Not"+idNum;
    }

    @Override
    public List<Note> getAllNotes() {
        List<Note> allNotesList=new ArrayList<>();
        notesRepository.findAll().forEach(noteEntity -> allNotesList.add(mapper.convertValue(noteEntity, Note.class)));
        return allNotesList;

    }

    @Override
    public List<Note> getAllNotesByCourseId(String courseId) {
        List<Note> allNOteListById = new ArrayList<>();
        getAllNotes().forEach(note -> {
            if (note.getCourseId().equals(courseId)) allNOteListById.add(note);
        });
        return allNOteListById;
    }
}
