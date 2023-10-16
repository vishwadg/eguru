package com.example.commonmodule.security.enums;

/**
 * The enum User role.
 */
public enum UserRole {
    /**
     * Role student user role.
     */
    ROLE_STUDENT("ROLE_STUDENT"),
    /**
     * Role tutor user role.
     */
    ROLE_TUTOR("ROLE_TUTOR");
    private String value;

    UserRole(String value) {this.value = value;}

    /**
     * Gets value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }
}