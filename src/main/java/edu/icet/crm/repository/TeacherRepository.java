package edu.icet.crm.repository;

import edu.icet.crm.entity.TeacherEntity;
import org.springframework.data.repository.CrudRepository;

public interface TeacherRepository extends CrudRepository<TeacherEntity,String> {
}
