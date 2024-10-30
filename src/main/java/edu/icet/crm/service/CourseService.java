package edu.icet.crm.service;

import edu.icet.crm.entity.CourseEntity;
import edu.icet.crm.model.Course;

import java.util.List;

public interface CourseService {
    void addCourse(String instituteId, Course course);

    List<Course> getAllCourses(String instituteId);

    void deleteInstitute(String instituteId,String courseId);
}
