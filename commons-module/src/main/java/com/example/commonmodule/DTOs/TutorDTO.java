package com.example.commonmodule.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Tutor dto.
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
