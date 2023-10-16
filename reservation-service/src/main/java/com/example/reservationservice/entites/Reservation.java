package com.example.reservationservice.entites;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The type Reservation.
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

