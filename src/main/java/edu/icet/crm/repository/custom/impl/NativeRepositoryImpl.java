package edu.icet.crm.repository.custom.impl;

import edu.icet.crm.repository.custom.NativeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class NativeRepositoryImpl implements NativeRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void removeCourseFromRegisteredStudents(String courseId) {
        String sql = "DELETE FROM StudentRegisteredCoursesEntity WHERE courseId = :courseId";
        Query query = entityManager.createQuery(sql);
        query.setParameter("courseId", courseId);
        query.executeUpdate();
    }

    @Override
    @Transactional
    public void removeStudentFromInstitute(String instituteId, String studentId) {
        String sql = "DELETE FROM RegisteredStudentsEntity WHERE instituteId = :instituteId AND studentId = :studentId";
        Query query = entityManager.createQuery(sql);
        query.setParameter("instituteId", instituteId);
        query.setParameter("studentId", studentId);
        query.executeUpdate();
    }

    @Override
    @Transactional
    public void removeTeacherFromInstitute(String instituteId, String teacherId) {
        String sql = "DELETE FROM RegisteredTeachersEntity WHERE instituteId = :instituteId AND teacherId = :teacherId";
        Query query = entityManager.createQuery(sql);
        query.setParameter("instituteId", instituteId);
        query.setParameter("teacherId", teacherId);
        query.executeUpdate();
    }

    @Override
    @Transactional
    public void removeStudentFromCourse(String courseId, String studentId) {
        String sql = "DELETE FROM StudentRegisteredCoursesEntity WHERE courseId = :courseId AND studentId = :studentId";
        Query query = entityManager.createQuery(sql);
        query.setParameter("courseId", courseId);
        query.setParameter("studentId", studentId);
        query.executeUpdate();
    }
}
