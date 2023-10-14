package com.example.commonmodule.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The TutorRequirementDTO class represents a data transfer object for tutor requirement-related information.
 * It includes fields such as ID, title, description, student user ID, and posted date.
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
