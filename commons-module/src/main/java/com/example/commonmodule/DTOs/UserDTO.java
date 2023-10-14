package com.example.commonmodule.DTOs;

import com.example.commonmodule.security.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The UserDTO class represents a data transfer object for user-related information.
 * It includes fields such as ID, username, password, role, full name, address, phone, description,
 * expertise, and short information.
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
