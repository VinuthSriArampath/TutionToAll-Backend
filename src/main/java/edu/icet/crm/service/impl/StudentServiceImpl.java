package edu.icet.crm.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.crm.entity.StudentEntity;
import edu.icet.crm.entity.StudentRegisteredCoursesEntity;
import edu.icet.crm.model.*;
import edu.icet.crm.repository.StudentRepository;
import edu.icet.crm.service.InstituteService;
import edu.icet.crm.service.StudentService;
import edu.icet.crm.util.Encryptor;
import edu.icet.crm.util.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final InstituteService instituteService;
    private final ObjectMapper mapper;
    private final Encryptor encryptor;

    @Override
    public void updateStudent(Student student) {
        StudentEntity studentEntity = studentRepository.findByid(student.getId());
        studentEntity.setFirstName(student.getFirstName());
        studentEntity.setLastName(student.getLastName());
        studentEntity.setContact(student.getContact());
        studentEntity.setDob(student.getDob());
        studentEntity.setEmail(student.getEmail());
        studentEntity.setAddress(student.getAddress());
        try {
            studentEntity.setPassword(encryptor.encryptString(student.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        studentRepository.save(studentEntity);
    }

    @Override
    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
    }

    @Override
    public String generateStudentId() {
        List<Student> allStudents = getAllStudents();
        allStudents.sort((stu1, stu2) -> {
            int id1 = Integer.parseInt(stu1.getId().split("S")[1]);
            int id2 = Integer.parseInt(stu2.getId().split("S")[1]);
            return Integer.compare(id1, id2);
        });
        int idNum = allStudents.isEmpty() ? 1 : Integer.parseInt(allStudents.get(allStudents.size() - 1).getId().split("S")[1]) + 1;
        return "S" + idNum;
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> allStudents = new ArrayList<>();
        studentRepository.findAll().forEach(studentEntity -> allStudents.add(mapper.convertValue(studentEntity, Student.class)));
        return allStudents;
    }

    @Override
    public void registerStudent(Student student) {
        student.setId(generateStudentId());
        try {
            student.setPassword(encryptor.encryptString(student.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        studentRepository.save(mapper.convertValue(student, StudentEntity.class));
    }

    @Override
    public Student searchStudentById(String studentId) {
        Student student = mapper.convertValue(studentRepository.findById(studentId), Student.class);
        StudentEntity studentEntity = mapper.convertValue(studentRepository.findById(studentId), StudentEntity.class);
        List<RegisteredStudents> registeredInstitutes = student.getRegisteredInstitutes();
        registeredInstitutes.forEach(institute -> {
            Institute institute1 = instituteService.getInstituteById(institute.getInstituteId());
            List<Course> courseList = institute1.getCourseList();
            List<StudentRegisteredCoursesEntity> registeredCourses = studentEntity.getRegisteredCourses();
            List<StudentRegisteredCourses> courses = new ArrayList<>();
            courseList.forEach(course -> registeredCourses.forEach(regCourse -> {
                if (course.getId().equals(regCourse.getCourseId())) {
                    courses.add(new StudentRegisteredCourses(regCourse.getStudentId(), regCourse.getCourseId(), regCourse.getDate()));
                }
            }));
            institute.setCourses(courses);
            institute.setInstituteName(institute1.getName());
        });
        return student;
    }

    @Override
    public ResponseEntity<ResponseMessage> authenticateStudentLogin(LoginUser loginUser) {
        Student student = searchStudentById(loginUser.getUserName());
        if (student == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseMessage("Invalid UserName"));
        }
        try {
            if (encryptor.encryptString(loginUser.getPassword()).equals(student.getPassword())) {
                return ResponseEntity.ok(new ResponseMessage("Login Successful"));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseMessage("Invalid Password"));
            }
        } catch (NoSuchAlgorithmException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("Backend Server Has An Error"));
        }
    }
}