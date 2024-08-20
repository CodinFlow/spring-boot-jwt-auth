package com.tericcabrel.authapi.cookie;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class CustomCsrfFilter extends GenericFilterBean {

    private final CsrfTokenRepository csrfTokenRepository;

    public CustomCsrfFilter(CsrfTokenRepository csrfTokenRepository) {
        this.csrfTokenRepository = csrfTokenRepository;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Handle the CSRF token
        CsrfToken csrfToken = csrfTokenRepository.loadToken(httpRequest);
        if (csrfToken != null) {
            String cookieName = csrfToken.getParameterName();
            String cookieValue = csrfToken.getToken();
            String cookiePath = "/";

            // Manually add the Set-Cookie header with SameSite attribute
            httpResponse.addHeader("Set-Cookie", String.format(
                    "%s=%s; Path=%s; HttpOnly=false; Secure=true; SameSite=None",
                    cookieName, cookieValue, cookiePath
            ));
        }

        chain.doFilter(request, response);
    }

    public CsrfTokenRepository getCsrfTokenRepository() {
        return csrfTokenRepository;
    }
}


