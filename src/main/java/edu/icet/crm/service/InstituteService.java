package edu.icet.crm.service;

import edu.icet.crm.model.Institute;
import edu.icet.crm.model.RegisteredStudents;
import jakarta.validation.Valid;

import java.util.List;

public interface InstituteService {
    void registerInstitutes(@Valid Institute institute);

    Institute getInstituteById(String id);

    void deleteInstitute(String id);

    void updateInstitute(@Valid Institute institute);

    List<Institute> getAllInstitute();

    String generateInstituteId();

    void addStudent(RegisteredStudents regStudents);
}
