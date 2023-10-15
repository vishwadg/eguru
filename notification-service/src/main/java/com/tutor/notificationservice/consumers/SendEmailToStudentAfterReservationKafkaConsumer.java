package com.tutor.notificationservice.consumers;

import com.example.commonmodule.DTOs.UserDTO;
import com.tutor.notificationservice.entity.Email;
import com.tutor.notificationservice.service.TutorEmailNotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service // Annotates this class as a Spring service.
@Slf4j // Enables the usage of logging with SLF4J.
@RequiredArgsConstructor // Generates a constructor with all required fields as arguments.
public class SendEmailToStudentAfterReservationKafkaConsumer {

    // Injects (DI) an instance of the TutorEmailNotificationService.
    private final TutorEmailNotificationService tutorEmailNotificationService;

    // Listens to a Kafka topic and processes messages from it.
    @KafkaListener(topics = {"${spring.kafka.custom.student-detail-after-reservation-email-topic}"},
            containerFactory = "kafkaListenerJsonFactory",
            groupId = "${spring.kafka.consumer.group-id}",
            autoStartup = "${spring.kafka.custom.enable-listeners}")
    // The method that handles messages received from Kafka.
    public void consumeSendEmailToStudentAfterReservationDTO(UserDTO userDTO) {
        log.info("Received UserDTO {}", userDTO);

        // Email object to set the email properties like subject, content.
        Email email = new Email();
        email.setTo(userDTO.getUsername());
        email.setSubject("Tutor Finder: Tutor Requirement Reservation");
        email.setText("Hi " + userDTO.getUsername() + "\n\n" + "Your Tutor requirement has been reserved.");

        // Use the TutorEmailNotificationService to send the email.
        tutorEmailNotificationService.sendEmail(email);
        log.info("Successfully sent email to Tutor {}", email);
    }
}
