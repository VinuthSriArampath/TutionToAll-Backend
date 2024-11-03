package edu.icet.crm.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.crm.entity.StudentEntity;
import edu.icet.crm.model.Student;
import edu.icet.crm.repository.StudentRepository;
import edu.icet.crm.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {
    @Override
    public void updateStudent(Student student) {
        StudentEntity studentEntity = studentRepository.findByid(student.getId());
        studentEntity.setFirstName(student.getFirstName());
        studentEntity.setLastName(student.getLastName());
        studentEntity.setContact(student.getContact());
        studentEntity.setDob(student.getDob());
        studentEntity.setEmail(student.getEmail());
        studentEntity.setAddress(student.getAddress());
        studentRepository.save(studentEntity);
    }

    @Override
    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
    }

    private final StudentRepository studentRepository;
    private final ObjectMapper mapper;
    @Override
    public String generateStudentId() {
        List<Student> allStudents = getAllStudents();
        allStudents.sort((stu1,stu2)->{
            int id1 = Integer.parseInt(stu1.getId().split("S")[1]);
            int id2 = Integer.parseInt(stu2.getId().split("S")[1]);
            return Integer.compare(id1,id2);
        });
        int idNum=Integer.parseInt(allStudents.get(allStudents.size()-1).getId().split("S")[1])+1;
        return "S"+idNum;
    }
    @Override
    public List<Student> getAllStudents() {
        List<Student> allStudents= new ArrayList<>();
        studentRepository.findAll().forEach(studentEntity -> allStudents.add(mapper.convertValue(studentEntity, Student.class)));
        return allStudents;
    }

    @Override
    public void registerStudent(Student student) {
        student.setId(generateStudentId());
        studentRepository.save(mapper.convertValue(student, StudentEntity.class));
    }
    @Override
    public Student searchStudentById(String studentId) {
        return mapper.convertValue(studentRepository.findByid(studentId), Student.class);
    }
}
