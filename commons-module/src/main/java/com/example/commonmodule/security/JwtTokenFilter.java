package com.example.commonmodule.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * The type Jwt token filter.
 */
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtTokenProvider.getTokenFromRequestHeader(request);
        if(StringUtils.hasText(token) && jwtTokenProvider.validateToken(token, request)){
            Authentication authentication = jwtTokenProvider.getAuthenticationFromTokenString(token, request);
            if(authentication != null){
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
