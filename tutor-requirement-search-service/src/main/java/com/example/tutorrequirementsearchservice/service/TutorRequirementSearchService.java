package com.example.tutorrequirementsearchservice.service;


import com.example.commonsmodule.DTOs.TutorRequirementDTO;

import java.util.List;

public interface TutorRequirementSearchService {
    //Save a TutorRequirementDTO object
    TutorRequirementDTO save(TutorRequirementDTO tutorRequirementDTO);

    //Find all TutorRequirementDTO objects by tutorRequirement
    List<TutorRequirementDTO> findAllByTutorRequirement(String tutorRequirement);

    //Find a single TutorRequirementDTO object by id
    TutorRequirementDTO findOne(String id);

    //Update a TutorRequirementDTO object
    TutorRequirementDTO update(TutorRequirementDTO tutorRequirementDTO);

    //Delete a TutorRequirementDTO object by id
    void deleteById(String id);

}