package com.example.tutorservice.controllers;

import com.example.commonmodule.DTOs.TutorDTO;
import com.example.tutorservice.services.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The TutorController class provides RESTful API endpoints for managing tutor-related operations.
 * It handles requests to retrieve tutor information by ID and list all tutors.
 */
@RestController
@CrossOrigin
@RequestMapping("/tutors")
public class TutorController {
    @Autowired
    TutorService tutorService;

    /**
     * Retrieve tutor information by their unique ID.
     *
     * @param id The ID of the tutor to retrieve.
     * @return A response entity containing the tutor information.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getTutorById(@PathVariable String id) {
        return new ResponseEntity<>(tutorService.findById(id), HttpStatus.OK);
    }

    /**
     * Retrieve a list of all tutors available in the system.
     *
     * @return A response entity containing the list of tutors.
     */
    @GetMapping
    public ResponseEntity<?> getAllTutor() {
        return new ResponseEntity<>(tutorService.findAll(), HttpStatus.OK);
    }

    /**
     * Fallback method to handle tutor service failures. It attempts to sign up the tutor again.
     *
     * @param tutorDTO  The data transfer object representing the tutor's details.
     * @param throwable The exception thrown by the tutor service.
     * @return A response entity containing the signed-up tutor information.
     */
    private TutorDTO tutorServiceFallback(TutorDTO tutorDTO, Throwable throwable) {
        return tutorService.signupTutor(tutorDTO);
    }
}
