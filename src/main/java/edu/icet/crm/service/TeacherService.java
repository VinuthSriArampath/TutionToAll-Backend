package edu.icet.crm.service;

import edu.icet.crm.model.Teacher;

import java.util.List;

public interface TeacherService {
    void registerTeacher(Teacher teacher);

    Teacher searchTeacherById(String teacherId);

    List<Teacher> getAllTeachers();

    String generateTeacherId();

    void deleteTeacher(String teacherId);

    void updateTeacher(Teacher teacher);
}
