package edu.icet.crm.service;

import edu.icet.crm.model.LoginUser;
import edu.icet.crm.model.Student;
import edu.icet.crm.util.ResponseMessage;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentService {
    void registerStudent(Student student);

    Student searchStudentById(String studentId);

    List<Student> getAllStudents();

    String generateStudentId();

    void deleteStudent(String id);

    void updateStudent(Student student);

    ResponseEntity<ResponseMessage> authenticateStudentLogin(LoginUser loginUser);
}
