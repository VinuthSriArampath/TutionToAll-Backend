package edu.icet.crm.repository.custom;
import org.springframework.transaction.annotation.Transactional;


public interface NativeRepository{


    @Transactional
    void unregisterStudentsFromCourse(String courseId);

    @Transactional
    void removeStudentFromInstitute(String instituteId, String studentId);

    @Transactional
    void removeTeacherFromInstitute(String instituteId, String teacherId);

}
