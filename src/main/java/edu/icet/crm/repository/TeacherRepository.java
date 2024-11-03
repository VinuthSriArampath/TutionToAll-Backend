package edu.icet.crm.repository;

import edu.icet.crm.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface TeacherRepository extends JpaRepository<TeacherEntity,String> {
}
