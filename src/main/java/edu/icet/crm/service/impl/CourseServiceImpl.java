package edu.icet.crm.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.crm.entity.CourseEntity;
import edu.icet.crm.entity.StudentRegisteredCoursesEntity;
import edu.icet.crm.entity.TeacherEntity;
import edu.icet.crm.model.*;
import edu.icet.crm.repository.CourseRepository;
import edu.icet.crm.repository.NativeRepository;
import edu.icet.crm.repository.TeacherRepository;
import edu.icet.crm.service.CourseService;
import edu.icet.crm.service.InstituteService;
import edu.icet.crm.service.TeacherService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final InstituteService instituteService;
    private final TeacherService teacherService;
    private final TeacherRepository teacherRepository;
    private final NativeRepository nativeRepository;
    private final ObjectMapper mapper;

    @Override
    public void addStudent(StudentRegisteredCourses studentRegisteredCourses) {
        CourseEntity courseEntity = mapper.convertValue(courseRepository.findById(studentRegisteredCourses.getCourseId()), CourseEntity.class);
        courseEntity.getStudentCoursesList().add(mapper.convertValue(studentRegisteredCourses, StudentRegisteredCoursesEntity.class));
        courseRepository.save(courseEntity);
    }

    @Override
    public void addTeacher(String courseId, String teacherId) {
        TeacherEntity teacherEntity = mapper.convertValue(teacherRepository.findById(teacherId), TeacherEntity.class);
        teacherEntity.getRegisteredCourses().add(mapper.convertValue(courseRepository.findById(courseId), CourseEntity.class));
        teacherRepository.save(teacherEntity);
    }

    @Override
    public Course getCourseByIdInInstitute(String courseId, String instituteId) {
        Institute institute = instituteService.getInstituteById(instituteId);
        List<Course> courseList = institute.getCourseList();
        for (int i = 0; i < courseList.size(); i++) {
            Course course = courseList.get(i);
            if (course.getId().equals(courseId)) {
                Teacher teacher = teacherService.searchTeacherById(course.getTeacherId());
                course.setTeacherName(teacher.getFirstName()+" "+teacher.getLastName());
                return course;
            }
        }

        return null;
    }

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
    public String addCourse(String instituteId, Course course) {
        Institute institute = instituteService.getInstituteById(instituteId);
        String id = generateCourseId();
        course.setId(id);
        institute.getCourseList().add(course);
        instituteService.updateInstitute(institute);
        return id;
    }
    @Override
    public List<Course> getAllCourses(String instituteId) {
        List<Course> courseList = instituteService.getInstituteById(instituteId).getCourseList();
        if (courseList.isEmpty()){
            return Collections.emptyList();
        }else {
            return courseList;
        }
    }
    @Override
    @Transactional // Ensures atomic operation
    public void deleteFromInstitute(String instituteId, String courseId) {
        courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course with id " + courseId + " not found."));

        nativeRepository.unregisterStudentsFromCourse(courseId);

        Institute institute = instituteService.getInstituteById(instituteId);
        institute.getCourseList().removeIf(course -> course.getId().equals(courseId));
        instituteService.updateInstitute(institute);

        courseRepository.flush();
        courseRepository.deleteById(courseId);
    }

    @Override
    public Course getCourseById(String courseId) {
        Course course = mapper.convertValue(courseRepository.findById(courseId), Course.class);
        if (course.getTeacherId()!=null){
            course.setTeacherName(teacherService.searchTeacherById(course.getTeacherId()).getFirstName()+" "+teacherService.searchTeacherById(course.getTeacherId()).getLastName());
        }else{
            course.setTeacherId("No Teacher is Added To the Course");
            course.setTeacherName("No Teacher is Added To the Course");
        }
        return course;
    }

    @Override
    public void updateTeacher(String courseId, String teacherId) {
        TeacherEntity teacherEntity = mapper.convertValue(teacherRepository.findById(teacherId), TeacherEntity.class);
        teacherEntity.getRegisteredCourses().add(mapper.convertValue(courseRepository.findById(courseId), CourseEntity.class));
        teacherRepository.save(teacherEntity);
    }
}
