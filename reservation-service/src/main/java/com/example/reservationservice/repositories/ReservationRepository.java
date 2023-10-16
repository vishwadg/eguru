package com.example.reservationservice.repositories;

import com.example.reservationservice.entites.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Reservation repository.
 */
@Repository
public interface ReservationRepository extends MongoRepository<Reservation, String> {
    /**
     * Find reservations by tutor requirement id list.
     *
     * @param tutorRequirementId the tutor requirement id
     * @return the list
     */
    List<Reservation> findReservationsByTutorRequirementId(String tutorRequirementId);

    /**
     * Find all by tutor user id list.
     *
     * @param userId the user id
     * @return the list
     */
    List<Reservation> findAllByTutorUserId(Long userId);

    /**
     * Find all by student user id list.
     *
     * @param userId the user id
     * @return the list
     */
    List<Reservation> findAllByStudentUserId(Long userId);
}