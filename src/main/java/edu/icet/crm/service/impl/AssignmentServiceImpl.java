package edu.icet.crm.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.crm.entity.AssignmentEntity;
import edu.icet.crm.model.Assignment;
import edu.icet.crm.repository.AssignmentRepository;
import edu.icet.crm.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        assignmentEntity.setPath(filePath.toString());
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
    public List<Assignment> getAllAssignmentsByCourseId(String courseId) {
        List<Assignment> allAssignmentListCourseId = new ArrayList<>();
        allAssignment().forEach(assignment -> {
            if (assignment.getCourseId().equals(courseId)) allAssignmentListCourseId.add(assignment);
        });
        return allAssignmentListCourseId;
    }

    @Override
    public String generateAssignmentId() {
        List<Assignment> assignmentList = allAssignment();
        assignmentList.sort((as1, as2)->{
            int id1 = Integer.parseInt(as1.getId().split("Asi")[1]);
            int id2 = Integer.parseInt(as2.getId().split("Asi")[1]);
            return Integer.compare(id1,id2);
        });
        int idNum = assignmentList.isEmpty() ? 1 : Integer.parseInt(assignmentList.get(assignmentList.size() - 1).getId().split("Asi")[1]) + 1;
        return "Asi" + idNum;
    }

    @Override
    public ResponseEntity<byte[]> getDocumentByAssignmentId(String assignmentId) {

        Optional<AssignmentEntity> optionalAssignmentEntity = assignmentRepository.findById(assignmentId);
        if (optionalAssignmentEntity.isPresent()) {
            AssignmentEntity assignmentEntity = optionalAssignmentEntity.get();
            Path filePath = Paths.get(assignmentEntity.getPath());

            try {
                byte[] content = Files.readAllBytes(filePath);

                String contentType = Files.probeContentType(filePath);
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.parseMediaType(contentType));
                headers.setContentDispositionFormData("attachment", filePath.getFileName().toString());

                return new ResponseEntity<>(content, headers, HttpStatus.OK);

            } catch (IOException e) {
                throw new RuntimeException("Failed to read file from path: " + filePath, e);
            }
        }
        throw new RuntimeException("Assignment with ID " + assignmentId + " not found.");
    }

}

