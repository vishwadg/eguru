package com.example.authenticationservice.controller;


import com.example.authenticationservice.entities.DTOs.TokenResponse;
import com.example.commonmodule.DTOs.UserDTO;
import com.example.commonsmodule.DTOs.UserDTO;
import com.example.authenticationservice.entities.DTOs.UserLoginDTO;
import com.example.authenticationservice.services.AuthenticationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/users")
public class AuthenticationController {
    // Inject the AuthenticationService for handling user authentication and registration
    private final AuthenticationService authenticationService;

    // Define a REST endpoint for user login
    @PostMapping
    public TokenResponse login(@RequestBody UserLoginDTO payload) throws JsonProcessingException {
        // Call the AuthenticationService to perform user login and generate a token response
        return authenticationService.login(payload);
    }

    // Define a REST endpoint for user registration
    @PostMapping("/register")
    public UserDTO register(@RequestBody UserDTO payload) {
        // Call the AuthenticationService to register a new user and return user details
        return authenticationService.register(payload);
    }
}
