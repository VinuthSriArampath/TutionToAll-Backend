package edu.icet.crm.repository;

import edu.icet.crm.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface NativeRepository extends JpaRepository<CourseEntity,String> {
    @Modifying
    @Transactional
    @Query("DELETE FROM StudentRegisteredCoursesEntity s WHERE s.courseId = :courseId")
    void unregisterStudentsFromCourse(@Param("courseId") String courseId);
}
