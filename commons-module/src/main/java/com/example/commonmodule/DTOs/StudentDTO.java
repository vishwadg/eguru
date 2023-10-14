package com.example.commonmodule.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The StudentDTO class represents a data transfer object for student-related information.
 * It includes fields such as ID, description, and user ID.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private String id;
    private String description;
    private Long userId;
}

