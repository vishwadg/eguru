package com.example.authenticationservice.services;

import com.example.authenticationservice.entities.DTOs.TokenResponse;
import com.example.authenticationservice.entities.DTOs.UserLoginDTO;
import com.example.commonmodule.DTOs.UserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface AuthenticationService {
    TokenResponse login(UserLoginDTO payload) throws JsonProcessingException;

    UserDTO register(UserDTO payload);

    UserDTO getUserByUserId(Long id);

    UserDTO sendReservationApprovedEmailToTutor(Long tutorUserId);
}
