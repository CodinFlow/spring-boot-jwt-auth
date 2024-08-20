package com.tericcabrel.authapi.cookie;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class CustomFilterConfig {

    @Bean
    public CustomCsrfFilter customCsrfFilter(CsrfTokenRepository csrfTokenRepository) {
        return new CustomCsrfFilter(csrfTokenRepository);
    }

    @Bean
    public CsrfTokenRepository csrfTokenRepository() {
        return new CookieCsrfTokenRepository(); // Use default repository
    }
}