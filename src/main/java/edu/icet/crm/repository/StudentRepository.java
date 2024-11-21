package edu.icet.crm.repository;

import edu.icet.crm.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<StudentEntity,String> {
    StudentEntity findByid(String id);
}
