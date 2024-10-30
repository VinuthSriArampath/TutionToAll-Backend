package edu.icet.crm.repository;

import edu.icet.crm.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface CourseRepository extends JpaRepository<CourseEntity,String> {
}
