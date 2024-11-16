package edu.icet.crm.repository;

import edu.icet.crm.entity.NoteEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;


public interface NotesRepository extends CrudRepository<NoteEntity,String> {
}
