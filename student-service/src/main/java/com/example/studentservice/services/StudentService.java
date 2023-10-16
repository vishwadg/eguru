package com.example.studentservice.services;


import com.example.commonmodule.DTOs.StudentDTO;

import java.util.List;

public interface StudentService {
    //Save a studentDTO object
    StudentDTO save(StudentDTO payload);

    //Retrieve all studentDTO objects
    List<StudentDTO> findAll();

    //Retrieve a single studentDTO object
    StudentDTO findOne(String id);
}