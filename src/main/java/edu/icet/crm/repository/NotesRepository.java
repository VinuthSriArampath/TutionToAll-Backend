package edu.icet.crm.repository;

import edu.icet.crm.entity.NoteEntity;
import org.springframework.data.repository.CrudRepository;

public interface NotesRepository extends CrudRepository<NoteEntity,String> {
}
