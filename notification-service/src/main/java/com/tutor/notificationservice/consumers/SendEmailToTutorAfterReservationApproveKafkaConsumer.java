package com.tutor.notificationservice.consumers;

import com.example.commonmodule.DTOs.UserDTO;
import com.tutor.notificationservice.entity.Email;
import com.tutor.notificationservice.service.TutorEmailNotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service // Annotates this class as a Spring service.
@Slf4j // Enables the usage of logging with SLF4J.
@AllArgsConstructor // Generates a constructor with all fields as arguments.
public class SendEmailToTutorAfterReservationApproveKafkaConsumer {

    // Injects (DI) an instance of the TutorEmailNotificationService.
    private final TutorEmailNotificationService tutorEmailNotificationService;

    // Listens to a Kafka topic and processes messages from it.
    @KafkaListener(topics = {"${spring.kafka.custom.reservation-approved-email-topic}"},
            containerFactory = "kafkaListenerJsonFactory",
            groupId = "${spring.kafka.consumer.group-id}",
            autoStartup = "${spring.kafka.custom.enable-listeners}")
    // The method that handles messages received from Kafka.
    public void consumeSendEmailToTutorAfterReservationApproveDTO(UserDTO userDTO) {
        log.info("Received TutorDTO {}", userDTO);

        // Email object to set the email properties like subject, content.
        Email email = new Email();
        email.setTo(userDTO.getUsername());
        email.setSubject("Tutor Finder: Reservation Approved");
        email.setText("Hi " + userDTO.getFullName() + "\n\n" + "Your reservation has been approved.");

        // Use the TutorEmailNotificationService to send the email.
        tutorEmailNotificationService.sendEmail(email);
        log.info("Successfully sent email to Tutor {}", email);
    }
}
