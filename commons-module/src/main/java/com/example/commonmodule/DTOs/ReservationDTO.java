package com.example.commonmodule.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The ReservationDTO class represents a data transfer object for reservation-related information.
 * It includes fields such as reservation ID, tutor user ID, tutor requirement ID, reservation date,
 * reservation status, student user ID, tutor requirement title, and tutor requirement description.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
    private String reservationId;
    private Long tutorUserId;
    private String tutorRequirementId;
    private String reservationDate;
    private boolean reservationStatus;
    private Long studentUserId;
    private String tutorRequirementTitle;
    private String tutorRequirementDesc;
}

