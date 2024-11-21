package edu.icet.crm.repository;

import edu.icet.crm.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseEntity,String> {
}
