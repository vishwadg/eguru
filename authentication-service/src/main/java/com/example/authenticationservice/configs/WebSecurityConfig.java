package com.example.authenticationservice.configs;


import com.example.authenticationservice.services.OurUserDetailsService;
import com.example.commonmodule.security.JwtTokenFilter;
import com.example.commonmodule.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    @Value("${app.security.jwt.secret}")
    private String secret;
    @Value("${app.security.jwt.expiry}")
    private Long jwtExpiry;

    // Configure NoOpPasswordEncoder, which is not secure and should be replaced with a secure password encoder
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    // Inject OurUserDetailsService for user authentication
    private final OurUserDetailsService ourUserDetailsService;

    // Define an AuthenticationManager bean for handling authentication
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(ourUserDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    // Configure security filters and rules
    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
//                .csrf().disable().httpBasic()
//                .and()
//                .authorizeRequests(req -> req
//                        .antMatchers("/users/**").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .exceptionHandling(e -> e
//                        .authenticationEntryPoint(((request, response, authException) ->
//                                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage())))
//                )
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
                .addFilterBefore(new JwtTokenFilter(jwtTokenProvider()), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    // Create a JwtTokenProvider bean for handling JWT (JSON Web Token) authentication
    @Bean
    public JwtTokenProvider jwtTokenProvider() {
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider(secret, jwtExpiry);
        return jwtTokenProvider;
    }

    // Define a ModelMapper bean for mapping objects
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
