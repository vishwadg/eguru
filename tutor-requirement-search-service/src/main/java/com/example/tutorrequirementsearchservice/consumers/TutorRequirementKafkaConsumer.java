package com.example.tutorrequirementsearchservice.consumers;

import com.example.tutorrequirementsearchservice.service.TutorRequirementSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TutorRequirementKafkaConsumer {

    public final TutorRequirementSearchService tutorRequirementSearchService;

    // Kafka Consumer to consume Tutor Requirement DTO from Kafka topic
    @KafkaListener(topics = {"${spring.kafka.custom.tutor-requirement-topic}"}, containerFactory = "kafkaListenerJsonFactory",
        groupId = "${spring.kafka.consumer.group-id}", autoStartup = "${spring.kafka.custom.enable-listeners}")
    public void consumeTutorRequirementDTO(TutorRequirementDTO tutorRequirementDTO){
        log.info("Received TutorRequirementDTO {}", tutorRequirementDTO);
        // Save Tutor Requirement DTO to database
        tutorRequirementDTO = tutorRequirementSearchService.save(tutorRequirementDTO);
        log.info("Successfully Saved Tutor Requirement {}", tutorRequirementDTO);
    }

}