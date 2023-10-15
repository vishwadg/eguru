package com.example.studentservice.services.impl;

import com.example.commonmodule.DTOs.StudentDTO;
import com.example.studentservice.entities.Student;
import com.example.studentservice.repositories.StudentRepository;
import com.example.studentservice.services.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    // Get the student signup email topic from the properties file
    @Value("${spring.kafka.custom.student-signup-email-topic}")
    private String studentSignupEmailTopic;
    // Create a KafkaTemplate to send the student signup email request
    private final KafkaTemplate<String, StudentDTO> kafkaTemplate;

    @Override
    public StudentDTO save(StudentDTO payload) {
        // Create a new student entity from the payload
        Student student = studentRepository.save(modelMapper.map(payload, Student.class));
        // Log the student entity saved on the database
        log.info("Student Saved on Database. {}", student);
        // Send the student signup email request to the kafka queue
        kafkaTemplate.send(studentSignupEmailTopic, payload);
        // Log the student signup email request sent on the kafka queue
        log.info("Student Signup email request sent on kafka queue. {}", student);
        // Return the student entity as a StudentDTO
        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public List<StudentDTO> findAll() {
        // Get all the student entities from the database
        List<Student> students = studentRepository.findAll();
        // Return the student entities as a list of StudentDTO
        return students.stream().map(s -> modelMapper.map(s, StudentDTO.class)).collect(Collectors.toList());
    }

    @Override
    public StudentDTO findOne(String id) {
        // Return the student entity as a StudentDTO
        return modelMapper.map(
                // Get the student entity from the database
                studentRepository.findById(id).orElseThrow(() -> new RuntimeException("No Such Student!"))
                , StudentDTO.class
        );
    }
}