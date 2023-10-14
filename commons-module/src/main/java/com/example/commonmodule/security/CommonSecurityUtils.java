package com.example.commonmodule.security;

import com.example.commonmodule.security.enums.UserRole;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * The CommonSecurityUtils class provides utility methods for handling security-related operations.
 * It includes methods for retrieving the current user's ID, user details, and converting role strings to user roles.
 */
public class CommonSecurityUtils {

    /**
     * Get the current user's ID, if available.
     *
     * @return An optional containing the current user's ID, or an empty optional if not authenticated.
     */
    public static Optional<Long> getCurrentUserId() {
        return Optional.ofNullable(getCurrentUserDetails()).map(ud -> ud.getId());
    }

    /**
     * Get the current user's details, if available.
     *
     * @return The user details of the currently authenticated user, or null if not authenticated.
     */
    public static OurUserDetails getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof OurUserDetails)
                return (OurUserDetails) authentication.getPrincipal();
        }
        return null;
    }

    /**
     * Convert a set of role strings to a set of user roles.
     *
     * @param roleString A set of role strings to convert.
     * @return A set of user roles corresponding to the input role strings.
     */
    public static Set<UserRole> stringSetToUserRoleSet(Set<String> roleString) {
        Set<UserRole> userRoles = new HashSet<>();
        for (String r : roleString) userRoles.add(Enum.valueOf(UserRole.class, r));
        return userRoles;
    }
}


