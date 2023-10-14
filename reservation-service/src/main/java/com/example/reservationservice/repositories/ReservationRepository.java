package com.example.reservationservice.repositories;

import com.example.reservationservice.entites.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The ReservationRepository interface provides access to the MongoDB data store for reservation entities.
 * It extends the MongoRepository interface and defines additional methods for querying and managing reservations.
 */
@Repository
public interface ReservationRepository extends MongoRepository<Reservation, String> {

    /**
     * Retrieves a list of reservations associated with a specific tutor requirement or subject.
     *
     * @param tutorRequirementId The ID of the tutor requirement or subject to filter reservations.
     * @return A list of Reservation objects matching the specified tutor requirement.
     */
    List<Reservation> findReservationsByTutorRequirementId(String tutorRequirementId);

    /**
     * Retrieves all reservations made by a tutor user.
     *
     * @param userId The ID of the tutor user to filter reservations.
     * @return A list of Reservation objects made by the specified tutor user.
     */
    List<Reservation> findAllByTutorUserId(Long userId);

    /**
     * Retrieves all reservation requests made by a student user.
     *
     * @param userId The ID of the student user to filter reservation requests.
     * @return A list of Reservation objects representing reservation requests made by the specified student user.
     */
    List<Reservation> findAllByStudentUserId(Long userId);
}

