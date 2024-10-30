// CourseServiceImpl.java
package edu.icet.crm.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.crm.entity.CourseEntity;
import edu.icet.crm.entity.InstituteEntity;
import edu.icet.crm.model.Course;
import edu.icet.crm.model.Institute;
import edu.icet.crm.repository.CourseRepository;
import edu.icet.crm.service.CourseService;
import edu.icet.crm.service.InstituteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final InstituteService instituteService;
    private final CourseRepository courseRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void addCourse(String instituteId, Course course) {
        Institute institute = instituteService.getInstituteById(instituteId);
        institute.getCourseList().add(course);
        instituteService.updateInstitute(institute);
    }
}
