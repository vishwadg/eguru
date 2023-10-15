package com.example.commonmodule.security;

import com.example.commonmodule.security.CommonSecurityUtils;
import com.example.commonmodule.security.OurUserDetails;
import com.example.commonmodule.security.enums.UserRole;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.Set;

/**
 * The JwtTokenProvider class provides methods for creating, parsing, and validating JWT tokens.
 * It is used for authentication and authorization in the application.
 */
@Slf4j
public class JwtTokenProvider {
    private static final String HEADER_AUTHORIZATION = HttpHeaders.AUTHORIZATION;
    private static final String BEARER_TOKEN_START = "Bearer ";

    private String secret;
    private Long jwtExpiry;

    /**
     * Constructs a JwtTokenProvider with a secret key and token validity duration.
     *
     * @param secretKey         The secret key for signing the JWT.
     * @param validityInSeconds The duration of token validity in seconds.
     */
    public JwtTokenProvider(String secretKey, long validityInSeconds) {
        this.secret = Base64.getEncoder().encodeToString(secretKey.getBytes());
        this.jwtExpiry = validityInSeconds;
    }

    /**
     * Creates a JWT token based on the user's authentication information.
     *
     * @param authentication The user's authentication details.
     * @return A JWT token as a string.
     * @throws JsonProcessingException If there's an issue processing JSON data.
     */
    public String createToken(Authentication authentication) throws JsonProcessingException {
        OurUserDetails ourUserDetails = (OurUserDetails) authentication.getPrincipal();
        Claims claims = Jwts.claims().setSubject(ourUserDetails.getUsername());

        claims.put("userId", String.valueOf(ourUserDetails.getId()));
        claims.put("username", ourUserDetails.getUsername());
        ObjectMapper objectMapper = new ObjectMapper();
        claims.put("roles", objectMapper.writeValueAsString(ourUserDetails.getUserRoles()));

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + (jwtExpiry * 1000)))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    /**
     * Retrieves a JWT token from the request's HTTP header.
     *
     * @param req The HTTP request.
     * @return The JWT token as a string or null if not found.
     */
    public String getTokenFromRequestHeader(HttpServletRequest req) {
        String bearerToken = req.getHeader(HEADER_AUTHORIZATION);
        if (bearerToken != null && bearerToken.startsWith(BEARER_TOKEN_START)) {
            return bearerToken.split(" ")[1];
        }
        return null;
    }

    /**
     * Validates a JWT token.
     *
     * @param token              The JWT token to be validated.
     * @param httpServletRequest The HTTP servlet request.
     * @return `true` if the token is valid, `false` otherwise.
     */
    public boolean validateToken(String token, HttpServletRequest httpServletRequest) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }
            return true;
        } catch (ExpiredJwtException ex) {
            log.error("Expired: JWT token");
            httpServletRequest.setAttribute("expired", "Expired: JWT token");
        } catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
            log.error("Invalid: JWT token");
            ex.printStackTrace();
            httpServletRequest.setAttribute("invalid", "Invalid: JWT token");
        }
        return false;
    }

    /**
     * Retrieves user authentication details from a JWT token.
     *
     * @param token   The JWT token as a string.
     * @param request The HTTP servlet request.
     * @return An Authentication object representing the user's authentication details.
     * @throws JsonProcessingException If there's an issue processing JSON data.
     */
    public Authentication getAuthenticationFromTokenString(String token, HttpServletRequest request) throws JsonProcessingException {
        Claims body = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        Long userId = Long.valueOf(String.valueOf(body.get("userId")));
        String username = ((String) body.get("username"));
        ObjectMapper objectMapper = new ObjectMapper();
        Set<String> stringRoles = objectMapper.readValue(body.get("roles").toString(), Set.class);
        Set<UserRole> userRolesSet = CommonSecurityUtils.stringSetToUserRoleSet(stringRoles);
        OurUserDetails customUserDetails = new OurUserDetails(userId, username, null, userRolesSet);

        Authentication authentication = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        return authentication;
    }
}
