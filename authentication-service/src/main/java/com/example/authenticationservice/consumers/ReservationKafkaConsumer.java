package com.example.authenticationservice.consumers;

import com.example.authenticationservice.services.AuthenticationService;
import com.example.commonmodule.DTOs.ReservationDTO;
import com.example.commonmodule.DTOs.UserDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationKafkaConsumer {
    // Inject the AuthenticationService for handling user-related operations
    public final AuthenticationService authenticationService;

    // Define a Kafka listener that consumes ReservationDTO messages from a specific topic
    @KafkaListener(topics = {"${spring.kafka.custom.reservation-topic}"}, //Kafka topic to listen to
            containerFactory = "kafkaListenerJsonFactory", // Kafka listener container factory
            groupId = "${spring.kafka.consumer.group-id}", // Consumer group ID
            autoStartup = "${spring.kafka.custom.enable-listeners}" // Auto-start the listener based on configuration
    )


    public void consumeReservationDTO(ReservationDTO reservationDTO) {
        log.info("Received ReservationDTO {}", reservationDTO);

        // Fetch the UserDTO associated with the student's ID from the reservationDTO
        UserDTO userDTO = authenticationService.getUserByUserId(reservationDTO.getStudentUserId());
        log.info("Successfully fetched UserDTO {}", userDTO);
    }

    // Define another Kafka listener for a different topic, consuming approved ReservationDTO messages
    @KafkaListener(topics = {"${spring.kafka.custom.reservation-approved-topic}"},// Kafka topic for approved reservations
            containerFactory = "kafkaListenerJsonFactory",// Kafka Listener container factory
            groupId = "${spring.kafka.consumer.group-id}", //Consumer group ID
            autoStartup = "${spring.kafka.custom.enable-listeners}"// Auto-start the listener based on configuration
    )
    public void consumeReservationApprovedTopic(ReservationDTO reservationDTO) {
        log.info("Received ReservationDTO which was approved {}", reservationDTO);

        // Email the tutor associated with the reservation and fetch the UserDTO
        UserDTO userDTO = authenticationService.sendReservationApprovedEmailToTutor(reservationDTO.getTutorUserId());
        log.info("Successfully fetched UserDTO {}", userDTO);
    }
}
