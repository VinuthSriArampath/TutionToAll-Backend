package edu.icet.crm.service;

import edu.icet.crm.model.Student;
import jakarta.validation.Valid;

public interface StudentService {
    void registerStudent(@Valid Student student);
}
