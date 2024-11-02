package edu.icet.crm.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.crm.model.Course;
import edu.icet.crm.model.Institute;
import edu.icet.crm.repository.CourseRepository;
import edu.icet.crm.service.CourseService;
import edu.icet.crm.service.InstituteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final InstituteService instituteService;
    private final ObjectMapper mapper;
    @Override
    public List<Course> getAllCourses() {
        List<Course> allCourseList=new ArrayList<>();
        courseRepository.findAll().forEach(courseEntity -> allCourseList.add(mapper.convertValue(courseEntity, Course.class)));
        return allCourseList;
    }

    @Override
    public String generateCourseId() {
        List<Course> allCourses = getAllCourses();
        allCourses.sort((cos1, cos2)->{
            int id1 = Integer.parseInt(cos1.getId().split("C")[1]);
            int id2 = Integer.parseInt(cos2.getId().split("C")[1]);
            return Integer.compare(id1,id2);
        });
        int idNum=allCourses.isEmpty() ? 1 : Integer.parseInt(allCourses.get(allCourses.size() - 1).getId().split("C")[1])+1;
        return "C"+idNum;
    }

    @Override
    public void updateCourse(String instituteId, Course course) {
        Institute institute = instituteService.getInstituteById(instituteId);
        List<Course> courseList = institute.getCourseList();
        courseList.forEach(courseFromList ->{
            if (courseFromList.getId().equals(course.getId())){
                courseFromList.setName(course.getName());
                courseFromList.setType(course.getType());
            }
        } );
        instituteService.updateInstitute(institute);
    }


    @Override
    public void addCourse(String instituteId, Course course) {
        Institute institute = instituteService.getInstituteById(instituteId);
        course.setId(generateCourseId());
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
