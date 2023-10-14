package com.example.authenticationservice.entities.enums;

public enum UserRole {
    // This enum defines user roles with associated values
    ROLE_USER("ROLE_USER"), // Represents a regular user role
    ROLE_ADMIN("ROLE_ADMIN");// Represents a admin user role
    private String value;// the value associated with each user role

    // Constructor to set the value for each role
    UserRole(String value) {
        this.value = value;
    }

    // Getter method to retrieve the value of a user role
    public String getValue() {
        return value;
    }
}
