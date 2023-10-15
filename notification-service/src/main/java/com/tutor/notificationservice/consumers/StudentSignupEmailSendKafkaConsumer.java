package com.tutor.notificationservice.consumers;

import com.example.commonmodule.DTOs.UserDTO;
import com.tutor.notificationservice.entity.Email;
import com.tutor.notificationservice.service.TutorEmailNotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentSignupEmailSendKafkaConsumer {
    private final TutorEmailNotificationService tutorEmailNotificationService;

    @KafkaListener(topics = {"${spring.kafka.custom.student-signup-email-topic}"}, containerFactory = "kafkaListenerJsonFactory",
            groupId = "${spring.kafka.consumer.group-id}", autoStartup = "${spring.kafka.custom.enable-listeners}")
    public void consumeStudentSignUpEmailDTO(UserDTO userDTO) {
        log.info("Received UserDTO {}", userDTO);

        Email email = new Email();
        email.setTo(userDTO.getUsername());
        email.setSubject("Welcome to Tutor Finder");
        email.setText("Hi " + userDTO.getFullName() + "\n\n" + "You have successfully registered to the Tutor Finder");

        tutorEmailNotificationService.sendEmail(email);
        log.info("Successfully sent email to Tutor {}", email);
    }
}
