package com.example.tutorrequirementsearchservice.controller;

//import statements for lombok, spring, and java
import com.example.tutorrequirementsearchservice.service.TutorRequirementSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//annotations for rest controller, slf4j, and cross origin
@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/search")
public class SearchController {
    //autowiring of service
    @Autowired
    TutorRequirementSearchService tutorRequirementSearchService;

    //get request mapping for all tutor requirements
    @GetMapping
    public ResponseEntity<?> findAllByTutorRequirement(@RequestParam(value = "tutorRequirement", required = false) String tutorRequirement) {
        log.info("SearchController: find all by tutor requirement");
        //returns a response entity of all tutor requirements with the given tutor requirement
        return new ResponseEntity<>(tutorRequirementSearchService.findAllByTutorRequirement(tutorRequirement), HttpStatus.OK);
    }

    //get request mapping for one tutor requirement by id
    @GetMapping("/{id}")
    public ResponseEntity<?> findByTutorRequirementId(@PathVariable String id) {
        log.info("SearchController: find by tutor requirement id ");
        //returns a response entity of the tutor requirement with the given id
        return new ResponseEntity<>(tutorRequirementSearchService.findOne(id), HttpStatus.OK);
    }

}