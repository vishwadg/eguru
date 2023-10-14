package com.example.reservationservice.services;

import com.example.commonmodule.DTOs.ReservationDTO;

import java.util.List;

/**
 * The ReservationService interface defines a set of methods for managing reservations within a tutoring application.
 * Reservations are bookings made by students for tutoring sessions with tutors.
 * This interface provides a contract for handling reservation-related operations.
 */
public interface ReservationService {

    /**
     * Saves a new reservation to the system, creating a record of the booking.
     *
     * @param reservationDTO The ReservationDTO object containing reservation information to be saved.
     * @return The ReservationDTO object representing the saved reservation.
     */
    ReservationDTO save(ReservationDTO reservationDTO);

    /**
     * Retrieves a list of reservations associated with a specific tutor requirement or subject.
     * This can be useful for tutors to see all their bookings for a particular subject.
     *
     * @param tutorRequirement The ID of the tutor requirement or subject to filter reservations.
     * @return A list of ReservationDTO objects matching the specified tutor requirement.
     */
    List<ReservationDTO> findAllByTutorRequirementId(String tutorRequirement);

    /**
     * Fetches a specific reservation by its unique identifier, allowing for detailed information retrieval or updates.
     *
     * @param id The unique identifier of the reservation to be retrieved.
     * @return The ReservationDTO object representing the found reservation.
     */
    ReservationDTO findById(String id);

    /**
     * Updates the status of a reservation, such as confirming or canceling it.
     * This function is essential for managing the booking lifecycle.
     *
     * @param reservationDTO The ReservationDTO object containing updated reservation status.
     * @return The ReservationDTO object representing the updated reservation.
     */
    ReservationDTO updateReservationStatus(ReservationDTO reservationDTO);

    /**
     * Retrieves all reservations made by a tutor user, helping tutors keep track of their appointments.
     *
     * @return A list of ReservationDTO objects representing reservations made by the tutor user.
     */
    List<ReservationDTO> findAllReservationByTutorUserId();

    /**
     * Fetches all reservation requests made by a student user, enabling students to view their pending or confirmed bookings.
     *
     * @return A list of ReservationDTO objects representing reservation requests made by the student user.
     */
    List<ReservationDTO> findAllReservationRequestByStudentUserId();
}

