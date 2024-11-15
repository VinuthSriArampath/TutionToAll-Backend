package edu.icet.crm.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.crm.entity.AssignmentEntity;
import edu.icet.crm.model.Assignment;
import edu.icet.crm.repository.AssignmentRepository;
import edu.icet.crm.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AssignmentServiceImpl implements AssignmentService {
    private final AssignmentRepository assignmentRepository;
    private final ObjectMapper mapper;

    @Override
    public void addAssignment(Assignment assignment, MultipartFile file) throws IOException {
        AssignmentEntity assignmentEntity = mapper.convertValue(assignment, AssignmentEntity.class);

        String uploadDir = System.getProperty("user.dir") + "/assignments";

        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        long currentTimeMillis = System.currentTimeMillis();
        String id = generateAssignmentId();
        String filename = currentTimeMillis + "_" + id + fileExtension;

        Path filePath = uploadPath.resolve(filename);

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        assignmentEntity.setId(id);
        assignmentEntity.setPath(filename);
        assignmentRepository.save(assignmentEntity);
    }

    @Override
    public List<Assignment> allAssignment() {
        List<Assignment> assignmentList = new ArrayList<>();
        assignmentRepository.findAll().forEach(
                assignmentEntity -> assignmentList.add(mapper.convertValue(assignmentEntity, Assignment.class))
        );
        return assignmentList;
    }

    @Override
    public String generateAssignmentId() {
        List<Assignment> assignmentList = allAssignment();
        int idNum = assignmentList.isEmpty() ? 1 : Integer.parseInt(assignmentList.get(assignmentList.size() - 1).getId().split("Asi")[1]) + 1;
        return "Asi" + idNum;
    }
}

