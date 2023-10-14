package com.example.tutorservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The Tutor class represents a tutor entity with attributes such as expertise, short information, and user ID.
 * It is used to store tutor-related information in the 'Tutors' collection of the MongoDB database.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "Tutors")
public class Tutor {
    @Id
    private String tutorId;
    private String expertise;
    private String shortInfo;
    private Long userId;
}

