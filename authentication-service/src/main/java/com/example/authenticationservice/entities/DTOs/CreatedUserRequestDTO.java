package com.example.authenticationservice.entities.DTOs;

import lombok.Data;

@Data
public class CreatedUserRequestDTO {
    // This class represents a Data Transfer Object (DTO) for creating a new user.
    private String fullName;
    private String username;
    private String password;
    private String phone;
    private String address;
}
