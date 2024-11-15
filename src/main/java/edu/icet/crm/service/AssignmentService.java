package edu.icet.crm.service;

import edu.icet.crm.model.Assignment;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AssignmentService {
    void addAssignment(Assignment assignment, MultipartFile file) throws IOException;
    String generateAssignmentId();
    List<Assignment> allAssignment();
}
