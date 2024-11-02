package edu.icet.crm.service;

import edu.icet.crm.model.Student;

import java.util.List;

public interface StudentService {
    void registerStudent(Student student);

    Student searchStudentById(String studentId);

    List<Student> getAllStudents();

    String generateStudentId();

    void deleteStudent(String id);
}
