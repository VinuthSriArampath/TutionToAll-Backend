package edu.icet.crm.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.crm.entity.InstituteEntity;
import edu.icet.crm.entity.RegisteredStudentsEntity;
import edu.icet.crm.entity.RegisteredTeachersEntity;
import edu.icet.crm.model.Institute;
import edu.icet.crm.model.RegisteredStudents;
import edu.icet.crm.model.RegisteredTeachers;
import edu.icet.crm.repository.InstituteRepository;
import edu.icet.crm.service.InstituteService;
import edu.icet.crm.util.validation.InstituteValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InstituteServiceImpl implements InstituteService {
    @Override
    public void addTeacher(RegisteredTeachers regTeachers) {
        InstituteEntity institute = instituteRepository.findByid(regTeachers.getInstituteId());
        institute.getRegisteredTeachers().add(mapper.convertValue(regTeachers, RegisteredTeachersEntity.class));
        instituteRepository.save(institute);
    }

    @Override
    public void addStudent(RegisteredStudents regStudents) {
        InstituteEntity institute = instituteRepository.findByid(regStudents.getInstituteId());
        institute.getRegisteredStudents().add(mapper.convertValue(regStudents, RegisteredStudentsEntity.class));
        instituteRepository.save(institute);
    }
    private final ObjectMapper mapper;
    private final InstituteRepository instituteRepository;
    InstituteValidationUtil instituteValidator = InstituteValidationUtil.getInstance();

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
        institute.setId(generateInstituteId());
        InstituteEntity instituteEntity = mapper.convertValue(institute, InstituteEntity.class);
        if (Boolean.TRUE.equals(instituteValidator.validateInstitute(instituteEntity))){
            instituteRepository.save(instituteEntity);
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

}

