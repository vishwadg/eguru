package com.example.commonmodule.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Tutor requirement dto.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TutorRequirementDTO {
    private String id;
    private String title;
    private String description;
    private Long studentUserId;
    private String postedDate;
}
