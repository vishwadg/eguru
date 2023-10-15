package com.example.tutorservice.configs;

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

/**
 * The WebSecurityConfig class configures web security for the application.
 * It enables global method security, sets JWT token properties, and configures CORS (Cross-Origin Resource Sharing).
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
    @Value("${app.jwt.token.secret-key}")
    private String secret;
    @Value("${app.jwt.token.expiry}")
    private Long expiry;


    /**
     * Configure security filters and settings for HTTP requests.
     *
     * @param httpSecurity The HttpSecurity object to configure.
     * @return A SecurityFilterChain.
     * @throws Exception If an exception occurs during configuration.
     */
    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.cors(Customizer.withDefaults())
                .csrf().disable().httpBasic().and()
//                .authorizeRequests(r ->
//                        r.antMatchers("/", "/actuators/**").permitAll()
//                                .anyRequest().authenticated()
//
//                )
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /**
     * Create a JWT token filter for handling JWT authentication.
     *
     * @return The configured JWT token filter.
     */
    public JwtTokenFilter jwtTokenFilter(){
        JwtTokenFilter jwtTokenFilter = new JwtTokenFilter(new JwtTokenProvider(secret, expiry));
        return jwtTokenFilter;
    }

    /**
     * Configure CORS (Cross-Origin Resource Sharing) settings.
     *
     * @return A CorsConfigurationSource.
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Arrays.asList("*"));
        corsConfig.setAllowedHeaders(Arrays.asList("*"));
        corsConfig.setAllowedMethods(Arrays.asList("*"));
        corsConfig.setExposedHeaders(Arrays.asList("*"));
        corsConfig.setMaxAge(3600L);
        corsConfig.setAllowCredentials(false);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);
        return source;
    }

    /**
     * Create a ModelMapper bean for object mapping.
     *
     * @return The configured ModelMapper.
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
