package com.example.commonmodule.security;

import com.example.commonmodule.security.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * The OurUserDetails class represents user details for authentication and authorization.
 * It implements the UserDetails interface required by Spring Security.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OurUserDetails implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private Set<UserRole> userRoles;

    /**
     * Retrieves the user's authorities, which are mapped from the user's roles.
     *
     * @return A collection of GrantedAuthority objects representing user roles.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new HashSet<>();
        for (UserRole role : this.userRoles) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.name());
            authorities.add(grantedAuthority);
        }
        return authorities;
    }

    /**
     * Retrieves the user's password for authentication.
     *
     * @return The user's password.
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * Retrieves the user's username for authentication.
     *
     * @return The user's username.
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * Checks if the user's account is not expired.
     *
     * @return `true` if the user's account is not expired, `false` otherwise.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Checks if the user's account is not locked.
     *
     * @return `true` if the user's account is not locked, `false` otherwise.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Checks if the user's credentials are not expired.
     *
     * @return `true` if the user's credentials are not expired, `false` otherwise.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Checks if the user's account is enabled.
     *
     * @return `true` if the user's account is enabled, `false` otherwise.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}

