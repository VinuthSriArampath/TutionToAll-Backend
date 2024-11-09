package edu.icet.crm.service;

import edu.icet.crm.model.Course;
import edu.icet.crm.model.StudentRegisteredCourses;
import jakarta.validation.Valid;

import java.util.List;

public interface CourseService {
    String generateCourseId();

    String addCourse(String instituteId, Course course);

    void updateCourse(String instituteId, @Valid Course course);

    List<Course> getAllCourses();

    List<Course> getAllCourses(String instituteId);

    Course getCourseById(String courseId);

    void deleteFromInstitute(String instituteId, String courseId);

    void addStudent(StudentRegisteredCourses studentRegisteredCourses);

    void addTeacher(String courseId,String teacherId);

    Course getCourseByIdInInstitute(String courseId, String instituteId);

    void updateTeacher(String courseId, String teacherId);
}
