package com.example.reservationservice.controllers;

import com.example.commonmodule.DTOs.ReservationDTO;
import com.example.reservationservice.services.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * The type Reservation controller.
 */
@Slf4j
@RestController
@RequestMapping("/reservation")
@CrossOrigin
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    /**
     * Reserve response entity.
     *
     * @param reservationDTO the reservation dto
     * @return the response entity
     */
    @PostMapping
    @PreAuthorize("hasRole('ROLE_TUTOR')")
    public ResponseEntity<?> reserve(@RequestBody ReservationDTO reservationDTO) {
        return new ResponseEntity<>(reservationService.save(reservationDTO), HttpStatus.OK);
    }

    /**
     * Update reservation status response entity.
     *
     * @param reservationDTO the reservation dto
     * @return the response entity
     */
    @PutMapping
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public ResponseEntity<?> updateReservationStatus(@RequestBody ReservationDTO reservationDTO) {
        return new ResponseEntity<>(reservationService.updateReservationStatus(reservationDTO), HttpStatus.OK);
    }

    /**
     * Find reservation by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> findReservationById(@PathVariable String id) {
        return new ResponseEntity<>(reservationService.findById(id), HttpStatus.OK);
    }


    /**
     * Find all reservation by tutor requirement id response entity.
     *
     * @param tutorRequirement the tutor requirement
     * @return the response entity
     */
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @GetMapping("/tutorRequirementId/{tutorRequirement}")
    public ResponseEntity<?> findAllReservationByTutorRequirementId(@PathVariable String tutorRequirement) {
        return new ResponseEntity<>(reservationService.findAllByTutorRequirementId(tutorRequirement), HttpStatus.OK);
    }

    /**
     * Find all reservations by tutor id response entity.
     *
     * @return the response entity
     */
    @GetMapping("/all-by-tutor")
    public ResponseEntity<?> findAllReservationsByTutorId(){
        return new ResponseEntity<>(reservationService.findAllReservationByTutorUserId(), HttpStatus.OK);
    }

    /**
     * Find all reservations requests by student user id response entity.
     *
     * @return the response entity
     */
    @GetMapping("/all-requests")
    public ResponseEntity<?> findAllReservationsRequestsByStudentUserId(){
        return new ResponseEntity<>(reservationService.findAllReservationRequestByStudentUserId(), HttpStatus.OK);
    }
}
