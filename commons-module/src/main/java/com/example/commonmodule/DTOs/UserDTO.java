package com.example.commonmodule.DTOs;

import com.example.commonmodule.security.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type User dto.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private UserRole role;

    private String fullName;
    private String address;
    private String phone;
    private String description;

    private String expertise;
    private String shortInfo;
}
