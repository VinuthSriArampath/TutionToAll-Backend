package edu.icet.crm.repository;

import edu.icet.crm.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


@Service
public interface StudentRepository extends JpaRepository<StudentEntity,String> {
    StudentEntity findByid(String id);
}
