package com.example.tutorservice.services.impl;

import com.example.commonmodule.DTOs.TutorDTO;
import com.example.tutorservice.entities.Tutor;
import com.example.tutorservice.repositories.TutorRepository;
import com.example.tutorservice.services.TutorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

/**
 * The TutorServiceImpl class provides the implementation of the TutorService interface.
 * It handles tutor-related operations, such as signing up a tutor, finding a tutor by ID, and listing all tutors.
 */
@Service
@Slf4j
public class TutorServiceImpl implements TutorService {

    @Value("${spring.kafka.custom.tutor-signup-email-topic}")
    private String tutorSignupEmailTopic;

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private KafkaTemplate<String, TutorDTO> kafkaTutorTemplate;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Sign up a new tutor with the provided information.
     *
     * @param tutorDTO The data transfer object representing the tutor's details.
     * @return A DTO representing the newly signed-up tutor.
     */
    @Override
    public TutorDTO signupTutor(TutorDTO tutorDTO) {
        Tutor tutor = modelMapper.map(tutorDTO, Tutor.class);
        Tutor tutorRepo = tutorRepository.save(tutor);
        log.info("Success: Tutor data saved");
        tutorDTO.setTutorId(tutorRepo.getTutorId());
        kafkaTutorTemplate.send(tutorSignupEmailTopic, tutorDTO);
        log.info("Tutor Signup email request sent on Kafka queue. {}", tutorDTO);
        return tutorDTO;
    }

    /**
     * Find and retrieve a tutor by their unique ID.
     *
     * @param id The ID of the tutor to find.
     * @return A DTO representing the tutor with the specified ID.
     */
    @Override
    public TutorDTO findById(String id) {
        Optional<Tutor> tutorOptional = tutorRepository.findById(id);
        Tutor tutor = tutorOptional.orElseThrow();

        if (tutor == null) {
            log.error("Failure: Tutor not found with id {}", id);
            throw new RuntimeException("Sorry, tutor not found in the system");
        }

        TutorDTO tutorDTO = modelMapper.map(tutor, TutorDTO.class);
        log.info("Success: Tutor found with id {}", id);
        return tutorDTO;
    }

    /**
     * Retrieve a list of all tutors available in the system.
     *
     * @return A list of DTOs representing all tutors.
     */
    @Override
    public List<TutorDTO> findAll() {
        List<Tutor> tutorList = tutorRepository.findAll();
        if (tutorList.isEmpty()) {
            log.info("Failure: Tutors are not found in the system");
            throw new RuntimeException("Sorry, tutors are not found in the system");
        }

        List<TutorDTO> tutorDTOList = tutorList.stream().map(
                tutor -> modelMapper.map(tutor, TutorDTO.class)
        ).toList();
        log.info("Success: Tutors list found");
        return tutorDTOList;
    }
}

