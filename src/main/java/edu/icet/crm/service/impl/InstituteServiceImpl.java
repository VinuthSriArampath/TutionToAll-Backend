package edu.icet.crm.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.crm.entity.InstituteEntity;
import edu.icet.crm.model.Institute;
import edu.icet.crm.repository.InstituteRepository;
import edu.icet.crm.service.InstituteService;
import edu.icet.crm.util.validation.InstituteValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public class InstituteServiceImpl implements InstituteService {
    InstituteValidationUtil instituteValidator = InstituteValidationUtil.getInstance();
    private final ObjectMapper objectmapper;
    private final InstituteRepository instituteRepository;
    @Override
    public void registerInstitutes(Institute institute) {
        InstituteEntity instituteEntity = objectmapper.convertValue(institute, InstituteEntity.class);
        if (Boolean.TRUE.equals(instituteValidator.validateInstitute(instituteEntity))){
            instituteRepository.save(instituteEntity);
        }
    }
    @Override
    public Institute getInstituteById(String id) {
        InstituteEntity byid = instituteRepository.findByid(id);
        return objectmapper.convertValue(byid,Institute.class);
    }
    @Override
    public void deleteInstitute(String id) {
        instituteRepository.deleteById(id);
    }
    @Override
    public void updateInstitute(Institute institute) {
        instituteRepository.save(objectmapper.convertValue(institute,InstituteEntity.class));
    }
}

