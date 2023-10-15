package com.example.tutorrequirementservice.configs;

import com.example.commonmodule.security.JwtTokenFilter;
import com.example.commonmodule.security.JwtTokenProvider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    // Get the secret key and expiry time from the environment variables
    @Value("${app.jwt.token.secret-key}")
    private String secretKey;
    @Value("${app.jwt.token.expiry}")
    private Long expiry;

    // Create a bean for the SecurityFilterChain
    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {

        // Disable CSRF and enable HTTPBasic authentication
        return http.cors(Customizer.withDefaults())
                .csrf().disable().httpBasic().and()
                // Allow requests to the root, actuator and any other requests to be unauthenticated
                .authorizeRequests(ar -> ar
                        .antMatchers("/", "/actuator/**").permitAll()
                        .anyRequest().authenticated()
                )
                // Disable sessions
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // Add the JWT token filter before the username password authentication filter
                .addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    // Create a bean for the JWT token filter
    public JwtTokenFilter jwtTokenFilter() {
        JwtTokenFilter jwtTokenFilter = new JwtTokenFilter(new JwtTokenProvider(secretKey, expiry));
        return jwtTokenFilter;
    }

    // Create a bean for the CORS configuration source
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Allow requests from any origin
        configuration.setAllowedOrigins(Arrays.asList("*"));
        // Allow requests with any method
        configuration.setAllowedMethods(Arrays.asList("*"));
        // Allow requests with any header
        configuration.setAllowedHeaders(Arrays.asList("*"));
        // Expose any header
        configuration.setExposedHeaders(Arrays.asList("*"));
        // Set the max age of the CORS request
        configuration.setMaxAge(3600L);
        // Disable credentials
        configuration.setAllowCredentials(false);
        // Create a URL based CORS configuration source
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // Register the CORS configuration
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    // Create a bean for the ModelMapper
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}