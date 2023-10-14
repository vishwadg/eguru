package com.example.commonmodule.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The TutorDTO class represents a data transfer object for tutor-related information.
 * It includes fields such as tutor ID, expertise, short information, and user ID.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TutorDTO {
    private String tutorId;
    private String expertise;
    private String shortInfo;
    private Long userId;
}

