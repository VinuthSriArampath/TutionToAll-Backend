package edu.icet.crm.service;

import edu.icet.crm.model.LoginUser;
import edu.icet.crm.model.Teacher;
import edu.icet.crm.util.ResponseMessage;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TeacherService {
    void registerTeacher(Teacher teacher);

    Teacher searchTeacherById(String teacherId);

    List<Teacher> getAllTeachers();

    String generateTeacherId();

    void deleteTeacher(String teacherId);

    void updateTeacher(Teacher teacher);

    ResponseEntity<ResponseMessage> authenticateTeacherLogin(LoginUser loginUser);

    void updateTeacherPassword(String teacherId, String password);
}
