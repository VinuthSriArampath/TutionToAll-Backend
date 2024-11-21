package edu.icet.crm.controller;

import edu.icet.crm.model.Assignment;
import edu.icet.crm.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/assignment")
public class AssignmentController {
    private final AssignmentService assignmentService;

    // ? GET parameters

    @GetMapping("/all") // -! Get All Assignments
    public List<Assignment> getAllAssignments(){
        return assignmentService.allAssignment();
    }

    @GetMapping("/all/byCourseId/{courseId}")// -! Get All Assignments by Course id
    public List<Assignment> getAllAssignmentsByCourseId(@PathVariable String courseId){
        return  assignmentService.getAllAssignmentsByCourseId(courseId);
    }

    @GetMapping("/getDocumentByAssignmentId/{assignmentId}")// -! Get the Document by assignment id
    public ResponseEntity<byte[]> getDocumentByAssignmentId(@PathVariable String assignmentId) throws IOException {
        return assignmentService.getDocumentByAssignmentId(assignmentId);
    }

    // ? POST parameters

    @PostMapping("/add")// -! Add an assignment to the course
    public void addAssignment(@RequestPart("assignment") Assignment assignment, @RequestPart("document") MultipartFile file) throws IOException {
        assignmentService.addAssignment(assignment, file);
    }
}

