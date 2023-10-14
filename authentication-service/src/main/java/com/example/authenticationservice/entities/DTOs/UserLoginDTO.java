package com.example.authenticationservice.entities.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {
    // This class represents a Data Transfer Object (DTO) for user login credentials.
    private String username;// username for authentication for User
    private String password;// password for authentication for User
}
