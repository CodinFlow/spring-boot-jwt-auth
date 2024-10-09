package com.tericcabrel.authapi.configs;

import com.tericcabrel.authapi.cookie.CustomCsrfFilter;
import com.tericcabrel.authapi.cookie.CustomCsrfTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    private final CustomCsrfFilter customCsrfFilter;

    public SecurityConfiguration(
            JwtAuthenticationFilter jwtAuthenticationFilter,
            AuthenticationProvider authenticationProvider, CustomCsrfFilter customCsrfFilter
    ) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.customCsrfFilter = customCsrfFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
               /* .cors(withDefaults())
                .csrf(csrf -> csrf
                        .csrfTokenRepository(customCsrfTokenRepository()))*/

                // Disable CSRF for Swagger access (optional, only if necessary)
                .cors(withDefaults())
                .csrf(csrf -> csrf
                        .disable())

                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(
                                "/auth/**",
                                "/auth/signup",
                                "/user/add-profile",
                                "/user/image/{uuid}",
                                "/user/image/profileId",
                                "/user/image/upload",
                                "/invoices/**",
                                "/api/v1/clients/**",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html",
                                "/api-docs",
                                "swagger-ui/index.html"
                        ).permitAll()



                        .anyRequest().authenticated())

                // Add JWT authentication filter before UsernamePasswordAuthenticationFilter
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CsrfTokenRepository customCsrfTokenRepository() {
        return new CustomCsrfTokenRepository(); // Use the custom repository
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(
                "http://localhost:5173",
                "http://localhost:8080",
                "https://web-application-development7405251-317d6980115c9bfc5d610a5bb25d.gitlab.io/"
        ));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
