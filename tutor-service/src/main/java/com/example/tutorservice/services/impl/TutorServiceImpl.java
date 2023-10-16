package com.example.tutorservice.services.impl;

import com.example.commonmodule.DTOs.TutorDTO;
import com.example.tutorservice.entities.Tutor;
import com.example.tutorservice.repositories.TutorRepository;
import com.example.tutorservice.services.TutorService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The type Tutor service.
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

    @Override
    public TutorDTO signupTutor(TutorDTO tutorDTO) {
        Tutor tutor = modelMapper.map(tutorDTO, Tutor.class);
        Tutor tutorRepo = tutorRepository.save(tutor);
        log.info("Success: Tutor data saved");
        tutorDTO.setTutorId(tutorRepo.getTutorId());
        kafkaTutorTemplate.send(tutorSignupEmailTopic, tutorDTO);
        log.info("Tutor Signup email request sent on kafka queue. {}", tutorDTO);
        return tutorDTO;
    }

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

    @Override
    public List<TutorDTO> findAll() {
        List<Tutor> tutorList = tutorRepository.findAll();
        if (tutorList.isEmpty()) {
            log.info("Failure: Tutors are not found in the system");
            throw new RuntimeException("Sorry, tutors are not found in the system");
        }

        List<TutorDTO> tutorDTOList = tutorList.stream().map(
                tutor -> {
                    TutorDTO tutorDTO = modelMapper.map(tutor, TutorDTO.class);
                    return tutorDTO;
                }).toList();
        log.info("Success: Tutors list found");
        return tutorDTOList;
    }
}
