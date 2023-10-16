package com.example.tutorrequirementservice.controllers;

import com.example.commonmodule.DTOs.TutorRequirementDTO;
import com.example.tutorrequirementservice.services.TutorRequirementService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/tutor-requirements")
@RequiredArgsConstructor
public class TutorRequirementController {

    private final TutorRequirementService tutorRequirementService;

    //@PostMapping
    //This annotation is used to map HTTP POST requests onto specific handler methods.
    @PostMapping
    //This annotation is used to check if the user has the role of ROLE_STUDENT before allowing the request to be processed.
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    //This method is used to save a TutorRequirementDTO object.
    public TutorRequirementDTO save(@RequestBody TutorRequirementDTO payload){
        return tutorRequirementService.save(payload);
    }

    //@GetMapping("/students")
    //This annotation is used to map HTTP GET requests onto specific handler methods.
    @GetMapping("/students")
    //This annotation is used to check if the user has the role of ROLE_STUDENT before allowing the request to be processed.
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    //This method is used to find all TutorRequirementDTO objects associated with a student user ID.
    public List<TutorRequirementDTO> findAllByStudentUserId(){
        return tutorRequirementService.findAllByStudentUserId();
    }

    //@GetMapping
    //This annotation is used to map HTTP GET requests onto specific handler methods.
    @GetMapping
    //This method is used to find all TutorRequirementDTO objects.
    public List<TutorRequirementDTO> findAll(){
        return tutorRequirementService.findAll();
    }

    //@GetMapping("/{id}")
    //This annotation is used to map HTTP GET requests onto specific handler methods.
    @GetMapping("/{id}")
    //This annotation is used to check if the user has the role of ROLE_STUDENT before allowing the request to be processed.
    public TutorRequirementDTO findOne(@PathVariable String id){
        return tutorRequirementService.findOne(id);
    }

}