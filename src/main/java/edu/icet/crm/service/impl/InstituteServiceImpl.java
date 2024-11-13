package edu.icet.crm.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.crm.entity.InstituteEntity;
import edu.icet.crm.entity.RegisteredStudentsEntity;
import edu.icet.crm.entity.RegisteredTeachersEntity;
import edu.icet.crm.model.Institute;
import edu.icet.crm.model.RegisteredStudents;
import edu.icet.crm.model.RegisteredTeachers;
import edu.icet.crm.repository.InstituteRepository;
import edu.icet.crm.repository.custom.NativeRepository;
import edu.icet.crm.repository.TeacherRepository;
import edu.icet.crm.service.EmailService;
import edu.icet.crm.service.InstituteService;
import edu.icet.crm.util.validation.InstituteValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InstituteServiceImpl implements InstituteService {
    private final ObjectMapper mapper;
    private final InstituteRepository instituteRepository;
    private final EmailService emailService;
    private final TeacherRepository teacherRepository;
    private final NativeRepository NativeRepository;
    InstituteValidationUtil instituteValidator = InstituteValidationUtil.getInstance();
    @Override
    public void addTeacher(RegisteredTeachers regTeachers) {
        teacherRepository.findById(regTeachers.getTeacherId()).orElseThrow(() -> new RuntimeException("Teacher not found"));
        InstituteEntity institute = instituteRepository.findByid(regTeachers.getInstituteId());
        RegisteredTeachersEntity registeredTeacherEntity = mapper.convertValue(regTeachers, RegisteredTeachersEntity.class);
        institute.getRegisteredTeachers().add(registeredTeacherEntity);
        instituteRepository.save(institute);
    }

    @Override
    public void addStudent(RegisteredStudents regStudents) {
        InstituteEntity institute = instituteRepository.findByid(regStudents.getInstituteId());
        institute.getRegisteredStudents().add(mapper.convertValue(regStudents, RegisteredStudentsEntity.class));
        instituteRepository.save(institute);
    }

    @Override
    public String generateInstituteId() {
        List<Institute> allInstitute = getAllInstitute();
        allInstitute.sort((ins1,ins2)->{
            int id1 = Integer.parseInt(ins1.getId().split("I")[1]);
            int id2 = Integer.parseInt(ins2.getId().split("I")[1]);
            return Integer.compare(id1,id2);
        });
        int idNum=allInstitute.isEmpty() ? 1 : Integer.parseInt(allInstitute.get(allInstitute.size() - 1).getId().split("I")[1])+1;
        return "I"+idNum;
    }



    @Override
    public void registerInstitutes(Institute institute) {
        String id = generateInstituteId();
        institute.setId(id);
        InstituteEntity instituteEntity = mapper.convertValue(institute, InstituteEntity.class);
        if (Boolean.TRUE.equals(instituteValidator.validateInstitute(instituteEntity))){
            try {
                instituteRepository.save(instituteEntity);
                emailService.sendInstituteRegistrationSuccessful(instituteEntity.getEmail(),"Registration Successful",id,instituteEntity.getName());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Institute getInstituteById(String id) {
        return mapper.convertValue(instituteRepository.findById(id),Institute.class);
    }

    @Override
    public void deleteInstitute(String id) {
        instituteRepository.deleteById(id);
    }

    @Override
    public void updateInstitute(Institute institute) {
        instituteRepository.save(mapper.convertValue(institute,InstituteEntity.class));
    }

    @Override
    public List<Institute> getAllInstitute() {
        List<InstituteEntity> allInstituteEntities = instituteRepository.findAll();
        List<Institute> instituteList=new ArrayList<>();
        for (InstituteEntity institute : allInstituteEntities) {
            instituteList.add(mapper.convertValue(institute,Institute.class));
        }
        return instituteList;
    }

    @Override
    @Transactional
    public void removeStudentFromInstitute(String instituteId, String studentId) {
        NativeRepository.removeStudentFromInstitute(instituteId,studentId);
    }

    @Override
    public void removeTeacherFromInstitute(String instituteId, String teacherId) {
        NativeRepository.removeTeacherFromInstitute(instituteId,teacherId);
    }
}

