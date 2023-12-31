package com.example.commonmodule.security;

import com.example.commonmodule.security.enums.UserRole;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * The type Common security utils.
 */
public class CommonSecurityUtils {

    /**
     * Get current user id optional.
     *
     * @return the optional
     */
    public static Optional<Long> getCurrentUserId(){
        return Optional.ofNullable(getCurrentUserDetails()).map(ud -> ud.getId());
    }

    /**
     * Get current user details our user details.
     *
     * @return the our user details
     */
    public static OurUserDetails getCurrentUserDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null){
            if(authentication.getPrincipal() instanceof OurUserDetails)
                return (OurUserDetails) authentication.getPrincipal();
        }
        return null;
    }

    /**
     * String set to user role set set.
     *
     * @param roleString the role string
     * @return the set
     */
    public static Set<UserRole> stringSetToUserRoleSet(Set<String> roleString){
        Set<UserRole> userRoles = new HashSet<>();
        for(String r: roleString) userRoles.add(Enum.valueOf(UserRole.class, r));
        return userRoles;
    }
}
