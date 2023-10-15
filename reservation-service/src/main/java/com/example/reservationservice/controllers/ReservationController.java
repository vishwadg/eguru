package com.example.reservationservice.controllers;

import com.example.commonmodule.DTOs.ReservationDTO;
import com.example.reservationservice.services.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * The ReservationController class serves as the controller for managing reservation-related endpoints.
 */
@Slf4j
@RestController
@RequestMapping("/reservation")
@CrossOrigin
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    /**
     * Creates a new reservation
     *
     * @param reservationDTO The ReservationDTO object containing reservation details.
     * @return A ResponseEntity with the saved ReservationDTO and an HTTP status of 200 (OK).
     */
    @PostMapping
    @PreAuthorize("hasRole('ROLE_TUTOR')")
    public ResponseEntity<?> reserve(@RequestBody ReservationDTO reservationDTO) {
        return new ResponseEntity<>(reservationService.save(reservationDTO), HttpStatus.OK);
    }

    /**
     * Updates the status of a reservation.
     *
     * @param reservationDTO The ReservationDTO object containing updated reservation status.
     * @return A ResponseEntity with the updated ReservationDTO and an HTTP status of 200 (OK).
     */
    @PutMapping
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public ResponseEntity<?> updateReservationStatus(@RequestBody ReservationDTO reservationDTO) {
        return new ResponseEntity<>(reservationService.updateReservationStatus(reservationDTO), HttpStatus.OK);
    }

    /**
     * Retrieves a reservation by its unique identifier.
     *
     * @param id The unique identifier of the reservation.
     * @return A ResponseEntity with the found ReservationDTO and an HTTP status of 200 (OK).
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> findReservationById(@PathVariable String id) {
        return new ResponseEntity<>(reservationService.findById(id), HttpStatus.OK);
    }

    /**
     * Retrieves a list of reservations associated with a specific tutor requirement or subject.
     *
     * @param tutorRequirement The ID of the tutor requirement or subject.
     * @return A ResponseEntity with a list of ReservationDTO objects and an HTTP status of 200 (OK).
     */
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @GetMapping("/tutorRequirementId/{tutorRequirement}")
    public ResponseEntity<?> findAllReservationByTutorRequirementId(@PathVariable String tutorRequirement) {
        return new ResponseEntity<>(reservationService.findAllByTutorRequirementId(tutorRequirement), HttpStatus.OK);
    }

    /**
     * Retrieves all reservations made by a user.
     *
     * @return A ResponseEntity with a list of ReservationDTO objects and an HTTP status of 200 (OK).
     */
    @GetMapping("/all-by-tutor")
    public ResponseEntity<?> findAllReservationsByTutorId(){
        return new ResponseEntity<>(reservationService.findAllReservationByTutorUserId(), HttpStatus.OK);
    }

    /**
     * Retrieves all reservation requests made by a user.
     *
     * @return A ResponseEntity with a list of ReservationDTO objects and an HTTP status of 200 (OK).
     */
    @GetMapping("/all-requests")
    public ResponseEntity<?> findAllReservationsRequestsByStudentUserId(){
        return new ResponseEntity<>(reservationService.findAllReservationRequestByStudentUserId(), HttpStatus.OK);
    }
}

