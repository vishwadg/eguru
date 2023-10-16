package com.example.tutorrequirementsearchservice.configs;

//import statements
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

//import statements
import java.util.Arrays;

//@Configuration annotation
@Configuration
//@EnableWebSecurity annotation
@EnableWebSecurity
//@EnableGlobalMethodSecurity annotation
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

//@Value annotation
    @Value("${app.jwt.token.secret-key}")
    private String secretKey;
    @Value("${app.jwt.token.expiry}")
    private Long expiry;

//@Bean annotation
    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.cors(Customizer.withDefaults())
//                .csrf().disable().httpBasic().and()
//                .authorizeRequests(r ->
//                        r.antMatchers("/", "/actuators/**").permitAll()
//                                .anyRequest().authenticated()
//                )
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

//jwtTokenFilter method
    public JwtTokenFilter jwtTokenFilter() {
        JwtTokenFilter jwtTokenFilter = new JwtTokenFilter(new JwtTokenProvider(secretKey,expiry));
        return jwtTokenFilter;
    }

//@Bean annotation
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setExposedHeaders(Arrays.asList("*"));
        configuration.setMaxAge(3600L);
        configuration.setAllowCredentials(false);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

//@Bean annotation
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}