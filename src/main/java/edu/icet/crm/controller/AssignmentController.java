package edu.icet.crm.controller;

import edu.icet.crm.model.Assignment;
import edu.icet.crm.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/assignment")
public class AssignmentController {
    private final AssignmentService assignmentService;

    @PostMapping("/add")
    public void addAssignment(@RequestPart("assignment") Assignment assignment, @RequestPart("document") MultipartFile file) throws IOException {
        assignmentService.addAssignment(assignment, file);
    }
}

