package com.example.commonmodule.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * The JwtTokenFilter class is responsible for filtering and processing JWT tokens in incoming requests.
 * It validates and extracts tokens from request headers and sets the authentication context if the token is valid.
 */
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * Performs the JWT token validation and sets the authentication context.
     *
     * @param request     The HTTP request to be filtered.
     * @param response    The HTTP response.
     * @param filterChain The filter chain for processing the request.
     * @throws ServletException If there's an issue during filtering.
     * @throws IOException      If there's an I/O issue.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtTokenProvider.getTokenFromRequestHeader(request);
        if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token, request)) {
            Authentication authentication = jwtTokenProvider.getAuthenticationFromTokenString(token, request);
            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        try {
            filterChain.doFilter(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }
}

