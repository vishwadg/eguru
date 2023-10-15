package com.example.tutorrequirementservice.services;

import com.example.commonmodule.DTOs.TutorRequirementDTO;
import com.example.commonmodule.security.CommonSecurityUtils;
import com.example.tutorrequirementservice.entity.TutorRequirement;
import com.example.tutorrequirementservice.repositories.TutorRequirementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TutorRequirementService {

    // Get the topic name from the properties file
    @Value("${spring.kafka.custom.tutor_requirement_topic}")
    private String tutorRequirementTopic;
    @Value("${spring.kafka.custom.notification-topic}")
    private String notificationTopic;
    // Create a KafkaTemplate object to send messages to the Kafka topic
    private final KafkaTemplate<String, TutorRequirementDTO> kafkaTutorRequirementTemplate;

    // Get the TutorRequirementRepository and ModelMapper from the TutorRequirementService
    private final TutorRequirementRepository tutorRequirementRepository;
    private final ModelMapper modelMapper;


    // Save a TutorRequirementDTO object to the database
    public TutorRequirementDTO save(TutorRequirementDTO payload) {
        // Get the current user's id
        Optional<Long> studentUserId = CommonSecurityUtils.getCurrentUserId();
        // Set the studentUserId in the TutorRequirementDTO object
        payload.setStudentUserId(studentUserId.get());
        // Set the posted date to the current date
        payload.setPostedDate(LocalDate.now().toString());
        // Map the TutorRequirementDTO object to a TutorRequirement object
        TutorRequirement tutorRequirement = modelMapper.map(payload, TutorRequirement.class);
        // Save the TutorRequirement object to the database
        tutorRequirement = tutorRequirementRepository.save(tutorRequirement);
        log.info("Tutor Requirements created Successfully!");
        // Map the TutorRequirement object to a TutorRequirementDTO object
        TutorRequirementDTO tutorRequirementDTO = modelMapper.map(tutorRequirement, TutorRequirementDTO.class);

        // Send the TutorRequirementDTO object to the Kafka topic
        kafkaTutorRequirementTemplate.send(tutorRequirementTopic, tutorRequirementDTO);
        // Return the TutorRequirementDTO object
        return tutorRequirementDTO;
    }

    // Find all TutorRequirementDTO objects by the student user id
    public List<TutorRequirementDTO> findAllByStudentUserId() {
        // Get the current user's id
        Long userId = CommonSecurityUtils.getCurrentUserId().get();
        // Return a list of TutorRequirementDTO objects by the student user id
        return tutorRequirementRepository.findAllByStudentUserId(userId)
                .stream().map(tr -> modelMapper.map(tr, TutorRequirementDTO.class))
                .collect(Collectors.toList());
    }

    // Find all TutorRequirementDTO objects
    public List<TutorRequirementDTO> findAll() {
        // Return a list of TutorRequirementDTO objects
        return tutorRequirementRepository.findAll()
                .stream().map(tr -> modelMapper.map(tr, TutorRequirementDTO.class))
                .collect(Collectors.toList());
    }

    // Find a single TutorRequirementDTO object by its id
    public TutorRequirementDTO findOne(String id) {
        // Return a single TutorRequirementDTO object
        return modelMapper.map(tutorRequirementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invalid ID!")), TutorRequirementDTO.class);
    }
}