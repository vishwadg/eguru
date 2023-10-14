package com.example.authenticationservice.entities.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponse {
    // This class represents a Data Transfer Object (DTO) for handling authentication tokens.
    private String token;// authentication token
}
