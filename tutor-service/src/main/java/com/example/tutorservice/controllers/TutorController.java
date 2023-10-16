package com.example.tutorservice.controllers;

import com.example.commonmodule.DTOs.TutorDTO;
import com.example.tutorservice.services.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The type Tutor controller.
 */
@RestController
@CrossOrigin
@RequestMapping("/tutors")
public class TutorController {
    /**
     * The Tutor service.
     */
    @Autowired
    TutorService tutorService;

    /**
     * Signup tutor response entity.
     *
     * @param tutorDTO the tutor dto
     * @return the response entity
     */
    @PostMapping
    ResponseEntity<?> signupTutor(@RequestBody TutorDTO tutorDTO) {
        return new ResponseEntity<>(tutorService.signupTutor(tutorDTO), HttpStatus.OK);
    }

    /**
     * Gets tutor by id.
     *
     * @param id the id
     * @return the tutor by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getTutorById(@PathVariable String id) {
        return new ResponseEntity<>(tutorService.findById(id), HttpStatus.OK);
    }

    /**
     * Gets all tutor.
     *
     * @return the all tutor
     */
    @GetMapping
    public ResponseEntity<?> getAllTutor() {
        return new ResponseEntity<>(tutorService.findAll(), HttpStatus.OK);
    }
}
