package edu.icet.crm.service;

import edu.icet.crm.model.Institute;
import edu.icet.crm.model.LoginUser;
import edu.icet.crm.model.RegisteredStudents;
import edu.icet.crm.model.RegisteredTeachers;
import edu.icet.crm.util.ResponseMessage;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InstituteService {
    void registerInstitutes(Institute institute);

    Institute getInstituteById(String id);

    void deleteInstitute(String id);

    void updateInstitute(Institute institute);

    List<Institute> getAllInstitute();

    String generateInstituteId();

    void addStudent(RegisteredStudents regStudents);

    void addTeacher(RegisteredTeachers regTeachers);

    void removeStudentFromInstitute(String instituteId, String studentId);

    void removeTeacherFromInstitute(String instituteId, String teacherId);

    ResponseEntity<ResponseMessage> authenticateInstituteLogin(LoginUser loginUser);

    void updateInstitutePassword(String instituteId, String password);
}
