package edu.icet.crm.service.impl;

import edu.icet.crm.model.Course;
import edu.icet.crm.model.Institute;
import edu.icet.crm.repository.CourseRepository;
import edu.icet.crm.service.CourseService;
import edu.icet.crm.service.InstituteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final InstituteService instituteService;

    @Override
    public void addCourse(String instituteId, Course course) {
        Institute institute = instituteService.getInstituteById(instituteId);
        institute.getCourseList().add(course);
        instituteService.updateInstitute(institute);
    }
    @Override
    public List<Course> getAllCourses(String instituteId) {
        return instituteService.getInstituteById(instituteId).getCourseList();
    }
    @Override
    public void deleteInstitute(String instituteId,String courseId) {
        Institute institute = instituteService.getInstituteById(instituteId);
        List<Course> courseList = institute.getCourseList();
        courseList.removeIf(course -> course.getId().equals(courseId));
        instituteService.updateInstitute(institute);
        courseRepository.delete(courseRepository.getReferenceById(courseId));
    }
}
