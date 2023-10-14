package com.example.tutorservice.consumers;

import com.example.commonmodule.DTOs.TutorDTO;
import com.example.commonmodule.DTOs.UserDTO;
import com.example.tutorservice.services.TutorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * The TutorKafkaConsumer class listens to Kafka messages related to tutor sign-up and processes them.
 * It consumes messages from the specified Kafka topic and triggers the tutor sign-up process.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TutorKafkaConsumer {

    public final TutorService tutorService;

    /**
     * Listens to the specified Kafka topic for tutor sign-up messages and processes them.
     *
     * @param userDTO The data transfer object representing tutor sign-up information.
     */
    @KafkaListener(topics = {"${spring.kafka.custom.tutor-signup-topic}"}, containerFactory = "kafkaListenerJsonFactory",
            groupId = "${spring.kafka.consumer.group-id}", autoStartup = "${spring.kafka.custom.enable-listeners}")
    public void consumeTutorSignUpDTO(UserDTO userDTO) {
        log.info("Received Tutor SignUp {}", userDTO);
        TutorDTO tutorDTO = new TutorDTO();
        tutorDTO.setShortInfo(userDTO.getShortInfo());
        tutorDTO.setExpertise(userDTO.getExpertise());
        tutorDTO.setUserId(userDTO.getId());
        tutorDTO = tutorService.signupTutor(tutorDTO);
        log.info("Successfully Saved Tutor {}", tutorDTO);
    }
}
