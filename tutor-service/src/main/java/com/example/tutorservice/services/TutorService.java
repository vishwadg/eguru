package com.example.tutorservice.services;

import com.example.commonmodule.DTOs.TutorDTO;

import java.util.List;

/**
 * The interface Tutor service.
 */
public interface TutorService {
    /**
     * Signup tutor tutor dto.
     *
     * @param tutorDTO the tutor dto
     * @return the tutor dto
     */
    TutorDTO signupTutor(TutorDTO tutorDTO);

    /**
     * Find by id tutor dto.
     *
     * @param id the id
     * @return the tutor dto
     */
    TutorDTO findById(String id);

    /**
     * Find all list.
     *
     * @return the list
     */
    List<TutorDTO> findAll();
}
