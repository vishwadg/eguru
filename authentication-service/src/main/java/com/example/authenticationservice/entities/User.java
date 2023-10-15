package com.example.authenticationservice.entities;

import com.example.commonmodule.security.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    // entity representing a user in the application's database
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private String fullName;
    private String phone;
    private String address;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<UserRole> roles = new HashSet<>();
    // User's roles, stored as a set of UserRole enums, defining their permissions
}
