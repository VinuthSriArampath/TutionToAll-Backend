package edu.icet.crm.service;

import edu.icet.crm.model.Course;
import edu.icet.crm.model.StudentRegisteredCourses;
import jakarta.validation.Valid;

import java.util.List;

public interface CourseService {
    void addCourse(String instituteId, Course course);

    List<Course> getAllCourses(String instituteId);

    List<Course> getAllCourses();

    void deleteInstitute(String instituteId,String courseId);

    void updateCourse(String instituteId, @Valid Course course);

    String generateCourseId();

    void addStudent(StudentRegisteredCourses studentRegisteredCourses);

    void addTeacher(String courseId,String teacherId);

    Course getCourseByIdInInstitute(String courseId, String instituteId);
}
