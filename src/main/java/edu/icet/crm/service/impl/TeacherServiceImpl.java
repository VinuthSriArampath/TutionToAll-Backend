package edu.icet.crm.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.crm.entity.TeacherEntity;
import edu.icet.crm.model.LoginUser;
import edu.icet.crm.model.Teacher;
import edu.icet.crm.repository.TeacherRepository;
import edu.icet.crm.service.EmailService;
import edu.icet.crm.service.TeacherService;
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
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final ObjectMapper mapper;
    private final Encryptor encryptor;
    private final EmailService emailService;

    @Override
    public String generateTeacherId() {
        List<Teacher> allTeachers = getAllTeachers();
        allTeachers.sort((tch1, tch2)->{
            int id1 = Integer.parseInt(tch1.getId().split("T")[1]);
            int id2 = Integer.parseInt(tch2.getId().split("T")[1]);
            return Integer.compare(id1,id2);
        });
        int idNum=allTeachers.isEmpty() ? 1 : Integer.parseInt(allTeachers.get(allTeachers.size()-1).getId().split("T")[1])+1;
        return "T"+idNum;
    }

    @Override
    public List<Teacher> getAllTeachers() {
        List<Teacher> allTeachers = new ArrayList<>();
        teacherRepository.findAll().forEach(teacherEntity -> allTeachers.add(mapper.convertValue(teacherEntity, Teacher.class)));
        return allTeachers;
    }

    @Override
    public Teacher searchTeacherById(String teacherId) {
        return mapper.convertValue(teacherRepository.findById(teacherId), Teacher.class);
    }

    @Override
    public void registerTeacher(Teacher teacher) {
        teacher.setId(generateTeacherId());
        try {
            teacher.setPassword(encryptor.encryptString(teacher.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        teacherRepository.save(mapper.convertValue(teacher, TeacherEntity.class));
        emailService.sentTeacherRegistrationSuccessful(teacher.getEmail(),"Successfully Registered To TuitionToAll",teacher.getId());
    }


    @Override
    public void updateTeacher(Teacher teacher) {
        TeacherEntity teacherEntity = mapper.convertValue(teacherRepository.findById(teacher.getId()),TeacherEntity.class);
        teacherEntity.setFirstName(teacher.getFirstName());
        teacherEntity.setLastName(teacher.getLastName());
        teacherEntity.setContact(teacher.getContact());
        teacherEntity.setDob(teacher.getDob());
        teacherEntity.setEmail(teacher.getEmail());
        teacherEntity.setAddress(teacher.getAddress());
        try {
            teacherEntity.setPassword(encryptor.encryptString(teacher.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        teacherRepository.save(teacherEntity);
    }

    @Override
    public void deleteTeacher(String teacherId) {
        teacherRepository.deleteById(teacherId);
    }

    @Override
    public ResponseEntity<ResponseMessage> authenticateTeacherLogin(LoginUser loginUser) {
        Teacher teacher = searchTeacherById(loginUser.getUserName());
        if (teacher == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseMessage("Invalid UserName"));
        }
        try {
            if (encryptor.encryptString(loginUser.getPassword()).equals(teacher.getPassword())) {
                return ResponseEntity.ok(new ResponseMessage("Login Successful"));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseMessage("Invalid Password"));
            }
        } catch (NoSuchAlgorithmException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("Backend Server Has An Error"));
        }
    }
}
