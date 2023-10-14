package com.example.reservationservice.services.impl;

import com.example.commonmodule.DTOs.ReservationDTO;
import com.example.commonmodule.security.CommonSecurityUtils;
import com.example.reservationservice.entites.Reservation;
import com.example.reservationservice.repositories.ReservationRepository;
import com.example.reservationservice.services.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The ReservationServiceImpl class is an implementation of the ReservationService interface.
 * It provides methods for managing reservations in a tutoring application.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ModelMapper modelMapper;

    @Value("${spring.kafka.custom.reservation-topic}")
    private String reservationTopic;
    @Value("${spring.kafka.custom.reservation-approved-topic}")
    private String reservationApprovedTopic;
    private final KafkaTemplate<String, ReservationDTO> kafkaReservationTemplate;

    /**
     * Saves a new reservation to the system, creating a record of the booking.
     *
     * @param reservationDTO The ReservationDTO object containing reservation information to be saved.
     * @return The ReservationDTO object representing the saved reservation.
     */
    @Override
    public ReservationDTO save(ReservationDTO reservationDTO) {
        if (CommonSecurityUtils.getCurrentUserId().isPresent()) {
            reservationDTO.setTutorUserId(CommonSecurityUtils.getCurrentUserId().get());
        }
        log.info("Reservation process started...");
        Reservation reservation = modelMapper.map(reservationDTO, Reservation.class);
        reservation.setReservationDate(LocalDate.now().toString());

        Reservation reservationRepo = reservationRepository.save(reservation);
        reservationDTO.setReservationId(reservationRepo.getReservationId());
        reservationDTO.setTutorRequirementId(reservationRepo.getTutorRequirementId());
        reservationDTO.setTutorRequirementTitle(reservationDTO.getTutorRequirementTitle());
        reservationDTO.setTutorRequirementDesc(reservationDTO.getTutorRequirementDesc());
        reservationDTO.setReservationDate(reservationRepo.getReservationDate());
        kafkaReservationTemplate.send(reservationTopic, reservationDTO);
        log.info("Success: Reservation data saved");
        return reservationDTO;
    }

    /**
     * Retrieves a list of reservations associated with a specific tutor requirement or subject.
     * This can be useful for tutors to see all their bookings for a particular subject.
     *
     * @param tutorRequirement The ID of the tutor requirement or subject to filter reservations.
     * @return A list of ReservationDTO objects matching the specified tutor requirement.
     */
    @Override
    public List<ReservationDTO> findAllByTutorRequirementId(String tutorRequirement) {
        List<Reservation> reservationList = reservationRepository.findReservationsByTutorRequirementId(tutorRequirement);
        if (reservationList.isEmpty()) {
            log.info("Failure: Reservations are not found in the system");
            throw new RuntimeException("Sorry, reservations are not found in the system");
        }
        List<ReservationDTO> reservationDTOList = reservationList.stream()
                .map(reservation -> modelMapper.map(reservation, ReservationDTO.class))
                .collect(Collectors.toList());
        log.info("Reservation List sent for tutorRequirementId: {}", tutorRequirement);
        return reservationDTOList;
    }

    /**
     * Fetches a specific reservation by its unique identifier, allowing for detailed information retrieval or updates.
     *
     * @param id The unique identifier of the reservation to be retrieved.
     * @return The ReservationDTO object representing the found reservation.
     */
    @Override
    public ReservationDTO findById(String id) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        Reservation reservation = reservationOptional.orElseThrow(() -> {
            log.error("Failure: Reservation not found with id {}", id);
            return new RuntimeException("Sorry, reservation not found in the system");
        });
        ReservationDTO reservationDTO = modelMapper.map(reservation, ReservationDTO.class);
        log.info("Success: Reservation found with id {}", id);
        return reservationDTO;
    }

    /**
     * Updates the status of a reservation, such as confirming or canceling it.
     * This function is essential for managing the booking lifecycle.
     *
     * @param reservationDTO The ReservationDTO object containing updated reservation status.
     * @return The ReservationDTO object representing the updated reservation.
     */
    @Override
    @Transactional
    public ReservationDTO updateReservationStatus(ReservationDTO reservationDTO) {
        log.info("Reservation to be approved of ID {}", reservationDTO.getReservationId());
        Reservation reservation = reservationRepository.findById(reservationDTO.getReservationId())
                .orElseThrow(() -> new RuntimeException("Reservation not found!"));
        reservation.setReservationStatus(true);
        reservationRepository.save(reservation);
        log.info("Reservation approved of ID {}", reservationDTO.getReservationId());

        log.info("Reservation approved event to be sent of ID {}", reservationDTO.getReservationId());
        kafkaReservationTemplate.send(reservationApprovedTopic, reservationDTO);
        log.info("Reservation approved event sent of ID {}", reservationDTO.getReservationId());

        return modelMapper.map(reservation, ReservationDTO.class);
    }

    /**
     * Retrieves all reservations made by a tutor user, helping tutors keep track of their appointments.
     *
     * @return A list of ReservationDTO objects representing reservations made by the tutor user.
     */
    @Override
    public List<ReservationDTO> findAllReservationByTutorUserId() {
        Long userId = CommonSecurityUtils.getCurrentUserId().get();
        return reservationRepository.findAllByTutorUserId(userId).stream()
                .map(re -> modelMapper.map(re, ReservationDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Fetches all reservation requests made by a student user, enabling students to view their pending or confirmed bookings.
     *
     * @return A list of ReservationDTO objects representing reservation requests made by the student user.
     */
    @Override
    public List<ReservationDTO> findAllReservationRequestByStudentUserId() {
        Long userId = CommonSecurityUtils.getCurrentUserId().get();
        return reservationRepository.findAllByStudentUserId(userId).stream()
                .map(re -> modelMapper.map(re, ReservationDTO.class))
                .collect(Collectors.toList());
    }
}
