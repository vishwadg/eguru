package com.example.reservationservice.entites;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * The Reservation class represents a reservation entity in the tutoring application.
 * It is mapped to the "Reservation" collection in the MongoDB database.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Reservation")
public class Reservation {
    @Id
    private String reservationId;
    private Long tutorUserId;
    private Long studentUserId;
    private String tutorRequirementId;
    private String reservationDate;
    private boolean reservationStatus;
    private String tutorRequirementTitle;
    private String tutorRequirementDesc;
}

