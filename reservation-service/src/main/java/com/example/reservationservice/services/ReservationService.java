package com.example.reservationservice.services;

import com.example.commonmodule.DTOs.ReservationDTO;

import java.util.List;

/**
 * The interface Reservation service.
 */
public interface ReservationService {
    /**
     * Save reservation dto.
     *
     * @param reservationDTO the reservation dto
     * @return the reservation dto
     */
    ReservationDTO save(ReservationDTO reservationDTO);

    /**
     * Find all by tutor requirement id list.
     *
     * @param tutorRequirement the tutor requirement
     * @return the list
     */
    List<ReservationDTO> findAllByTutorRequirementId(String tutorRequirement);

    /**
     * Find by id reservation dto.
     *
     * @param id the id
     * @return the reservation dto
     */
    ReservationDTO findById(String id);

    /**
     * Update reservation status reservation dto.
     *
     * @param reservationDTO the reservation dto
     * @return the reservation dto
     */
    ReservationDTO updateReservationStatus(ReservationDTO reservationDTO);

    /**
     * Find all reservation by tutor user id list.
     *
     * @return the list
     */
    List<ReservationDTO> findAllReservationByTutorUserId();

    /**
     * Find all reservation request by student user id list.
     *
     * @return the list
     */
    List<ReservationDTO> findAllReservationRequestByStudentUserId();
}
