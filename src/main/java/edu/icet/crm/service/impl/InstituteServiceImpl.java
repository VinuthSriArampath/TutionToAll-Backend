package edu.icet.crm.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.crm.entity.InstituteEntity;
import edu.icet.crm.model.Institute;
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


    private final ObjectMapper objectmapper;
    private final InstituteRepository instituteRepository;
    @Override
    public void registerInstitutes(Institute institute) {
        institute.setId(generateInstituteId());
        InstituteEntity instituteEntity = objectmapper.convertValue(institute, InstituteEntity.class);
        if (Boolean.TRUE.equals(instituteValidator.validateInstitute(instituteEntity))){
            instituteRepository.save(instituteEntity);
        }
    }

    @Override
    public Institute getInstituteById(String id) {
        InstituteEntity byId = instituteRepository.findByid(id);
        return objectmapper.convertValue(byId,Institute.class);
    }

    @Override
    public void deleteInstitute(String id) {
        instituteRepository.deleteById(id);
    }

    @Override
    public void updateInstitute(Institute institute) {
        instituteRepository.save(objectmapper.convertValue(institute,InstituteEntity.class));
    }

    @Override
    public List<Institute> getAllInstitute() {
        List<InstituteEntity> allInstituteEntities = instituteRepository.findAll();
        List<Institute> instituteList=new ArrayList<>();
        for (InstituteEntity institute : allInstituteEntities) {
            instituteList.add(objectmapper.convertValue(institute,Institute.class));
        }
        return instituteList;
    }

}

