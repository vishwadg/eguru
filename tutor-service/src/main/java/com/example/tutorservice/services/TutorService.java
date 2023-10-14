package com.example.tutorservice.services;

import com.example.commonmodule.DTOs.TutorDTO;

import java.util.List;

/**
 * The TutorService interface defines the operations available for managing tutors.
 * It includes methods for signing up a tutor, finding a tutor by ID, and listing all tutors.
 */
public interface TutorService {
    /**
     * Sign up a new tutor with the provided information.
     *
     * @param tutorDTO The data transfer object representing the tutor's details.
     * @return A DTO representing the newly signed-up tutor.
     */
    TutorDTO signupTutor(TutorDTO tutorDTO);

    /**
     * Find and retrieve a tutor by their unique ID.
     *
     * @param id The ID of the tutor to find.
     * @return A DTO representing the tutor with the specified ID.
     */
    TutorDTO findById(String id);

    /**
     * Retrieve a list of all tutors available in the system.
     *
     * @return A list of DTOs representing all tutors.
     */
    List<TutorDTO> findAll();
}

