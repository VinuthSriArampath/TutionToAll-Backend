package edu.icet.crm.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.crm.entity.CourseEntity;
import edu.icet.crm.entity.StudentRegisteredCoursesEntity;
import edu.icet.crm.entity.TeacherEntity;
import edu.icet.crm.model.*;
import edu.icet.crm.repository.CourseRepository;
import edu.icet.crm.repository.custom.NativeRepository;
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
    public void addTeacherToCourse(String courseId, String teacherId) {
        TeacherEntity teacherEntity = mapper.convertValue(teacherRepository.findById(teacherId), TeacherEntity.class);
        List<CourseEntity> registeredCourses = teacherEntity.getRegisteredCourses();
        registeredCourses.add(mapper.convertValue(courseRepository.findById(courseId), CourseEntity.class));
        teacherEntity.setRegisteredCourses(registeredCourses);
        teacherRepository.save(teacherEntity);
    }

    @Override
    public Course getCourseByIdInInstitute(String courseId, String instituteId) {
        Institute institute = instituteService.getInstituteById(instituteId);
        List<Course> courseList = institute.getCourseList();
        for (Course course:courseList){
            if (course.getId().equals(courseId)){
                return getCourseById(courseId);
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
    @Transactional
    public void deleteFromInstitute(String instituteId, String courseId) {
        courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course with id " + courseId + " not found."));

        nativeRepository.removeCourseFromRegisteredStudents(courseId);

        Institute institute = instituteService.getInstituteById(instituteId);
        institute.getCourseList().removeIf(course -> course.getId().equals(courseId));
        instituteService.updateInstitute(institute);
        courseRepository.flush();
        courseRepository.deleteById(courseId);
    }

    @Override
    public Course getCourseById(String courseId) {
        Course course = mapper.convertValue(courseRepository.findById(courseId), Course.class);
        List<Teacher> allTeachers = teacherService.getAllTeachers();
        for (Teacher teacher: allTeachers){
            List<Course> registeredCourses = teacher.getRegisteredCourses();
            for (Course course1:registeredCourses){
                if (course1.getId().equals(courseId)){
                    course.setTeacherId(teacher.getId());
                    course.setTeacherName(teacher.getFirstName()+" "+teacher.getLastName());
                    break;
                }else{
                    course.setTeacherId("No Teacher is Added To the Course");
                    course.setTeacherName("No Teacher is Added To the Course");
                }
            }
        }
        return course;
    }

    @Override
    public void updateCourseTeacher(String courseId, String teacherId) {
        TeacherEntity teacherEntity = mapper.convertValue(teacherRepository.findById(teacherId), TeacherEntity.class);
        teacherEntity.getRegisteredCourses().add(mapper.convertValue(courseRepository.findById(courseId), CourseEntity.class));
        teacherRepository.save(teacherEntity);
    }

    @Override
    public void removeStudentFromCourse(String courseId, String studentId) {
        nativeRepository.removeStudentFromCourse(courseId,studentId);
    }
}
