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
    public void unregisterStudentsFromCourse(String courseId) {
        String jpql = "DELETE FROM StudentRegisteredCoursesEntity WHERE courseId = :courseId";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("courseId", courseId);
        query.executeUpdate();
    }

    @Override
    @Transactional
    public void removeStudentFromInstitute(String instituteId, String studentId) {
        String jpql = "DELETE FROM RegisteredStudentsEntity WHERE instituteId = :instituteId AND studentId = :studentId";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("instituteId", instituteId);
        query.setParameter("studentId", studentId);
        query.executeUpdate();
    }

    @Override
    @Transactional
    public void removeTeacherFromInstitute(String instituteId, String teacherId) {
        String jpql = "DELETE FROM RegisteredTeachersEntity WHERE instituteId = :instituteId AND teacherId = :teacherId";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("instituteId", instituteId);
        query.setParameter("teacherId", teacherId);
        query.executeUpdate();
    }
}
