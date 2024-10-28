package edu.icet.crm.service;

import edu.icet.crm.model.Institute;
import jakarta.validation.Valid;

public interface InstituteService {
    void registerInstitutes(@Valid Institute institute);
}
